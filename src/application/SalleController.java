package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Salle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SalleController implements Initializable {
    @FXML
    private TableView<Salle> tableSalle;
    @FXML
    private TableColumn<Salle, Integer> numeroSalleCol;
    @FXML
    private TableColumn<Salle, Integer> surveillantCol;
    @FXML
    private TableColumn<Salle, Integer> nombreLitsCol;

    private ObservableList<Salle> salleList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numeroSalleCol.setCellValueFactory(cellData -> cellData.getValue().numeroSalleProperty().asObject());
        surveillantCol.setCellValueFactory(cellData -> cellData.getValue().surveillantProperty().asObject());
        nombreLitsCol.setCellValueFactory(cellData -> cellData.getValue().nombreLitsProperty().asObject());

        loadData();
    }

    private void loadData() {
        Connection connection = DBconnection.getCon();
        String query = "SELECT NumeroSalle, Surveillant, NombreLits FROM Salles";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int numeroSalle = resultSet.getInt("NumeroSalle");
                int surveillant = resultSet.getInt("Surveillant");
                int nombreLits = resultSet.getInt("NombreLits");

                Salle salle = new Salle(numeroSalle, surveillant, nombreLits);
                salleList.add(salle);
            }

            resultSet.close();
            preparedStatement.close();
            tableSalle.setItems(salleList); // Set the items to the TableView
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void deleteSalle() {
        Salle selectedSalle = tableSalle.getSelectionModel().getSelectedItem();
        if (selectedSalle == null) {
            showAlert("No Selection", "Please select a salle to delete.");
            return;
        }
        
        int salleId = selectedSalle.getNumeroSalle();
        deleteFromDatabase(salleId);
        
        salleList.remove(selectedSalle);
        
        showAlert("Success", "Salle deleted successfully!");
    }
    
    private void deleteFromDatabase(int salleId) {
        Connection connection = DBconnection.getCon();
        String query = "DELETE FROM Salles WHERE NumeroSalle = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, salleId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete salle. Please try again.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

