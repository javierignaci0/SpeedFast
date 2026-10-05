create DATABASE speedfast_db;

CREATE TABLE repartidores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(100) NOT NULL,
    tipo ENUM('COMIDA','ENCOMIENDA','EXPRESS'),
    estado ENUM('PENDIENTE','EN_REPARTO','ENTREGADO')
);

CREATE TABLE entregas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_repartidor INT,
    fecha DATE,
    hora TIME,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id),
    FOREIGN KEY (id_repartidor) REFERENCES repartidores(id)
);

DELETE FROM entregas where id=1;
INSERT into entregas VALUES  (4, 2, 2001-12-14);

pedidosINSERT INTO pedidos VALUES (3,'5 de abril 2332','COMIDA', 'PENDIENTE');
select * from pedidos;

ALTER TABLE pedidos MODIFY tipo VARCHAR(30);