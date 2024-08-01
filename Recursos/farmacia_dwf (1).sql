-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 01, 2024 at 05:25 AM
-- Server version: 8.0.30
-- PHP Version: 8.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `farmacia_dwf`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

CREATE TABLE `categorias` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`, `created_at`, `updated_at`) VALUES
(1, 'Antibióticos', 'Medicamentos utilizados para tratar infecciones bacterianas.', '2024-07-29 03:06:38', '2024-07-29 03:06:38'),
(2, 'Analgésicos', 'Medicamentos utilizados para aliviar el dolor.', '2024-07-29 03:06:38', '2024-07-29 03:06:38'),
(3, 'Antisépticos', 'Sustancias que previenen la infección.', '2024-07-29 03:06:38', '2024-07-29 03:06:38'),
(4, 'Vacunas', 'Preparados biológicos que proporcionan inmunidad contra enfermedades.', '2024-07-29 03:06:38', '2024-07-29 03:06:38'),
(5, 'Vitaminas', 'Suplementos que proporcionan nutrientes esenciales.', '2024-07-29 03:06:38', '2024-07-29 03:06:38');

-- --------------------------------------------------------

--
-- Table structure for table `insumos`
--

CREATE TABLE `insumos` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `cantidad` int NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fecha_de_expiracion` date DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `insumos`
--

INSERT INTO `insumos` (`id`, `nombre`, `descripcion`, `cantidad`, `precio`, `fecha_de_expiracion`, `categoria_id`, `created_at`, `updated_at`) VALUES
(1, 'Paracetamol', 'Analgésico y antipirético AGOTADO.', 100, '0.50', '2025-12-31', 1, '2024-07-29 05:24:51', '2024-07-31 18:09:39'),
(2, 'Estetoscopio', 'Instrumento médico para auscultación AGOTADO.', 20, '15.00', NULL, 1, '2024-07-29 05:24:51', '2024-07-31 17:45:10'),
(3, 'Guantes', 'Guantes desechables de látex AGOTADO.', 500, '0.10', '2023-12-31', 3, '2024-07-29 05:24:51', '2024-07-31 17:44:53'),
(4, 'Ibuprofeno', 'Medicamento para el dolor y la inflamación.', 200, '1.00', '2024-06-30', 2, '2024-07-31 05:10:52', '2024-07-31 06:05:15'),
(5, 'Sutura quirúrgica', 'Hilos utilizados para cerrar heridas.', 150, '2.50', NULL, 5, '2024-07-31 06:10:04', '2024-07-31 06:10:27'),
(6, 'Jeringas 10ml', 'Jeringas de plástico estériles para inyecciones.', 1000, '0.20', '2025-01-01', 3, '2024-07-31 06:13:11', '2024-07-31 06:13:39'),
(7, 'Mascarilla quirúrgica', 'Mascarillas desechables para uso médico.', 2000, '0.05', '2024-12-31', 4, '2024-07-31 06:14:49', '2024-07-31 06:14:49'),
(11, 'Test', 'Example', 5, '6.70', NULL, 3, '2024-07-31 19:49:34', '2024-07-31 19:49:34');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `insumos`
--
ALTER TABLE `insumos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `insumos`
--
ALTER TABLE `insumos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `insumos`
--
ALTER TABLE `insumos`
  ADD CONSTRAINT `insumos_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
