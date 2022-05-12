package app.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import app.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HomeController implements Initializable
{ 
	@FXML private AnchorPane ancpHome;
	@FXML private Label lblName;
	@FXML private TextField txtSearch;
    @FXML private ImageView imgUniversity;
        
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// Displays student name
		lblName.setText("Hello, " + Database.STUDENT_NAME);
		
		
		// Set university image
		if (Database.UNIVERSITY == "University of Bedfordshire") {
			File file = new File("src/app/images/uob.jpeg");
			Image img = new Image(file.toURI().toString());
			imgUniversity.setImage(img);
		}
		else if (Database.UNIVERSITY == "University of Leicester") {
			File file = new File("src/app/images/uol.jpeg");
			Image img = new Image(file.toURI().toString());
			imgUniversity.setImage(img);
		}
		else if (Database.UNIVERSITY == "Brunel University London") {
			File file = new File("src/app/images/bul.jpeg");
			Image img = new Image(file.toURI().toString());
			imgUniversity.setImage(img);	
		}
		else if (Database.UNIVERSITY == "University of Southampton") {
			File file = new File("src/app/images/uos.jpeg");
			Image img = new Image(file.toURI().toString());
			imgUniversity.setImage(img);
		}
		else if (Database.UNIVERSITY == "University of Hertfordshire") {
			File file = new File("src/app/images/uoh.jpeg");
			Image img = new Image(file.toURI().toString());
			imgUniversity.setImage(img);
		}
		
		
	}
	
	
	@FXML
	private void searchAction(ActionEvent event) throws IOException
	{
		String searchText = txtSearch.getText().trim();
		
		if (searchText.isEmpty())
		{
			return;
		}
		
		Database.SEARCH_TEXT = searchText;
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../search/Search.fxml"));
		ancpHome.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void bk1(ActionEvent event) throws IOException
	{
		Database.BOOK_ID = 111827304;
		gotoResult();
	}
	
	@FXML
	private void bk2(ActionEvent event) throws IOException
	{
		Database.BOOK_ID = 978147375;
		gotoResult();
	}
	
	@FXML
	private void bk3(ActionEvent event) throws IOException
	{
		Database.BOOK_ID = 149194600;
		gotoResult();
	}
	
	@FXML
	private void bk4(ActionEvent event) throws IOException
	{
		Database.BOOK_ID = 978019996;
		gotoResult();
	}
	
	@FXML
	private void bk5(ActionEvent event) throws IOException
	{
		Database.BOOK_ID = 111884155;
		gotoResult();
	}
	
	
	private void gotoResult() throws IOException 
	{
		// Open Result Page
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../result/Result.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("UK Uni Libraries");
		primaryStage.showAndWait();
	}
	
	@FXML
	private void home(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../home/Home.fxml"));
		ancpHome.getChildren().setAll(pane);
	}
	
	@FXML
	private void logOut(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
		ancpHome.getChildren().setAll(pane);
	}
	
}
