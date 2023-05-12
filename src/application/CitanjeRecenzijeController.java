package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionPool;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CitanjeRecenzijeController {

    @FXML
    private ImageView imageView_Background;

    @FXML
    private Label label_Datum;

    @FXML
    private Label label_ImeRestorana;

    @FXML
    private Text label_Komentar;

    @FXML
    private Label label_KorisnickoIme;

    @FXML
    private Label label_Ocjena;
    
    public void setFields(String r, Recenzija recenzija) {
    	Connection c = null;
    	ResultSet rs = null;
    	PreparedStatement ps = null;
    	String query = "select KorisnickoIme from foodie.korisnik where idKorisnik=?";
    	try {
    		c = ConnectionPool.getInstance().checkOut();
    		ps = c.prepareStatement(query);
    		ps.setInt(1, recenzija.getIdKorisnik());
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			label_ImeRestorana.setText(r);
    			label_KorisnickoIme.setText(rs.getString(1)); 
    			label_Ocjena.setText(String.valueOf(recenzija.getOcjena()) + "/10");
    			label_Datum.setText(String.valueOf(recenzija.getDatumObjave()));
    			label_Komentar.setText(recenzija.getKomentar());
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