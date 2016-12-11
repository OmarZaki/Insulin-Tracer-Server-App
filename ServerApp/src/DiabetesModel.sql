-- MySQL Script generated by MySQL Workbench
-- 12/05/16 16:39:29
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema diabetesDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema diabetesDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `diabetesDB` DEFAULT CHARACTER SET utf8 ;
USE `diabetesDB` ;

-- -----------------------------------------------------
-- Table `diabetesDB`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `admin` TINYINT(1) NULL,
  `type` TINYINT(1) NULL,
  `token` VARCHAR(45) NULL,
  `creation_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diabetesDB`.`Meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`Meal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `date_time` DATETIME NULL,
  `description` VARCHAR(45) NULL,
  `image` VARCHAR(45) NULL,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Meal_Users_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Meal_Users`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesDB`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diabetesDB`.`Appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`Appointments` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `date_time` DATETIME NULL,
  `assessment` VARCHAR(45) NULL,
  `blood_pressure` FLOAT NULL,
  `weight` FLOAT NULL,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Appointments_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Appointments_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesDB`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diabetesDB`.`InsulinDose`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`InsulinDose` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` FLOAT NULL,
  `taken` TINYINT(1) DEFAULT 0,
  `date_time` DATETIME NULL,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_InsulinDose_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_InsulinDose_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesDB`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diabetesDB`.`Messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`Messages` (
  `id` INT NOT NULL,
  `text` TEXT NULL,
  `date_time` DATETIME NULL,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Messages_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Messages_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesDB`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diabetesDB`.`Category_name`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`Category_name` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diabetesDB`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesDB`.`Categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NULL,
  `value` VARCHAR(45) NULL,
  `Users_id` INT NOT NULL,
  `Category_name_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Categories_Users1_idx` (`Users_id` ASC),
  INDEX `fk_Categories_Category_name1_idx` (`Category_name_id` ASC),
  CONSTRAINT `fk_Categories_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesDB`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Categories_Category_name1`
    FOREIGN KEY (`Category_name_id`)
    REFERENCES `diabetesDB`.`Category_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
