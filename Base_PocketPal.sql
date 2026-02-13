-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: finanzaspocketpal
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria_egresos`
--

DROP TABLE IF EXISTS `categoria_egresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_egresos` (
  `ID_Categoria` int NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_egresos`
--

LOCK TABLES `categoria_egresos` WRITE;
/*!40000 ALTER TABLE `categoria_egresos` DISABLE KEYS */;
INSERT INTO `categoria_egresos` VALUES (1,'Supermercado'),(2,'Restaurantes y Bares'),(3,'Delivery / Pedidos'),(4,'Alquiler / Hipoteca'),(5,'Servicios Básicos (Luz, Agua, Gas)'),(6,'Impuestos y Tasas'),(7,'Expensas'),(8,'Internet'),(9,'Telefonía Móvil / Celular'),(10,'Suscripciones Digitales (Netflix, Spotify)'),(11,'Transporte Público'),(12,'Combustible'),(13,'Taxis y Apps de Viaje'),(14,'Mantenimiento Vehículo'),(15,'Salud y Farmacia'),(16,'Cuidado Personal'),(17,'Ropa y Calzado'),(18,'Ocio y Entretenimiento'),(19,'Gimnasio / Deporte'),(20,'Hogar y Decoración'),(21,'Tecnología'),(22,'Educación'),(23,'Regalos'),(24,'Mascotas'),(25,'Ahorro e Inversión'),(26,'Vacaciones'),(27,'Pagos de Deudas'),(28,'Otros / Varios');
/*!40000 ALTER TABLE `categoria_egresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_ingresos`
--

DROP TABLE IF EXISTS `categoria_ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_ingresos` (
  `ID_Categoria` int NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_ingresos`
--

LOCK TABLES `categoria_ingresos` WRITE;
/*!40000 ALTER TABLE `categoria_ingresos` DISABLE KEYS */;
INSERT INTO `categoria_ingresos` VALUES (1,'Sueldo'),(2,'Honorarios'),(3,'Comisiones'),(4,'Bonificaciones'),(5,'Alquileres'),(6,'Intereses'),(7,'Dividendos'),(8,'Ventas'),(9,'Rentas'),(10,'Jubilación / Pensión'),(11,'Subsidios'),(12,'Regalos'),(13,'Herencias'),(14,'Premios o sorteos'),(15,'Reembolsos'),(16,'Indemnizaciones'),(17,'Regalías'),(18,'Aportes de socios'),(19,'Inversiones'),(20,'Otros ingresos');
/*!40000 ALTER TABLE `categoria_ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egresos`
--

DROP TABLE IF EXISTS `egresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `egresos` (
  `ID_Egresos` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `ID_Categoria` int NOT NULL,
  `ID_Medio` int NOT NULL,
  `Monto` float NOT NULL,
  `ID_Usuario` int DEFAULT NULL,
  PRIMARY KEY (`ID_Egresos`),
  KEY `egresos_ibfk_3_idx` (`ID_Usuario`),
  KEY `egresos_ibfk_1_idx` (`ID_Categoria`),
  KEY `egresos_ibfk_2_idx` (`ID_Medio`),
  CONSTRAINT `egresos_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categoria_egresos` (`ID_Categoria`),
  CONSTRAINT `egresos_ibfk_2` FOREIGN KEY (`ID_Medio`) REFERENCES `medio_egresos` (`ID_Medio`),
  CONSTRAINT `egresos_ibfk_3` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuario` (`ID_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egresos`
--

LOCK TABLES `egresos` WRITE;
/*!40000 ALTER TABLE `egresos` DISABLE KEYS */;
INSERT INTO `egresos` VALUES (11,'2025-11-06 00:00:00.000000','Compra Libros',22,1,2500,2),(14,'2025-10-10 10:00:00.000000','Pago factura de luz',2,1,8500,1),(15,'2025-10-18 22:00:00.000000','Cena con amigos',3,4,12000,1),(17,'2025-11-05 11:00:00.000000','Suscripción Netflix',4,4,4500,1),(35,'2025-10-20 00:00:00.000000','Compra coto',1,2,2500,1),(36,'2025-10-20 00:00:00.000000','Comrpa supermercado',1,1,2500,1);
/*!40000 ALTER TABLE `egresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresos`
--

DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresos` (
  `ID_Ingresos` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `ID_Categoria` int NOT NULL,
  `ID_Medio` int NOT NULL,
  `Monto` float NOT NULL,
  `ID_Usuario` int DEFAULT NULL,
  PRIMARY KEY (`ID_Ingresos`),
  KEY `ID_Categoria` (`ID_Categoria`),
  KEY `ID_Medio` (`ID_Medio`),
  KEY `ingresos_ibfk_3_idx` (`ID_Usuario`),
  CONSTRAINT `ingresos_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categoria_ingresos` (`ID_Categoria`),
  CONSTRAINT `ingresos_ibfk_2` FOREIGN KEY (`ID_Medio`) REFERENCES `medio_ingresos` (`ID_Medio`),
  CONSTRAINT `ingresos_ibfk_3` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuario` (`ID_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresos`
--

LOCK TABLES `ingresos` WRITE;
/*!40000 ALTER TABLE `ingresos` DISABLE KEYS */;
INSERT INTO `ingresos` VALUES (23,'2025-11-06 00:00:00.000000','Sueldo Noviembre',1,2,1500000,2),(28,'2025-10-20 18:00:00.000000','Regalo cumpleaños',3,2,10000,1),(32,'2025-10-20 00:00:00.000000','Comida',8,1,5000,1),(33,'2025-10-20 00:00:00.000000','Honorarios Soporte',2,2,4500,1);
/*!40000 ALTER TABLE `ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medio_egresos`
--

DROP TABLE IF EXISTS `medio_egresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medio_egresos` (
  `ID_Medio` int NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Medio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medio_egresos`
--

LOCK TABLES `medio_egresos` WRITE;
/*!40000 ALTER TABLE `medio_egresos` DISABLE KEYS */;
INSERT INTO `medio_egresos` VALUES (1,'Efectivo'),(2,'Tarjeta de Débito'),(3,'Tarjeta de Crédito'),(4,'Billetera Digital'),(5,'Transferencia Bancaria'),(6,'Otro');
/*!40000 ALTER TABLE `medio_egresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medio_ingresos`
--

DROP TABLE IF EXISTS `medio_ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medio_ingresos` (
  `ID_Medio` int NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Medio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medio_ingresos`
--

LOCK TABLES `medio_ingresos` WRITE;
/*!40000 ALTER TABLE `medio_ingresos` DISABLE KEYS */;
INSERT INTO `medio_ingresos` VALUES (1,'Efectivo'),(2,'Transferencia bancaria'),(3,'Depósito bancario'),(4,'Tarjeta de crédito'),(5,'Tarjeta de débito'),(6,'Cheque'),(7,'Mercado Pago'),(8,'Billetera virtual'),(9,'Criptomonedas'),(10,'Retiro de inversión'),(11,'Pago por terceros'),(12,'Otros medios');
/*!40000 ALTER TABLE `medio_ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID_Usuario` int NOT NULL AUTO_INCREMENT,
  `mail` varchar(255) DEFAULT NULL,
  `contraseña` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `moneda` varchar(255) DEFAULT NULL,
  `fecha_registro` datetime(6) NOT NULL,
  PRIMARY KEY (`ID_Usuario`),
  UNIQUE KEY `Mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'matiasroldan@ejemplo.com','Matu123','Matuelo','Roldan','USD','2025-10-24 00:00:00.000000'),(2,'facundo@ejemplo.com','Facu123','Facundo','Caucota','ARS','2025-11-06 12:01:50.000000'),(3,'aguirremorena@ejemplol.com','More2003.','Morena','Aguirre','USD','2025-11-10 11:01:08.494000'),(4,'solemartinez@ejemplo.com','Sole123','Soledad','Martinez','ARS','2025-11-10 11:06:56.327000'),(5,'nicocaucota@ejemplo.com','Nico123','Nicolas ','Caucota','EUR','2025-11-10 11:08:13.510000'),(9,'matias2@ejemplo.com','Mati123','Matias2','Roldan2','USD','2025-11-18 10:35:48.917000'),(10,'matiasrodlan695@ejemplo.com','123456','Matias2','Roldan2','USD','2025-11-18 10:36:09.735000');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-13 16:17:48
