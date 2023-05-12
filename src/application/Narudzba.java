package application;

import java.sql.Date;

public class Narudzba {
	private int idNarudzba;
	private Date vrijemeNarucivanja;
	private  int idStatus;
	private int ukupnaCijena;
	private Date VrijemeDostave;
	private int idAdresa;
	private int idOsoba;
	public Narudzba(int idNarudzba, Date vrijemeNarucivanja, int idStatus, int ukupnaCijena, Date vrijemeDostave,
			int idAdresa, int idOsoba) {
		super();
		this.idNarudzba = idNarudzba;
		this.vrijemeNarucivanja = vrijemeNarucivanja;
		this.idStatus = idStatus;
		this.ukupnaCijena = ukupnaCijena;
		VrijemeDostave = vrijemeDostave;
		this.idAdresa = idAdresa;
		this.idOsoba = idOsoba;
	}
	public Narudzba() {
		super();
	}
	public int getIdNarudzba() {
		return idNarudzba;
	}
	public void setIdNarudzba(int idNarudzba) {
		this.idNarudzba = idNarudzba;
	}
	public Date getVrijemeNarucivanja() {
		return vrijemeNarucivanja;
	}
	public void setVrijemeNarucivanja(Date vrijemeNarucivanja) {
		this.vrijemeNarucivanja = vrijemeNarucivanja;
	}
	public int getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	public int getUkupnaCijena() {
		return ukupnaCijena;
	}
	public void setUkupnaCijena(int ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}
	public Date getVrijemeDostave() {
		return VrijemeDostave;
	}
	public void setVrijemeDostave(Date vrijemeDostave) {
		VrijemeDostave = vrijemeDostave;
	}
	public int getIdAdresa() {
		return idAdresa;
	}
	public void setIdAdresa(int idAdresa) {
		this.idAdresa = idAdresa;
	}
	public int getIdOsoba() {
		return idOsoba;
	}
	public void setIdOsoba(int idOsoba) {
		this.idOsoba = idOsoba;
	}
	
	
	
}
