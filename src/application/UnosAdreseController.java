package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UnosAdreseController {
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
	    private Button button_OK;
	 private Adresa adresa;
	 

	 public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	@FXML	
	 private TextField field_BrojSprata;

	 @FXML
	 private TextField field_BrojStana;
	 
	 @FXML
	 private TextField field_BrojUlice;

	 @FXML
	 private TextField field_NazivGrada;

	 @FXML
	 private TextField field_NazivUlice;

	 @FXML
	 private ImageView imageView_Background;

	// Event Listener on Button[#button_OK].onAction
	 @FXML
	 public void goToNarudzbaPage(ActionEvent event) {

		 
		 Connection c = null;
		 ResultSet rs = null;
		 PreparedStatement ps = null;
		 try {
			c = ConnectionPool.getInstance().checkOut();
			 ps=c.prepareStatement("insert into foodie.adresa(NazivUlice, BrojUlice, NazivGrada, BrojSprata, BrojStana) values (?, ?, ?, ?, ?)");
				
			 ps.setString(1, field_NazivUlice.getText());
			 ps.setShort(2,  (short) Integer.parseInt(field_BrojUlice.getText()));
			 ps.setString(3, field_NazivGrada.getText());
			 if(!field_BrojSprata.getText().equals(""))
				 ps.setByte(4,  (byte) Integer.parseInt(field_BrojSprata.getText()));
			 else ps.setByte(4, (byte)0);
			 if(!field_BrojStana.getText().equals(""))
			 ps.setByte(5,  (byte) Integer.parseInt(field_BrojStana.getText()));
			 else ps.setByte(5, (byte)0);
			 ps.executeUpdate();
			
			 ps.close();
			 
			 ps = c.prepareStatement("select idAdresa from foodie.adresa");
			 rs = ps.executeQuery();
			 
			 int i = 0;
			 while(rs.next()) i++;
			 
			 adresa = new Adresa();
			 adresa.setIdAdresa(i);
			 adresa.setNazivUlice(field_NazivUlice.getText());
			 adresa.setBrojUlice((short) Integer.parseInt(field_BrojUlice.getText()));
			 adresa.setNazivGrada(field_NazivGrada.getText());
			 if(!field_BrojSprata.getText().equals(""))
				 adresa.setBrojSprata((byte) Integer.parseInt(field_BrojSprata.getText()));
			 else adresa.setBrojSprata((byte)0);
			 if(!field_BrojStana.getText().equals(""))
				 adresa.setBrojStana((byte) Integer.parseInt(field_BrojStana.getText()));
			 else adresa.setBrojStana((byte)0);
			 
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
		 
		 try {
	 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("NarudzbaPage.fxml")); 
	 			
	 	        Stage stage = new Stage();
	 	        stage.initOwner(button_OK.getScene().getWindow());
	 	        stage.setScene(new Scene((Parent) loader.load()));
	 	        NarudzbaPageController controller = loader.getController();
	 	        controller.setIdKorisnik(idKorisnik);
	 	        controller.setStavke(stavke);
	 	        controller.setIdAdresa(adresa.getIdAdresa());
	 	        stage.showAndWait();

	 	        
	 		} catch(Exception ex) {
	 			ex.printStackTrace();
	 		}
	 }
}
