-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-10-2023 a las 15:15:09
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdgatos`
--
CREATE DATABASE IF NOT EXISTS `bdgatos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bdgatos`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `InsertarAdoptador`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarAdoptador` (IN `adop_dni` VARCHAR(8), IN `adop_nombre` VARCHAR(30), IN `adop_apellido` VARCHAR(30))  BEGIN
    DECLARE next_id INT;
    DECLARE new_id VARCHAR(6);
    
    -- Obtener el próximo ID
    SELECT MAX(CAST(SUBSTRING(adop_id, 2) AS SIGNED)) INTO next_id FROM adoptador;
    SET next_id = next_id + 1;
    SET new_id = CONCAT('A', LPAD(next_id, 5, '0'));
    
    -- Insertar el nuevo registro
    INSERT INTO adoptador (adop_id, adop_dni, adop_nombre, adop_apellido)
    VALUES (new_id, adop_dni, adop_nombre, adop_apellido);
END$$

DROP PROCEDURE IF EXISTS `InsertarGato`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarGato` (IN `gat_nom` VARCHAR(30), IN `raza_id` VARCHAR(4), IN `asp_id` CHAR(1))  BEGIN
    DECLARE next_id INT;
    DECLARE new_id VARCHAR(6);
    
    -- Obtener el próximo ID
    SELECT MAX(CAST(SUBSTRING(gat_id, 2) AS SIGNED)) INTO next_id FROM gato;
    SET next_id = next_id + 1;
    SET new_id = CONCAT('G', LPAD(next_id, 5, '0'));
    
    -- Insertar el nuevo registro
    INSERT INTO gato (gat_id, gat_nom, raza_id, asp_id)
    VALUES (new_id, gat_nom, raza_id, asp_id);
END$$

DROP PROCEDURE IF EXISTS `spContrato`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spContrato` (`ID` VARCHAR(6))  SELECT c.cont_id, A.adop_nombre,A.adop_apellido, L.local_nombre, L.local_direc, C.cont_fecha
FROM contrato C
INNER JOIN local L
ON C.local_id = L.local_id
INNER JOIN adoptador A
ON C.adop_id = A.adop_id
WHERE gat_id = ID$$

DROP PROCEDURE IF EXISTS `spGato`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spGato` ()  SELECT gat_id, gat_nom, R.raza , A.aspecto
FROM gato G INNER JOIN raza R
ON g.raza_id=r.raza_id 
INNER JOIN aspecto A
ON g.asp_id=a.asp_id
ORDER BY gat_id$$

DROP PROCEDURE IF EXISTS `spReg`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spReg` (`ID` VARCHAR(6))  SELECT reg_id, V.vac_nom,reg_fech 
FROM registro R INNER JOIN vacunas V 
ON R.vac_id=V.vac_id 
WHERE gat_id=ID$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adoptador`
--

DROP TABLE IF EXISTS `adoptador`;
CREATE TABLE `adoptador` (
  `adop_id` varchar(10) NOT NULL,
  `adop_dni` varchar(8) NOT NULL,
  `adop_nombre` varchar(30) NOT NULL,
  `adop_apellido` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `adoptador`
--

INSERT INTO `adoptador` (`adop_id`, `adop_dni`, `adop_nombre`, `adop_apellido`) VALUES('A00001', '73108884', 'Daniel Justo', 'Purizaca Sanchez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aspecto`
--

