package app.result;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import app.model.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class ResultController implements Initializable
{ 
	
    @FXML private AnchorPane ancpResult;
    @FXML private ImageView imgBook;
    @FXML private Label lblTitle;
    @FXML private Label lblAuthor;
    @FXML private Label lblYear;
    @FXML private Label lblEdition;
    @FXML private Label lblPublisher;
    @FXML private Label lblLanguage;
    @FXML private Label lblPages;

    private Database database = new Database();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		database.DBConnection();
		
		String edition = null;
		
		try 
		{
			// SQL select statement
			String query = String.format("SELECT *\r\n" + 
					"FROM books  \r\n" + 
					"WHERE Book_ID = %s", Database.BOOK_ID);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);
			
			// Gets the query result from the movie table		 
			if(database.resultSet.next())
			{
				// Set movie information
				lblTitle.setText(database.resultSet.getString("Title"));
				lblAuthor.setText(database.resultSet.getString("Author"));
				lblYear.setText(database.resultSet.getString("Year"));
				edition = database.resultSet.getString("Edition");
				lblPublisher.setText(database.resultSet.getString("Publisher"));
				lblLanguage.setText(database.resultSet.getString("Language"));
				lblPages.setText(database.resultSet.getString("Pages"));
			}
			
			if (edition == null) {
				lblEdition.setText("*None*");
			}
			else {
				lblEdition.setText(edition);
			}
			
			// Set movie image
			File file = new File("src/app/book_img/" + Database.BOOK_ID + ".jpg");
			Image img = new Image(file.toURI().toString());
			imgBook.setImage(img);
			
		} 
		catch (Exception e) {
			System.out.println(e);}
		finally {
			try {
				database.connection.close();
				database.statement.close();
				database.resultSet.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}}
		
	}
	
	@FXML
    void borrowBook(ActionEvent event) 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("UK Uni Libraries");
		alert.setContentText("Your request has been successful. \r\n\n"
				+ "The book will be delivered to the address provided in evision. \r\n\n"
				+ "Go to evision to see more.");
		
		alert.setHeaderText(null);	
		alert.showAndWait();
    }
    
	
	
}
