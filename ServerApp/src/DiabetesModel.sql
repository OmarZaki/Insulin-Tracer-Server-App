-- MySQL Script generated by MySQL Workbench
-- 12/12/16 18:54:52
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema diabetesdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema diabetesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `diabetesdb` DEFAULT CHARACTER SET utf8 ;
USE `diabetesdb` ;

-- -----------------------------------------------------
-- Table `diabetesdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL DEFAULT NULL,
  `lname` VARCHAR(45) NULL DEFAULT NULL,
  `phone_number` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `admin` TINYINT(1) NULL DEFAULT NULL,
  `type` TINYINT(1) NULL DEFAULT NULL,
  `token` VARCHAR(45) NULL DEFAULT NULL,
  `birth_date` DATETIME NULL DEFAULT NULL,
  `creation_date` DATETIME NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diabetesdb`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`appointments` (
  `id` INT(11) NOT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `date_time` DATETIME NULL DEFAULT NULL,
  `assessment` VARCHAR(45) NULL DEFAULT NULL,
  `blood_pressure` FLOAT NULL DEFAULT NULL,
  `weight` FLOAT NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Appointments_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Appointments_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diabetesdb`.`category_name`
-- 1 - Heart Rate
-- 2 - Medication
-- 3 - Blood Sugar
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`category_name` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO category_name (id, title) VALUES (1,"Heart Rate");
INSERT INTO category_name (id, title) VALUES (2,"Medication");
INSERT INTO category_name (id, title) VALUES (3,"Blood Sugar");


-- -----------------------------------------------------
-- Table `diabetesdb`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`categories` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NULL DEFAULT NULL,
  `value` VARCHAR(45) NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  `Category_name_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Categories_Users1_idx` (`Users_id` ASC),
  INDEX `fk_Categories_Category_name1_idx` (`Category_name_id` ASC),
  CONSTRAINT `fk_Categories_Category_name1`
    FOREIGN KEY (`Category_name_id`)
    REFERENCES `diabetesdb`.`category_name` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Categories_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diabetesdb`.`insulindose`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`insulindose` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `quantity` FLOAT NULL DEFAULT NULL,
  `taken` TINYINT(1) NULL DEFAULT '0',
  `date_time` DATETIME NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_InsulinDose_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_InsulinDose_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diabetesdb`.`meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`meal` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `date_time` DATETIME NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Meal_Users_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Meal_Users`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diabetesdb`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diabetesdb`.`messages` (
  `id` INT(11) NOT NULL,
  `text` TEXT NULL DEFAULT NULL,
  `date_time` DATETIME NULL DEFAULT NULL,
  `Users_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Messages_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Messages_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `diabetesdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
