SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `foodie` DEFAULT CHARACTER SET utf8 ;
USE `foodie` ;

CREATE TABLE IF NOT EXISTS `foodie`.`Adresa` (
  `idAdresa` INT NOT NULL AUTO_INCREMENT,
  `NazivUlice` VARCHAR(60) NOT NULL,
  `BrojUlice` SMALLINT(3) NOT NULL,
  `NazivGrada` VARCHAR(30) NOT NULL,
  `BrojSprata` TINYINT(2) NULL,
  `BrojStana` TINYINT(2) NULL,
  PRIMARY KEY (`idAdresa`))
ENGINE = InnoDB;

INSERT INTO adresa(NazivUlice, BrojUlice, NazivGrada, BrojSprata, BrojStana) values('Bana Milosavljevića', '30', 'Banja Luka', null, null);
INSERT INTO adresa(NazivUlice, BrojUlice, NazivGrada, BrojSprata, BrojStana) values('Save Kovačevića', '48', 'Banja Luka', null, null);
INSERT INTO adresa(NazivUlice, BrojUlice, NazivGrada, BrojSprata, BrojStana) values('Sime Šolaje', '1', 'Banja Luka', null, null);

CREATE TABLE IF NOT EXISTS `foodie`.`Restoran` (
  `idRestoran` INT NOT NULL,
  `Naziv` VARCHAR(50) NOT NULL,
  `BrojTelefona` VARCHAR(20) NOT NULL,
  `Adresa_idAdresa` INT NOT NULL,
  PRIMARY KEY (`idRestoran`),
  CONSTRAINT `fk_Restoran_Adresa1`
    FOREIGN KEY (`Adresa_idAdresa`)
    REFERENCES `foodie`.`Adresa` (`idAdresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO restoran(idRestoran, Naziv, BrojTelefona, Adresa_idAdresa) values('111', 'Marcello', '051/349-700', '1');
INSERT INTO restoran(idRestoran, Naziv, BrojTelefona, Adresa_idAdresa) values('112', 'Plan B', '051/253-373', '2');
INSERT INTO restoran(idRestoran, Naziv, BrojTelefona, Adresa_idAdresa) values('113', 'Kodiak Burger & Steak Bar', '066/828-080', '3');


CREATE TABLE IF NOT EXISTS `foodie`.`VrstaHrane` (
  `idVrstaHrane` INT NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVrstaHrane`))
ENGINE = InnoDB;

INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('1', 'Burger');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('2', 'Pica');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('3', 'Piletina');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('4', 'Svinjetina');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('5', 'Teletina');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('6', 'Riba');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('7', 'Povrće');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('8', 'Jaja');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('9', 'Čorbe');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('10', 'Tijesto');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('11', 'Voće');
INSERT INTO vrstaHrane(idVrstaHrane, Naziv) values('12', 'Čokolada');


