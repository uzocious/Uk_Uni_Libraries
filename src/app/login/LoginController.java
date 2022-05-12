package app.login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.model.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class LoginController implements Initializable
{
	@FXML private AnchorPane ancpLogin;
	@FXML private Label lblErrorMessage;
	@FXML private ComboBox<String> cmbUniversity;
    @FXML private TextField txtStudentID;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;

    //Database object
    private Database database = new Database();
    
    //University List
    ObservableList<String> list = FXCollections.observableArrayList(
    		"University of Bedfordshire",
    		"University of Leicester",
    		"Brunel University London",
    		"University of Southampton",
    		"University of Hertfordshire");
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		lblErrorMessage.setVisible(false);
		
		cmbUniversity.setItems(list);
		
	}
	
	@FXML
	private void login(ActionEvent event) throws IOException, SQLException
	{
		String studentID = txtStudentID.getText().toLowerCase();
		String password = txtPassword.getText();
		
		//Trims white space
		studentID = studentID.trim();
		
		//Open Database
		database.DBConnection();
		
		try 
		{
			// SQL select statement
			String query = String.format("SELECT Student_ID, Firstname, Lastname, DES_DECRYPT(Pass_Word, 'um')\r\n" + 
					"FROM students  \r\n" + 
					"WHERE Student_ID = '%s' AND Pass_Word = DES_ENCRYPT('%s', 'um')", studentID, password);
			
			// Executes SQL query
			database.resultSet = database.statement.executeQuery(query);
			
			// Gets the query result from the student table		 
			if(database.resultSet.next())
			{
				Database.STUDENT_ID = database.resultSet.getInt("Student_ID");
				String fname = database.resultSet.getString("Firstname");
				String lname = database.resultSet.getString("Lastname");
				Database.STUDENT_NAME = fname + " " + lname;
				Database.UNIVERSITY = cmbUniversity.getValue();
				
				//opens home page
				AnchorPane pane = FXMLLoader.load(getClass().getResource("../home/Home.fxml"));
				ancpLogin.getChildren().setAll(pane);
			}
			else
			{
				lblErrorMessage.setVisible(true);
				return;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);}
		finally {
			database.connection.close();
			database.statement.close();
			database.resultSet.close();}
	}
	
	
	@FXML
	private void comboChanged(ActionEvent event)
	{
		btnLogin.setDisable(false);
	}

	
	

}
