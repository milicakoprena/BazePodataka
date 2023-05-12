package application;

import java.sql.Date;

public class Recenzija {
	private int idRecenzija;
	private byte ocjena;
	private String komentar;
	private Date datumObjave;
	private int idKorisnik;
	private int idRestoran;
	
	public Recenzija(int idRecenzija, byte ocjena, String komentar, Date datumObjave, int idKorisnik, int idRestoran) {
		super();
		this.idRecenzija = idRecenzija;
		this.ocjena = ocjena;
		this.komentar = komentar;
		this.datumObjave = datumObjave;
		this.idKorisnik = idKorisnik;
		this.idRestoran = idRestoran;
	}

	public int getIdRecenzija() {
		return idRecenzija;
	}

	public void setIdRecenzija(int idRecenzija) {
		this.idRecenzija = idRecenzija;
	}

	public byte getOcjena() {
		return ocjena;
	}

	public void setOcjena(byte ocjena) {
		this.ocjena = ocjena;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Date getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(Date datumObjave) {
		this.datumObjave = datumObjave;
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public int getIdRestoran() {
		return idRestoran;
	}

	public void setIdRestoran(int idRestoran) {
		this.idRestoran = idRestoran;
	}
	
	
}
