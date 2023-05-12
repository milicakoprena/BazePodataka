package application;

public class Restoran {
	private int idRestoran;
	private String naziv;
	private String brojTelefona;
	private int id_Adresa;
	
	public Restoran(int idRestoran, String naziv, String brojTelefona, int id_Adresa) {
		super();
		this.idRestoran = idRestoran;
		this.naziv = naziv;
		this.brojTelefona = brojTelefona;
		this.id_Adresa = id_Adresa;
	}
	
	public int getIdRestoran() {
		return idRestoran;
	}
	public void setIdRestoran(int idRestoran) {
		this.idRestoran = idRestoran;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public int getId_Adresa() {
		return id_Adresa;
	}
	public void setId_Adresa(int id_Adresa) {
		this.id_Adresa = id_Adresa;
	}
}
