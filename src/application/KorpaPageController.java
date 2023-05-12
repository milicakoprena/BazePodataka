package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectionPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class KorpaPageController implements Initializable{
	private int idKorisnik;
	
	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	 private ObservableList<Stavka> stavke;
	 @FXML
	 private Button button_Naruci;

	 @FXML
	 private ImageView imageView_Background;

	 @FXML
	 private Label label_ImeRestorana;

	 @FXML
	 private ListView<Stavka> listView_Stavke;
	 @FXML
	 private Button buttonPrikazi;
	 
	 private class StavkaCell extends ListCell<Stavka>{
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
		    protected void updateItem(Stavka stavka, boolean empty) {
		    	super.updateItem(stavka, empty);
		    	
		    	if(empty || stavka == null) {

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

	                Connection c = null;
	                ResultSet rs = null;
	                PreparedStatement ps = null;
	                String query = "select Naziv from foodie.artikal where idArtikal=?";
	                
	                try {
	        			c = ConnectionPool.getInstance().checkOut();
	        			ps = c.prepareStatement(query);
	        			ps.setInt(1, stavka.getIdArtikal());
	        			rs = ps.executeQuery();
	        			if(rs.next()) {
	        				label.setText(String.valueOf(rs.getString(1)));
	    	    	    	label_Cijena.setText(String.valueOf(stavka.getUkupnaCijena()) + "KM");
	    	    	    	ImageView next = new ImageView("/application/cell/remove.png");
	    	    	    	next.setFitWidth(30);
	    	    	    	next.setFitHeight(30);
	    	    	    	button.setGraphic(next);
	    	    	    	button.setOnAction(e -> {
	    	    	    		try {
	    	    	    			
	    	    	    			stavke.remove(stavka);
	    	    	    			
	    	    	    		} catch(Exception ex) {
	    	    	    			ex.printStackTrace();
	    	    	    		}
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
	                
	    	    	
	    	    	setText(null);
	    	    	setGraphic(pane);
	            }
		    	
		    }
		}
	 
	 public void ucitajStavke(ObservableList<Stavka> s, String ime) {
		 this.stavke = FXCollections.observableArrayList();
		 for(Stavka temp : s)
			 this.stavke.add(temp);
		 this.label_ImeRestorana.setText(ime);
	 }
	 
	 @Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
		 
	 }
	
	 @FXML
	 void prikaziStavke(ActionEvent event) {
		 listView_Stavke.setItems(this.stavke);
		 listView_Stavke.setCellFactory(stavkaListView -> new StavkaCell());
	 }

	 
	 @FXML
	 void goToPromjenaAdrese(ActionEvent event) {
		 try {
	 			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PromjenaAdrese.fxml")); 
	 			
	 	        Stage stage = new Stage();
	 	        stage.initOwner(button_Naruci.getScene().getWindow());
	 	        stage.setScene(new Scene((Parent) loader.load()));
	 	        PromjenaAdreseController controller = loader.getController();
	 	        controller.setIdKorisnik(idKorisnik);
	 	        controller.setStavke(stavke);
	 	        stage.showAndWait();

	 	        
	 		} catch(Exception ex) {
	 			ex.printStackTrace();
	 		}

	 }

}
