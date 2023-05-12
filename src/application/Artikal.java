package application;

public class Artikal {
	private int idArtikal;
	private String naziv;
	private double cijena;
	private int idVrsta;
	private int idKategorija;
	private int idRestoran;
	
	public Artikal(int idArtikal, String naziv, double cijena, int idVrsta, int idKategorija, int idRestoran) {
		super();
		this.idArtikal = idArtikal;
		this.naziv = naziv;
		this.cijena = cijena;
		this.idVrsta = idVrsta;
		this.idKategorija = idKategorija;
		this.idRestoran = idRestoran;
	}

	public int getIdArtikal() {
		return idArtikal;
	}

	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public int getIdVrsta() {
		return idVrsta;
	}

	public void setIdVrsta(int idVrsta) {
		this.idVrsta = idVrsta;
	}

	public int getIdKategorija() {
		return idKategorija;
	}

	public void setIdKategorija(int idKategorija) {
		this.idKategorija = idKategorija;
	}

	public int getIdRestoran() {
		return idRestoran;
	}

	public void setIdRestoran(int idRestoran) {
		this.idRestoran = idRestoran;
	}
	
	
}
