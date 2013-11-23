CREATE DATABASE  IF NOT EXISTS `hibnatedb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hibnatedb`;
-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: hibnatedb
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `PendingQueue`
--

DROP TABLE IF EXISTS `PendingQueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PendingQueue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationDate` date DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Queue_id` int(11) NOT NULL,
  `Account_id` int(11) NOT NULL,
  `GarantedQueue` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Queue_id`,`Account_id`),
  KEY `fk_PendingQueue_Queue1_idx` (`Queue_id`),
  KEY `fk_PendingQueue_Account1_idx` (`Account_id`),
  KEY `fk_PendingQueue_Queue2_idx` (`GarantedQueue`),
  CONSTRAINT `fk_PendingQueue_Account1` FOREIGN KEY (`Account_id`) REFERENCES `Account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_PendingQueue_Queue1` FOREIGN KEY (`Queue_id`) REFERENCES `Queue` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_PendingQueue_Queue2` FOREIGN KEY (`GarantedQueue`) REFERENCES `Queue` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PendingQueue`
--

LOCK TABLES `PendingQueue` WRITE;
/*!40000 ALTER TABLE `PendingQueue` DISABLE KEYS */;
INSERT INTO `PendingQueue` VALUES (1,NULL,'PENDING',2,2,65),(2,NULL,'PENDING',2,2,66),(3,NULL,'PENDING',65,2,167),(4,NULL,'PENDING',65,2,228),(5,NULL,'PENDING',66,2,269),(6,NULL,'PENDING',269,2,350);
/*!40000 ALTER TABLE `PendingQueue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-23 21:17:28
