-- Insertar más datos en la tabla categoria
INSERT INTO categoria (nombre, descripcion, fecha_creacion, fecha_actualizacion) VALUES
('Lácteos', 'Productos derivados de la leche', '2023-01-01', '2023-01-01'),
('Carnes', 'Productos cárnicos', '2023-01-01', '2023-01-01'),
('Bebidas', 'Bebidas alcohólicas y no alcohólicas', '2023-01-01', '2023-10-12'),
('Panadería', 'Productos de panadería y pastelería', '2023-01-01', '2023-01-01'),
('Frutas y Verduras', 'Frutas y verduras frescas', '2023-01-01', '2024-07-07'),
('Congelados', 'Productos congelados', '2023-01-01', '2023-01-01'),
('Aperitivos', 'Snacks y aperitivos', '2023-01-01', '2023-01-01'),
('Cereales', 'Cereales y granos', '2023-01-01', '2023-01-01'),
('Especias y Condimentos', 'Especias, hierbas y condimentos', '2023-01-01', NULL),
('Productos en Conserva', 'Productos enlatados y conservas', '2023-01-01', '2024-01-02'),
('Limpieza', 'Productos de limpieza', '2023-01-01', NULL),
('Higiene Personal', 'Productos de higiene personal', '2023-01-01', '2023-01-01'),
('Mascotas', 'Productos para mascotas', '2023-01-01', '2023-01-01'),
('Electrónica', 'Productos electrónicos', '2023-01-01', '2023-01-01'),
('Ropa', 'Ropa y accesorios', '2023-01-01', NULL),
('Bebés', 'Manta', '2024-01-01', NULL);

-- Insertar más datos en la tabla producto con descripciones en español
INSERT INTO producto (nombre, descripcion, precio, imagen, marca, origen_producto, id_categoria) VALUES
-- Limpieza
('Detergente Líquido', 'Fórmula eficaz para eliminar manchas difíciles. Capacidad: 1.5L. Apto para todo tipo de ropa, con tecnología que protege los colores y las fibras.', 4.50, 'detergente_liquido.jpg', 'Bosque Verde', 'Local', 11),
('Limpiador Multiusos', 'Fragancia fresca, ideal para superficies como pisos, encimeras y baños. Botella de 750ml con fórmula antibacteriana.', 3.00, 'limpiador_multiusos.jpg', 'Carrefour', 'Local', 11),

-- Higiene Personal
('Champú', 'Hidratante para todo tipo de cabello. Enriquecido con vitaminas para fortalecer el cabello, evitando la caída y dándole un brillo saludable. Botella de 500ml.', 2.75, 'champu.jpg', 'Dia', 'Local', 12),
('Jabón Líquido', 'Suave para manos, enriquecido con aloe vera. Ideal para el uso diario y adecuado para pieles sensibles. Presentación de 300ml.', 1.50, 'jabon_liquido.jpg', 'Hacendado', 'Local', 12),

-- Mascotas
('Comida para Perro', 'Alimento balanceado para perros adultos. Rico en proteínas y vitaminas esenciales para la salud de las mascotas. Presentación en sacos de 10kg.', 20.00, 'comida_perro.jpg', 'Lidl', 'Local', 13),
('Arena para Gato', 'Absorbente, con control de olores. Paquete de 5kg, fácil de limpiar y de larga duración.', 10.00, 'arena_gato.jpg', 'Nestle', 'Local', 13),

-- Electrónica
('Auriculares', 'Bluetooth 5.0 con cancelación de ruido. Batería de larga duración, hasta 12 horas de reproducción continua. Incluye estuche de carga.', 25.00, 'auriculares.jpg', 'Bosque Verde', 'Importado', 14),
('Cargador USB', 'Cargador rápido de 20W, compatible con smartphones y tablets. Incluye cable USB tipo C.', 15.00, 'cargador_usb.jpg', 'Carrefour', 'Importado', 14),

-- Ropa
('Camiseta', 'Algodón 100%, cómoda y transpirable. Ideal para uso diario. Disponible en talla M y varios colores.', 10.00, 'camiseta.jpg', 'Dia', 'Local', 15),
('Pantalones', 'Casuales de mezclilla, material resistente con diseño moderno. Disponible en talla 32. Apto para cualquier ocasión.', 20.00, 'pantalones.jpg', 'Hacendado', 'Local', 15),

