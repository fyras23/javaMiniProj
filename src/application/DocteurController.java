package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Docteur;
import entites.Employee;
import entites.Infirmier;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class DocteurController implements Initializable {
	@FXML 
	private TableView<Docteur> tableDocteur ; 
	@FXML 
	private TableColumn<Docteur , String >  numDocCol ;
	@FXML 
	private TableColumn<Docteur , String >  nomDocCol ;
	@FXML 
	private TableColumn<Docteur , String >  prenomDocCol;
	@FXML 
	private TableColumn<Docteur , String >  adrDocCol ;
	@FXML 
	private TableColumn<Docteur , String >  telDocCol ;
	@FXML 
	private TableColumn<Docteur , String >  DNDoc ;
	@FXML 
	private TableColumn<Docteur , String >  specialiteCol ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoadDataDoc();
		refrechDocList();
		
		
	}
	

	
	
	private Connection connection = null ;
	private String query= "" ;
	private ResultSet resultSet = null ;
	Docteur doc =null ; 
	Infirmier inf =null ; 

	java.sql.PreparedStatement preparedStatement = null ;
	ObservableList<Docteur> ListeDoc = FXCollections.observableArrayList();


	
	@FXML
	public void refrechDocList() {
		ListeDoc.clear();
		query= "Select * from employes,docteurs where employes.NumeroEmploye =docteurs.NumeroEmploye ";
		try {
			preparedStatement  = connection.prepareStatement(query) ;
			resultSet = preparedStatement.executeQuery() ;
			while(resultSet.next()) {
				query = String.format("Select * from employes,docteurs where employes.NumeroEmploye = %d", resultSet.getInt("docteurs.NumeroEmploye")) ; 
				preparedStatement  = connection.prepareStatement(query) ;
				ResultSet	resultSet1 = preparedStatement.executeQuery() ;
				resultSet1.next() ;
				
				Docteur doc = new Docteur(resultSet.getInt("NumeroEmploye"),
                        resultSet.getString("employes.Nom"),
                        resultSet.getString("Prenom"),
                        resultSet.getDate("DateNaissance"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("NumeroTelephone"),
                        resultSet.getString("specialite"));
					
				ListeDoc.add(doc);
						System.out.print(doc.toString());
			}
			tableDocteur.setItems(ListeDoc);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
		private void LoadDataDoc() {
			connection  = DBconnection.getCon() ; 
			
			
			numDocCol.setCellValueFactory(cellData -> {
			    Docteur docteur = cellData.getValue(); // Get the Docteur object for the current row
			    Integer num = docteur.getNumEm(); // Assuming 'getnum()' returns an Integer, adjust the method name and type accordingly
			    String numString = num != null ? num.toString() : ""; // Convert the Integer to a String
			    return new SimpleObjectProperty<>(numString);
			});

			nomDocCol.setCellValueFactory(cellData -> {
			    Docteur docteur = cellData.getValue();
			    String nom = docteur.getNom();
			    return new SimpleObjectProperty<>(nom);
			});

			prenomDocCol.setCellValueFactory(cellData -> {
			    Docteur docteur = cellData.getValue();
			    String prenom = docteur.getPrenom();
			    return new SimpleObjectProperty<>(prenom);
			});

			adrDocCol.setCellValueFactory(cellData -> {
			    Docteur docteur = cellData.getValue();
			    String adr = docteur.getAdr();
			    return new SimpleObjectProperty<>(adr);
			});

			telDocCol.setCellValueFactory(cellData -> {
			    Docteur docteur = cellData.getValue();
			    String tel = docteur.getTel();
			    return new SimpleObjectProperty<>(tel);
			});

			DNDoc.setCellValueFactory(cellData -> {
		    	Employee employee = cellData.getValue();
		        Date DateNaissance = employee.getDateNaissance();
		        String DateString = DateNaissance != null ? DateNaissance.toString() : "";
		        return new SimpleObjectProperty<>(DateString);
		    });

			specialiteCol.setCellValueFactory(cellData -> {
			    Docteur docteur = cellData.getValue();
			    String specialite = docteur.getSpecialite();
			    return new SimpleObjectProperty<>(specialite);
			});

			


			 
		}
		@FXML
		void getAjoutDocView(ActionEvent e ) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("addDocteur.fxml"));
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
		void deleteDocteur(ActionEvent event) {
		    // Get the selected doctor from the table view
		    Docteur selectedDocteur = tableDocteur.getSelectionModel().getSelectedItem();
		    
		    if (selectedDocteur != null) {
		        // Display a confirmation dialog to confirm the deletion
		        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		        confirmationAlert.setTitle("Confirmation");
		        confirmationAlert.setHeaderText("Delete Doctor");
		        confirmationAlert.setContentText("Are you sure you want to delete this doctor?");
		        
		        // Wait for user confirmation
		        Optional<ButtonType> result = confirmationAlert.showAndWait();
		        
		        if (result.isPresent() && result.get() == ButtonType.OK) {
		            // User confirmed the deletion, proceed with deleting the doctor
		            deleteDoctorFromDatabase(selectedDocteur);
		            ListeDoc.remove(selectedDocteur);
		            tableDocteur.refresh();
		        }
		    }
		}

		private void deleteDoctorFromDatabase(Docteur doctor) {
		    // Implement the database deletion logic here
		    // Use the doctor object to retrieve the necessary information (e.g., doctor.getId()) and perform the deletion in the database
		    // You can use the DBconnection class or any other mechanism to connect to the database and execute the deletion query
		    // Make sure to handle any exceptions that may occur during the database operation
		    // Example: 
		     Connection connection = DBconnection.getCon();
		     String deleteQuery = "DELETE FROM Doctors WHERE NumeroEmploye = ?";
		     try {
		         PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
		         preparedStatement.setInt(1, doctor.getNumEm());
		         preparedStatement.executeUpdate();
		         preparedStatement.close();
		         connection.close();
		     } catch (SQLException e) {
		         e.printStackTrace();
		     }
		}

		
	
}
