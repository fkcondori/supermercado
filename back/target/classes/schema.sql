CREATE TABLE categoria (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    fecha_creacion DATE NOT NULL,
    fecha_actualizacion DATE
);

CREATE TABLE producto (
    id_producto BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255) NULL,
    marca VARCHAR(30) NOT NULL,
    origen_producto VARCHAR(30),
    id_categoria BIGINT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);