-- Eliminar la base de datos si ya existe
DROP DATABASE IF EXISTS pry_final_lp2;

-- Crear la base de datos
CREATE DATABASE pry_final_lp2;

-- Seleccionar la base de datos
USE pry_final_lp2;

-- Tabla proveedor
CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL,
    contacto_proveedor VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo VARCHAR(100) NOT NULL
);

-- Tabla tipo_producto
CREATE TABLE tipo_producto (
    id_tipo_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(50) NOT NULL
);

-- Tabla producto
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL,
    stock_maximo INT NOT NULL,
    stock_actual INT NOT NULL,
    id_proveedor INT NOT NULL,
    estado INT NOT NULL, -- 0=Inactivo, 1=Activo, 2=Desconocido
    id_tipo_producto INT NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto)
);

-- Tabla inventario
CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    fecha_ingreso DATE NOT NULL,
    id_producto INT NOT NULL,
    cantidad_ingresada INT NOT NULL,
    precio_costo DECIMAL(10,2) NOT NULL,
    id_tipo_producto INT NOT NULL,
    id_proveedor INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

-- Tabla Tipos de Usuarios
CREATE TABLE tb_tipos_usuarios(
    idtipousua INT NOT NULL PRIMARY KEY,
    rol VARCHAR(30)
);

-- Tabla Usuarios
CREATE TABLE tb_usuarios(
    id_usua INT AUTO_INCREMENT PRIMARY KEY,
    nom_usua VARCHAR(50) NOT NULL,
    ape_usua VARCHAR(50) NOT NULL,
    user_usua VARCHAR(45) NOT NULL UNIQUE,
    pswd_usua VARCHAR(100) NOT NULL,
    fnac_usua DATE,
    idtipousua INT NOT NULL DEFAULT 2 CHECK (idtipousua IN (1,2,3,4,5,6)),
    est_usua BIT NOT NULL DEFAULT 1 CHECK (est_usua IN (0,1)),
    FOREIGN KEY (idtipousua) REFERENCES tb_tipos_usuarios(idtipousua)
);

-- Tabla Cabecera de Boleta
CREATE TABLE tb_cab_boleta (
    num_bol INT AUTO_INCREMENT PRIMARY KEY,
    fch_bol DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usua INT NOT NULL,
    FOREIGN KEY (id_usua) REFERENCES tb_usuarios(id_usua)
);

