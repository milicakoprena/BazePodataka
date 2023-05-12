package application;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionPool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;

public class RecenzijaPageController {
	private int idKorisnik;
	
	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	@FXML
    private Button button_Postavi;
	@FXML
	private ImageView imageView_Background;
	@FXML
	private Label label_Ime;
	@FXML
	private TextField field_Ocjena;
	@FXML
	private TextArea field_Komentar;
	
	
	public void setIme(Restoran restoran) {
		label_Ime.setText(restoran.getNaziv());
	}

	@FXML
    void postaviRecenziju(ActionEvent event) {
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "insert into foodie.recenzija(Ocjena, Komentar, DatumObjave, Korisnik_idKorisnik, Restoran_idRestoran) values(?, ?, ?, ?, ?)";
		try {
			c = ConnectionPool.getInstance().checkOut();
			
			ps = c.prepareStatement("select idRestoran from foodie.Restoran where Naziv=?");
			ps.setString(1, label_Ime.getText());
			rs = ps.executeQuery();
			int idRestoran = 0;;
			if(rs.next()) {
				idRestoran=rs.getInt(1);
			}
			rs.close();
			
			if(idRestoran != 0 && idKorisnik!=0)
			{	ps = c.prepareStatement(query);
				ps.setByte(1, Byte.parseByte(field_Ocjena.getText()));
				ps.setString(2, field_Komentar.getText());
				ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
				ps.setInt(4, idKorisnik);
				ps.setInt(5, idRestoran);
				ps.executeUpdate();
	     	}
			
			ps.close();
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
		button_Postavi.getScene().getWindow().hide();
    }

}
