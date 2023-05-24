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
import entites.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class ServiceController implements Initializable {
    @FXML
    private TableView<Service> tableView;
    @FXML
    private TableColumn<Service, String> codeServiceCol;
    @FXML
    private TableColumn<Service, String> nomCol;
    @FXML
    private TableColumn<Service, String> blocCol;
    @FXML
    private TableColumn<Service, Integer> directeurCol;

    private ObservableList<Service> serviceList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeServiceCol.setCellValueFactory(cellData -> cellData.getValue().codeServiceProperty());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        blocCol.setCellValueFactory(cellData -> cellData.getValue().blocProperty());
        directeurCol.setCellValueFactory(cellData -> cellData.getValue().directeurProperty().asObject());

        loadData();
    }

    private void loadData() {
        Connection connection = DBconnection.getCon();
        String query = "SELECT CodeService, Nom, Bloc, Directeur FROM Services";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codeService = resultSet.getString("CodeService");
                String nom = resultSet.getString("Nom");
                String bloc = resultSet.getString("Bloc");
                int directeur = resultSet.getInt("Directeur");

                Service service = new Service(codeService, nom, bloc, directeur);
                serviceList.add(service);
            }

            resultSet.close();
            preparedStatement.close();
            tableView.setItems(serviceList); // Set the items to the TableView
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void getAjoutServiceView(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addService.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @FXML
    void updateBloc() {
        Service selectedService = tableView.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            showAlert("No Selection", "Please select a service to update.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update Bloc");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the new value for Bloc:");

        // Retrieve the new bloc value entered by the user
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String newBloc = result.get();
            selectedService.setBloc(newBloc);
            updateInDatabase(selectedService);
            showAlert("Success", "Bloc updated successfully!");
        } else {
            showAlert("Error", "No new value entered for Bloc.");
        }
    }
    private void updateInDatabase(Service service) {
        Connection connection = DBconnection.getCon();
        String query = "UPDATE Services SET Bloc = ? WHERE CodeService = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, service.getBloc());
            preparedStatement.setString(2, service.getCodeService());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update service. Please try again.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void deleteService() {
        Service selectedService = tableView.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            showAlert("No Selection", "Please select a service to delete.");
            return;
        }
        
        String serviceId = selectedService.getCodeService();
        deleteFromDatabase(serviceId);
        
        serviceList.remove(selectedService);
        
        showAlert("Success", "Service deleted successfully!");
    }
    private void deleteFromDatabase(String serviceId) {
        Connection connection = DBconnection.getCon();
        String query = "DELETE FROM Services WHERE CodeService = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, serviceId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete service. Please try again.");
        }
    }


}
