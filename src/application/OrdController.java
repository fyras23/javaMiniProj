package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db_conn.DBconnection;
import entites.Docteur;
import entites.Employee;
import entites.Infirmier;
import entites.Malade;
import entites.Ordonnance;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OrdController implements Initializable{
	@FXML 
	private TableView<Ordonnance> tableOrd ; 
	@FXML 
	private TableColumn<Ordonnance , String >  NumeroOrdonnance ;
	@FXML 
	private TableColumn<Ordonnance , String >  nomMlCol ;
	@FXML 
	private TableColumn<Ordonnance , String >  prenomMlCol;
	@FXML 
	private TableColumn<Ordonnance , String >  idDoc ;
	@FXML 
	private TableColumn<Ordonnance , String >  DateVisite ;
	@FXML 
	private TableColumn<Ordonnance , String >  descri ;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoadDataDoc();
		refrechDocList();
		
		
	}
	

	
	
	private Connection connection = null ;
	private String query= "" ;
	private ResultSet resultSet = null ;
	Docteur doc =null ; 
	Infirmier inf =null ; 

	java.sql.PreparedStatement preparedStatement = null ;
	ObservableList<Ordonnance> ListeOrd = FXCollections.observableArrayList();


	
	@FXML
	public void refrechDocList() {
		ListeOrd.clear();
		query= "Select * from ordonnances,malades where malades.NumeroMalade =ordonnances.NumeroMalade ";
		try {
			preparedStatement  = connection.prepareStatement(query) ;
			resultSet = preparedStatement.executeQuery() ;
			while(resultSet.next()) {
				query = String.format("Select * from ordonnances,malades where malades.NumeroMalade = %d", resultSet.getInt("ordonnances.NumeroMalade")) ; 
				preparedStatement  = connection.prepareStatement(query) ;
				ResultSet	resultSet1 = preparedStatement.executeQuery() ;
				resultSet1.next() ;
				
				Ordonnance Ord = new Ordonnance(
						resultSet.getString("Nom"),
						resultSet.getString("Prenom"),
						resultSet.getInt("NumeroOrdonnance"),
						resultSet.getInt("NumeroMalade"),
						resultSet.getInt("idDoc"),
						resultSet.getDate("DateVisite"),
                        resultSet.getString("descri"));
					
				ListeOrd.add(Ord);
						System.out.print(Ord.toString());
			}
			tableOrd.setItems(ListeOrd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
		private void LoadDataDoc() {
			connection  = DBconnection.getCon() ; 
			
			
			NumeroOrdonnance.setCellValueFactory(cellData -> {
				Ordonnance ord = cellData.getValue(); // Get the Docteur object for the current row
			    Integer num = ord.getNumeroOrdonnance(); // Assuming 'getnum()' returns an Integer, adjust the method name and type accordingly
			    String numString = num != null ? num.toString() : ""; // Convert the Integer to a String
			    return new SimpleObjectProperty<>(numString);
			});

			nomMlCol.setCellValueFactory(cellData -> {
				Malade ord = cellData.getValue();
			    String nom = ord.getNom();
			    return new SimpleObjectProperty<>(nom);
			});

			prenomMlCol.setCellValueFactory(cellData -> {
				Malade ord = cellData.getValue();
			    String prenom = ord.getPrenom();
			    return new SimpleObjectProperty<>(prenom);
			});
			idDoc.setCellValueFactory(cellData -> {
				Ordonnance ord = cellData.getValue(); // Get the Docteur object for the current row
			    Integer num = ord.getIdDoc(); // Assuming 'getnum()' returns an Integer, adjust the method name and type accordingly
			    String numStrin = num != null ? num.toString() : ""; // Convert the Integer to a String
			    return new SimpleObjectProperty<>(numStrin);
			});

			descri.setCellValueFactory(cellData -> {
				Ordonnance ord = cellData.getValue();
			    String des = ord.getDescri();
			    return new SimpleObjectProperty<>(des);
			});

			DateVisite.setCellValueFactory(cellData -> {
				Ordonnance ord = cellData.getValue();
		        java.util.Date date = ord.getDateVisite();
		        String DateString = date != null ? date.toString() : "";
		        return new SimpleObjectProperty<>(DateString);
		    });

			

			


			 
		}
		@FXML
		void getAjoutOrdView(ActionEvent e ) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("addOrd.fxml"));
				Scene scene= new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
		}
		@FXML
		void deleteSelectedOrdonnance(ActionEvent e) {
		    Ordonnance selectedOrd = tableOrd.getSelectionModel().getSelectedItem();
		    if (selectedOrd != null) {
		        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
		                "Are you sure you want to delete this ordonnance?", ButtonType.YES, ButtonType.NO);
		        alert.showAndWait();
		        if (alert.getResult() == ButtonType.YES) {
		            try {
		                query = "DELETE FROM ordonnances WHERE NumeroOrdonnance = ?";
		                preparedStatement = connection.prepareStatement(query);
		                preparedStatement.setInt(1, selectedOrd.getNumeroOrdonnance());
		                preparedStatement.executeUpdate();
		                refrechDocList();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    } else {
		        Alert alert = new Alert(Alert.AlertType.WARNING, "No ordonnance selected.", ButtonType.OK);
		        alert.showAndWait();
		    }
		}
	 
		
	

}
