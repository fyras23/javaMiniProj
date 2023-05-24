package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import db_conn.DBconnection;
import entites.Docteur;
import entites.Infirmier;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addMaladeController {
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
private TextField servField ;


private Connection connection = null ;
private String query= "" ;
private ResultSet resultSet = null ;
 

java.sql.PreparedStatement preparedStatement = null ;

@FXML
public void save() {
    Connection connection = DBconnection.getCon();
    String nom = nomField.getText();
    String prenom = prenomField.getText();
    String serv = servField.getText();
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
        String queryEmployee = "INSERT INTO `Malades`(`NumeroMalade`, `nom`, `prenom`, `adresse`, `NumeroTelephone`, `ServiceDemande`) VALUES (?, ?, ?, ?, ?, ?)";
        
        
        try {
            PreparedStatement preparedStatementEmployee = (PreparedStatement) connection.prepareStatement(queryEmployee);
            preparedStatementEmployee.setInt(1, num);
            preparedStatementEmployee.setString(2, nom);
            preparedStatementEmployee.setString(3, prenom);
            preparedStatementEmployee.setString(4, adr);
            preparedStatementEmployee.setString(5, tel);
            preparedStatementEmployee.setString(6, serv);
            preparedStatementEmployee.executeUpdate();

            Stage currentStage = (Stage) idField.getScene().getWindow();
			currentStage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}

}
