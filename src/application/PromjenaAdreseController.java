package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class PromjenaAdreseController {
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

	@FXML
	private Button button_DA;
	@FXML
	private Button button_NE;
	private Adresa adresa;

	// Event Listener on Button[#button_DA].onAction
	@FXML
	public void goToUnosAdrese(ActionEvent event) {
		try {
 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("UnosAdrese.fxml")); 
 			
 	        Stage stage = new Stage();
 	        stage.initOwner(button_DA.getScene().getWindow());
 	        stage.setScene(new Scene((Parent) loader.load()));
 	        UnosAdreseController controller = loader.getController();
 	        controller.setIdKorisnik(idKorisnik);
 	        controller.setStavke(stavke);
 	        stage.showAndWait();
 	       
 	        pokupiAdresu(controller.getAdresa());
 	        
 	        
 		} catch(Exception ex) {
 			ex.printStackTrace();
 		}
	}
	
	public void pokupiAdresu(Adresa adresa) {
		this.adresa = adresa;
	}
	// Event Listener on Button[#button_NE].onAction
	@FXML
	public void goToNarudzbaPage(ActionEvent event) {
		
		Connection c = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			String query = "select Adresa_idAdresa from foodie.korisnik where idKorisnik=?";
			try {
				c = ConnectionPool.getInstance().checkOut();
				ps = c.prepareStatement(query);
				ps.setInt(1, idKorisnik);
				rs = ps.executeQuery();
				if(rs.next()) {
					try {
			 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("NarudzbaPage.fxml")); 
			 			
			 	        Stage stage = new Stage();
			 	        stage.initOwner(button_NE.getScene().getWindow());
			 	        stage.setScene(new Scene((Parent) loader.load()));
			 	        NarudzbaPageController controller = loader.getController();
			 	        controller.setIdKorisnik(idKorisnik);
			 	        controller.setStavke(stavke);
			 	        controller.setIdAdresa(rs.getInt(1));
			 	        stage.showAndWait();
			 	        
			 	       

			 	        
			 		} catch(Exception ex) {
			 			ex.printStackTrace();
			 		}
				}
				rs.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		 
		 catch(Exception ex) {
				ex.printStackTrace();
			}
		
	}
}
