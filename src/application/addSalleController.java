package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class addSalleController implements Initializable {

    @FXML
    private TextField numeroSalleField;

    @FXML
    private TextField surveillantField;

    @FXML
    private TextField nombreLitsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic, if needed
    }
    

    @FXML
    void addSalle() {
        int numeroSalle = Integer.parseInt(numeroSalleField.getText());
        int surveillant = Integer.parseInt(surveillantField.getText());
        int nombreLits = Integer.parseInt(nombreLitsField.getText());

        // Validate input
        if (numeroSalle <= 0 || surveillant <= 0 || nombreLits <= 0) {
            showAlert("Invalid Input", "Please enter valid values for all fields.");
            return;
        }

        // Insert into the "salles" table
        insertIntoDatabase(numeroSalle, surveillant, nombreLits);

        // Clear the input fields
        clearFields();

        // Show success message
        showAlert("Success", "Salle added successfully!");
    }

    private void insertIntoDatabase(int numeroSalle, int surveillant, int nombreLits) {
        Connection connection = DBconnection.getCon();
        String query = "INSERT INTO salles (NumeroSalle, Surveillant, NombreLits) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numeroSalle);
            preparedStatement.setInt(2, surveillant);
            preparedStatement.setInt(3, nombreLits);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add Salle. Please try again.");
        }
    }

    private void clearFields() {
        numeroSalleField.clear();
        surveillantField.clear();
        nombreLitsField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
