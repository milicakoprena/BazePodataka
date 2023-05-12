package application;

public class Stavka {
	private byte kolicina;
	private double ukupnaCijena;
	private int idArtikal;
	
	public Stavka() {
		super();
	}
	
	public Stavka(byte kolicina, double ukupnaCijena, int idArtikal) {
		super();
		this.kolicina = kolicina;
		this.ukupnaCijena = ukupnaCijena;
		this.idArtikal = idArtikal;
	}


	public byte getKolicina() {
		return kolicina;
	}

	public void setKolicina(byte kolicina) {
		this.kolicina = kolicina;
	}

	public double getUkupnaCijena() {
		return ukupnaCijena;
	}

	public void setUkupnaCijena(double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}

	public int getIdArtikal() {
		return idArtikal;
	}

	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}
	
	
}
