CREATE DATABASE  IF NOT EXISTS `justgetit` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `justgetit`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: justgetit
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bestelbonnen`
--

DROP TABLE IF EXISTS `bestelbonnen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bestelbonnen` (
  `bestelbonId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `datum` datetime NOT NULL,
  `levertermijn` int(10) unsigned NOT NULL,
  `klantId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`bestelbonId`),
  UNIQUE KEY `bestelbonId_UNIQUE` (`bestelbonId`),
  KEY `fk_BestelBon_Klant1_idx` (`klantId`),
  CONSTRAINT `fk_BestelBon_Klant1` FOREIGN KEY (`klantId`) REFERENCES `klanten` (`klantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestelbonnen`
--

LOCK TABLES `bestelbonnen` WRITE;
/*!40000 ALTER TABLE `bestelbonnen` DISABLE KEYS */;
/*!40000 ALTER TABLE `bestelbonnen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bestelregels`
--

DROP TABLE IF EXISTS `bestelregels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bestelregels` (
  `bestelbonId` bigint(20) unsigned NOT NULL,
  `productId` bigint(20) unsigned NOT NULL,
  `prijs` decimal(10,0) unsigned NOT NULL,
  `aantal` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`bestelbonId`,`productId`),
  KEY `fk_BestelRegels_BestelBon1_idx` (`bestelbonId`),
  KEY `fk_BestelRegels_Producten1` (`productId`),
  CONSTRAINT `fk_BestelRegels_BestelBon1` FOREIGN KEY (`bestelbonId`) REFERENCES `bestelbonnen` (`bestelbonId`),
  CONSTRAINT `fk_BestelRegels_Producten1` FOREIGN KEY (`productId`) REFERENCES `producten` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestelregels`
--

