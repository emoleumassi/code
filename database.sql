SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `masterthesis`.`UDDISLA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`UDDISLA` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`UDDISLA` (
  `uddislaId` VARCHAR(50) NOT NULL,
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
  `slaId` VARCHAR(50) NOT NULL,
  `uddislaId` VARCHAR(50) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `startTime` DATE NOT NULL,
  `endTime` DATE NOT NULL,
  PRIMARY KEY (`slaId`),
  CONSTRAINT `fk_SLA_UDDISLA1`
    FOREIGN KEY (`uddislaId`)
    REFERENCES `masterthesis`.`UDDISLA` (`uddislaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
PACK_KEYS = DEFAULT;

CREATE INDEX `fk_SLA_UDDISLA1_idx` ON `masterthesis`.`SLA` (`uddislaId` ASC);


-- -----------------------------------------------------
-- Table `masterthesis`.`ServiceTerm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`ServiceTerm` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`ServiceTerm` (
  `serviceTermId` VARCHAR(50) NOT NULL,
  `slaId` VARCHAR(50) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `designation` VARCHAR(100) NOT NULL,
  `description` TEXT NOT NULL,
  `costPerUnitOfAccount` DECIMAL NOT NULL,
  `unitOfAccount` INT NOT NULL,
  PRIMARY KEY (`serviceTermId`),
  CONSTRAINT `fk_ServiceTerms_SLA1`
    FOREIGN KEY (`slaId`)
    REFERENCES `masterthesis`.`SLA` (`slaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_ServiceTerms_SLA1_idx` ON `masterthesis`.`ServiceTerm` (`slaId` ASC);


-- -----------------------------------------------------
-- Table `masterthesis`.`GuaranteeTerm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`GuaranteeTerm` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`GuaranteeTerm` (
  `guaranteeTermId` VARCHAR(50) NOT NULL,
  `slaId` VARCHAR(50) NOT NULL,
  `obligated` VARCHAR(10) NOT NULL,
  `serviceName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`guaranteeTermId`),
  CONSTRAINT `fk_GuaranteeTerms_SLA1`
    FOREIGN KEY (`slaId`)
    REFERENCES `masterthesis`.`SLA` (`slaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_GuaranteeTerms_SLA1_idx` ON `masterthesis`.`GuaranteeTerm` (`slaId` ASC);


-- -----------------------------------------------------
-- Table `masterthesis`.`KeyPerformanceIndicator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`KeyPerformanceIndicator` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`KeyPerformanceIndicator` (
  `keyPerformanceIndicatorId` VARCHAR(50) NOT NULL,
  `guaranteeTermId` VARCHAR(50) NOT NULL,
  `designation` VARCHAR(100) NOT NULL,
  `qualifyingCondiction` VARCHAR(5) NOT NULL,
  `targetValue` VARCHAR(5) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`keyPerformanceIndicatorId`),
  CONSTRAINT `fk_KeyPerformanceIndicator_GuaranteeTerms1`
    FOREIGN KEY (`guaranteeTermId`)
    REFERENCES `masterthesis`.`GuaranteeTerm` (`guaranteeTermId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_KeyPerformanceIndicator_GuaranteeTerms1_idx` ON `masterthesis`.`KeyPerformanceIndicator` (`guaranteeTermId` ASC);


-- -----------------------------------------------------
-- Table `masterthesis`.`UDDI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`UDDI` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`UDDI` (
  `uddiId` VARCHAR(50) NOT NULL,
  `uddislaId` VARCHAR(50) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`uddiId`),
  CONSTRAINT `fk_UDDI_UDDISLA1`
    FOREIGN KEY (`uddislaId`)
    REFERENCES `masterthesis`.`UDDISLA` (`uddislaId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_UDDI_UDDISLA1_idx` ON `masterthesis`.`UDDI` (`uddislaId` ASC);


-- -----------------------------------------------------
-- Table `masterthesis`.`OverviewDoc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `masterthesis`.`OverviewDoc` ;

CREATE TABLE IF NOT EXISTS `masterthesis`.`OverviewDoc` (
  `overviewDocId` VARCHAR(50) NOT NULL,
  `uddiId` INT NOT NULL,
  `description` TEXT NOT NULL,
  `overviewUrl` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`overviewDocId`),
  CONSTRAINT `fk_OverviewDoc_UDDI`
    FOREIGN KEY (`uddiId`)
    REFERENCES `masterthesis`.`UDDI` (`uddiId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_OverviewDoc_UDDI_idx` ON `masterthesis`.`OverviewDoc` (`uddiId` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
