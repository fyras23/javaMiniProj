package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Docteur;
import entites.Employee;
import entites.Infirmier;
import entites.Malade;
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

public class EmployeController implements Initializable {
	
	@FXML 
	private TableView<Employee> tableEmpoyee ; 
	@FXML 
	private TableColumn<Employee , String > NumeroEmploye ;
	@FXML 
	private TableColumn<Employee , String >  Nom ;
	@FXML 
	private TableColumn<Employee , String >  Prenom;
	@FXML 
	private TableColumn<Employee , String >  Adresse ;
	@FXML 
	private TableColumn<Employee , String >  DateNaissance ;
	@FXML 
	private TableColumn<Employee , String >  NumeroTelephone ;
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
	ObservableList<Employee> ListeEmployee = FXCollections.observableArrayList();

	 
	@FXML
	public void refrechDocList() {
	    ListeEmployee.clear();
	    query = "SELECT * FROM employes";
	    try {
	        connection = DBconnection.getCon(); // Initialize the connection

	        preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            Employee employee = new Employee(resultSet.getInt("NumeroEmploye"),
	                    resultSet.getString("Nom"),
	                    resultSet.getString("Prenom"),
	                    resultSet.getDate("DateNaissance"),
	                    resultSet.getString("Adresse"),
	                    resultSet.getString("NumeroTelephone"));
	            ListeEmployee.add(employee);
	            System.out.print(employee.toString());
	        }
	        tableEmpoyee.setItems(ListeEmployee);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private void LoadData() {
	    connection = DBconnection.getCon();

	    NumeroEmploye.setCellValueFactory(cellData -> {
	    	Employee employee = cellData.getValue(); // Get the Malade object for the current row
	        Integer idEm = employee.getNumEm(); // Assuming 'getidMalade()' returns an Integer
	        String idEmString = idEm != null ? idEm.toString() : ""; // Convert the Integer to a String
	        return new SimpleObjectProperty<>(idEmString);
	    });

	    Nom.setCellValueFactory(cellData -> {
	    	Employee employee  = cellData.getValue();
	        String nom = employee.getNom();
	        return new SimpleObjectProperty<>(nom);
	    });

	    Prenom.setCellValueFactory(cellData -> {
	    	Employee employee = cellData.getValue();
	        String prenom = employee.getPrenom();
	        return new SimpleObjectProperty<>(prenom);
	    });

	    Adresse.setCellValueFactory(cellData -> {
	    	Employee employee = cellData.getValue();
	        String adresse = employee.getAdr();
	        return new SimpleObjectProperty<>(adresse);
	    });

	 
	    DateNaissance.setCellValueFactory(cellData -> {
	    	Employee employee = cellData.getValue();
	        Date DateNaissance = employee.getDateNaissance();
	        String DateString = DateNaissance != null ? DateNaissance.toString() : "";
	        return new SimpleObjectProperty<>(DateString);
	    });
	    NumeroTelephone.setCellValueFactory(cellData -> {
	    	Employee employee = cellData.getValue();
	        String NumeroTel = employee.getTel();
	        return new SimpleObjectProperty<>(NumeroTel);
	    });
	}
	 @FXML
		void getAjoutEmView(ActionEvent e ) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
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
	 void deleteSelectedEmployee(ActionEvent e) {
	     Employee selectedEmployee = tableEmpoyee.getSelectionModel().getSelectedItem();
	     if (selectedEmployee != null) {
	         Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this employee?",
	                 ButtonType.YES, ButtonType.NO);
	         alert.showAndWait();
	         if (alert.getResult() == ButtonType.YES) {
	             try {
	                 query = "DELETE FROM employes WHERE NumeroEmploye = ?";
	                 preparedStatement = connection.prepareStatement(query);
	                 preparedStatement.setInt(1, selectedEmployee.getNumEm());
	                 preparedStatement.executeUpdate();
	                 refrechDocList();
	             } catch (SQLException ex) {
	                 ex.printStackTrace();
	             }
	         }
	     } else {
	         Alert alert = new Alert(Alert.AlertType.WARNING, "No employee selected.", ButtonType.OK);
	         alert.showAndWait();
	     }
	 }
	 
	 

	
	
	

}
