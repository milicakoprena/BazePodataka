package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionPool;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RestoranInfoController {
	private int idKorisnik;
	
	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	@FXML
	private ImageView imageView_Background;
	@FXML
	private Button buttonRecenzije;
	 @FXML
	 private Label label_BrojTelefona;

	 @FXML
	 private Label label_Grad;

	 @FXML
	 private Label label_NazivUlice;
	 @FXML
	 private Label label_Ime;
	 @FXML
	 private Label label_BrojUlice;

	// Event Listener on Button[#buttonRecenzije].onAction
	@FXML
	public void goToPregledRecenzija(ActionEvent event) {
		Restoran restoran = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "select * from foodie.restoran where Naziv=?";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			ps.setString(1, label_Ime.getText());
			rs = ps.executeQuery();
			if(rs.next()) {
				restoran = new Restoran(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	 
	 catch(Exception ex) {
			ex.printStackTrace();
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PregledRecenzija.fxml")); 
			
	        Stage stage = new Stage();
	        stage.initOwner(buttonRecenzije.getScene().getWindow());
	        stage.setScene(new Scene((Parent) loader.load()));
	        PregledRecenzijaController controller = loader.getController();
	        controller.setImeRestorana(restoran);
	        controller.setIdKorisnik(idKorisnik);
	        stage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInfoFields(Restoran restoran) {
		label_Ime.setText(restoran.getNaziv());
		label_BrojTelefona.setText(restoran.getBrojTelefona());
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "select * from foodie.adresa where idAdresa=?";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			ps.setInt(1, restoran.getId_Adresa());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				label_NazivUlice.setText(rs.getString(2));
				label_BrojUlice.setText(String.valueOf(rs.getShort(3)));
				label_Grad.setText(rs.getString(4));
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
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
