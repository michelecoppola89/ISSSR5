SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `rest5db` DEFAULT CHARACTER SET latin1 ;
USE `rest5db` ;

-- -----------------------------------------------------
-- Table `rest5db`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rest5db`.`user` (
  `user` VARCHAR(45) NOT NULL ,
  `psw` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`user`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rest5db`.`custommacroservice`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rest5db`.`custommacroservice` (
  `idCustomMacroService` VARCHAR(45) NOT NULL ,
  `numOperand` INT(11) NULL DEFAULT NULL ,
  `description` TEXT NULL DEFAULT NULL ,
  `parameterList` TEXT NULL DEFAULT NULL ,
  `elementaryServiceList` TEXT NULL DEFAULT NULL ,
  `isPrivate` TINYINT(1) NULL DEFAULT NULL ,
  `user` VARCHAR(45) NOT NULL ,
  `keywords` TEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`idCustomMacroService`) ,
  INDEX `fk_custommacroservice_user1_idx` (`user` ASC) ,
  CONSTRAINT `fk_custommacroservice_user1`
    FOREIGN KEY (`user` )
    REFERENCES `rest5db`.`user` (`user` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rest5db`.`scale`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rest5db`.`scale` (
  `idScale` INT(11) NOT NULL AUTO_INCREMENT ,
  `scaleType` VARCHAR(45) NULL DEFAULT NULL ,
  `user_user` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idScale`) ,
  INDEX `fk_scale_user1_idx` (`user_user` ASC) ,
  CONSTRAINT `fk_scale_user1`
    FOREIGN KEY (`user_user` )
    REFERENCES `rest5db`.`user` (`user` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rest5db`.`dataseries`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rest5db`.`dataseries` (
  `iddataSeries` INT(11) NOT NULL AUTO_INCREMENT ,
  `operandMode` VARCHAR(45) NULL DEFAULT NULL ,
  `operandType` VARCHAR(45) NULL DEFAULT NULL ,
  `dataseries` TEXT NULL DEFAULT NULL ,
  `idScale` INT(11) NOT NULL ,
  `user` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`iddataSeries`) ,
  INDEX `fk_dataseries_scale_idx` (`idScale` ASC) ,
  INDEX `fk_dataseries_user1_idx` (`user` ASC) ,
  CONSTRAINT `fk_dataseries_scale`
    FOREIGN KEY (`idScale` )
    REFERENCES `rest5db`.`scale` (`idScale` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_dataseries_user1`
    FOREIGN KEY (`user` )
    REFERENCES `rest5db`.`user` (`user` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rest5db`.`domain`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rest5db`.`domain` (
  `idScale` INT(11) NOT NULL ,
  `scalePoint` TEXT NULL DEFAULT NULL ,
  `min` DOUBLE NULL DEFAULT NULL ,
  `max` DOUBLE NULL DEFAULT NULL ,
  `discr_dom` VARCHAR(45) NULL DEFAULT NULL ,
  `domType` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`idScale`) ,
  INDEX `fk_domain_scale1_idx` (`idScale` ASC) ,
  CONSTRAINT `fk_domain_scale1`
    FOREIGN KEY (`idScale` )
    REFERENCES `rest5db`.`scale` (`idScale` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

USE `rest5db` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
