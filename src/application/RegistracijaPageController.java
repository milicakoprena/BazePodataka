package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import java.util.ResourceBundle;

import connection.ConnectionPool;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegistracijaPageController implements Initializable{
	private int idKorisnik;
	
	 public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}


	@FXML
	    private Button button_GoToAdresa;

	    @FXML
	    private Button button_Previous;

	    @FXML
	    private Button button_goToRestoran;

	    @FXML
	    private TextField field_Broj;

	    @FXML
	    private TextField field_BrojSprata;

	    @FXML
	    private TextField field_BrojStana;

	    @FXML
	    private TextField field_BrojUlice;

	    @FXML
	    private TextField field_ImePrezime;

	    @FXML
	    private TextField field_KorisnickoIme;

	    @FXML
	    private PasswordField field_Lozinka;

	    @FXML
	    private TextField field_NazivGrada;

	    @FXML
	    private TextField field_NazivUlice;

	    @FXML
	    private ImageView imageView_Background;

	    @FXML
	    private ImageView imageView_Background1;

	    @FXML
	    private ImageView imageView_Logo;

	    @FXML
	    private AnchorPane pane1;

	    @FXML
	    private AnchorPane pane2;
	// Event Listener on Button[#button_GoToAdresa].onAction
	@FXML
	public void goToAdresaPage(ActionEvent event) {
		pane1.setVisible(false);
		pane2.setVisible(true);
	}
	
	@FXML
    void goToStartPage(ActionEvent event) {
		try {
			button_Previous.getScene().getWindow().hide();
			Stage start = new Stage();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StartPage.fxml"));
			Scene scene = new Scene(root);
			
			start.setScene(scene);
			start.show();
			start.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	 @FXML
	 void goToRestoranPage(ActionEvent event) {
		
		 String imeprezime[] = field_ImePrezime.getText().split(" ");
		 
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
			 
			 
			 
			 String query = "select idAdresa from foodie.adresa";
			 
			 ps = c.prepareStatement(query);
			
			 rs = ps.executeQuery();
			 int idAdresa = 0;
			 while(rs.next()) {
				 idAdresa++;
			 }
			 rs.close();	 
				 
		     ps=c.prepareStatement("insert into foodie.korisnik(Ime, Prezime, KorisnickoIme, Lozinka, BrojTelefona, Adresa_idAdresa) values (?, ?, ?, ?, ?, ?)");
		 			
		 	 ps.setString(1, imeprezime[0]);
		 	 ps.setString(2, imeprezime[1]);
		 	 ps.setString(3, field_KorisnickoIme.getText());
		 	 ps.setString(4, field_Lozinka.getText());
		 	 ps.setString(5, field_Broj.getText());
		 	 ps.setInt(6, idAdresa);
		 	 ps.executeUpdate();
			 
						
 			
 			 ps.close();
 			 
 			 ps = c.prepareStatement("select idKorisnik from foodie.korisnik");
 			 rs = ps.executeQuery();
 			 int i = 0;
 			 while(rs.next()) i++;
 			 this.idKorisnik = i;
 			 
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
		 
		 
		 try {
	 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RestoranPage.fxml")); 
	 			
	 	        Stage stage = new Stage();
	 	        stage.initOwner(button_goToRestoran.getScene().getWindow());
	 	        button_goToRestoran.getScene().getWindow().hide();
	 	        stage.setScene(new Scene((Parent) loader.load()));
	 	        RestoranPageController controller = loader.getController();
	 	        controller.setIdKorisnik(idKorisnik);
	 	        stage.showAndWait();

	 	        
	 		} catch(Exception ex) {
	 			ex.printStackTrace();
	 		}
	 }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ImageView img = new ImageView("/resources/previous.png");
		img.setFitWidth(40);
		img.setFitHeight(40);
		button_Previous.setGraphic(img);
	}

}
