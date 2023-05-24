package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_conn.DBconnection;
import entites.Infirmier;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addInfirmierController {
    @FXML
    private TextField numeroEmployeField;
    @FXML
    private TextField salaireField;
    @FXML
    private TextField gradeField;
    @FXML
    private TextField docteurAttacheField;

    @FXML
    public void save() {
        Connection connection = DBconnection.getCon();
        int numeroEmploye = Integer.parseInt(numeroEmployeField.getText());
        double salaire = Double.parseDouble(salaireField.getText());
        String grade = gradeField.getText();
        int docteurAttache = Integer.parseInt(docteurAttacheField.getText());

        if (grade.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            String queryInfirmier = "INSERT INTO Infirmiers (NumeroEmploye, Salaire, Grade, DocteurAttache) VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatementInfirmier = connection.prepareStatement(queryInfirmier);
                preparedStatementInfirmier.setInt(1, numeroEmploye);
                preparedStatementInfirmier.setDouble(2, salaire);
                preparedStatementInfirmier.setString(3, grade);
                preparedStatementInfirmier.setInt(4, docteurAttache);
                preparedStatementInfirmier.executeUpdate();

                Stage currentStage = (Stage) numeroEmployeField.getScene().getWindow();
                currentStage.close();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Database Error");
                alert.setContentText("An error occurred while saving the data to the database.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
}
