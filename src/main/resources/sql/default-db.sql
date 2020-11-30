--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `storeId` int NOT NULL,
  `storeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`storeId`)
) ENGINE=InnoDB;

--
-- Dumping data for table `store`
--

INSERT INTO `store` VALUES (1,'sus-01'),(2,'sus-02');

--
-- Table structure for table `sync_group`
--

DROP TABLE IF EXISTS `sync_group`;
CREATE TABLE `sync_group` (
  `syncGroupId` int NOT NULL,
  `syncGroupName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`syncGroupId`)
) ENGINE=InnoDB;

--
-- Dumping data for table `sync_group`
--

INSERT INTO `sync_group` VALUES (1,'sus');

--
-- Table structure for table `sync_level`
--

DROP TABLE IF EXISTS `sync_level`;
CREATE TABLE `sync_level` (
  `syncLevelId` int NOT NULL,
  `syncGroupId` int DEFAULT NULL,
  `storeId` int DEFAULT NULL,
  `syncCreate` tinyint(1) DEFAULT NULL,
  `syncUpdate` tinyint(1) DEFAULT NULL,
  `syncDelete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`syncLevelId`),
  KEY `fk_group_idx` (`syncGroupId`),
  KEY `fk_store_idx` (`storeId`),
  CONSTRAINT `fk_synclevel_group` FOREIGN KEY (`syncGroupId`) REFERENCES `sync_group` (`syncGroupId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_synclevel_store` FOREIGN KEY (`storeId`) REFERENCES `store` (`storeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

--
-- Dumping data for table `sync_level`
--

INSERT INTO `sync_level` VALUES (1,1,1,0,0,0),(2,1,2,1,1,0);


--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `regId` int NOT NULL AUTO_INCREMENT,
  `productId` int DEFAULT NULL,
  `productName` varchar(45) DEFAULT NULL,
  `storeId` int DEFAULT NULL,
  PRIMARY KEY (`regId`),
  KEY `fk_reg_store_idx` (`storeId`),
  CONSTRAINT `fk_reg_store` FOREIGN KEY (`storeId`) REFERENCES `store` (`storeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3;

--
-- Dumping data for table `product`
--

INSERT INTO `product` VALUES (1,1,'book',1),(2,2,'book',2);