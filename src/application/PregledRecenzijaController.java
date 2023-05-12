package application;
import connection.ConnectionPool;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class PregledRecenzijaController implements Initializable{
	private int idKorisnik;
	
	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	private ObservableList<Recenzija> recenzije;
	@FXML
	private CheckBox checkBoxMoje;
	@FXML
	private CheckBox checkBoxSve;
	@FXML
    private Label label_ImeRestorana;
	@FXML
	private ImageView imageView_Background;
	@FXML
	private ListView<Recenzija> listView_Recenzije;
	
	public void setImeRestorana(Restoran r) {
		label_ImeRestorana.setText(r.getNaziv());
	}
	
	private class RecenzijaCell extends ListCell<Recenzija>{
		@FXML
	    private Label label;
		@FXML
	    private AnchorPane pane;
	    @FXML
	    private ImageView next;
	    @FXML
	    private Button button;
	    @FXML
	    private Label label_Ime;
	    @Override
	    protected void updateItem(Recenzija recenzija, boolean empty) {
	    	super.updateItem(recenzija, empty);
	    	
	    	if(empty || recenzija == null) {

	            setText(null);
	            setGraphic(null);

	        }
	    	
	    	else {
	    		FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/application/cell/ListCellRecenzija.fxml"));
                mLLoader.setController(this);

                try {
                	mLLoader.load();
                } 
                catch (IOException e) {
                e.printStackTrace();
                }

    	    	label.setText(String.valueOf(recenzija.getKomentar()));
    	    	
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
    	    			label_Ime.setText(rs.getString(1));
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
    	    	
    	    	
    	    	ImageView next = new ImageView("/application/cell/share.png");
    	    	next.setFitWidth(30);
    	    	next.setFitHeight(30);
    	    	button.setGraphic(next);
    	    	button.setOnAction(e -> {
    	    		
    	    		try {
    	    			Stage info = new Stage();
    	    			
    	    			
    	    			 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CitanjeRecenzije.fxml"));
    	    			
    	    		    Parent root = loader.load();
    	    		    Scene scene = new Scene(root);
    	    		    CitanjeRecenzijeController recController = loader.getController();
    	    		    recController.setFields(label_ImeRestorana.getText(), recenzija);
    	    			info.setScene(scene);
    	    			info.show();
    	    			info.setResizable(false);
    	    			
    	    		} catch(Exception ex) {
    	    			ex.printStackTrace();
    	    		}
                });
    	    	setText(null);
    	    	setGraphic(pane);
            }
	    	
	    }
	}
	
	
	
	@FXML
    void prikaziSve(ActionEvent event) {
		if(checkBoxSve.isSelected()) {
			listView_Recenzije.getItems().clear();
			Connection c = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			CallableStatement cs = null;
			recenzije = FXCollections.observableArrayList();
			String query = "select idRestoran from foodie.restoran where Naziv=?";
			try {
				c = ConnectionPool.getInstance().checkOut();
				ps = c.prepareStatement(query);
				ps.setString(1, label_ImeRestorana.getText());
				rs = ps.executeQuery();
				
				int idRestoran = 0;
				if(rs.next())
				{
					
					idRestoran = rs.getInt(1);
					rs.close();
					cs = c.prepareCall("call prikaziRecenzijeRestorana(?)");
					cs.setInt(1, idRestoran);
					rs = cs.executeQuery();
					while(rs.next()) {
						Recenzija recenzija = new Recenzija(rs.getInt(1), rs.getByte(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
						recenzije.add(recenzija);
					}
					rs.close();
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
			

			listView_Recenzije.setItems(recenzije);
			listView_Recenzije.setCellFactory(recenzijeListView -> new RecenzijaCell());
		}
		
	

    }
	
	@FXML
    void prikaziMoje(ActionEvent event) {
		if(checkBoxMoje.isSelected()) {
			listView_Recenzije.getItems().clear();
			Connection c = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			CallableStatement cs = null;
			recenzije = FXCollections.observableArrayList();
			String query = "select idRestoran from foodie.restoran where Naziv=?";
			try {
				c = ConnectionPool.getInstance().checkOut();
				ps = c.prepareStatement(query);
				ps.setString(1, label_ImeRestorana.getText());
				rs = ps.executeQuery();
				
				int idRestoran = 0;
				if(rs.next())
				{
					
					idRestoran = rs.getInt(1);
					rs.close();
					cs = c.prepareCall("call prikaziMojeRecenzijeRestorana(?, ?)");
					cs.setInt(1, idRestoran);
					cs.setInt(2, idKorisnik);
					rs = cs.executeQuery();
					while(rs.next()) {
						Recenzija recenzija = new Recenzija(rs.getInt(1), rs.getByte(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
						recenzije.add(recenzija);
					}
					rs.close();
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
			

			listView_Recenzije.setItems(recenzije);
			listView_Recenzije.setCellFactory(recenzijeListView -> new RecenzijaCell());
		}
		
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

}