-- Lácteos
('Leche Entera', 'Rica en calcio y proteínas, ideal para desayunos y recetas. Presentación de 1L.', 1.50, 'leche.jpg', 'Puleva', 'Local', 1),
('Queso Cheddar', 'Madurado, con sabor fuerte y textura suave. Paquete de 200g, ideal para sándwiches y ensaladas.', 3.75, 'queso_cheddar.jpg', 'Nestle', 'Importado', 1),

-- Carnes
('Carne de Res', 'Fresca, apta para asados y guisos. Paquete de 1kg, proveniente de origen local con altos estándares de calidad.', 10.00, 'carne_res.jpg', 'Carrefour', 'Local', 2),
('Pollo Entero', 'Listo para asar o cocinar al horno. Rico en proteínas y bajo en grasas. Peso aproximado de 1.5kg.', 7.50, 'pollo_entero.jpg', 'Dia', 'Local', 2),

-- Bebidas
('Coca Cola', 'Refresco con gas, ideal para acompañar comidas y reuniones. Botella de 2L.', 1.00, 'coca_cola.jpg', 'Coca Cola', 'Importado', 3),
('Agua Mineral', 'Agua pura de manantial, sin gas. Botella de 1.5L, perfecta para mantenerse hidratado a lo largo del día.', 0.75, 'agua_mineral.jpg', 'Lidl', 'Local', 3),

-- Panadería
('Pan Integral', 'Elaborado con harina de trigo 100% integral. Rico en fibra y nutrientes esenciales. Paquete de 500g.', 1.20, 'pan_integral.jpg', 'Hacendado', 'Local', 4),
('Croissants', 'Suaves y esponjosos. Paquete de 6 unidades, perfecto para desayunos o meriendas. Elaborados con mantequilla.', 3.00, 'croissants.jpg', 'Carrefour', 'Importado', 4),

-- Frutas y Verduras
('Manzanas', 'Frescas de cultivo local. Ricas en vitaminas y fibra, perfectas para consumir crudas o en ensaladas. Paquete de 1kg.', 1.80, 'manzanas.jpg', 'Dia', 'Local', 5),
('Zanahorias', 'Ideales para guisos, ensaladas y como snack saludable. Presentación de 1kg.', 1.00, 'zanahorias.jpg', 'Lidl', 'Local', 5),

-- Congelados
('Helado de Vainilla', 'Cremoso, ideal para postres o como complemento de frutas y tartas. Tarrina de 500ml.', 3.50, 'helado_vainilla.jpg', 'Nestle', 'Importado', 6),
('Pizza Congelada', 'Masa fina y crujiente, cubierta con salsa de tomate, queso y pepperoni. Peso de 400g.', 5.00, 'pizza_congelada.jpg', 'Pescanova', 'Local', 6),

-- Aperitivos
('Papitas', 'Crujientes con sal, perfectas para picoteos y reuniones. Bolsa de 150g.', 1.50, 'papitas.jpg', 'Calvo', 'Local', 7),
('Pretzels', 'Salados, ideales para snacks y aperitivos. Paquete de 200g.', 2.00, 'pretzels.jpg', 'Carrefour', 'Importado', 7),

-- Cereales
('Cornflakes', 'Cereal de maíz crujiente, perfecto para desayunos con leche o yogur. Caja de 500g.', 2.75, 'cornflakes.jpg', 'Nestle', 'Importado', 8),
('Avena', 'Rica en fibra, ideal para preparar desayunos saludables o batidos. Paquete de 1kg.', 1.80, 'avena.jpg', 'Dia', 'Local', 8),

-- Especias y Condimentos
('Sal', 'Perfecta para sazonar todo tipo de comidas y conservar alimentos. Paquete de 1kg.', 0.50, 'sal.jpg', 'Lidl', 'Local', 9),
('Pimienta Negra', 'Molida, ideal para dar sabor y un toque picante a los platos. Bote de 50g.', 1.00, 'pimienta_negra.jpg', 'Carrefour', 'Importado', 9),

-- Productos en Conserva
('Sopa en Conserva', 'Lista para consumir, ideal para comidas rápidas y nutritivas. Lata de 400g.', 2.25, 'sopa_conserva.jpg', 'Calvo', 'Importado', 10),
('Frijoles Negros', 'Listos para consumir o agregar a guisos y ensaladas. Lata de 500g.', 1.75, 'frijoles_negros.jpg', 'Pescanova', 'Local', 10);
