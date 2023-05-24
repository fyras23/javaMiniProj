package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class sideBarController {
	
	@FXML 
	private BorderPane bp ; 
	
	@FXML 
	private AnchorPane ap ;
	 
	public void getToService(MouseEvent A )
	{
		LoadPage("service");
	}
	 
	public void getToPatients(MouseEvent A )
	{
		LoadPage("patients");
	}
	 
	public void getToParametres(MouseEvent A )
	{
		LoadPage("infermiesur");
	}
	 
	public void getToEmplyee(MouseEvent A )
	{
		LoadPage("emplyee");
	}
	
	public void getToDocteur(MouseEvent A )
	{
		LoadPage("Docteur");
	}
	public void getTosurev(MouseEvent A )
	{
		LoadPage("surveillant");
	}
	public void getToOrd(MouseEvent A )
	{
		LoadPage("Ord");
	}
	private void LoadPage(String page ) {
		Parent root = null ;
		try {
			root = FXMLLoader.load(getClass().getResource(page+".FXML"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		bp.setCenter(root);
		
	}
	
}
