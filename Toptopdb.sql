CREATE DATABASE  IF NOT EXISTS `cp2196g03g2_toptopdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cp2196g03g2_toptopdb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: cp2196g03g2_toptopdb
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `tbl_coupon`
--

DROP TABLE IF EXISTS `tbl_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `expired_at` datetime DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_coupon`
--

LOCK TABLES `tbl_coupon` WRITE;
/*!40000 ALTER TABLE `tbl_coupon` DISABLE KEYS */;
INSERT INTO `tbl_coupon` VALUES (1,'XYZKIMALUSGHWM','2022-10-11 15:59:19',100,_binary '\0',0.2,'0000-00-00 00:00:00'),(2,'BJHXXOHZQV','1999-01-10 00:00:00',100,_binary '',0,'0000-00-00 00:00:00'),(3,'YNRWIACVNB','2012-09-10 00:00:00',100,_binary '',0,'0000-00-00 00:00:00'),(4,'RJPKYJUBSO','2012-09-10 00:00:00',100,_binary '',0,'0000-00-00 00:00:00'),(5,'LIKSAOEKFV','2012-09-10 00:00:00',100,_binary '',0,'0000-00-00 00:00:00'),(6,'DSADASD','2012-09-10 00:00:00',100,_binary '',0,'2022-10-13 15:11:37'),(7,'ĐÂSDXCXZCSA','2012-09-10 00:00:00',100,_binary '',0,'2022-10-13 15:12:09'),(8,'SADAXĐQƯEDSAD','2012-09-10 00:00:00',100,_binary '',0,'2011-08-30 00:00:00'),(9,'LIKUSDNNCWKL','2012-09-10 00:00:00',100,_binary '\0',0,'2011-08-30 00:00:00');
/*!40000 ALTER TABLE `tbl_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_coupon_user`
--

DROP TABLE IF EXISTS `tbl_coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_coupon_user` (
  `coupon_id` int NOT NULL,
  `user_id` varchar(255) NOT NULL,
  KEY `FKffrjipubpcypc7970gerg4m6r` (`user_id`),
  KEY `FK6drchro9a3dkxv1abp3i4cl2g` (`coupon_id`),
  CONSTRAINT `FK6drchro9a3dkxv1abp3i4cl2g` FOREIGN KEY (`coupon_id`) REFERENCES `tbl_coupon` (`id`),
  CONSTRAINT `FKffrjipubpcypc7970gerg4m6r` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_coupon_user`
--

