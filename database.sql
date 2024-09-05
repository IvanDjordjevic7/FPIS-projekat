CREATE DATABASE  IF NOT EXISTS `fpis-art-expo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fpis-art-expo`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: fpis-art-expo
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `izlozba`
--

DROP TABLE IF EXISTS `izlozba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `izlozba` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  `vreme_otvaranja` time NOT NULL,
  `vreme_zatvaranja` time NOT NULL,
  `tip` varchar(255) NOT NULL,
  `manifestacija_id` int NOT NULL,
  `maks_broj_prisutnih` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `manifestacija_id` (`manifestacija_id`),
  CONSTRAINT `izlozba_ibfk_1` FOREIGN KEY (`manifestacija_id`) REFERENCES `manifestacija` (`id`),
  CONSTRAINT `izlozba_chk_1` CHECK ((`tip` in (_utf8mb4'Slikarstvo',_utf8mb4'Fotografija')))
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `izlozba`
--

LOCK TABLES `izlozba` WRITE;
/*!40000 ALTER TABLE `izlozba` DISABLE KEYS */;
INSERT INTO `izlozba` VALUES (1,'2024-05-15','10:00:00','18:00:00','Slikarstvo',1,300),(2,'2024-05-16','10:00:00','18:00:00','Fotografija',1,300),(3,'2024-06-10','11:00:00','19:00:00','Slikarstvo',2,250),(4,'2024-06-11','11:00:00','19:00:00','Fotografija',2,250),(5,'2024-09-05','10:00:00','18:00:00','Slikarstvo',3,400),(6,'2024-09-06','10:00:00','18:00:00','Fotografija',3,400),(7,'2024-07-20','09:30:00','17:30:00','Slikarstvo',4,350),(8,'2024-07-21','09:30:00','17:30:00','Fotografija',4,350),(9,'2024-08-15','10:00:00','18:00:00','Slikarstvo',5,220),(10,'2024-08-16','10:00:00','18:00:00','Fotografija',5,220),(11,'2024-07-05','11:00:00','19:00:00','Slikarstvo',6,300),(12,'2024-07-06','11:00:00','19:00:00','Fotografija',6,300),(13,'2024-10-15','10:00:00','18:00:00','Slikarstvo',7,250),(14,'2024-10-16','10:00:00','18:00:00','Fotografija',7,250),(15,'2024-09-20','11:00:00','19:00:00','Slikarstvo',8,400),(16,'2024-09-21','11:00:00','19:00:00','Fotografija',8,400),(17,'2024-08-05','09:30:00','17:30:00','Slikarstvo',9,350),(18,'2024-08-06','09:30:00','17:30:00','Fotografija',9,350),(19,'2024-05-01','10:00:00','18:00:00','Slikarstvo',10,220),(20,'2024-05-02','10:00:00','18:00:00','Fotografija',10,220),(21,'2024-09-25','10:00:00','18:00:00','Slikarstvo',11,300),(22,'2024-09-26','10:00:00','18:00:00','Fotografija',11,300),(23,'2024-06-01','11:00:00','19:00:00','Slikarstvo',12,250),(24,'2024-06-02','11:00:00','19:00:00','Fotografija',12,250),(25,'2024-07-15','10:00:00','18:00:00','Slikarstvo',13,400),(26,'2024-07-16','10:00:00','18:00:00','Fotografija',13,400),(27,'2024-05-20','09:30:00','17:30:00','Slikarstvo',14,350),(28,'2024-05-21','09:30:00','17:30:00','Fotografija',14,350),(29,'2024-10-05','10:00:00','18:00:00','Slikarstvo',15,220),(30,'2024-10-06','10:00:00','18:00:00','Fotografija',15,220),(31,'2024-08-25','11:00:00','19:00:00','Slikarstvo',16,300),(32,'2024-08-26','11:00:00','19:00:00','Fotografija',16,300),(33,'2024-07-25','10:00:00','18:00:00','Slikarstvo',17,250),(34,'2024-07-26','10:00:00','18:00:00','Fotografija',17,250),(35,'2024-06-20','11:00:00','19:00:00','Slikarstvo',18,400),(36,'2024-06-21','11:00:00','19:00:00','Fotografija',18,400),(37,'2024-05-10','09:30:00','17:30:00','Slikarstvo',19,350),(38,'2024-05-11','09:30:00','17:30:00','Fotografija',19,350),(39,'2024-08-01','10:00:00','18:00:00','Slikarstvo',20,220),(40,'2024-08-02','10:00:00','18:00:00','Fotografija',20,220);
/*!40000 ALTER TABLE `izlozba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `izlozba_lista_umetnika`
--

