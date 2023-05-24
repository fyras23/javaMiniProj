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

public class addOrdController {
	@FXML
	private TextField Numeromalade ;
	@FXML
	private TextField numOrd ;

	@FXML
	private TextField iddoc ;
	@FXML
	private TextField descrip ;
	@FXML
	private TextField telField ;
	@FXML
	private DatePicker Datedd ;


	private Connection connection = null ;
	private String query= "" ;
	private ResultSet resultSet = null ;
	 

	java.sql.PreparedStatement preparedStatement = null ;

	@FXML
	public void save() {
	    Connection connection = DBconnection.getCon();
	    int  NumeroMalade  = Integer.parseInt(Numeromalade.getText());
	    int  numord  = Integer.parseInt(numOrd.getText());
	    int  idDoc  = Integer.parseInt(iddoc.getText());
	    LocalDate DateVisite = LocalDate.now();
	    String descri = descrip.getText();
	    

	    
	    if (descri.isEmpty() ) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Error");
	        alert.setContentText("Veuillez remplir tous les champs");
	        alert.showAndWait();	
	    } else {
	        String queryEmployee = "INSERT INTO `ordonnances`(`NumeroOrdonnance`, `NumeroMalade`,`idDoc`,`DateVisite`,`descri`) VALUES (?, ?, ?, ? ,?)";
	        
	        
	        try {
	            PreparedStatement preparedStatementEmployee = (PreparedStatement) connection.prepareStatement(queryEmployee);
	            preparedStatementEmployee.setInt(1, numord);
	            preparedStatementEmployee.setInt(2, NumeroMalade);
	            preparedStatementEmployee.setInt(3, idDoc);
	            preparedStatementEmployee.setDate(4, Date.valueOf(DateVisite));
	            preparedStatementEmployee.setString(5, descri);
	           
	            preparedStatementEmployee.executeUpdate();

	            Stage currentStage = (Stage) numOrd.getScene().getWindow();
				currentStage.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	}

}
