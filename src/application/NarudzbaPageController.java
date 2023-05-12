package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import connection.ConnectionPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class NarudzbaPageController implements Initializable{
	@FXML
    private Button button_OK;

    
	private ObservableList<Stavka> stavke;
	public ObservableList<Stavka> getStavke() {
		return stavke;
	}
	public void setStavke(ObservableList<Stavka> stavke) {
		this.stavke = FXCollections.observableArrayList();
		 for(Stavka temp : stavke)
			 this.stavke.add(temp);
	}
	
	private int idKorisnik;
	
	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	
	private int idAdresa;
	
	public int getIdAdresa() {
		return idAdresa;
	}
	public void setIdAdresa(int idAdresa) {
		this.idAdresa = idAdresa;
	}
	
	public void dodajStavke(int idNarudzba) {
		Connection c = null;
		PreparedStatement ps = null;
		String query = "insert into foodie.stavka(Kolicina, UkupnaCijena, Artikal_idArtikal, Narudzba_idNarudzba) values(?, ?, ?, ?)";
		try {
			c = ConnectionPool.getInstance().checkOut();
			for(Stavka stavka : stavke) {
			   ps = c.prepareStatement(query);
			   ps.setByte(1, stavka.getKolicina());
			   ps.setDouble(2, stavka.getUkupnaCijena());
			   ps.setInt(3, stavka.getIdArtikal());
			   ps.setInt(4, idNarudzba);
			   
			   ps.executeUpdate();
			   ps.close();
			}
		}
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	 
		catch(Exception ex) {
			ex.printStackTrace();
		}
	 
		finally {
			
			if(c!=null) {
				ConnectionPool.getInstance().checkIn(c);
			}
			
		}
		
		
	}
	
	@FXML
    void zavrsi(ActionEvent event) {
		double ukupno = 0;
		for(Stavka stavka : stavke) {
			ukupno += stavka.getUkupnaCijena();
		}
		
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query1 = "insert into foodie.narudzba(VrijemeNarucivanja, Korisnik_idKorisnik, StatusNarudzbe_idStatusNarudzbe, UkupnaCijena, "
				+ "Adresa_idAdresa) values(?, ?, ?, ?, ?)";
		String query2 = "select idNarudzba from foodie.narudzba";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query1);
			Calendar cal = Calendar.getInstance();  
		
			ps.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
			ps.setInt(2,  idKorisnik);
			ps.setInt(3, 1);
			ps.setDouble(4, ukupno);
			ps.setInt(5,  idAdresa);
			ps.executeUpdate();
			
			ps = c.prepareStatement(query2);
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()) i++;
			rs.close();
			dodajStavke(i);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	 
		catch(Exception ex) {
			ex.printStackTrace();
		}
	 
		finally {
			
			if(c!=null) {
				ConnectionPool.getInstance().checkIn(c);
			}
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		button_OK.getScene().getWindow().hide();
		
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	

}
