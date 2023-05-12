DELIMITER $$
USE `foodie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prikaziArtiklePoKategorijama`(in idKategorija int , in idRestoran int)
BEGIN
	select * from artikal where KategorijaHrane_idKategorijaHrane=idKategorija and Restoran_idRestoran=idRestoran;
END$$

DELIMITER ;
;

DELIMITER $$
USE `foodie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prikaziArtiklePoVrstama`(in idVrsta int , in idRestoran int)
BEGIN
	select * from artikal where VrstaHrane_idVrstaHrane=idVrsta and Restoran_idRestoran=idRestoran;
END$$

DELIMITER ;
;

DELIMITER $$
USE `foodie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prikaziRecenzijeRestorana`(in idRestoran int)
BEGIN
	select * from recenzija where Restoran_idRestoran=idRestoran;
END$$

DELIMITER ;
;

DELIMITER $$
USE `foodie`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prikaziMojeRecenzijeRestorana`(in idRestoran int, in idKorisnik int)
BEGIN
	select * from recenzija where Restoran_idRestoran=idRestoran and Korisnik_idKorisnik=idKorisnik;
END$$

DELIMITER ;
;