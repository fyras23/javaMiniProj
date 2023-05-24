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

public class addDocteurController {
	@FXML
	private TextField idField ;
	@FXML
	private TextField specialiteField ;

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
	    String spe = specialiteField.getText();
	    int  num  = Integer.parseInt(idField.getText());

	    
	    if (spe.isEmpty() ) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Error");
	        alert.setContentText("Veuillez remplir tous les champs");
	        alert.showAndWait();	
	    } else {
	        String queryEmployee = "INSERT INTO `docteurs`(`NumeroEmploye`, `Specialite`) VALUES (?, ?)";
	        
	        
	        try {
	            PreparedStatement preparedStatementEmployee = (PreparedStatement) connection.prepareStatement(queryEmployee);
	            preparedStatementEmployee.setInt(1, num);
	            preparedStatementEmployee.setString(2, spe);
	           
	            preparedStatementEmployee.executeUpdate();

	            Stage currentStage = (Stage) idField.getScene().getWindow();
				currentStage.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	}

}
