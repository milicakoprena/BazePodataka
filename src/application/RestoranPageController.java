package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import connection.ConnectionPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RestoranPageController implements Initializable{

	private int idKorisnik;
	
	 public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}


	private ObservableList<Restoran> restorani;
	@FXML
	private ImageView imageView_Logo;
	@FXML
	private ImageView imageView_Background;
	@FXML
	private Button button_Previous;
	@FXML
	private ListView<Restoran> listView_Restorani;
	@FXML
	private Button button_Next;
	
	
	private class RestoranCell extends ListCell<Restoran>{
		@FXML
	    private Label label;
		@FXML
	    private AnchorPane pane;
	    @FXML
	    private ImageView next;
	    @FXML
	    private Button button;
	    @Override
	    protected void updateItem(Restoran restoran, boolean empty) {
	    	super.updateItem(restoran, empty);
	    	
	    	if(empty || restoran == null) {

	            setText(null);
	            setGraphic(null);

	        }
	    	
	    	else {
	    		FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/application/cell/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                	mLLoader.load();
                } 
                catch (IOException e) {
                e.printStackTrace();
                }

    	    	label.setText(String.valueOf(restoran.getNaziv()));
    	    	ImageView next = new ImageView("/application/cell/nextbrown.png");
    	    	next.setFitWidth(30);
    	    	next.setFitHeight(30);
    	    	button.setGraphic(next);
    	    	button.setOnAction(e -> {
        			try {
        				 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MeniPage.fxml"));
        			        Parent root = loader.load();
        			        MeniPageController meniPageController = loader.getController();
        			        meniPageController.setFields(restoran);
        			        meniPageController.setIdKorisnik(idKorisnik);
        			        
        			        button.getScene().setRoot(root);
        				
        			} catch(Exception ex) {
        				ex.printStackTrace();
        			}
                });
    	    	setText(null);
    	    	setGraphic(pane);
            }
	    	
	    }
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		restorani = FXCollections.observableArrayList();
		String query = "select * from foodie.restoran";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Restoran temp = new Restoran(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				restorani.add(temp);
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
		
		listView_Restorani.setItems(restorani);
		listView_Restorani.setCellFactory(restoranListView -> new RestoranCell());

	}
	
	
}

	

