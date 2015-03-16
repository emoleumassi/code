SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `masterthesis`.`UDDISLA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`UDDISLA` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`UDDISLA` (
  `uddislaId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NOT NULL,
  `state` VARCHAR(10) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `version` CHAR(5) NOT NULL,
  PRIMARY KEY (`uddislaId`))
ENGINE = InnoDB
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `masterthesis`.`SLA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`SLA` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`SLA` (
  `slaId` INT NOT NULL AUTO_INCREMENT,
  `uddislaId` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `startTime` DATE NOT NULL,
  `endTime` DATE NOT NULL,
  PRIMARY KEY (`slaId`),
  INDEX `fk_SLA_UDDISLA1_idx` (`uddislaId` ASC),
  CONSTRAINT `fk_SLA_UDDISLA1`
    FOREIGN KEY (`uddislaId`)
    REFERENCES `masterthesis`.`UDDISLA` (`uddislaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `masterthesis`.`ServiceTerms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`ServiceTerms` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`ServiceTerms` (
  `serviceTermsId` INT NOT NULL AUTO_INCREMENT,
  `slaId` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `designation` VARCHAR(100) NOT NULL,
  `description` TEXT NOT NULL,
  `costPerUnitOfAccount` DECIMAL NOT NULL,
  `unitOfAccount` INT NOT NULL,
  PRIMARY KEY (`serviceTermsId`),
  INDEX `fk_ServiceTerms_SLA1_idx` (`slaId` ASC),
  CONSTRAINT `fk_ServiceTerms_SLA1`
    FOREIGN KEY (`slaId`)
    REFERENCES `masterthesis`.`SLA` (`slaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masterthesis`.`Method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`Method` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`Method` (
  `methodId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`methodId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masterthesis`.`KeyPerformanceIndicator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`KeyPerformanceIndicator` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`KeyPerformanceIndicator` (
  `keyPerformanceIndicatorId` INT NOT NULL AUTO_INCREMENT,
  `methodId` INT NOT NULL,
  `designation` VARCHAR(100) NOT NULL,
  `qualifyingCondiction` VARCHAR(5) NOT NULL,
  `targetValue` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`keyPerformanceIndicatorId`),
  INDEX `fk_KeyPerformanceIndicator_Method1_idx` (`methodId` ASC),
  CONSTRAINT `fk_KeyPerformanceIndicator_Method1`
    FOREIGN KEY (`methodId`)
    REFERENCES `masterthesis`.`Method` (`methodId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masterthesis`.`UDDI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`UDDI` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`UDDI` (
  `uddiId` INT NOT NULL AUTO_INCREMENT,
  `uddislaId` INT NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`uddiId`),
  INDEX `fk_UDDI_UDDISLA1_idx` (`uddislaId` ASC),
  CONSTRAINT `fk_UDDI_UDDISLA1`
    FOREIGN KEY (`uddislaId`)
    REFERENCES `masterthesis`.`UDDISLA` (`uddislaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masterthesis`.`OverviewDoc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`OverviewDoc` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`OverviewDoc` (
  `overviewDocId` INT NOT NULL AUTO_INCREMENT,
  `uddiId` INT NOT NULL,
  `description` TEXT NOT NULL,
  `overviewUrl` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`overviewDocId`),
  INDEX `fk_OverviewDoc_UDDI_idx` (`uddiId` ASC),
  CONSTRAINT `fk_OverviewDoc_UDDI`
    FOREIGN KEY (`uddiId`)
    REFERENCES `masterthesis`.`UDDI` (`uddiId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `masterthesis`.`GuaranteeTerms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`GuaranteeTerms` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`GuaranteeTerms` (
  `guaranteeTermsId` INT NOT NULL AUTO_INCREMENT,
  `slaId` INT NOT NULL,
  `keyPerformanceIndicatorId` INT NOT NULL,
  `obligated` VARCHAR(10) NOT NULL,
  `serviceName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`guaranteeTermsId`),
  INDEX `fk_GuaranteeTerms_SLA1_idx` (`slaId` ASC),
  INDEX `fk_GuaranteeTerms_KeyPerformanceIndicator1_idx` (`keyPerformanceIndicatorId` ASC),
  CONSTRAINT `fk_GuaranteeTerms_SLA1`
    FOREIGN KEY (`slaId`)
    REFERENCES `masterthesis`.`SLA` (`slaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_GuaranteeTerms_KeyPerformanceIndicator1`
    FOREIGN KEY (`keyPerformanceIndicatorId`)
    REFERENCES `masterthesis`.`KeyPerformanceIndicator` (`keyPerformanceIndicatorId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
