-- Insertar datos en la tabla categoria
INSERT INTO categoria (nombre, descripcion, fecha_creacion, fecha_actualizacion) VALUES
('Lácteos', 'Productos derivados de la leche', '2023-01-01', NULL),
('Carnes', 'Productos cárnicos', '2023-01-01', NULL),
('Bebidas', 'Bebidas alcohólicas y no alcohólicas', '2023-01-01', NULL),
('Panadería', 'Productos de panadería y pastelería', '2023-01-01', NULL),
('Frutas y Verduras', 'Frutas y verduras frescas', '2023-01-01', NULL),
('Congelados', 'Productos congelados', '2023-01-01', NULL),
('Aperitivos', 'Snacks y aperitivos', '2023-01-01', NULL),
('Cereales', 'Cereales y granos', '2023-01-01', NULL),
('Especias y Condimentos', 'Especias, hierbas y condimentos', '2023-01-01', NULL),
('Productos en Conserva', 'Productos enlatados y conservas', '2023-01-01', NULL);

-- Insertar datos en la tabla producto
INSERT INTO producto (nombre, descripcion, precio, imagen, marca, origen_producto, id_categoria) VALUES
-- Lácteos
('Leche Entera', 'Leche entera de vaca', 1.50, 'leche.jpg', 'Marca A', 'Local', 1),
('Queso Cheddar', 'Queso cheddar madurado', 3.75, 'queso_cheddar.jpg', 'Marca B', 'Importado', 1),
('Yogur Natural', 'Yogur natural sin azúcar', 2.00, 'yogur_natural.jpg', 'Marca C', 'Local', 1),
('Mantequilla', 'Mantequilla de vaca', 2.50, 'mantequilla.jpg', 'Marca D', 'Importado', 1),

-- Carnes
('Carne de Res', 'Carne de res fresca', 10.00, 'carne_res.jpg', 'Marca E', 'Local', 2),
('Pollo Entero', 'Pollo entero fresco', 7.50, 'pollo_entero.jpg', 'Marca F', 'Local', 2),
('Chuletas de Cerdo', 'Chuletas de cerdo frescas', 8.00, 'chuletas_cerdo.jpg', 'Marca G', 'Importado', 2),
('Salchichas', 'Salchichas de cerdo', 5.00, 'salchichas.jpg', 'Marca H', 'Local', 2),

-- Bebidas
('Coca Cola', 'Bebida gaseosa', 1.00, 'coca_cola.jpg', 'Coca Cola', 'Importado', 3),
('Agua Mineral', 'Agua mineral sin gas', 0.75, 'agua_mineral.jpg', 'Marca I', 'Local', 3),
('Jugo de Naranja', 'Jugo natural de naranja', 2.25, 'jugo_naranja.jpg', 'Marca J', 'Local', 3),
('Cerveza Lager', 'Cerveza lager fría', 2.00, 'cerveza_lager.jpg', 'Marca K', 'Importado', 3),

-- Panadería
('Pan Integral', 'Pan integral fresco', 1.20, 'pan_integral.jpg', 'Marca L', 'Local', 4),
('Croissants', 'Croissants de mantequilla', 3.00, 'croissants.jpg', 'Marca M', 'Importado', 4),
('Muffins de Arándano', 'Muffins con arándano', 2.50, 'muffins_arandano.jpg', 'Marca N', 'Local', 4),

-- Frutas y Verduras
('Manzanas', 'Manzanas frescas', 1.80, 'manzanas.jpg', 'Marca O', 'Local', 5),
('Zanahorias', 'Zanahorias frescas', 1.00, 'zanahorias.jpg', 'Marca P', 'Local', 5),
('Lechuga', 'Lechuga fresca', 1.50, 'lechuga.jpg', 'Marca Q', 'Local', 5),
('Plátanos', 'Plátanos maduros', 2.00, 'platanos.jpg', 'Marca R', 'Local', 5),

-- Congelados
('Helado de Vainilla', 'Helado de vainilla cremoso', 3.50, 'helado_vainilla.jpg', 'Marca S', 'Importado', 6),
('Pizza Congelada', 'Pizza congelada de pepperoni', 5.00, 'pizza_congelada.jpg', 'Marca T', 'Local', 6),

-- Aperitivos
('Papitas', 'Papitas fritas de sal', 1.50, 'papitas.jpg', 'Marca U', 'Local', 7),
('Pretzels', 'Pretzels salados', 2.00, 'pretzels.jpg', 'Marca V', 'Importado', 7),

-- Cereales
('Cornflakes', 'Cereales de maíz', 2.75, 'cornflakes.jpg', 'Marca W', 'Importado', 8),
('Avena', 'Avena integral para desayuno', 1.80, 'avena.jpg', 'Marca X', 'Local', 8),

-- Especias y Condimentos
('Sal', 'Sal de mesa', 0.50, 'sal.jpg', 'Marca Y', 'Local', 9),
('Pimienta Negra', 'Pimienta negra molida', 1.00, 'pimienta_negra.jpg', 'Marca Z', 'Importado', 9),

-- Productos en Conserva
('Sopa en Conserva', 'Sopa de pollo en conserva', 2.25, 'sopa_conserva.jpg', 'Marca AA', 'Importado', 10),
('Frijoles Negros', 'Frijoles negros enlatados', 1.75, 'frijoles_negros.jpg', 'Marca AB', 'Local', 10);