CREATE TABLE IF NOT EXISTS `foodie`.`KategorijaHrane` (
  `idKategorijaHrane` INT NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idKategorijaHrane`))
ENGINE = InnoDB;

INSERT INTO kategorijaHrane(idKategorijaHrane, Naziv) values('1', 'Doručak');
INSERT INTO kategorijaHrane(idKategorijaHrane, Naziv) values('2', 'Glavno jelo');
INSERT INTO kategorijaHrane(idKategorijaHrane, Naziv) values('3', 'Dezert');
INSERT INTO kategorijaHrane(idKategorijaHrane, Naziv) values('4', 'Predjelo');
INSERT INTO kategorijaHrane(idKategorijaHrane, Naziv) values('5', 'Prilozi');


CREATE TABLE IF NOT EXISTS `foodie`.`Artikal` (
  `idArtikal` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Cijena` DECIMAL(4,2) NOT NULL,
  `VrstaHrane_idVrstaHrane` INT NOT NULL,
  `KategorijaHrane_idKategorijaHrane` INT NOT NULL,
  `Restoran_idRestoran` INT NOT NULL,
  PRIMARY KEY (`idArtikal`),
  CONSTRAINT `fk_Artikal_VrstaHrane1`
    FOREIGN KEY (`VrstaHrane_idVrstaHrane`)
    REFERENCES `foodie`.`VrstaHrane` (`idVrstaHrane`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_KategorijaHrane1`
    FOREIGN KEY (`KategorijaHrane_idKategorijaHrane`)
    REFERENCES `foodie`.`KategorijaHrane` (`idKategorijaHrane`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_Restoran1`
    FOREIGN KEY (`Restoran_idRestoran`)
    REFERENCES `foodie`.`Restoran` (`idRestoran`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Marcello omlet', '8.8', '8', '1', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Kajgana sa kranjskom kobasicom', '7.5', '8', '1', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Krem čorba od povrća', '6.3', '9', '4', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Domaći uštipci sa heljdom', '7.5', '10', '4', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Pohovane pileće krpice', '16.9', '3', '2', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Ramstek Marcello', '30.6', '5', '2', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Bečka teleća šnicla', '22.5', '5', '2', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Kaprićoza', '13.1', '2', '2', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Svinjska karadjordjeva šnicla', '21.3', '4', '2', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Pastrmka na žaru', '18.8', '6', '2', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Sufle', '8.8', '10', '3', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Slatke palačinke', '6.3', '10', '3', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Grilovano povrće', '4.1', '7', '5', '111');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Pekarski krompir', '3.4', '7', '5', '111');

INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Jaja sa slaninom', '9.5', '8', '1', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Tortilja s piletinom', '11.5', '3', '1', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Marinirani pileći file', '15.3', '3', '2', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Piletina u kornfleksu', '16.5', '3', '2', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Biftek na žaru', '31.5', '5', '2', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Biftek turnedo', '35.3', '5', '2', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Karadjordjeva šnicla', '20.5', '4', '2', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Teleći kotleti', '18.0', '5', '2', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Voćna salata', '8.0', '11', '3', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Plan B torta', '9.0', '12', '3', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Madjarska čorba', '8.5', '9', '4', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Domaća supa', '7.5', '9', '4', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Šopska salata', '6.0', '7', '5', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Grčka salata', '6.0', '7', '5', '112');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Pomfrit', '4.0', '7', '5', '112');

INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Biftek u fefer sosu', '36.5', '5', '2', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Biftek natur', '35.5', '5', '2', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Klasik burger', '15.0', '1', '2', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Kodiak burger', '16.5', '1', '2', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Brusketi s pršutom', '10.3', '4', '4', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Pasta karbonara', '16.5', '10', '2', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Pekarski krompir', '5.5', '7', '5', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Slanina', '6.5', '4', '5', '113');
INSERT INTO artikal(Naziv, Cijena, VrstaHrane_idVrstaHrane, KategorijaHrane_idKategorijaHrane, Restoran_idRestoran) values('Sladoled od čokolade', '5.5', '12', '3', '113');


CREATE TABLE IF NOT EXISTS `foodie`.`Korisnik` (
  `idKorisnik` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(50) NOT NULL,
  `Lozinka` VARCHAR(40) NOT NULL,
  `BrojTelefona` VARCHAR(20) NOT NULL,
  `Adresa_idAdresa` INT NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  CONSTRAINT `fk_Korisnik_Adresa1`
    FOREIGN KEY (`Adresa_idAdresa`)
    REFERENCES `foodie`.`Adresa` (`idAdresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;





CREATE TABLE IF NOT EXISTS `foodie`.`Recenzija` (
  `idRecenzija` INT NOT NULL AUTO_INCREMENT,
  `Ocjena` TINYINT(2) NOT NULL,
  `Komentar` VARCHAR(255) NULL,
  `DatumObjave` DATE NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  `Restoran_idRestoran` INT NOT NULL,
  PRIMARY KEY (`idRecenzija`),
  CONSTRAINT `fk_Recenzija_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `foodie`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recenzija_Restoran1`
    FOREIGN KEY (`Restoran_idRestoran`)
    REFERENCES `foodie`.`Restoran` (`idRestoran`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




CREATE TABLE IF NOT EXISTS `foodie`.`StatusNarudzbe` (
  `idStatusNarudzbe` INT NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idStatusNarudzbe`))
