-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema JustGetIt
-- -----------------------------------------------------
-- DB voor de beste webshop in VDAB-land
DROP SCHEMA IF EXISTS `JustGetIt` ;

-- -----------------------------------------------------
-- Schema JustGetIt
--
-- DB voor de beste webshop in VDAB-land
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `JustGetIt` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
-- -----------------------------------------------------
-- Schema justgetit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `justgetit` ;

-- -----------------------------------------------------
-- Schema justgetit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `justgetit` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `JustGetIt` ;

-- -----------------------------------------------------
-- Table `JustGetIt`.`merken`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`merken` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`merken` (
  `merkId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `minimumMargePercent` DECIMAL UNSIGNED NULL DEFAULT 0,
  `minimumMargeBedrag` DECIMAL UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`merkId`),
  UNIQUE INDEX `merkId_UNIQUE` (`merkId` ASC) VISIBLE,
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`categorieen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`categorieen` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`categorieen` (
  `categorieId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`categorieId`),
  UNIQUE INDEX `categorieId_UNIQUE` (`categorieId` ASC) VISIBLE,
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`subcategorieen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`subcategorieen` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`subcategorieen` (
  `subcategorieId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `minimumMargePercent` DECIMAL UNSIGNED NULL DEFAULT 0,
  `minimumMargeBedrag` DECIMAL UNSIGNED NULL DEFAULT 0,
  `categorieId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`subcategorieId`),
  UNIQUE INDEX `subcategorieId_UNIQUE` (`subcategorieId` ASC) VISIBLE,
  INDEX `fk_Subcategorieen_Categorieen1_idx` (`categorieId` ASC) VISIBLE,
  CONSTRAINT `fk_Subcategorieen_Categorieen1`
    FOREIGN KEY (`categorieId`)
    REFERENCES `JustGetIt`.`categorieen` (`categorieId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`producten`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`producten` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`producten` (
  `productId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `inkoopprijs` DECIMAL UNSIGNED NOT NULL,
  `verkoopprijs` DECIMAL UNSIGNED NOT NULL,
  `minimumprijs` DECIMAL UNSIGNED NOT NULL,
  `omschrijving` VARCHAR(45) NULL,
  `voorraad` BIGINT UNSIGNED NOT NULL,
  `besteld` BIGINT UNSIGNED NOT NULL,
  `merkId` BIGINT UNSIGNED NOT NULL,
  `subcategorieId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`productId`),
  UNIQUE INDEX `productId_UNIQUE` (`productId` ASC) VISIBLE,
  INDEX `fk_Producten_Merken1_idx` (`merkId` ASC) VISIBLE,
  INDEX `fk_Producten_Subcategorieen1_idx` (`subcategorieId` ASC) VISIBLE,
  CONSTRAINT `fk_Producten_Merken1`
    FOREIGN KEY (`merkId`)
    REFERENCES `JustGetIt`.`merken` (`merkId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producten_Subcategorieen1`
    FOREIGN KEY (`subcategorieId`)
    REFERENCES `JustGetIt`.`subcategorieen` (`subcategorieId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`landen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`landen` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`landen` (
  `landId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`landId`),
  UNIQUE INDEX `landId_UNIQUE` (`landId` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`provincies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`provincies` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`provincies` (
  `provincieId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `landId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`provincieId`),
  UNIQUE INDEX `provincieId_UNIQUE` (`provincieId` ASC) VISIBLE,
  INDEX `fk_Provincies_Landen_idx` (`landId` ASC) VISIBLE,
  CONSTRAINT `fk_Provincies_Landen`
    FOREIGN KEY (`landId`)
    REFERENCES `JustGetIt`.`landen` (`landId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`gemeenten`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`gemeenten` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`gemeenten` (
  `gemeenteId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `postcode` VARCHAR(10) NOT NULL,
  `provincieId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`gemeenteId`),
  UNIQUE INDEX `plaatsId_UNIQUE` (`gemeenteId` ASC) VISIBLE,
  INDEX `fk_Gemeenten_Provincies1_idx` (`provincieId` ASC) VISIBLE,
  CONSTRAINT `fk_Gemeenten_Provincies1`
    FOREIGN KEY (`provincieId`)
    REFERENCES `JustGetIt`.`provincies` (`provincieId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`klant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`klant` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`klant` (
  `klantId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `voornaam` VARCHAR(45) NOT NULL,
  `achternaam` VARCHAR(45) NOT NULL,
  `adres` VARCHAR(45) NOT NULL,
  `telefoonnummer` VARCHAR(16) NULL,
  `email` VARCHAR(45) NOT NULL,
  `gemeenteId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`klantId`),
  UNIQUE INDEX `klantId_UNIQUE` (`klantId` ASC) VISIBLE,
  INDEX `fk_Klant_Gemeenten1_idx` (`gemeenteId` ASC) VISIBLE,
  CONSTRAINT `fk_Klant_Gemeenten1`
    FOREIGN KEY (`gemeenteId`)
    REFERENCES `JustGetIt`.`gemeenten` (`gemeenteId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`lijsten`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`lijsten` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`lijsten` (
  `lijstId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `klantId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`lijstId`),
  UNIQUE INDEX `lijstId_UNIQUE` (`lijstId` ASC) VISIBLE,
  INDEX `fk_Lijsten_Klant1_idx` (`klantId` ASC) VISIBLE,
  CONSTRAINT `fk_Lijsten_Klant1`
    FOREIGN KEY (`klantId`)
    REFERENCES `JustGetIt`.`klant` (`klantId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`lijstregels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`lijstregels` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`lijstregels` (
  `lijstId` INT UNSIGNED NOT NULL,
  `productId` BIGINT UNSIGNED NOT NULL,
  `aantal` BIGINT UNSIGNED NOT NULL,
  INDEX `fk_LijstRegels_Lijsten1_idx` (`lijstId` ASC) VISIBLE,
  PRIMARY KEY (`lijstId`, `productId`),
  INDEX `fk_LijstRegels_Producten1_idx` (`productId` ASC) VISIBLE,
  CONSTRAINT `fk_LijstRegels_Lijsten1`
    FOREIGN KEY (`lijstId`)
    REFERENCES `JustGetIt`.`lijsten` (`lijstId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LijstRegels_Producten1`
    FOREIGN KEY (`productId`)
    REFERENCES `JustGetIt`.`producten` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `JustGetIt`.`bestelbon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`bestelbon` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`bestelbon` (
  `bestelbonId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `datum` DATE NOT NULL,
  `levertermijn` INT UNSIGNED NOT NULL,
  `klantId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`bestelbonId`),
  UNIQUE INDEX `bestelbonId_UNIQUE` (`bestelbonId` ASC) VISIBLE,
  INDEX `fk_BestelBon_Klant1_idx` (`klantId` ASC) VISIBLE,
  CONSTRAINT `fk_BestelBon_Klant1`
    FOREIGN KEY (`klantId`)
    REFERENCES `JustGetIt`.`klant` (`klantId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `JustGetIt`.`bestelregels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`bestelregels` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`bestelregels` (
  `bestelbonId` BIGINT UNSIGNED NOT NULL,
  `productId` BIGINT UNSIGNED NOT NULL,
  `prijs` DECIMAL UNSIGNED NOT NULL,
  `aantal` BIGINT UNSIGNED NOT NULL,
  INDEX `fk_BestelRegels_BestelBon1_idx` (`bestelbonId` ASC) VISIBLE,
  PRIMARY KEY (`bestelbonId`, `productId`),
  CONSTRAINT `fk_BestelRegels_BestelBon1`
    FOREIGN KEY (`bestelbonId`)
    REFERENCES `JustGetIt`.`bestelbon` (`bestelbonId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BestelRegels_Producten1`
    FOREIGN KEY (`productId`)
    REFERENCES `JustGetIt`.`producten` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`subcategorieeigenschappen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`subcategorieeigenschappen` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`subcategorieeigenschappen` (
  `subcategorieEigenschapId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `subcategorieId` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`subcategorieEigenschapId`),
  UNIQUE INDEX `subcategorieEigenschapId_UNIQUE` (`subcategorieEigenschapId` ASC) VISIBLE,
  INDEX `fk_SubcategorieEigenschappen_Subcategorieen1_idx` (`subcategorieId` ASC) VISIBLE,
  CONSTRAINT `fk_SubcategorieEigenschappen_Subcategorieen1`
    FOREIGN KEY (`subcategorieId`)
    REFERENCES `JustGetIt`.`subcategorieen` (`subcategorieId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`producteigenschappen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`producteigenschappen` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`producteigenschappen` (
  `subcategorieEigenschapId` BIGINT UNSIGNED NOT NULL,
  `productId` BIGINT UNSIGNED NOT NULL,
  `waarde` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subcategorieEigenschapId`, `productId`),
  INDEX `fk_ProductEigenschappen_Producten1_idx` (`productId` ASC) VISIBLE,
  CONSTRAINT `fk_ProductEigenschappen_SubcategorieEigenschappen1`
    FOREIGN KEY (`subcategorieEigenschapId`)
    REFERENCES `JustGetIt`.`subcategorieeigenschappen` (`subcategorieEigenschapId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductEigenschappen_Producten1`
    FOREIGN KEY (`productId`)
    REFERENCES `JustGetIt`.`producten` (`productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JustGetIt`.`klantenkaart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JustGetIt`.`klantenkaart` ;

CREATE TABLE IF NOT EXISTS `JustGetIt`.`klantenkaart` (
  `klantId` BIGINT UNSIGNED NOT NULL,
  `punten` BIGINT NOT NULL,
  PRIMARY KEY (`klantId`),
  CONSTRAINT `fk_KlantenKaart_Klant1`
    FOREIGN KEY (`klantId`)
    REFERENCES `JustGetIt`.`klant` (`klantId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `justgetit` ;

-- -----------------------------------------------------
-- Table `justgetit`.`landen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`landen` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`landen` (
  `landId` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`landId`),
  UNIQUE INDEX `landId_UNIQUE` (`landId` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`provincies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`provincies` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`provincies` (
  `provincieId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `landId` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`provincieId`),
  UNIQUE INDEX `provincieId_UNIQUE` (`provincieId` ASC) VISIBLE,
  INDEX `fk_Provincies_Landen_idx` (`landId` ASC) VISIBLE,
  CONSTRAINT `fk_Provincies_Landen`
    FOREIGN KEY (`landId`)
    REFERENCES `justgetit`.`landen` (`landId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`gemeenten`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`gemeenten` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`gemeenten` (
  `gemeenteId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `postcode` VARCHAR(10) NOT NULL,
  `provincieId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`gemeenteId`),
  UNIQUE INDEX `plaatsId_UNIQUE` (`gemeenteId` ASC) VISIBLE,
  INDEX `fk_Gemeenten_Provincies1_idx` (`provincieId` ASC) VISIBLE,
  CONSTRAINT `fk_Gemeenten_Provincies1`
    FOREIGN KEY (`provincieId`)
    REFERENCES `justgetit`.`provincies` (`provincieId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`klant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`klant` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`klant` (
  `klantId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `voornaam` VARCHAR(45) NOT NULL,
  `achternaam` VARCHAR(45) NOT NULL,
  `adres` VARCHAR(45) NOT NULL,
  `telefoonnummer` VARCHAR(16) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `gemeenteId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`klantId`),
  UNIQUE INDEX `klantId_UNIQUE` (`klantId` ASC) VISIBLE,
  INDEX `fk_Klant_Gemeenten1_idx` (`gemeenteId` ASC) VISIBLE,
  CONSTRAINT `fk_Klant_Gemeenten1`
    FOREIGN KEY (`gemeenteId`)
    REFERENCES `justgetit`.`gemeenten` (`gemeenteId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`bestelbon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`bestelbon` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`bestelbon` (
  `bestelbonId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `datum` DATE NOT NULL,
  `levertermijn` INT(10) UNSIGNED NOT NULL,
  `klantId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`bestelbonId`),
  UNIQUE INDEX `bestelbonId_UNIQUE` (`bestelbonId` ASC) VISIBLE,
  INDEX `fk_BestelBon_Klant1_idx` (`klantId` ASC) VISIBLE,
  CONSTRAINT `fk_BestelBon_Klant1`
    FOREIGN KEY (`klantId`)
    REFERENCES `justgetit`.`klant` (`klantId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`merken`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`merken` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`merken` (
  `merkId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `minimumMargePercent` DECIMAL(10,0) UNSIGNED NULL DEFAULT '0',
  `minimumMargeBedrag` DECIMAL(10,0) UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`merkId`),
  UNIQUE INDEX `merkId_UNIQUE` (`merkId` ASC) VISIBLE,
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`categorieen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`categorieen` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`categorieen` (
  `categorieId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`categorieId`),
  UNIQUE INDEX `categorieId_UNIQUE` (`categorieId` ASC) VISIBLE,
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`subcategorieen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`subcategorieen` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`subcategorieen` (
  `subcategorieId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `minimumMargePercent` DECIMAL(10,0) UNSIGNED NULL DEFAULT '0',
  `minimumMargeBedrag` DECIMAL(10,0) UNSIGNED NULL DEFAULT '0',
  `categorieId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`subcategorieId`),
  UNIQUE INDEX `subcategorieId_UNIQUE` (`subcategorieId` ASC) VISIBLE,
  UNIQUE INDEX `naamInCategorie_UNIQUE` (`naam` ASC, `subcategorieId` ASC) VISIBLE,
  INDEX `fk_Subcategorieen_Categorieen1_idx` (`categorieId` ASC) VISIBLE,
  CONSTRAINT `fk_Subcategorieen_Categorieen1`
    FOREIGN KEY (`categorieId`)
    REFERENCES `justgetit`.`categorieen` (`categorieId`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`producten`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`producten` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`producten` (
  `productId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `inkoopprijs` DECIMAL(10,0) UNSIGNED NOT NULL,
  `verkoopprijs` DECIMAL(10,0) UNSIGNED NOT NULL,
  `minimumprijs` DECIMAL(10,0) UNSIGNED NOT NULL,
  `omschrijving` VARCHAR(45) NULL DEFAULT NULL,
  `voorraad` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `besteld` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `merkId` BIGINT(20) UNSIGNED NOT NULL,
  `subcategorieId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`productId`),
  UNIQUE INDEX `productId_UNIQUE` (`productId` ASC) VISIBLE,
  INDEX `fk_Producten_Merken1_idx` (`merkId` ASC) VISIBLE,
  INDEX `fk_Producten_Subcategorieen1_idx` (`subcategorieId` ASC) VISIBLE,
  CONSTRAINT `fk_Producten_Merken1`
    FOREIGN KEY (`merkId`)
    REFERENCES `justgetit`.`merken` (`merkId`),
  CONSTRAINT `fk_Producten_Subcategorieen1`
    FOREIGN KEY (`subcategorieId`)
    REFERENCES `justgetit`.`subcategorieen` (`subcategorieId`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`bestelregels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`bestelregels` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`bestelregels` (
  `bestelbonId` BIGINT(20) UNSIGNED NOT NULL,
  `productId` BIGINT(20) UNSIGNED NOT NULL,
  `prijs` DECIMAL(10,0) UNSIGNED NOT NULL,
  `aantal` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`bestelbonId`, `productId`),
  INDEX `fk_BestelRegels_BestelBon1_idx` (`bestelbonId` ASC) VISIBLE,
  INDEX `fk_BestelRegels_Producten1` (`productId` ASC) VISIBLE,
  CONSTRAINT `fk_BestelRegels_BestelBon1`
    FOREIGN KEY (`bestelbonId`)
    REFERENCES `justgetit`.`bestelbon` (`bestelbonId`),
  CONSTRAINT `fk_BestelRegels_Producten1`
    FOREIGN KEY (`productId`)
    REFERENCES `justgetit`.`producten` (`productId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`klantenkaart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`klantenkaart` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`klantenkaart` (
  `klantId` BIGINT(20) UNSIGNED NOT NULL,
  `punten` BIGINT(20) NOT NULL,
  PRIMARY KEY (`klantId`),
  CONSTRAINT `fk_KlantenKaart_Klant1`
    FOREIGN KEY (`klantId`)
    REFERENCES `justgetit`.`klant` (`klantId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`lijsten`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`lijsten` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`lijsten` (
  `lijstId` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `klantId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`lijstId`),
  UNIQUE INDEX `lijstId_UNIQUE` (`lijstId` ASC) VISIBLE,
  INDEX `fk_Lijsten_Klant1_idx` (`klantId` ASC) VISIBLE,
  CONSTRAINT `fk_Lijsten_Klant1`
    FOREIGN KEY (`klantId`)
    REFERENCES `justgetit`.`klant` (`klantId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`lijstregels`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`lijstregels` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`lijstregels` (
  `lijstId` INT(10) UNSIGNED NOT NULL,
  `productId` BIGINT(20) UNSIGNED NOT NULL,
  `aantal` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`lijstId`, `productId`),
  INDEX `fk_LijstRegels_Lijsten1_idx` (`lijstId` ASC) VISIBLE,
  INDEX `fk_LijstRegels_Producten1_idx` (`productId` ASC) VISIBLE,
  CONSTRAINT `fk_LijstRegels_Lijsten1`
    FOREIGN KEY (`lijstId`)
    REFERENCES `justgetit`.`lijsten` (`lijstId`),
  CONSTRAINT `fk_LijstRegels_Producten1`
    FOREIGN KEY (`productId`)
    REFERENCES `justgetit`.`producten` (`productId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`subcategorieeigenschappen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`subcategorieeigenschappen` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`subcategorieeigenschappen` (
  `subcategorieEigenschapId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `subcategorieId` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`subcategorieEigenschapId`),
  UNIQUE INDEX `subcategorieEigenschapId_UNIQUE` (`subcategorieEigenschapId` ASC) VISIBLE,
  INDEX `fk_SubcategorieEigenschappen_Subcategorieen1_idx` (`subcategorieId` ASC) VISIBLE,
  CONSTRAINT `fk_SubcategorieEigenschappen_Subcategorieen1`
    FOREIGN KEY (`subcategorieId`)
    REFERENCES `justgetit`.`subcategorieen` (`subcategorieId`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `justgetit`.`producteigenschappen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `justgetit`.`producteigenschappen` ;

CREATE TABLE IF NOT EXISTS `justgetit`.`producteigenschappen` (
  `subcategorieEigenschapId` BIGINT(20) UNSIGNED NOT NULL,
  `productId` BIGINT(20) UNSIGNED NOT NULL,
  `waarde` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subcategorieEigenschapId`, `productId`),
  INDEX `fk_ProductEigenschappen_Producten1_idx` (`productId` ASC) VISIBLE,
  CONSTRAINT `fk_ProductEigenschappen_Producten1`
    FOREIGN KEY (`productId`)
    REFERENCES `justgetit`.`producten` (`productId`),
  CONSTRAINT `fk_ProductEigenschappen_SubcategorieEigenschappen1`
    FOREIGN KEY (`subcategorieEigenschapId`)
    REFERENCES `justgetit`.`subcategorieeigenschappen` (`subcategorieEigenschapId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `producten` VALUES (1,'Lord of the Rings Trilogy: Extended Edition',23,100,50,'Meer dan 12u kijkplezier!',0,0,1,5),(2,'Lord of the Rings Boxset',5,18,10,'Meer dan 32u leesplezier!',0,0,3,1),(3,'QE55Q6F(2018) - QLED',200,799,399,'Meer kijkplezier!',0,0,2,7),(4,'Overhemd',5,39,15,'Meer dan zeer comfortabel draagplezier!',0,0,4,3);
INSERT INTO `producteigenschappen` VALUES (1,2,'Paperback'),(3,4,'M'),(5,1,'2100 minuten'),(7,3,'140 cm');
INSERT INTO `subcategorieeigenschappen` VALUES (1,'Uitvoering',1),(2,'Druknummer',2),(3,'Maat',3),(4,'Maat',4),(5,'Duur',5),(6,'Duur',6),(7,'Diameter',7),(8,'Verbruik',8),(9,'Houdbaarheidsdatum',9),(10,'Volume',10);
INSERT INTO `subcategorieen` VALUES (1,'Fictie',0,0,2),(2,'Non-Fictie',0,0,2),(3,'Mannen',0,0,4),(4,'Vrouwen',0,0,4),(5,'DVD',0,0,1),(6,'Blu-Ray',0,0,1),(7,'Televisies',0,0,3),(8,'Huishoudapparatuur',0,0,3),(9,'Fruit',0,0,5),(10,'Conserven',0,0,5);
INSERT INTO `merken` VALUES (1,'A Film Benelux MSD B.V.',0,0),(2,'Samsung',0,0),(3,'HarperCollins Publishers',0,0),(4,'Hollister Co.',0,0),(5,'Vaporesso',0,0);
INSERT INTO `categorieen` VALUES (2,'Boeken'),(3,'Elektronica'),(4,'Fashion'),(1,'Films'),(5,'Voeding');