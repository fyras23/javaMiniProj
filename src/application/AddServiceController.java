package application;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db_conn.DBconnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddServiceController {
    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField blocField;
    @FXML
    private TextField directeurField;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    @FXML
    public void save() {
        Connection connection = DBconnection.getCon();
        int CodeService = Integer.parseInt(codeField.getText());
        String nom = nomField.getText();
        String bloc = blocField.getText();
        int directeur = Integer.parseInt(directeurField.getText());

        if (nom.isEmpty() || bloc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            String queryService = "INSERT INTO services (CodeService, nom, bloc, directeur) VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatementService = connection.prepareStatement(queryService);
                preparedStatementService.setInt(1, CodeService);
                preparedStatementService.setString(2, nom);
                preparedStatementService.setString(3, bloc);
                preparedStatementService.setInt(4, directeur);
                preparedStatementService.executeUpdate();

                Stage currentStage = (Stage) codeField.getScene().getWindow();
                currentStage.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
