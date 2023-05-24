package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Infirmier;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class InfirmierController implements Initializable {
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

    private ObservableList<Infirmier> infirmierList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numeroEmployeCol.setCellValueFactory(cellData -> cellData.getValue().numeroEmployeProperty().asObject());
        salaireCol.setCellValueFactory(cellData -> cellData.getValue().salaireProperty().asObject());
        gradeCol.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        docteurAttacheCol.setCellValueFactory(cellData -> cellData.getValue().docteurAttacheProperty().asObject());

        loadData();
        tableInfirmier.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleRowSelection(newSelection);
            }
        });
    }

    private void loadData() {
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
            tableInfirmier.setItems(infirmierList); // Set the items to the TableView
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getAjoutinfView(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addinf.FXML"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void handleRowSelection(Infirmier infirmier) {
        // Handle the selected row
        int selectedNumeroEmploye = infirmier.getNumeroEmploye();
        double selectedSalaire = infirmier.getSalaire();
        String selectedGrade = infirmier.getGrade();
        int selectedDocteurAttache = infirmier.getDocteurAttache();

        // Implement the logic for updating the selected column

        // For example, let's update the grade column
  

        // Update the grade in the TableView and the database

    }

    private void updateGradeInDatabase(int numeroEmploye, String newGrade) {
        Connection connection = DBconnection.getCon();
        String query = "UPDATE Infirmiers SET Grade = ? WHERE NumeroEmploye = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newGrade);
            preparedStatement.setInt(2, numeroEmploye);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleUpdateButton(ActionEvent event) {
        Infirmier selectedInfirmier = tableInfirmier.getSelectionModel().getSelectedItem();
        if (selectedInfirmier != null) {
            // Open a dialog or window to allow the user to modify the selected item
            // Once the update is done, update the corresponding item in the table
            // For example, you can pass the selected Infirmier object to the dialog/window and retrieve the modified values

            // After updating the item, refresh the table view to reflect the changes
            tableInfirmier.refresh();
        } else {
            // No row selected, show an alert
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Infirmier Selected");
            alert.setContentText("Please select an Infirmier from the table.");
            alert.showAndWait();
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
}
