package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Surveillant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class survController implements Initializable {
    @FXML
    private TableView<Surveillant> tableSurveillant;
    @FXML
    private TableColumn<Surveillant, Integer> infirmierNumCol;
    @FXML
    private TableColumn<Surveillant, String> dateDebutCol;
    @FXML
    private TableColumn<Surveillant, String> heureFinCol;

    private ObservableList<Surveillant> surveillantList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infirmierNumCol.setCellValueFactory(cellData -> cellData.getValue().infirmierNumProperty().asObject());
        dateDebutCol.setCellValueFactory(cellData -> cellData.getValue().dateDebutProperty());
        heureFinCol.setCellValueFactory(cellData -> cellData.getValue().heureFinProperty());

        loadData();
    }

    private void loadData() {
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
            tableSurveillant.setItems(surveillantList); // Set the items to the TableView
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void getAjoutsurvView(ActionEvent e) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addsurv.FXML"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