DROP TABLE IF EXISTS `aspecto`;
CREATE TABLE `aspecto` (
  `asp_id` char(1) NOT NULL,
  `aspecto` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `aspecto`
--

INSERT INTO `aspecto` (`asp_id`, `aspecto`) VALUES('G', 'Grande');
INSERT INTO `aspecto` (`asp_id`, `aspecto`) VALUES('P', 'Pequeño');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

DROP TABLE IF EXISTS `contrato`;
CREATE TABLE `contrato` (
  `cont_id` varchar(6) NOT NULL,
  `adop_id` varchar(10) NOT NULL,
  `gat_id` varchar(6) NOT NULL,
  `local_id` int(4) NOT NULL,
  `cont_fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `contrato`
--

INSERT INTO `contrato` (`cont_id`, `adop_id`, `gat_id`, `local_id`, `cont_fecha`) VALUES('C00001', 'A00001', 'G00001', 1, '2023-10-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gato`
--

DROP TABLE IF EXISTS `gato`;
CREATE TABLE `gato` (
  `gat_id` varchar(6) NOT NULL,
  `gat_nom` varchar(30) DEFAULT NULL,
  `raza_id` varchar(4) NOT NULL,
  `asp_id` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `gato`
--

INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00001', 'Micho', 'R001', 'G');
INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00002', 'Mimi', 'R008', 'P');
INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00003', 'Mina                          ', 'R002', 'P');
INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00004', 'Hurrem', 'R007', 'P');
INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00005', 'Ashok', 'R002', 'G');
INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00006', 'Anaisha', 'R003', 'G');
INSERT INTO `gato` (`gat_id`, `gat_nom`, `raza_id`, `asp_id`) VALUES('G00007', 'Moona', 'R002', 'G');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `local`
--

DROP TABLE IF EXISTS `local`;
CREATE TABLE `local` (
  `local_id` int(4) NOT NULL,
  `local_nombre` varchar(40) NOT NULL,
  `local_direc` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `local`
--

INSERT INTO `local` (`local_id`, `local_nombre`, `local_direc`) VALUES(1, 'AmiPet', 'Sta. Rosa 120, La Perla');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `raza`
--

DROP TABLE IF EXISTS `raza`;
CREATE TABLE `raza` (
  `raza_id` varchar(4) NOT NULL,
  `raza` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `raza`
--

INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R001', 'Siamés');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R002', 'Persa');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R003', 'Siberiano');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R004', 'Maine Coon');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R005', 'Bengalí');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R006', 'Ragdoll');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R007', 'Exótico');
INSERT INTO `raza` (`raza_id`, `raza`) VALUES('R008', 'Mestizo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

DROP TABLE IF EXISTS `registro`;
CREATE TABLE `registro` (
  `reg_id` varchar(6) NOT NULL,
  `vac_id` varchar(4) NOT NULL,
  `gat_id` varchar(6) NOT NULL,
  `reg_fech` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`reg_id`, `vac_id`, `gat_id`, `reg_fech`) VALUES('R00001', 'V001', 'G00001', '2023-10-01');
INSERT INTO `registro` (`reg_id`, `vac_id`, `gat_id`, `reg_fech`) VALUES('R00002', 'V002', 'G00001', '2023-09-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacunas`
--

DROP TABLE IF EXISTS `vacunas`;
CREATE TABLE `vacunas` (
  `vac_id` varchar(4) NOT NULL,
  `vac_nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vacunas`
--

INSERT INTO `vacunas` (`vac_id`, `vac_nom`) VALUES('V001', 'Antirábica');
INSERT INTO `vacunas` (`vac_id`, `vac_nom`) VALUES('V002', 'Triple Felina');
INSERT INTO `vacunas` (`vac_id`, `vac_nom`) VALUES('V003', 'Leucemia');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `adoptador`
--
ALTER TABLE `adoptador`
  ADD PRIMARY KEY (`adop_id`);

--
-- Indices de la tabla `aspecto`
--
ALTER TABLE `aspecto`
  ADD PRIMARY KEY (`asp_id`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`cont_id`),
  ADD KEY `FK_adop_id` (`adop_id`),
  ADD KEY `FK_gat_id` (`gat_id`),
  ADD KEY `FK_local_id` (`local_id`);

--
-- Indices de la tabla `gato`
--
ALTER TABLE `gato`
  ADD PRIMARY KEY (`gat_id`),
  ADD KEY `FK_raza_id` (`raza_id`),
  ADD KEY `FK_asp_id` (`asp_id`);

--
-- Indices de la tabla `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`local_id`) USING BTREE;

--
-- Indices de la tabla `raza`
--
ALTER TABLE `raza`
  ADD PRIMARY KEY (`raza_id`);

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`reg_id`),
  ADD KEY `FK_gat_id` (`gat_id`),
  ADD KEY `FK_vac_id` (`vac_id`);

--
-- Indices de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  ADD PRIMARY KEY (`vac_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `local`
--
ALTER TABLE `local`
  MODIFY `local_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `contrato_ibfk_1` FOREIGN KEY (`adop_id`) REFERENCES `adoptador` (`adop_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `contrato_ibfk_2` FOREIGN KEY (`gat_id`) REFERENCES `gato` (`gat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `contrato_ibfk_3` FOREIGN KEY (`local_id`) REFERENCES `local` (`local_id`);

--
-- Filtros para la tabla `gato`
--
ALTER TABLE `gato`
  ADD CONSTRAINT `gato_ibfk_1` FOREIGN KEY (`raza_id`) REFERENCES `raza` (`raza_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `gato_ibfk_2` FOREIGN KEY (`asp_id`) REFERENCES `aspecto` (`asp_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`gat_id`) REFERENCES `gato` (`gat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `registro_ibfk_2` FOREIGN KEY (`vac_id`) REFERENCES `vacunas` (`vac_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