ENGINE = InnoDB;

INSERT INTO statusNarudzbe(idStatusNarudzbe, Status) values('1', 'Narudžba primljena');
INSERT INTO statusNarudzbe(idStatusNarudzbe, Status) values('2', 'Narudžba u obradi');
INSERT INTO statusNarudzbe(idStatusNarudzbe, Status) values('3', 'Hrana u pripremi');
INSERT INTO statusNarudzbe(idStatusNarudzbe, Status) values('4', 'Hrana pripremljena');


CREATE TABLE IF NOT EXISTS `foodie`.`Narudzba` (
  `idNarudzba` INT NOT NULL AUTO_INCREMENT,
  `VrijemeNarucivanja` DATETIME NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  `StatusNarudzbe_idStatusNarudzbe` INT NOT NULL,
  `UkupnaCijena` DECIMAL(5,2) NOT NULL,
  `Adresa_idAdresa` INT NOT NULL,
  PRIMARY KEY (`idNarudzba`),
  CONSTRAINT `fk_Narudzba_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `foodie`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_StatusNarudzbe1`
    FOREIGN KEY (`StatusNarudzbe_idStatusNarudzbe`)
    REFERENCES `foodie`.`StatusNarudzbe` (`idStatusNarudzbe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_Adresa1`
    FOREIGN KEY (`Adresa_idAdresa`)
    REFERENCES `foodie`.`Adresa` (`idAdresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `foodie`.`Dostavljac` (
  `idDostavljac` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(50) NOT NULL,
  `Lozinka` VARCHAR(40) NOT NULL,
  `BrojTelefona` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idDostavljac`) )
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `foodie`.`Dostava` (
  `idDostava` INT NOT NULL AUTO_INCREMENT,
  `Dostavljac_idDostavljac` INT NOT NULL,
  `VrijemePreuzimanjaNarudzbe` DATETIME NOT NULL,
  `VrijemeDostave` DATETIME NOT NULL,
  `UkupnaCijena` DECIMAL(5,2) NOT NULL,
  `Adresa_idAdresa` INT NOT NULL,
  `StatusDostave_idStatusDostave` INT NOT NULL,
  PRIMARY KEY (`idDostava`),
  CONSTRAINT `fk_Dostava_Dostavljac1`
    FOREIGN KEY (`Dostavljac_idDostavljac`)
    REFERENCES `foodie`.`Dostavljac` (`idDostavljac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dostava_StatusDostave1`
    FOREIGN KEY (`StatusDostave_idStatusDostave`)
    REFERENCES `foodie`.`StatusDostave` (`idStatusDostave`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dostava_Adresa1`
    FOREIGN KEY (`Adresa_idAdresa`)
    REFERENCES `foodie`.`Adresa` (`idAdresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `foodie`.`StatusDostave` (
  `idStatusDostave` INT NOT NULL,
  `Status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idStatusDostave`))
ENGINE = InnoDB;

INSERT INTO statusDostave(idStatusDostave, Status) values('1', 'Narudžbu preuzeo dostavljač');
INSERT INTO statusDostave(idStatusDostave, Status) values('2', 'Dostavljač na putu');
INSERT INTO statusDostave(idStatusDostave, Status) values('3', 'Korisnik preuzeo hranu');


CREATE TABLE IF NOT EXISTS `foodie`.`Stavka` (
  `idStavka` INT NOT NULL AUTO_INCREMENT,
  `Kolicina` TINYINT(2) NOT NULL,
  `UkupnaCijena` DECIMAL(4,2) NOT NULL,
  `Artikal_idArtikal` INT NOT NULL,
  `Narudzba_idNarudzba` INT NOT NULL,
  PRIMARY KEY (`idStavka`),
  CONSTRAINT `fk_Stavka_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `foodie`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stavka_Narudzba1`
    FOREIGN KEY (`Narudzba_idNarudzba`)
    REFERENCES `foodie`.`Narudzba` (`idNarudzba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
