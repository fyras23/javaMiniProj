package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Infirmier;
import entites.Salle;
import entites.Surveillant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class CombinedController implements Initializable {
    @FXML
    private TableView<Infirmier> tableInfirmier;
    @FXML
    private TableColumn<Infirmier, Integer> numeroEmployeCol;
    @FXML
    private TableColumn<Infirmier, Double> salaireCol;
    @FXML
    private TableColumn<Infirmier, String> gradeCol;
    @FXML
    private TableColumn<Infirmier, Integer> docteurAttacheCol;

    @FXML
    private TableView<Surveillant> tableSurveillant;
    @FXML
    private TableColumn<Surveillant, Integer> infirmierNumCol;
    @FXML
    private TableColumn<Surveillant, String> dateDebutCol;
    @FXML
    private TableColumn<Surveillant, String> heureFinCol;

    @FXML
    private TableView<Salle> tableSalle;
    @FXML
    private TableColumn<Salle, Integer> numeroSalleCol;
    @FXML
    private TableColumn<Salle, Integer> surveillantCol;
    @FXML
    private TableColumn<Salle, Integer> nombreLitsCol;

    private ObservableList<Infirmier> infirmierList = FXCollections.observableArrayList();
    private ObservableList<Surveillant> surveillantList = FXCollections.observableArrayList();
    private ObservableList<Salle> salleList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numeroEmployeCol.setCellValueFactory(cellData -> cellData.getValue().numeroEmployeProperty().asObject());
        salaireCol.setCellValueFactory(cellData -> cellData.getValue().salaireProperty().asObject());
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        docteurAttacheCol.setCellValueFactory(cellData -> cellData.getValue().docteurAttacheProperty().asObject());

        infirmierNumCol.setCellValueFactory(cellData -> cellData.getValue().infirmierNumProperty().asObject());
        dateDebutCol.setCellValueFactory(cellData -> cellData.getValue().dateDebutProperty());
        heureFinCol.setCellValueFactory(cellData -> cellData.getValue().heureFinProperty());

        numeroSalleCol.setCellValueFactory(cellData -> cellData.getValue().numeroSalleProperty().asObject());
        surveillantCol.setCellValueFactory(cellData -> cellData.getValue().surveillantProperty().asObject());
        nombreLitsCol.setCellValueFactory(cellData -> cellData.getValue().nombreLitsProperty().asObject());

        loadData();
    }

    private void loadData() {
        loadInfirmiers();
        loadSurveillants();
        loadSalles();
    }

    private void loadInfirmiers() {
        Connection connection = DBconnection.getCon();
        String query = "SELECT NumeroEmploye, Salaire, Grade, DocteurAttache FROM Infirmiers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int numeroEmploye = resultSet.getInt("NumeroEmploye");
                double salaire = resultSet.getDouble("Salaire");
                String grade = resultSet.getString("Grade");
                int docteurAttache = resultSet.getInt("DocteurAttache");

                Infirmier infirmier = new Infirmier(numeroEmploye, salaire, grade, docteurAttache);
                infirmierList.add(infirmier);
            }

            resultSet.close();
            preparedStatement.close();
            tableInfirmier.setItems(infirmierList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSurveillants() {
        Connection connection = DBconnection.getCon();
        String query = "SELECT infirmierNum, dateDebut, heureFin FROM Surveillant";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int infirmierNum = resultSet.getInt("infirmierNum");
                String dateDebut = resultSet.getString("dateDebut");
                String heureFin = resultSet.getString("heureFin");

                Surveillant surveillant = new Surveillant(infirmierNum, dateDebut, heureFin);
                surveillantList.add(surveillant);
            }

            resultSet.close();
            preparedStatement.close();
            tableSurveillant.setItems(surveillantList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSalles() {
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
            tableSalle.setItems(salleList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getAjoutInfirmierView(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addinf.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    void getAjoutSurveillantView(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addsurv.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    void getAjoutSalleView(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addSalle.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    void deleteSelectedRow(ActionEvent event) {
        Infirmier selectedInfirmier = tableInfirmier.getSelectionModel().getSelectedItem();
        if (selectedInfirmier != null) {
            int selectedIndex = tableInfirmier.getSelectionModel().getSelectedIndex();
            int numeroEmploye = selectedInfirmier.getNumeroEmploye();
            deleteRowFromDatabase(numeroEmploye);
            tableInfirmier.getItems().remove(selectedIndex);
        } else {
            // No row selected, show an alert
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Infirmier Selected");
            alert.setContentText("Please select an Infirmier from the table.");
            alert.showAndWait();
        }
    }

    private void deleteRowFromDatabase(int numeroEmploye) {
        Connection connection = DBconnection.getCon();
        String query = "DELETE FROM Infirmiers WHERE NumeroEmploye = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numeroEmploye);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void deleteSalle(ActionEvent event) {
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
    @FXML
    void deleteSurveillant(ActionEvent event) {
        // Get the selected surveillant from the TableView
        Surveillant selectedSurveillant = tableSurveillant.getSelectionModel().getSelectedItem();
        if (selectedSurveillant == null) {
            // No surveillant selected, show an error message
            showAlert("Error", "Please select a surveillant to delete.");
            return;
        }

        // Confirmation dialog for deletion
        boolean confirmDeletion = showConfirmationDialog("Confirm Deletion", "Are you sure you want to delete the selected surveillant?");
        if (confirmDeletion) {
            // Delete the selected surveillant from the database
            deleteSurveillantFromDatabase(selectedSurveillant);

            // Remove the selected surveillant from the TableView
            tableSurveillant.getItems().remove(selectedSurveillant);

            // Show success message
            showAlert("Success", "Surveillant deleted successfully!");
        }
    }

    private void deleteSurveillantFromDatabase(Surveillant surveillant) {
        Connection connection = DBconnection.getCon();
        String query = "DELETE FROM Surveillant WHERE infirmierNum = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, surveillant.getInfirmierNum());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete Surveillant. Please try again.");
        }
    }

    private void showAlert1(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    @FXML
    void updateSalle() {
        Salle selectedSalle = tableSalle.getSelectionModel().getSelectedItem();
        if (selectedSalle == null) {
            showAlert("No Selection", "Please select a salle to update.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(String.valueOf(selectedSalle.getNombreLits()));
        dialog.setTitle("Update Nombre Lits");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the new value for Nombre Lits:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newValue -> {
            int newNombreLits = Integer.parseInt(newValue);
            selectedSalle.setNombreLits(newNombreLits);
            updateInDatabase(selectedSalle);
            tableSalle.refresh();
            showAlert("Success", "Salle updated successfully!");
        });
    }

    private void updateInDatabase(Salle salle) {
        Connection connection = DBconnection.getCon();
        String query = "UPDATE Salles SET NombreLits = ? WHERE NumeroSalle = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, salle.getNombreLits());
            preparedStatement.setInt(2, salle.getNumeroSalle());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update salle. Please try again.");
        }
    }

}

