CREATE USER "Bidea_ADM" WITH
  LOGIN
  SUPERUSER
  CREATEDB
  CREATEROLE
  INHERIT
  REPLICATION
  CONNECTION LIMIT -1
  PASSWORD 'Bidea_ADM753$#_%';


CREATE DATABASE "Bidea"
  WITH OWNER = "Bidea_ADM"
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;
