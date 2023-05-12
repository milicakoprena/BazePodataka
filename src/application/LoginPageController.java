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
import javafx.stage.Stage;

public class LoginPageController implements Initializable{
	private int idKorisnik;
	
	 public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	@FXML
	private ImageView imageView_Logo;
	@FXML
	private Button button_GoToLogin;
	@FXML
	private ImageView imageView_Background;
	@FXML
	private Button button_Previous;
    @FXML
	private Button button_GoToRestoran;
    @FXML
    private TextField field_KorisnickoIme;

    @FXML
    private PasswordField field_Lozinka;

	// Event Listener on Button[#button_Previous].onAction
	@FXML
	public void goToStartPage(ActionEvent event) {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ImageView img = new ImageView("/resources/previous.png");
		img.setFitWidth(40);
		img.setFitHeight(40);
		button_Previous.setGraphic(img);
	}
	
	@FXML
    void goToRestoranPage(ActionEvent event) {
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "select idKorisnik from foodie.korisnik where KorisnickoIme=? and Lozinka=?";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			ps.setString(1, field_KorisnickoIme.getText());
			ps.setString(2, field_Lozinka.getText());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				this.idKorisnik = rs.getInt(1);
				success();
			}
			else {
				fail();
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
		
	}
	
	void success() {
		try {
 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RestoranPage.fxml")); 
 			
 	        Stage stage = new Stage();
 	        stage.initOwner(button_GoToRestoran.getScene().getWindow());
 	        stage.setScene(new Scene((Parent) loader.load()));
 	        button_GoToRestoran.getScene().getWindow().hide();
 	        RestoranPageController controller = loader.getController();
 	        controller.setIdKorisnik(idKorisnik);
 	        stage.showAndWait();

 	        
 		} catch(Exception ex) {
 			ex.printStackTrace();
 		}

	}
	
	void fail() {
		try {
			Stage fail = new Stage();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginFailPage.fxml"));
			Scene scene = new Scene(root);
			
			fail.setScene(scene);
			fail.show();
			fail.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
