package com.bideafactory.coding_test.model.service.impl;

import com.bideafactory.coding_test.model.service.IDiscountService;
import com.bideafactory.coding_test.payload.discount.DiscountRequest;
import com.bideafactory.coding_test.payload.discount.DiscountResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements IDiscountService {

    private static final Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

    private final WebClient.Builder webClient;

    @Value("${discount.service.url}")
    private String url;

    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Mono<DiscountResponse> validateDiscount(DiscountRequest discountRequest) {
        logger.info("Iniciando validación de descuento para el código: {}", discountRequest.getDiscountCode());

        return webClient.build().post()
                .uri(url)
                .bodyValue(discountRequest)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> {
                            logger.warn("Error de cliente al validar descuento. Status: {}", response.statusCode());
                            return Mono.error(new BadRequestException("Bad Request"));
                        })
                .bodyToMono(DiscountResponse.class)
                .doOnSuccess(response -> {
                    if (response.isStatus()) {
                        logger.info("Descuento validado con éxito. Código: {}", discountRequest.getDiscountCode());
                    } else {
                        logger.warn("Descuento no válido. Código: {}", discountRequest.getDiscountCode());
                    }
                }).onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        logger.warn("Solicitud incorrecta al validar descuento. Código: {}", discountRequest.getDiscountCode());
                        return Mono.error(new BadRequestException("Bad Request"));
                    }
                    logger.error("Error inesperado al llamar al servicio externo de descuentos", ex);
                    return Mono.error(new RuntimeException("Error calling external discount service", ex));
                });
    }
}