LOCK TABLES `tbl_coupon_user` WRITE;
/*!40000 ALTER TABLE `tbl_coupon_user` DISABLE KEYS */;
INSERT INTO `tbl_coupon_user` VALUES (2,'088574c8-5cf6-4d33-aa7f-254d39208280'),(3,'088574c8-5cf6-4d33-aa7f-254d39208280');
/*!40000 ALTER TABLE `tbl_coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_role`
--

DROP TABLE IF EXISTS `tbl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `description` text,
  `alias` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b8pdcpsskpvgwwxu1mey2x6dq` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_role`
--

LOCK TABLES `tbl_role` WRITE;
/*!40000 ALTER TABLE `tbl_role` DISABLE KEYS */;
INSERT INTO `tbl_role` VALUES (1,'ROLE_SUPERADMIN','Management everything','Super Admin'),(2,'ROLE_CONTENT_MODERATOR','Content Moderation','Content Censorship Admin'),(3,'ROLE_COUPON_MODERATOR','Coupon Moderation','Coupon Management User'),(4,'ROLE_TICKET_MODERATOR','Management And Approve Ticket Enable Toptop Shop Feature','TopTop Shop Management Admin'),(5,'ROLE_USER','Customer Of Website','Customer'),(6,'ROLE_SHOP_USER','Customer Of Website With Toptop Shop Feature','Shop Management User');
/*!40000 ALTER TABLE `tbl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ticketshop`
--

DROP TABLE IF EXISTS `tbl_ticketshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ticketshop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `created_date` datetime NOT NULL,
  `reply` text,
  `status` int DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6m5unl690xvfi716pbico885m` (`userid`),
  CONSTRAINT `FK6m5unl690xvfi716pbico885m` FOREIGN KEY (`userid`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ticketshop`
--

LOCK TABLES `tbl_ticketshop` WRITE;
/*!40000 ALTER TABLE `tbl_ticketshop` DISABLE KEYS */;
INSERT INTO `tbl_ticketshop` VALUES (1,'Anh ơi duyệt mở cho em tính năng shop','2022-10-08 20:32:01',NULL,1,NULL),(2,'Anh ơi duyệt mở cho em tính năng shop','2022-10-08 20:55:14',NULL,0,NULL),(3,'Anh ơi duyệt mở cho em tính năng shop','2022-10-08 20:56:11',NULL,0,NULL),(4,'Anh ơi duyệt mở cho em tính năng shop','2022-10-09 14:55:40',NULL,0,NULL),(5,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:42:16',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(6,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:44:15',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(7,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:44:39',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(8,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:45:18',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(9,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:45:36',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(10,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:46:23',NULL,2,'088574c8-5cf6-4d33-aa7f-254d39208280'),(11,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 14:48:58',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(12,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 15:03:02',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(13,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 15:03:38',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(14,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 15:04:32',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(15,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 15:05:04',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(16,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 15:08:18',NULL,1,'088574c8-5cf6-4d33-aa7f-254d39208280'),(17,'Anh ơi DkM ANH LUÔN!!!!','2022-10-10 15:11:13',NULL,1,'c68efbea-781b-43d1-96f9-8d02f512769e');
/*!40000 ALTER TABLE `tbl_ticketshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(300) NOT NULL,
  `password` varchar(300) DEFAULT NULL,
  `full_name` varchar(256) DEFAULT NULL,
  `history` text,
  `avatar` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_npn1wf1yu1g5rjohbek375pp1` (`email`),
  KEY `FKqyhp9ytkc0o8uapy1vtqmw350` (`role_id`),
  CONSTRAINT `FKqyhp9ytkc0o8uapy1vtqmw350` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES ('088574c8-5cf6-4d33-aa7f-254d39208280','joesph1994@yahoo.com',NULL,'Nguyen Hong','Something I Must Change','https://www.fakepersongenerator.com/Face/male/male20161083987553070.jpg','nguyenhong088574',NULL),('1bcdba33-216d-4e78-a92a-d7e740ff7908','johnnie_grim@gmail.com',NULL,'Merrill H Robinson','Evening news is where they begin with \'Good evening\', and then they proceed to tell you why it isn\'t.','','merrillhrobinson47235',NULL),('2ad83cce-a7b1-4b73-84dd-4de69541043d','leanne1988@hotmail.com',NULL,'Beverly D Johnson','The NFL is taking it\'s crackdown on violence so seriously, the refs are now using rape whistles.','','BeverlyDJohnson0e318',NULL),('405404d9-64c7-4905-8df6-4f26b07f3870','cierra1986@gmail.com','aLdexYDc6HVNlqD','Walter O Clouse','Professional social mediaholic. Award-winning writer. Problem solver. Certified troublemaker.','https://www.fakepersongenerator.com/Face/male/male20161083837574982.jpg','walteroclouse405404d9',NULL),('739b62da-5e23-4302-9931-94532c250cda','anjali.row12@hotmail.com','sDxSMRZPQcSHZO0','Kathleen J Thaler','Devoted reader. Certified travel aficionado. Pop culture advocate. Creator. Entrepreneur.','https://www.fakepersongenerator.com/Face/male/male108459814588.jpg','kathleenjthaler739b62da',NULL),('891f7e7b-4dc5-4a8a-9cf8-824195b81979','ismael.breitenbe@hotmail.com','eNHVX8VpCBU6AOs','Virginia M. Payan','3 reasons why I\'m single… Can\'t date food, can\'t date celebs, and I can\'t date the internet.','https://www.fakepersongenerator.com/Face/male/male1084909622101.jpg','virginiaM.payan891f7e7b',NULL),('8f1d9ea3-72ee-4ae6-8258-f66fbc957168','benedict1986@yahoo.com','sQD113loq9bxpPX','Abraham J Pogue','The art of living does not consist in preserving and clinging to a particular mode of happiness','https://www.fakepersongenerator.com/Face/female/female2015102398053925.jpg','abrahamjpogue8f1d9ea3',NULL),('c68efbea-781b-43d1-96f9-8d02f512769e','clemmie1992@hotmail.com',NULL,'Michael H Schmidt','Friendly internet specialist. Alcohol fanatic. Infuriatingly humble beer enthusiast.','https://www.fakepersongenerator.com/Face/male/male10857581648.jpg','michaelhschmidtd6e22',2);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-14  8:55:36
