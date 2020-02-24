-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	5.6.45-log

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
-- Table structure for table `documentrequest`
--

DROP TABLE IF EXISTS `documentrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documentrequest` (
  `BrojZahteva` bigint(12) NOT NULL,
  `Status` varchar(45) DEFAULT NULL,
  `Ime` varchar(45) DEFAULT NULL,
  `Prezime` varchar(45) DEFAULT NULL,
  `ImeMajke` varchar(45) DEFAULT NULL,
  `PrezimeMajke` varchar(45) DEFAULT NULL,
  `ImeOca` varchar(45) DEFAULT NULL,
  `PrezimeOca` varchar(45) DEFAULT NULL,
  `Pol` varchar(45) DEFAULT NULL,
  `DatumRodjenja` varchar(40) DEFAULT NULL,
  `Nacionalnost` varchar(45) DEFAULT NULL,
  `Profesija` varchar(45) DEFAULT NULL,
  `BracnoStanje` varchar(45) DEFAULT NULL,
  `OpstinaPrebivalista` varchar(45) DEFAULT NULL,
  `UlicaPrebivalista` varchar(45) DEFAULT NULL,
  `BrojPrebivalista` varchar(10) DEFAULT NULL,
  `JMBG` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`BrojZahteva`),
  UNIQUE KEY `idDokument_UNIQUE` (`BrojZahteva`),
  UNIQUE KEY `JMBG_UNIQUE` (`JMBG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentrequest`
--

LOCK TABLES `documentrequest` WRITE;
/*!40000 ALTER TABLE `documentrequest` DISABLE KEYS */;
INSERT INTO `documentrequest` VALUES (174200000002,'Ceka na urucenje','Aleksa','Jankovic','Milena','Jankovic','Zvezdan','Jankovic','M','01.07.1998','srbin','student','neozenjen','Miljakovac','Bogdana Zerajica','3','0107998563256'),(174200000003,'Kreiran','Suzana','Markovic','Slobodanka','Vujovic','Radomir','Vujovic','Z','1970-11-17','srpkinja','profesor','udata','NBG','Milentija','37','1711970568965'),(174200000004,'Urucen','Djurica','Markovic','Radojka','Markovic','Milovan','Markovic','M','1968-05-05','srbin','profesor','ozenjen','NBG','Milentija','37','0505968919958'),(174200000005,'Kreiran','Novi','bhjb','bjhb','ojioj','mmokmo','bubu','M','2017-95-98','bajhbdjh','kbhjbjh','bjhb','bhjbj','vguv','bhbh','3012998919958'),(174200000009,'Kreiran','Andjela','Jankovic','Milena','Jankovic','Zvezdan','Jankovic','Z','1998-07-01','srpkinja','student','neudata','Miljakovac','Bogdana Zerajica','56','0107998536963'),(174200000010,'U produkciji','Andjela','Jankovic','fghjk','fghjk','vbnm,','ghjk','Z','1558-95-55','ndsjkan','nainun','ghjk','fghjkl','rtyuio','ghjk','0107998');
/*!40000 ALTER TABLE `documentrequest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-23  0:16:33
