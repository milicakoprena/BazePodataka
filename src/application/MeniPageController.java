package application;
import connection.ConnectionPool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class MeniPageController implements Initializable{
	private ObservableList<Artikal> artikli;
	private int idKorisnik;
	
	 public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	private static ObservableList<Stavka> stavke;
    @FXML
    private Button button_Info;

    @FXML
    private Button button_Korpa;

    @FXML
    private Button button_Recenzija;

    @FXML
    private ImageView imageView_Background;

    @FXML
    private Label label_ImeRestorana;

    @FXML
    private ListView<Artikal> listView_Kategorije;

    @FXML
    private ListView<Artikal> listView_Vrste;

    @FXML
    private MenuButton menuButton_Kategorije;

    @FXML
    private MenuButton menuButton_Vrste;
	
	public void setFields(Restoran restoran) {
		label_ImeRestorana.setText(restoran.getNaziv());
	}
	
	private class ArtikalCell extends ListCell<Artikal>{
		@FXML
	    private Label label;
		@FXML
	    private AnchorPane pane;
	    @FXML
	    private ImageView next;
	    @FXML
	    private Button button;
	    @FXML
	    private Label label_Cijena;
	    @Override
	    protected void updateItem(Artikal artikal, boolean empty) {
	    	super.updateItem(artikal, empty);
	    	
	    	if(empty || artikal == null) {

	            setText(null);
	            setGraphic(null);

	        }
	    	
	    	else {
	    		FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/application/cell/ListCellArtikal.fxml"));
                mLLoader.setController(this);

                try {
                	mLLoader.load();
                } 
                catch (IOException e) {
                e.printStackTrace();
                }

    	    	label.setText(String.valueOf(artikal.getNaziv()));
    	    	label_Cijena.setText(String.valueOf(artikal.getCijena()) + "KM");
    	    	ImageView next = new ImageView("/application/cell/add.png");
    	    	next.setFitWidth(30);
    	    	next.setFitHeight(30);
    	    	button.setGraphic(next);
    	    	button.setOnAction(e -> {
    	    		try {
    	    			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("KolicinaPage.fxml")); 
    	    			
    	    	        Stage stage = new Stage();
    	    	        stage.initOwner(button.getScene().getWindow());
    	    	        stage.setScene(new Scene((Parent) loader.load()));
    	    	        KolicinaPageController controller = loader.getController();
    	    	        controller.postaviID(artikal);
    	    	        stage.showAndWait();

    	    	        
    	    	        dodajUStavke(controller.getStavka());
    	    		} catch(Exception ex) {
    	    			ex.printStackTrace();
    	    		}
                });
    	    	setText(null);
    	    	setGraphic(pane);
            }
	    	
	    }
	}

	public void dodajUStavke(Stavka stavka) {
		stavke.add(stavka);
	}
	// Event Listener on Button[#button_Info].onAction
	@FXML
	public void goToRestoranInfo(ActionEvent event) {
		Restoran restoran = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "select * from foodie.restoran where Naziv=?";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			ps.setString(1, label_ImeRestorana.getText());
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
			Stage info = new Stage();
			
			
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RestoranInfo.fxml"));
			
		    Parent root = loader.load();
		    Scene scene = new Scene(root);
		    RestoranInfoController restoranInfoController = loader.getController();
		    restoranInfoController.setInfoFields(restoran);
		    restoranInfoController.setIdKorisnik(idKorisnik);
			info.setScene(scene);
			info.show();
			info.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#button_Recenzija].onAction
	@FXML
	public void goToRecenzijaPage(ActionEvent event) {
		Restoran restoran = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "select * from foodie.restoran where Naziv=?";
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query);
			ps.setString(1, label_ImeRestorana.getText());
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
			Stage rec = new Stage();
			
			
			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RecenzijaPage.fxml"));
			
		    Parent root = loader.load();
		    Scene scene = new Scene(root);
		    RecenzijaPageController recenzijaPageController = loader.getController();
		    recenzijaPageController.setIme(restoran);
		    recenzijaPageController.setIdKorisnik(idKorisnik);
			rec.setScene(scene);
			rec.show();
			rec.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prikazi(int idtemp, String query) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		artikli = FXCollections.observableArrayList();
		try {
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement("select idRestoran from foodie.Restoran where Naziv=?");
			ps.setString(1, label_ImeRestorana.getText());
			rs = ps.executeQuery();
			int idrestoran = 0;
			if(rs.next()) {
				idrestoran=rs.getInt(1);
			}
			rs.close();
			cs = c.prepareCall("call " + query + "(?, ?)");
			cs.setInt(1, idtemp);
			cs.setInt(2, idrestoran);
			rs = cs.executeQuery();
			while(rs.next()) {
				Artikal artikal = new Artikal(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				artikli.add(artikal);
			}
			rs.close();
			
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
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		stavke = FXCollections.observableArrayList();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			String query1 = "select * from foodie.kategorijahrane";
			String query2 = "select * from foodie.vrstahrane";
			c = ConnectionPool.getInstance().checkOut();
			ps = c.prepareStatement(query1);
			rs = ps.executeQuery();
			while(rs.next()) {
				MenuItem temp = new MenuItem();
				temp.setText(rs.getString(2));
				int idtemp = rs.getInt(1);
				menuButton_Kategorije.getItems().add(temp);
                temp.setOnAction(e -> {
                	listView_Kategorije.getItems().clear();
        			menuButton_Kategorije.setText(temp.getText());
        			
        				prikazi(idtemp, "prikaziArtiklePoKategorijama");
        				listView_Kategorije.setItems(artikli);
        				listView_Kategorije.setCellFactory(artikalListView -> new ArtikalCell());
					
                });
			}
			rs.close();
			
            ps = c.prepareStatement(query2);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			MenuItem temp = new MenuItem();
    			temp.setText(rs.getString(2));
    			int idtemp = rs.getInt(1);
    			menuButton_Vrste.getItems().add(temp);
    			temp.setOnAction(e -> {
    				listView_Vrste.getItems().clear();
        			menuButton_Vrste.setText(temp.getText());
        			
        				prikazi(idtemp, "prikaziArtiklePoVrstama");
        				listView_Vrste.setItems(artikli);
        				listView_Vrste.setCellFactory(artikalListView -> new ArtikalCell());
					
    			});
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
	
	 @FXML
	 void goToKorpaPage(ActionEvent event) {
		 
		 try {
 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("KorpaPage.fxml")); 
 			
 	        Stage stage = new Stage();
 	        stage.initOwner(button_Korpa.getScene().getWindow());
 	        stage.setScene(new Scene((Parent) loader.load()));
 	        KorpaPageController controller = loader.getController();
 	        controller.ucitajStavke(stavke, label_ImeRestorana.getText());
 	        controller.setIdKorisnik(idKorisnik);
 	        stage.showAndWait();

 	        
 		} catch(Exception ex) {
 			ex.printStackTrace();
 		}
	 }
	
}
