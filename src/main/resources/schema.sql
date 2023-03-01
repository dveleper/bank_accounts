CREATE DATABASE  IF NOT EXISTS bank_accounts;
USE bank_accounts;

DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS persona;
--
-- Table structure for table persona
--

CREATE TABLE persona (
  identificacion varchar(20) NOT NULL,
  nombre varchar(45) NOT NULL,
  genero varchar(10) NOT NULL,
  edad int NOT NULL,
  direccion varchar(60) NOT NULL,
  telefono varchar(20) NOT NULL,
  PRIMARY KEY (identificacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table cliente
--

CREATE TABLE cliente (
  contrasena varchar(10) NOT NULL,
  estado varchar(10) NOT NULL,
  identificacion varchar(20) NOT NULL,
  KEY identificacion_fk_idx (identificacion),
  CONSTRAINT identificacion_fk FOREIGN KEY (identificacion) REFERENCES persona (identificacion) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table cuenta
--

CREATE TABLE cuenta (
  numero varchar(20) NOT NULL,
  tipo_cuenta varchar(10) NOT NULL,
  saldo bigint NOT NULL DEFAULT '0',
  estado varchar(5) NOT NULL DEFAULT 'true',
  cliente_id varchar(20) DEFAULT NULL,
  PRIMARY KEY (numero),
  KEY cliiente_id_fk_idx (cliente_id),
  CONSTRAINT cliiente_id_fk FOREIGN KEY (cliente_id) REFERENCES cliente (identificacion) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table movimiento
--

CREATE TABLE movimiento (
  movimiento_id int NOT NULL AUTO_INCREMENT,
  fecha date NOT NULL,
  tipo_movimiento varchar(15) NOT NULL,
  valor bigint NOT NULL,
  saldo bigint NOT NULL,
  cuenta_id varchar(20) NOT NULL,
  PRIMARY KEY (movimiento_id),
  KEY cuenta_fk_idx (cuenta_id),
  CONSTRAINT cuenta_fk FOREIGN KEY (cuenta_id) REFERENCES cuenta (numero)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
