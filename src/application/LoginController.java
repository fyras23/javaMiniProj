package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db_conn.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginController {
@FXML 
private Button signIn ;
@FXML 
private PasswordField password ;
@FXML 
private TextField userName ; 
 
public void Login(ActionEvent e) {
	try {
	Connection con = DBconnection.getCon() ;
	Statement transwmission = (Statement) con.createStatement();
	ResultSet result = transwmission.executeQuery("select * from auth");

	while (result.next()) {
		System.out.println("noms = " + result.getString(2) + result.getString(3));
		if(result.getString(2).equals(userName.getText()) && result.getString(3).equals(password.getText())) {
				CurrentUser.isConnected = true ;
				Stage currentStage = (Stage) userName.getScene().getWindow();
				currentStage.close();
			
				// Create a new FXMLLoader to load the new FXML file
				FXMLLoader loader = new FXMLLoader(getClass().getResource("homeMain.fxml"));
				Parent root = loader.load();

				// Create a new stage for the loaded FXML file
				Stage newStage = new Stage();
				newStage.setScene(new Scene(root));


				currentStage.close();

				newStage.show();

				
		}else {
			 Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Error");
             alert.setContentText("Verifier vos Données");
             alert.showAndWait();
		}
	}
	}catch (SQLException ex) {
		ex.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
}


}
