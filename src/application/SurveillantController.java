package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SurveillantController implements Initializable {

    @FXML
    private TextField infirmierNumField;

    @FXML
    private TextField dateDebutField;

    @FXML
    private TextField heureFinField;

    @FXML
    private Button addButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initializations, if any
    }

    @FXML
    void addSurveillant(ActionEvent event) {
        String infirmierNum = infirmierNumField.getText();
        String dateDebut = dateDebutField.getText();
        String heureFin = heureFinField.getText();

        if (infirmierNum.isEmpty() || dateDebut.isEmpty() || heureFin.isEmpty()) {
            showAlert(AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            Connection connection = DBconnection.getCon();
            String query = "INSERT INTO surveillant (infirmierNum, dateDebut, heureFin) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, infirmierNum);
            preparedStatement.setString(2, dateDebut);
            preparedStatement.setString(3, heureFin);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(AlertType.INFORMATION, "Succès", "Surveillant ajouté avec succès.");
                clearFields();
            } else {
                showAlert(AlertType.ERROR, "Erreur", "Échec de l'ajout du surveillant.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'ajout du surveillant.");
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        infirmierNumField.clear();
        dateDebutField.clear();
        heureFinField.clear();
    }
}
