package application;

public class Adresa {
	private int idAdresa;
	private String nazivUlice;
	private short brojUlice;
	private String nazivGrada;
	private byte brojSprata;
	private byte brojStana;
	public Adresa(int idAdresa, String nazivUlice, short brojUlice, String nazivGrada, byte brojSprata,
			byte brojStana) {
		super();
		this.idAdresa = idAdresa;
		this.nazivUlice = nazivUlice;
		this.brojUlice = brojUlice;
		this.nazivGrada = nazivGrada;
		this.brojSprata = brojSprata;
		this.brojStana = brojStana;
	}
	public Adresa() {
		super();
	}
	public int getIdAdresa() {
		return idAdresa;
	}
	public void setIdAdresa(int idAdresa) {
		this.idAdresa = idAdresa;
	}
	public String getNazivUlice() {
		return nazivUlice;
	}
	public void setNazivUlice(String nazivUlice) {
		this.nazivUlice = nazivUlice;
	}
	public short getBrojUlice() {
		return brojUlice;
	}
	public void setBrojUlice(short brojUlice) {
		this.brojUlice = brojUlice;
	}
	public String getNazivGrada() {
		return nazivGrada;
	}
	public void setNazivGrada(String nazivGrada) {
		this.nazivGrada = nazivGrada;
	}
	public byte getBrojSprata() {
		return brojSprata;
	}
	public void setBrojSprata(byte brojSprata) {
		this.brojSprata = brojSprata;
	}
	public byte getBrojStana() {
		return brojStana;
	}
	public void setBrojStana(byte brojStana) {
		this.brojStana = brojStana;
	}
	
	
}
