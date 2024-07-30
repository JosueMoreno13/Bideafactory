package com.bideafactory.coding_test.model.repository;

import com.bideafactory.coding_test.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, String> {
}