LOCK TABLES `bestelregels` WRITE;
/*!40000 ALTER TABLE `bestelregels` DISABLE KEYS */;
/*!40000 ALTER TABLE `bestelregels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorieen`
--

DROP TABLE IF EXISTS `categorieen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorieen` (
  `categorieId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`categorieId`),
  UNIQUE KEY `categorieId_UNIQUE` (`categorieId`),
  UNIQUE KEY `naam_UNIQUE` (`naam`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorieen`
--

LOCK TABLES `categorieen` WRITE;
/*!40000 ALTER TABLE `categorieen` DISABLE KEYS */;
INSERT INTO `categorieen` VALUES (2,'Boeken'),(3,'Elektronica'),(4,'Fashion'),(1,'Films'),(5,'Voeding');
/*!40000 ALTER TABLE `categorieen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gebruikersrollen`
--

DROP TABLE IF EXISTS `gebruikersrollen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gebruikersrollen` (
  `klantId` bigint(20) unsigned NOT NULL,
  `rolId` bigint(20) unsigned NOT NULL,
  KEY `fk_gebruikersrollen_rollen_idx` (`rolId`),
  KEY `fk_gebruikersrollen_klantenaccounts_idx` (`klantId`),
  CONSTRAINT `fk_gebruikersrollen_klantenaccounts` FOREIGN KEY (`klantId`) REFERENCES `klanten` (`klantId`),
  CONSTRAINT `fk_gebruikersrollen_rollen` FOREIGN KEY (`rolId`) REFERENCES `rollen` (`rolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gebruikersrollen`
--

LOCK TABLES `gebruikersrollen` WRITE;
/*!40000 ALTER TABLE `gebruikersrollen` DISABLE KEYS */;
INSERT INTO `gebruikersrollen` VALUES (1,1);
/*!40000 ALTER TABLE `gebruikersrollen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gemeenten`
--

DROP TABLE IF EXISTS `gemeenten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gemeenten` (
  `gemeenteId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `postcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `provincieId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`gemeenteId`),
  UNIQUE KEY `plaatsId_UNIQUE` (`gemeenteId`),
  KEY `fk_Gemeenten_Provincies1_idx` (`provincieId`),
  CONSTRAINT `fk_Gemeenten_Provincies1` FOREIGN KEY (`provincieId`) REFERENCES `provincies` (`provincieId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gemeenten`
--

LOCK TABLES `gemeenten` WRITE;
/*!40000 ALTER TABLE `gemeenten` DISABLE KEYS */;
INSERT INTO `gemeenten` VALUES (1,'Rotselaar','3110',1),(2,'Brugge','8000',2),(3,'Heverlee','3001',1),(4,'Schoten','2900',3),(5,'Landgraaf','6372 VG',5);
/*!40000 ALTER TABLE `gemeenten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klanten`
--

DROP TABLE IF EXISTS `klanten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `klanten` (
  `klantId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `achternaam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `adres` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telefoonnummer` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `gemeenteId` bigint(20) unsigned NOT NULL,
  `accountnaam` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `wachtwoord` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `actief` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`klantId`),
  UNIQUE KEY `klantId_UNIQUE` (`klantId`),
  UNIQUE KEY `accountnaam_UNIQUE` (`accountnaam`),
  KEY `fk_Klant_Gemeenten1_idx` (`gemeenteId`),
  CONSTRAINT `fk_Klant_Gemeenten1` FOREIGN KEY (`gemeenteId`) REFERENCES `gemeenten` (`gemeenteId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klanten`
--

LOCK TABLES `klanten` WRITE;
/*!40000 ALTER TABLE `klanten` DISABLE KEYS */;
INSERT INTO `klanten` VALUES (1,'Hilde','Pollet','Groenstraat 33','016447001','hildepollet@gmail.com',1,'hildepollet','hildepollet',1),(2,'Gervijn','Pollet','Koningin Astridlaan 158','','gervijnpollet@gmail.com',2,'gervpollet','gervpollet',1),(3,'Jan','Nannas','Broekstraat 43','0478456283','janvddr@gmail.com',3,'janvddr','janvddr',1),(4,'Joske','Vermeulen','Tramazantenlaan 143','078659325','joskedebospoeper@geenechtadres.be',4,'joskepoeper','joskepoeper',1),(5,'Kers','T. Man','Hofstraat 1','+3169696969','hey@ho.ho',5,'kerstman','kerstman',1);
/*!40000 ALTER TABLE `klanten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klantenkaarten`
--

DROP TABLE IF EXISTS `klantenkaarten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `klantenkaarten` (
  `klantId` bigint(20) unsigned NOT NULL,
  `punten` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`klantId`),
  CONSTRAINT `fk_KlantenKaart_Klant1` FOREIGN KEY (`klantId`) REFERENCES `klanten` (`klantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klantenkaarten`
--

LOCK TABLES `klantenkaarten` WRITE;
/*!40000 ALTER TABLE `klantenkaarten` DISABLE KEYS */;
INSERT INTO `klantenkaarten` VALUES (1,0),(2,0),(3,0),(4,0),(5,0);
/*!40000 ALTER TABLE `klantenkaarten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `landen`
--

DROP TABLE IF EXISTS `landen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `landen` (
  `landId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`landId`),
  UNIQUE KEY `landId_UNIQUE` (`landId`),
  UNIQUE KEY `naam_UNIQUE` (`naam`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `landen`
--

LOCK TABLES `landen` WRITE;
/*!40000 ALTER TABLE `landen` DISABLE KEYS */;
INSERT INTO `landen` VALUES (1,'België'),(2,'Nederland');
/*!40000 ALTER TABLE `landen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lijsten`
--

DROP TABLE IF EXISTS `lijsten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lijsten` (
  `lijstId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `klantId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`lijstId`),
  UNIQUE KEY `lijstId_UNIQUE` (`lijstId`),
  KEY `fk_Lijsten_Klant1_idx` (`klantId`),
  CONSTRAINT `fk_Lijsten_Klant1` FOREIGN KEY (`klantId`) REFERENCES `klanten` (`klantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lijsten`
--

LOCK TABLES `lijsten` WRITE;
/*!40000 ALTER TABLE `lijsten` DISABLE KEYS */;
/*!40000 ALTER TABLE `lijsten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lijstregels`
--

DROP TABLE IF EXISTS `lijstregels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lijstregels` (
  `lijstId` int(10) unsigned NOT NULL,
  `productId` bigint(20) unsigned NOT NULL,
  `aantal` bigint(20) unsigned NOT NULL,
  `datumToegevoegd` date NOT NULL,
  PRIMARY KEY (`lijstId`,`productId`),
  KEY `fk_LijstRegels_Lijsten1_idx` (`lijstId`),
  KEY `fk_LijstRegels_Producten1_idx` (`productId`),
  CONSTRAINT `fk_LijstRegels_Lijsten1` FOREIGN KEY (`lijstId`) REFERENCES `lijsten` (`lijstId`),
  CONSTRAINT `fk_LijstRegels_Producten1` FOREIGN KEY (`productId`) REFERENCES `producten` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lijstregels`
--

LOCK TABLES `lijstregels` WRITE;
/*!40000 ALTER TABLE `lijstregels` DISABLE KEYS */;
/*!40000 ALTER TABLE `lijstregels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merken`
--

DROP TABLE IF EXISTS `merken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `merken` (
  `merkId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `minimumMargePercent` decimal(10,0) unsigned DEFAULT '0',
  `minimumMargeBedrag` decimal(10,0) unsigned DEFAULT '0',
  `versie` bigint(20) unsigned DEFAULT '0',
  PRIMARY KEY (`merkId`),
  UNIQUE KEY `merkId_UNIQUE` (`merkId`),
  UNIQUE KEY `naam_UNIQUE` (`naam`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merken`
--

LOCK TABLES `merken` WRITE;
/*!40000 ALTER TABLE `merken` DISABLE KEYS */;
INSERT INTO `merken` VALUES (1,'A Film Benelux MSD B.V.',0,0,NULL),(2,'Samsung',0,0,NULL),(3,'HarperCollins Publishers',0,0,NULL),(4,'Hollister Co.',0,0,NULL),(5,'Vaporesso',0,0,NULL);
/*!40000 ALTER TABLE `merken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producteigenschappen`
--

DROP TABLE IF EXISTS `producteigenschappen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `producteigenschappen` (
  `subcategorieEigenschapId` bigint(20) unsigned NOT NULL,
  `productId` bigint(20) unsigned NOT NULL,
  `waarde` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`subcategorieEigenschapId`,`productId`),
  KEY `fk_ProductEigenschappen_Producten1_idx` (`productId`),
  CONSTRAINT `fk_ProductEigenschappen_Producten1` FOREIGN KEY (`productId`) REFERENCES `producten` (`productId`),
  CONSTRAINT `fk_ProductEigenschappen_SubcategorieEigenschappen1` FOREIGN KEY (`subcategorieEigenschapId`) REFERENCES `subcategorieeigenschappen` (`subcategorieEigenschapId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producteigenschappen`
--

LOCK TABLES `producteigenschappen` WRITE;
/*!40000 ALTER TABLE `producteigenschappen` DISABLE KEYS */;
INSERT INTO `producteigenschappen` VALUES (1,2,'Paperback'),(3,4,'M'),(5,1,'2100 minuten'),(7,3,'140 cm');
/*!40000 ALTER TABLE `producteigenschappen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producten.html`
--

DROP TABLE IF EXISTS `producten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `producten` (
  `productId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `inkoopprijs` decimal(10,0) unsigned NOT NULL,
  `verkoopprijs` decimal(10,0) unsigned NOT NULL,
  `minimumprijs` decimal(10,0) unsigned NOT NULL,
  `omschrijving` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `voorraad` bigint(20) unsigned NOT NULL DEFAULT '0',
  `besteld` bigint(20) unsigned NOT NULL DEFAULT '0',
  `merkId` bigint(20) unsigned NOT NULL,
  `subcategorieId` bigint(20) unsigned NOT NULL,
  `versie` bigint(20) unsigned DEFAULT '0',
  PRIMARY KEY (`productId`),
  UNIQUE KEY `productId_UNIQUE` (`productId`),
  KEY `fk_Producten_Merken1_idx` (`merkId`),
  KEY `fk_Producten_Subcategorieen1_idx` (`subcategorieId`),
  CONSTRAINT `fk_Producten_Merken1` FOREIGN KEY (`merkId`) REFERENCES `merken` (`merkId`),
  CONSTRAINT `fk_Producten_Subcategorieen1` FOREIGN KEY (`subcategorieId`) REFERENCES `subcategorieen` (`subcategorieId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producten.html`
--

LOCK TABLES `producten` WRITE;
/*!40000 ALTER TABLE `producten` DISABLE KEYS */;
INSERT INTO `producten` VALUES (1,'Lord of the Rings Trilogy: Extended Edition',23,100,50,'Meer dan 12u kijkplezier!',0,0,1,5,NULL),(2,'Lord of the Rings Boxset',5,18,10,'Meer dan 32u leesplezier!',0,0,3,1,NULL),(3,'QE55Q6F(2018) - QLED',200,799,399,'Meer kijkplezier!',0,0,2,7,NULL),(4,'Overhemd',5,39,15,'Meer dan zeer comfortabel draagplezier!',0,0,4,3,NULL);
/*!40000 ALTER TABLE `producten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincies`
--

DROP TABLE IF EXISTS `provincies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `provincies` (
  `provincieId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `landId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`provincieId`),
  UNIQUE KEY `provincieId_UNIQUE` (`provincieId`),
  KEY `fk_Provincies_Landen_idx` (`landId`),
  CONSTRAINT `fk_Provincies_Landen` FOREIGN KEY (`landId`) REFERENCES `landen` (`landId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincies`
--

LOCK TABLES `provincies` WRITE;
/*!40000 ALTER TABLE `provincies` DISABLE KEYS */;
INSERT INTO `provincies` VALUES (1,'Vlaams-Brabant',1),(2,'West-Vlaanderen',1),(3,'Antwerpen',1),(4,'Limburg',1),(5,'Limburg',2);
/*!40000 ALTER TABLE `provincies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rollen`
--

DROP TABLE IF EXISTS `rollen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rollen` (
  `rolId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`rolId`),
  UNIQUE KEY `rolId_UNIQUE` (`rolId`),
  UNIQUE KEY `naam_UNIQUE` (`naam`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rollen`
--

LOCK TABLES `rollen` WRITE;
/*!40000 ALTER TABLE `rollen` DISABLE KEYS */;
INSERT INTO `rollen` VALUES (2,'bediende'),(3,'klant'),(1,'manager');
/*!40000 ALTER TABLE `rollen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategorieeigenschappen`
--

DROP TABLE IF EXISTS `subcategorieeigenschappen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subcategorieeigenschappen` (
  `subcategorieEigenschapId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `subcategorieId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`subcategorieEigenschapId`),
  UNIQUE KEY `subcategorieEigenschapId_UNIQUE` (`subcategorieEigenschapId`),
  KEY `fk_SubcategorieEigenschappen_Subcategorieen1_idx` (`subcategorieId`),
  CONSTRAINT `fk_SubcategorieEigenschappen_Subcategorieen1` FOREIGN KEY (`subcategorieId`) REFERENCES `subcategorieen` (`subcategorieId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategorieeigenschappen`
--

LOCK TABLES `subcategorieeigenschappen` WRITE;
/*!40000 ALTER TABLE `subcategorieeigenschappen` DISABLE KEYS */;
INSERT INTO `subcategorieeigenschappen` VALUES (1,'Uitvoering',1),(2,'Druknummer',2),(3,'Maat',3),(4,'Maat',4),(5,'Duur',5),(6,'Duur',6),(7,'Diameter',7),(8,'Verbruik',8),(9,'Houdbaarheidsdatum',9),(10,'Volume',10);
/*!40000 ALTER TABLE `subcategorieeigenschappen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategorieen`
--

DROP TABLE IF EXISTS `subcategorieen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subcategorieen` (
  `subcategorieId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `minimumMargePercent` decimal(10,0) unsigned DEFAULT '0',
  `minimumMargeBedrag` decimal(10,0) unsigned DEFAULT '0',
  `categorieId` bigint(20) unsigned NOT NULL,
  `versie` bigint(20) unsigned DEFAULT '0',
  PRIMARY KEY (`subcategorieId`),
  UNIQUE KEY `subcategorieId_UNIQUE` (`subcategorieId`),
  UNIQUE KEY `naamInCategorie_UNIQUE` (`naam`,`subcategorieId`),
  KEY `fk_Subcategorieen_Categorieen1_idx` (`categorieId`),
  CONSTRAINT `fk_Subcategorieen_Categorieen1` FOREIGN KEY (`categorieId`) REFERENCES `categorieen` (`categorieId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategorieen`
--

LOCK TABLES `subcategorieen` WRITE;
/*!40000 ALTER TABLE `subcategorieen` DISABLE KEYS */;
INSERT INTO `subcategorieen` VALUES (1,'Fictie',0,0,2,NULL),(2,'Non-Fictie',0,0,2,NULL),(3,'Mannen',0,0,4,NULL),(4,'Vrouwen',0,0,4,NULL),(5,'DVD',0,0,1,NULL),(6,'Blu-Ray',0,0,1,NULL),(7,'Televisies',0,0,3,NULL),(8,'Huishoudapparatuur',0,0,3,NULL),(9,'Fruit',0,0,5,NULL),(10,'Conserven',0,0,5,NULL);
/*!40000 ALTER TABLE `subcategorieen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'justgetit'
--

--
-- Dumping routines for database 'justgetit'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06  9:51:55