-- Tabla Detalle de Boleta
CREATE TABLE tb_det_boleta (
    num_bol INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    preciovta DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (num_bol, id_producto),
    FOREIGN KEY (num_bol) REFERENCES tb_cab_boleta(num_bol),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- Inserción de datos de ejemplo

-- Proveedores
INSERT INTO proveedor (nombre_empresa, contacto_proveedor, telefono, correo) VALUES
('Distribuidora Central', 'Juan Pérez', '987654321', 'contacto@dcentral.com'),
('Alimentos del Norte', 'Ana Torres', '912345678', 'ventas@anorte.com'),
('Bebidas del Sur', 'Carlos Gómez', '923456781', 'info@bsur.com'),
('Snacks Express', 'Lucía Ruiz', '934567812', 'lucia@snacksexpress.com'),
('Supermercados Unidos', 'Mario Lazo', '945678123', 'proveedor@su.com');

-- Agregando View Header Boleta
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `v_header_boleta` AS
    SELECT 
        `cab`.`num_bol` AS `num_bol`,
        CONCAT('B001 - ',
                CONVERT( LPAD(`cab`.`num_bol`, 8, '0') USING UTF8MB4)) AS `numBolText`,
        CONCAT(`usu`.`nom_usua`, ' ', `usu`.`ape_usua`) AS `nombreCompletoUsuario`,
        DATE_FORMAT(`cab`.`fch_bol`, '%d/%m/%Y %h:%i:%s %p') AS `fechaText`
    FROM
        (`tb_cab_boleta` `cab`
        JOIN `tb_usuarios` `usu` ON ((`cab`.`id_usua` = `usu`.`id_usua`)));

-- Agregando View Detalle Boleta
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `v_detail_boleta` AS
    SELECT 
        `det`.`num_bol` AS `num_bol`,
        `det`.`id_producto` AS `id_producto`,
        `pro`.`nombre_producto` AS `nombre_producto`,
        `det`.`cantidad` AS `cantidad`,
        `det`.`preciovta` AS `preciovta`,
        (`det`.`cantidad` * `det`.`preciovta`) AS `subtotal`
    FROM
        (`tb_det_boleta` `det`
        JOIN `producto` `pro` ON ((`det`.`id_producto` = `pro`.`id_producto`)));

-- Tipos de Producto
INSERT INTO tipo_producto (nombre_tipo) VALUES
('Bebidas'),
('Snacks'),
('Lácteos'),
('Limpieza'),
('Panadería');

-- Productos (estado: 0=Inactivo, 1=Activo, 2=Desconocido)
INSERT INTO producto (nombre_producto, stock_maximo, stock_actual, id_proveedor, estado, id_tipo_producto) VALUES
('Coca Cola 500ml', 100, 30, 1, 1, 1),
('Papas Lays 45g', 70, 10, 2, 1, 2),
('Leche Gloria 1L', 80, 25, 3, 1, 3),
('Jabón Líquido 1L', 80, 20, 4, 1, 4),
('Pan de Molde', 120, 60, 5, 1, 5),
('Fanta 350ml', 90, 20, 1, 1, 1),
('Doritos 50g', 60, 8, 2, 1, 2),
('Yogurt Batido 1L', 90, 20, 3, 1, 3),
('Cloro 1L', 100, 50, 4, 1, 4),
('Croissant', 95, 15, 5, 1, 5);

-- Inventario (no se ingresa id_inventario, es AUTO_INCREMENT)
INSERT INTO inventario (fecha_ingreso, id_producto, cantidad_ingresada, precio_costo, id_tipo_producto, id_proveedor) VALUES
('2025-06-19', 1, 30, 0.45, 1, 1),
('2025-06-18', 2, 10, 0.30, 2, 2),
('2025-06-17', 3, 25, 0.60, 3, 3),
('2025-06-16', 4, 40, 1.20, 4, 4),
('2025-06-15', 5, 60, 0.80, 5, 5),
('2025-06-14', 6, 20, 0.40, 1, 1),
('2025-06-13', 7, 8, 0.35, 2, 2),
('2025-06-12', 8, 30, 0.90, 3, 3),
('2025-06-11', 9, 50, 1.00, 4, 4),
('2025-06-10', 10, 15, 1.50, 5, 5);

-- Tipo Usuario
INSERT INTO tb_tipos_usuarios (idtipousua, rol) VALUES
(1, 'administrador'),
(2, 'gerente'),
(3, 'supervisor'),
(4, 'soporte Técnico'),
(5, 'marketing'),
(6, 'cajero');

-- Usuario
INSERT INTO tb_usuarios (id_usua, nom_usua, ape_usua, user_usua, pswd_usua, fnac_usua, idtipousua, est_usua) VALUES
(null,'Diego', 'Castilla','diegocas', 'diego123', curdate(),1,default),
(null,'Renan', 'Osorio','renanoso', 'renan123', curdate(),3,0),
(null,'Diana', 'Chero','dianache', 'diana123', curdate(),2,default),
(null,'Yurico', 'España','yuricoes', 'yurico123', curdate(),5,0),
(null,'Daniel', 'Cochachi','danielco', 'daniel123', curdate(),6,default);

-- Boleta
-- Cabeceras de boleta
INSERT INTO tb_cab_boleta (num_bol, fch_bol, id_usua) VALUES
(1, '2025-06-30 09:15:00', 1), -- Diego
(2, '2025-07-01 10:45:00', 3), -- Diana
(3, '2025-07-02 11:30:00', 2), -- Renan
(4, '2025-07-03 13:00:00', 5), -- Daniel
(5, '2025-07-04 15:20:00', 4); -- Yurico

-- Detalles de boleta
INSERT INTO tb_det_boleta (num_bol, id_producto, cantidad, preciovta) VALUES
-- Boleta 1 (2 productos)
(1, 1, 2, 1.50),
(1, 2, 3, 1.00),

-- Boleta 2 (3 productos)
(2, 3, 1, 2.00),
(2, 4, 2, 3.50),
(2, 6, 1, 1.20),

-- Boleta 3 (5 productos)
(3, 1, 1, 1.50),
(3, 2, 2, 1.00),
(3, 5, 1, 0.90),
(3, 6, 2, 1.20),
(3, 7, 1, 1.00),

-- Boleta 4 (4 productos)
(4, 3, 2, 2.00),
(4, 4, 1, 3.50),
(4, 9, 1, 1.80),
(4, 10, 2, 2.20),

-- Boleta 5 (5 productos)
(5, 1, 1, 1.50),
(5, 3, 1, 2.00),
(5, 5, 2, 0.90),
(5, 7, 1, 1.00),
(5, 9, 2, 1.80);
