package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartPageController {
	@FXML
	private ImageView imageView_Logo;
	@FXML
	private Button button_GoToLogin;
	@FXML
	private ImageView imageView_Background;
	@FXML
	private Button button_GoToRegistracija;

	// Event Listener on Button[#button_GoToLogin].onAction
	@FXML
	public void goToLoginPage(ActionEvent event) {
		try {
			button_GoToLogin.getScene().getWindow().hide();
			Stage login = new Stage();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
			Scene scene = new Scene(root);
			
			login.setScene(scene);
			login.show();
			login.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToRegistracijaPage(ActionEvent event) {
		try {
			button_GoToRegistracija.getScene().getWindow().hide();
			Stage registracija = new Stage();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RegistracijaPage.fxml"));
			Scene scene = new Scene(root);
			
			registracija.setScene(scene);
			registracija.show();
			registracija.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