DROP TABLE IF EXISTS `izlozba_lista_umetnika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `izlozba_lista_umetnika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `izlozba_id` int NOT NULL,
  `umetnik_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `izlozba_id` (`izlozba_id`),
  KEY `umetnik_id` (`umetnik_id`),
  CONSTRAINT `izlozba_lista_umetnika_ibfk_1` FOREIGN KEY (`izlozba_id`) REFERENCES `izlozba` (`id`),
  CONSTRAINT `izlozba_lista_umetnika_ibfk_2` FOREIGN KEY (`umetnik_id`) REFERENCES `umetnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `izlozba_lista_umetnika`
--

LOCK TABLES `izlozba_lista_umetnika` WRITE;
/*!40000 ALTER TABLE `izlozba_lista_umetnika` DISABLE KEYS */;
INSERT INTO `izlozba_lista_umetnika` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,4),(5,2,5),(6,2,6),(7,3,7),(8,3,8),(9,3,9),(10,4,10),(11,4,11),(12,4,12),(13,5,13),(14,5,14),(15,5,15),(16,6,16),(17,6,17),(18,6,18),(19,7,19),(20,7,20),(21,7,21),(22,8,22),(23,8,23),(24,8,24),(25,9,25),(26,9,26),(27,9,27),(28,10,28),(29,10,29),(30,10,30),(31,11,31),(32,11,32),(33,11,33),(34,12,34),(35,12,35),(36,12,36),(37,13,37),(38,13,38),(39,13,39),(40,14,40),(41,14,41),(42,14,42),(43,15,43),(44,15,44),(45,15,45),(46,16,46),(47,16,47),(48,16,48),(49,17,49),(50,17,50),(51,17,1),(52,18,2),(53,18,3),(54,18,4),(55,19,5),(56,19,6),(57,19,7),(58,20,8),(59,20,9),(60,20,10),(61,21,11),(62,21,12),(63,21,13),(64,22,14),(65,22,15),(66,22,16),(67,23,17),(68,23,18),(69,23,19),(70,24,20),(71,24,21),(72,24,22),(73,25,23),(74,25,24),(75,25,25),(76,26,26),(77,26,27),(78,26,28),(79,27,29),(80,27,30),(81,27,31),(82,28,32),(83,28,33),(84,28,34),(85,29,35),(86,29,36),(87,29,37),(88,30,38),(89,30,39),(90,30,40),(91,31,41),(92,31,42),(93,31,43),(94,32,44),(95,32,45),(96,32,46),(97,33,47),(98,33,48),(99,33,49),(100,34,50),(101,34,1),(102,34,2),(103,35,3),(104,35,4),(105,35,5),(106,36,6),(107,36,7),(108,36,8),(109,37,9),(110,37,10),(111,37,11),(112,38,12),(113,38,13),(114,38,14),(115,39,15),(116,39,16),(117,39,17),(118,40,18),(119,40,19),(120,40,20);
/*!40000 ALTER TABLE `izlozba_lista_umetnika` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manifestacija`
--

DROP TABLE IF EXISTS `manifestacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manifestacija` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `grad` varchar(255) NOT NULL,
  `lokacija` varchar(255) NOT NULL,
  `datum_pocetka` date NOT NULL,
  `datum_zavrsetka` date NOT NULL,
  `cena_slikarstvo` double DEFAULT NULL,
  `cena_fotografija` double DEFAULT NULL,
  `datum_umanjenja` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manifestacija`
--

LOCK TABLES `manifestacija` WRITE;
/*!40000 ALTER TABLE `manifestacija` DISABLE KEYS */;
INSERT INTO `manifestacija` VALUES (1,'Art Fest Novi Sad','Novi Sad','SPENS','2024-05-15','2024-05-16',1500,2000,'2024-05-01'),(2,'Beogradski Art Expo','Beograd','Sava Centar','2024-06-10','2024-06-11',1600,2100,'2024-05-27'),(3,'Likovna Jesen','Niš','Hala Čair','2024-09-05','2024-09-06',1400,1900,'2024-08-22'),(4,'Subotička Art Noć','Subotica','Gradska Kuća','2024-07-20','2024-07-21',1300,1800,'2024-07-06'),(5,'Zrenjaninska Vizija','Zrenjanin','Kulturni Centar','2024-08-15','2024-08-16',1550,2050,'2024-08-01'),(6,'Pančevački Likovni Krug','Pančevo','Galerija Milorada Bate Mihailovića','2024-07-05','2024-07-06',1500,1950,'2024-06-21'),(7,'Somborski Kulturni Festival','Sombor','Gradski Muzej','2024-10-15','2024-10-16',1450,1950,'2024-10-01'),(8,'Kragujevački Likovni Salon','Kragujevac','Šumarice','2024-09-20','2024-09-21',1600,2100,'2024-09-06'),(9,'Požarevačka Art Smotra','Požarevac','Dom Kulture','2024-08-05','2024-08-06',1350,1850,'2024-07-22'),(10,'Čačanski Umetnički Dani','Čačak','Zavičajni Muzej','2024-05-01','2024-05-02',1400,1800,'2024-04-17'),(11,'Umetnost Užica','Užice','Narodni Muzej','2024-09-25','2024-09-26',1500,1950,'2024-09-11'),(12,'Valjevski Art Vikend','Valjevo','Moderna Galerija','2024-06-01','2024-06-02',1450,1900,'2024-05-18'),(13,'Kraljevačka Umetnička Sreda','Kraljevo','Kulturni Centar','2024-07-15','2024-07-16',1500,2000,'2024-07-01'),(14,'Art u Vranju','Vranje','Narodni Muzej','2024-05-20','2024-05-21',1400,1900,'2024-05-06'),(15,'Novi Pazar Art','Novi Pazar','Kulturni Centar','2024-10-05','2024-10-06',1550,2050,'2024-09-21'),(16,'Aranđelovac Art Fest','Aranđelovac','Bukovička Banja','2024-08-25','2024-08-26',1350,1850,'2024-08-11'),(17,'Vršac Likovna Nedelja','Vršac','Dom Vojske','2024-07-25','2024-07-26',1500,2000,'2024-07-11'),(18,'Šabac Umetnički Krug','Šabac','Dom Kulture','2024-06-20','2024-06-21',1400,1900,'2024-06-06'),(19,'Art u Negotinu','Negotin','Galerija Hajduk Veljka','2024-05-10','2024-05-11',1500,1950,'2024-04-26'),(20,'Prokupačka Art Scena','Prokuplje','Dom Kulture','2024-08-01','2024-08-02',1300,1800,'2024-07-18');
/*!40000 ALTER TABLE `manifestacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prijava`
--

DROP TABLE IF EXISTS `prijava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prijava` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `profesija` varchar(255) NOT NULL,
  `adresa1` varchar(255) NOT NULL,
  `adresa2` varchar(255) DEFAULT NULL,
  `postanski_broj` varchar(255) NOT NULL,
  `mesto` varchar(255) NOT NULL,
  `drzava` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `potvrda_email` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `broj_osoba` int NOT NULL,
  `datum_prijave` date NOT NULL,
  `manifestacija_id` int NOT NULL,
  `otkazana` tinyint(1) NOT NULL,
  `konacna_cena` double DEFAULT NULL,
  `promo_kod_unos` varchar(255) DEFAULT NULL,
  `tip_prijave` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `manifestacija_id` (`manifestacija_id`),
  CONSTRAINT `prijava_ibfk_1` FOREIGN KEY (`manifestacija_id`) REFERENCES `manifestacija` (`id`),
  CONSTRAINT `prijava_chk_1` CHECK ((`tip_prijave` in (_utf8mb4'Slikarstvo',_utf8mb4'Fotografija',_utf8mb4'Oba dana')))
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prijava`
--

LOCK TABLES `prijava` WRITE;
/*!40000 ALTER TABLE `prijava` DISABLE KEYS */;
INSERT INTO `prijava` VALUES (1,'Pera','Peric','Student','JNA xx','Borisa Kidrica x','26227','Pancevo','Srbija','asd@gmail.com','asd@gmail.com',NULL,4,'2024-09-01',1,1,5760,NULL,'Slikarstvo'),(2,'John','Doe','Artist','1234 Main St','Apt 5','12345','City','Country','john.doe@example.com','john.doe@example.com','53c3619e-db74-4778-806e-9dfdfce0cbff',2,'2024-09-03',1,0,1500,NULL,'Slikarstvo'),(3,'Marko','Milenkovic','profesor','Zarka Zrenjanina 33','JNA 15','11000','Beograd','Srbija','marko@gmail.com','marko@gmail.com','7d952075-eea8-408c-9415-82adfac2824f',4,'2024-09-04',1,0,12096,NULL,'Oba dana'),(5,'Milan','Milankovic','Doktor','Omladisnka 16','Vojvodjanska 15','26000','Pancevo','Srbija','milan@gmail.com','milan@gmail.com',NULL,4,'2024-09-04',2,1,1459.2,'6BCF3984','Slikarstvo'),(7,'Djura','Djukic','Medicinski tehnicar','Omladisnka 16','Vojvodjanska 15','26000','Pancevo','Srbija','milan@gmail.com','milan@gmail.com',NULL,4,'2024-09-04',2,1,3036.96,'3439BA3C','Oba dana'),(8,'Djura','Djukic','Medicinski tehnicar','Omladisnka 16','Vojvodjanska 15','26000','Pancevo','Srbija','milan@gmail.com','milan@gmail.com','fac55e39-d64b-4d7f-912d-16424b06e748',4,'2024-09-04',2,0,3036.96,'asdfghjkl','Oba dana'),(9,'Nikola','Nikolic','Biolog','Cara Dusana 16','','26000','Pancevo','Srbija','nikola@gmail.com','nikola@gmail.com',NULL,5,'2024-09-04',3,1,1330,'','Slikarstvo'),(10,'Milica','Mitic','Geograf','Sveti Sava 88','','26000','Pancevo','Srbija','milica@gmail.com','milica@gmail.com','589850fc-df7f-4a84-9a13-12c42e8a57ef',6,'2024-09-04',1,0,17955,'','Oba dana'),(11,'Ivan','Djordjeivc','Student','Jna 33','Deligradska 23','26227','Dolovo','Srbija','ivan@gmail.com','ivan@gmail.com','0c815fb2-df96-4512-8b4a-9d9b7bc5d008',3,'2024-09-04',3,0,5529,'','Fotografija'),(12,'Petar','Petrovic','Slikar','Kralja Petra I','','26227','Dolovo','Srbija','petar@gmail.com','petar@gmail.com','897456fb-2a9d-4ec8-b122-cc0707d26cfb',3,'2024-09-04',3,0,8210.565,'189CFE5A','Oba dana'),(13,'Jelisaveta','Stojanovic','Uciteljica','asdfg','','12345','Pirot','Srbija','jelisaveta@gmail.com','jelisaveta@gmail.com','d89d5eac-cf13-4af1-b21d-627f7cc5e8ff',20,'2024-09-04',6,0,58995,'','Oba dana'),(14,'Ivan','Djordjeivc','Slikar','Jna 33','Deligradska 23','26227','Dolovo','Srbija','ivan@gmail.com','ivan@gmail.com','6fb3bb26-e13f-4a19-8822-6dafddee1de1',56,'2024-09-05',1,0,79800,'','Slikarstvo'),(15,'Ana','Anic','fotograf','Kralja Petra I','','123456','Pirot','Srbija','ana@gmail.com','ana@gmail.com','b8ac132d-6727-4afc-93b1-dd57535ecea8',3,'2024-09-05',5,0,5965.5,'','Fotografija'),(16,'Anica','Draga','Prodavac','Kralja Milutina 21','','123456','Pancevo','Srbija','anica@gmail.com','anica@gmail.com','6217a3f3-b18f-4d44-84a0-ecef818272d3',37,'2024-09-05',9,0,101232,'','Oba dana'),(17,'Milojko','Milojkovic','Mesar','qwertuyijhgfd','dsfgfhfhthhbg','123456','Pancevo','Srbija','milojko@gmail.com','milojko@gmail.com',NULL,7,'2024-09-05',6,1,12967.5,'','Fotografija');
/*!40000 ALTER TABLE `prijava` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prijava_izlozba`
--

DROP TABLE IF EXISTS `prijava_izlozba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prijava_izlozba` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prijava_id` int NOT NULL,
  `izlozba_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prijava_id` (`prijava_id`),
  KEY `izlozba_id` (`izlozba_id`),
  CONSTRAINT `prijava_izlozba_ibfk_1` FOREIGN KEY (`prijava_id`) REFERENCES `prijava` (`id`),
  CONSTRAINT `prijava_izlozba_ibfk_2` FOREIGN KEY (`izlozba_id`) REFERENCES `izlozba` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prijava_izlozba`
--

LOCK TABLES `prijava_izlozba` WRITE;
/*!40000 ALTER TABLE `prijava_izlozba` DISABLE KEYS */;
INSERT INTO `prijava_izlozba` VALUES (2,2,1),(4,5,3),(5,8,4),(6,8,3),(14,9,5),(16,10,1),(17,10,2),(20,12,5),(21,12,6),(22,3,2),(23,3,1),(24,11,6),(25,13,12),(26,13,11),(27,14,1),(28,1,1),(29,15,10),(30,16,17),(31,16,18),(33,17,12);
/*!40000 ALTER TABLE `prijava_izlozba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo_kod`
--

DROP TABLE IF EXISTS `promo_kod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promo_kod` (
  `id` int NOT NULL AUTO_INCREMENT,
  `kod` varchar(255) NOT NULL,
  `koriscen` tinyint(1) NOT NULL,
  `prijava_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prijava_id` (`prijava_id`),
  CONSTRAINT `promo_kod_ibfk_1` FOREIGN KEY (`prijava_id`) REFERENCES `prijava` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_kod`
--

LOCK TABLES `promo_kod` WRITE;
/*!40000 ALTER TABLE `promo_kod` DISABLE KEYS */;
INSERT INTO `promo_kod` VALUES (1,'asdfghjkl',1,1),(2,'6BCF3984',1,2),(3,'3439BA3C',1,3),(5,'27DD3187',1,5),(7,'C09B38BA',1,7),(8,'43C53E5C',0,8),(9,'56D8752D',1,9),(10,'9187B08E',0,10),(11,'189CFE5A',1,11),(12,'D6946E4F',0,12),(13,'1E952253',0,13),(14,'9E2CBFCB',0,14),(15,'27BC8E03',0,15),(16,'C86995B8',0,16),(17,'B39BC59F',1,17);
/*!40000 ALTER TABLE `promo_kod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `umetnik`
--

DROP TABLE IF EXISTS `umetnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `umetnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `umetnik`
--

LOCK TABLES `umetnik` WRITE;
/*!40000 ALTER TABLE `umetnik` DISABLE KEYS */;
INSERT INTO `umetnik` VALUES (1,'Milan','Petrović'),(2,'Jelena','Jovanović'),(3,'Marko','Mitić'),(4,'Ana','Đorđević'),(5,'Nikola','Nikolić'),(6,'Ivana','Ilić'),(7,'Dejan','Stefanović'),(8,'Marija','Marković'),(9,'Vladimir','Vučić'),(10,'Mina','Milovanović'),(11,'Nenad','Stojanović'),(12,'Lana','Kovačević'),(13,'Petar','Stanković'),(14,'Dragana','Pavlović'),(15,'Aleksandar','Ristić'),(16,'Milica','Marić'),(17,'Miloš','Jovanović'),(18,'Nina','Stanković'),(19,'Davor','Perić'),(20,'Kristina','Radovanović'),(21,'Bojan','Dimitrijević'),(22,'Tamara','Nedeljković'),(23,'Filip','Živković'),(24,'Jovana','Milenković'),(25,'Igor','Bogdanović'),(26,'Katarina','Krstić'),(27,'Stefan','Đorđević'),(28,'Dušica','Savić'),(29,'Uroš','Blagojević'),(30,'Tanja','Todorović'),(31,'Nemanja','Antić'),(32,'Sanja','Petrić'),(33,'Dragan','Babić'),(34,'Snežana','Rakić'),(35,'Darko','Vasić'),(36,'Aleksandra','Simić'),(37,'Žarko','Mladenović'),(38,'Gordana','Milić'),(39,'Miodrag','Kostić'),(40,'Tijana','Ćirić'),(41,'Jovan','Protić'),(42,'Olivera','Radić'),(43,'Saša','Janković'),(44,'Vesna','Filipović'),(45,'Lazar','Ristić'),(46,'Anđela','Bojović'),(47,'Mladen','Tomić'),(48,'Dragoslav','Lazarević'),(49,'Isidora','Đokić'),(50,'Boško','Nikolić'),(51,'Mirjana','Obradović');
/*!40000 ALTER TABLE `umetnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-05 19:57:19
