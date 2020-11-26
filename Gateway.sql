CREATE DATABASE  IF NOT EXISTS `gateway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gateway`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gateway
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `gateways`
--

DROP TABLE IF EXISTS `gateways`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gateways` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `SERIAL_NUMBER` varchar(45) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `IPV4_ADDRESS` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `serial_no_UNIQUE` (`SERIAL_NUMBER`)
) ENGINE=InnoDB AUTO_INCREMENT=17056 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gateways`
--

LOCK TABLES `gateways` WRITE;
/*!40000 ALTER TABLE `gateways` DISABLE KEYS */;
INSERT INTO `gateways` VALUES (17050,'11400530425','iot','172.16.254.1'),(17051,'11400678234','router','172.16.254.2'),(17052,'MV1921VS004Q','ARRIS NVG448','176.8.9.28'),(17055,'MV1678VS004Q','Pace528','176.8.9.28');
/*!40000 ALTER TABLE `gateways` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peripheral_devices`
--

DROP TABLE IF EXISTS `peripheral_devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peripheral_devices` (
  `UID` bigint NOT NULL AUTO_INCREMENT,
  `VENDOR` varchar(45) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL,
  `STATUS` varchar(45) NOT NULL,
  `GATEWAY_SERIAL_NUMBER` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`),
  KEY `FK_GATEWAY_SERIAL_NUMBER_idx` (`GATEWAY_SERIAL_NUMBER`),
  CONSTRAINT `FK_GATEWAY_SERIAL_NUMBER` FOREIGN KEY (`GATEWAY_SERIAL_NUMBER`) REFERENCES `gateways` (`SERIAL_NUMBER`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peripheral_devices`
--

LOCK TABLES `peripheral_devices` WRITE;
/*!40000 ALTER TABLE `peripheral_devices` DISABLE KEYS */;
INSERT INTO `peripheral_devices` VALUES (11100,'Heyjin','2020-11-24 19:10:28','offline','11400678234'),(11101,'Kim','2020-11-24 19:10:40','online','11400678234'),(11102,'Trump','2020-11-24 20:07:28','online','MV1921VS004Q'),(11103,'John','2020-11-24 20:22:39','online','11400678234'),(11104,'Harper','2020-11-24 20:23:18','online','11400678234'),(11105,'Mason','2020-11-24 20:23:28','online','11400678234');
/*!40000 ALTER TABLE `peripheral_devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gateway'
--

--
-- Dumping routines for database 'gateway'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-26  8:14:15
