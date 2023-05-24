package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import db_conn.DBconnection;
import entites.Docteur;
import entites.Infirmier;
import entites.Malade;
import entites.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleObjectProperty;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class maladeController implements Initializable  {
	@FXML 
	private TableView<Malade> tableMalade ; 
	@FXML 
	private TableColumn<Malade , String > idCol ;
	@FXML 
	private TableColumn<Malade , String >  nomCol ;
	@FXML 
	private TableColumn<Malade , String >  prenomCol;
	@FXML 
	private TableColumn<Malade , String >  adrCol ;
	@FXML 
	private TableColumn<Malade , String >  numServiceCol ;
	@FXML 
	private TableColumn<Malade , String >  telCol ;
	@FXML
	private TextField serviceTextField;

	@FXML
	private Button searchButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoadData();
		refrechDocList();

	}
	
	private Connection connection = null ;
	private String query= "" ;
	private ResultSet resultSet = null ;
	Docteur doc =null ; 
	Infirmier inf =null ; 

	java.sql.PreparedStatement preparedStatement = null ;
	ObservableList<Malade> ListeMalade = FXCollections.observableArrayList();

	 
	@FXML
	public void refrechDocList() {
	    ListeMalade.clear();
	    query = "SELECT * FROM malades";
	    try {
	        preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            
	           

	            Malade malade = new Malade(resultSet.getInt("NumeroMalade"),
	                    resultSet.getString("nom"),
	                    resultSet.getString("prenom"),
	                    resultSet.getString("adresse"),
	                    resultSet.getString("NumeroTelephone"),
	                   resultSet.getString("ServiceDemande"));
	            ListeMalade.add(malade);
	            System.out.print(malade.toString());
	        }
	        tableMalade.setItems(ListeMalade);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private void LoadData() {
	    connection = DBconnection.getCon();

	    idCol.setCellValueFactory(cellData -> {
	        Malade malade = cellData.getValue(); // Get the Malade object for the current row
	        Integer idMalade = malade.getNumeroMalade(); // Assuming 'getidMalade()' returns an Integer
	        String idMaladeString = idMalade != null ? idMalade.toString() : ""; // Convert the Integer to a String
	        return new SimpleObjectProperty<>(idMaladeString);
	    });

	    nomCol.setCellValueFactory(cellData -> {Malade malade = cellData.getValue(); String nom = malade.getNom();
	        return new SimpleObjectProperty<>(nom);
	    });

	    prenomCol.setCellValueFactory(cellData -> {
	        Malade malade = cellData.getValue();
	        String prenom = malade.getPrenom();
	        return new SimpleObjectProperty<>(prenom);
	    });

	    adrCol.setCellValueFactory(cellData -> {
	        Malade malade = cellData.getValue();
	        String adresse = malade.getAdresse();
	        return new SimpleObjectProperty<>(adresse);
	    });

	 
	    telCol.setCellValueFactory(cellData -> {
	        Malade malade = cellData.getValue();
	        String telephone = malade.getNumeroTelephone();
	        return new SimpleObjectProperty<>(telephone);
	    });
	    numServiceCol.setCellValueFactory(cellData -> {
	        Malade malade = cellData.getValue();
	        String service = malade.getServiceDemande();
	      
	        String numServiceString =  service.toString() ; 
	        return new SimpleObjectProperty<>(numServiceString);
	    });
	}
	 @FXML
		void getAjoutDocView(ActionEvent e ) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("addMalade.FXML"));
				Scene scene= new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
		}
	 
	 @FXML
	    void deleteSelectedMalade(ActionEvent e) {
	        Malade selectedMalade = tableMalade.getSelectionModel().getSelectedItem();
	        if (selectedMalade != null) {
	            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this malade?",
	                    ButtonType.YES, ButtonType.NO);
	            alert.showAndWait();
	            if (alert.getResult() == ButtonType.YES) {
	                try {
	                    query = "DELETE FROM malades WHERE NumeroMalade = ?";
	                    preparedStatement = connection.prepareStatement(query);
	                    preparedStatement.setInt(1, selectedMalade.getNumeroMalade());
	                    preparedStatement.executeUpdate();
	                    refrechDocList();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        } else {
	            Alert alert = new Alert(Alert.AlertType.WARNING, "No malade selected.", ButtonType.OK);
	            alert.showAndWait();
	        }
	    }
	 @FXML
	 void searchByService(ActionEvent e) {
	     String service = serviceTextField.getText();
	     if (service.isEmpty()) {
	         Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a service.", ButtonType.OK);
	         alert.showAndWait();
	     } else {
	         ListeMalade.clear();
	         query = "SELECT * FROM malades WHERE ServiceDemande = ?";
	         try {
	             preparedStatement = connection.prepareStatement(query);
	             preparedStatement.setString(1, service);
	             resultSet = preparedStatement.executeQuery();
	             while (resultSet.next()) {
	                 Malade malade = new Malade(resultSet.getInt("NumeroMalade"),
	                         resultSet.getString("nom"),
	                         resultSet.getString("prenom"),
	                         resultSet.getString("adresse"),
	                         resultSet.getString("NumeroTelephone"),
	                         resultSet.getString("ServiceDemande"));
	                 ListeMalade.add(malade);
	                 System.out.print(malade.toString());
	             }
	             tableMalade.setItems(ListeMalade);
	         } catch (SQLException ex) {
	             ex.printStackTrace();
	         }
	     }
	 }
	
	
	
	
}
