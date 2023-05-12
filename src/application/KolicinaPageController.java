package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectionPool;
import javafx.event.ActionEvent;

public class KolicinaPageController implements Initializable{
	@FXML
	private Button button_OK;
    @FXML
    private Label label_ID;
	@FXML
	private MenuButton menuButton;
	private Stavka stavka;

	public Stavka getStavka() {
		return stavka;
	}
	
	public void postaviID(Artikal ar) {
		label_ID.setText(String.valueOf(ar.getIdArtikal()));
	}
	// Event Listener on Button[#button_OK].onAction
	@FXML
	public void goToMeniPage(ActionEvent event) {
		stavka = new Stavka();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "select * from foodie.artikal where idArtikal=?";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(label_ID.getText()));
			rs = ps.executeQuery();
			if(rs.next()) {
				stavka.setIdArtikal(rs.getInt(1));
				stavka.setKolicina(Byte.parseByte(menuButton.getText()));
				stavka.setUkupnaCijena(rs.getDouble(3) * Byte.parseByte(menuButton.getText()));
				
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
		
		button_OK.getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int i = 1; i <= 5; i++) {
			MenuItem temp = new MenuItem();
			temp.setText(String.valueOf(i));
			menuButton.getItems().add(temp);
			temp.setOnAction(e -> {
    			menuButton.setText(temp.getText());
    			
			});
		}
	}
	
	
}
