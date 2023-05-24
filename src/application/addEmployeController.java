package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import db_conn.DBconnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addEmployeController {
	@FXML
	private TextField idField ;
	@FXML
	private TextField nomField ;

	@FXML
	private TextField prenomField ;
	@FXML
	private TextField adrField ;
	@FXML
	private TextField telField ;
	@FXML
	private DatePicker dateField ;


	private Connection connection = null ;
	private String query= "" ;
	private ResultSet resultSet = null ;
	 

	java.sql.PreparedStatement preparedStatement = null ;

	@FXML
	public void save() {
	    Connection connection = DBconnection.getCon();
	    String nom = nomField.getText();
	    String prenom = prenomField.getText();
	    LocalDate birthDate = LocalDate.now();
	    String adr = adrField.getText(); 
	    String tel = telField.getText();
	    int num  = Integer.parseInt(idField.getText());

	    
	    if (nom.isEmpty() ||  adr.isEmpty() || tel.isEmpty()|| prenom.isEmpty()) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Error");
	        alert.setContentText("Veuillez remplir tous les champs");
	        alert.showAndWait();	
	    } else {
	        String queryEmployee = "INSERT INTO `employes`(`NumeroEmploye`, `Nom`, `Prenom`, `Adresse`, `DateNaissance`, `NumeroTelephone`) VALUES (?, ?, ?, ?, ?, ?)";
	        
	        
	        try {
	            PreparedStatement preparedStatementEmployee = (PreparedStatement) connection.prepareStatement(queryEmployee);
	            preparedStatementEmployee.setInt(1, num);
	            preparedStatementEmployee.setString(2, nom);
	            preparedStatementEmployee.setString(3, prenom);
	            preparedStatementEmployee.setString(4, adr);
	            preparedStatementEmployee.setDate(5, Date.valueOf(birthDate));
	            preparedStatementEmployee.setString(6, tel);
	            preparedStatementEmployee.executeUpdate();

	            Stage currentStage = (Stage) idField.getScene().getWindow();
				currentStage.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	}

}
