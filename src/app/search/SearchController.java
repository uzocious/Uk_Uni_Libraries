package app.search;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;
import app.model.Book;
import app.model.Database;
import app.model.SearchResult;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SearchController implements Initializable
{ 
	@FXML private AnchorPane ancpSearch;
	@FXML private Label lblName;
	@FXML private Label lblSearchText;
	@FXML private TextField txtSearch;
	@FXML private ListView<String> lstSearchResult;
	
	private Database database = new Database();
	
	Vector<Book> lstOfBooks = new Vector<Book>();
	Vector<SearchResult> searchResult = new Vector<SearchResult>();
	Vector<Integer> sortCount = new Vector<Integer>();
	Vector<SearchResult> sortSearchResult = new Vector<SearchResult>();
        
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// Displays student name
		lblName.setText("Hello, " + Database.STUDENT_NAME);
		
		// Displays search text
		lblSearchText.setText(Database.SEARCH_TEXT);
		
		// gets list of books
		lstOfBooks = getListOfBook();
		
		
		//--------------------------------\\
		// Search Books by Title
		//--------------------------------\\
		for (int i = 0; i < lstOfBooks.size(); i++)
		{			
			//Get book title and split into an array from the list of books
			String[] title_arr = lstOfBooks.get(i).getTitle().toLowerCase().split(" ");
			
			//Sort the title array
			Arrays.sort(title_arr);
			
			//Get search text and split into an array
			String[] word = Database.SEARCH_TEXT.toLowerCase().trim().split(" ");
			
			int count = 0; //for counting number of words that occur in the title
			
			//Search each word in the title
			for (int j = 0; j < word.length; j++) 
			{
				if(search(title_arr, word[j]) >= 0) 
				{
					count++;
				}
			}
			
			//Get the search result index and count
			if (count > 0) {
				searchResult.add(new SearchResult(i, count));
			}
			
			// reset count
			count = 0;
		}
		
		
		//--------------------------------\\
		//Sort count array from highest to lowest
		//--------------------------------\\
		int temp;
		for (int i = 0; i < searchResult.size(); i++) 
		{
			sortCount.add(searchResult.get(i).getCount());
		}
		
		for (int i = 0; i < (sortCount.size() - 1); i++) {
			
			for (int j = 0; j < sortCount.size() - i - 1; j++) {
				
		        if (sortCount.get(j) < sortCount.get(j+1)) 
		        {
		          temp = sortCount.get(j);
		          sortCount.set(j, sortCount.get(j+1));
		          sortCount.set(j+1, temp);
		        }
			}
		}
		
//		for (int i = 0; i < sortCount.size(); i++) 
//		{
//			sortCount.get(i); //4
//			int n = searchResult.indexOf(sortCount.get(i)); // 2 i.e. 2nd place
//			sortSearchResult.add(new SearchResult(searchResult.get(n).getIndex(), searchResult.get(n).getCount()));
//		}
	     
		
		//--------------------------------\\
		// Display Search Result
		//--------------------------------\\
		int index;
		for (Integer count : sortCount) {
			
			for (SearchResult result : searchResult) {
				
				if (count == result.getCount()) 
				{
					index = result.getIndex();
				    					
					lstSearchResult.getItems().add(lstOfBooks.get(index).getTitle());
				}
			}
		}
		
		
		//--------------------------------\\
		// List view selection
		//--------------------------------\\
		lstSearchResult.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		        for (Book book : lstOfBooks)
		        {
		        	if (book.getTitle().equals(newValue))
		        	{
		        		Database.BOOK_ID = book.getID();
		        		
						try {
							// Open Result Page
							Stage primaryStage = new Stage();
							Parent root = FXMLLoader.load(getClass().getResource("../result/Result.fxml"));
							Scene scene = new Scene(root);
							primaryStage.setScene(scene);
							primaryStage.setResizable(false);
							primaryStage.setTitle("UK Uni Libraries");
							primaryStage.showAndWait();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		
					}	
				}
		    }
		});
		
		// Sets list's selection mode to single
		lstSearchResult.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
	}
	
	
	
	//--------------------------------\\
	// Boolean Search Algorithm
	//--------------------------------\\
	private static int search(String[] arr, String target)
	{
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) 
        {
            int mid = (left + right) / 2;
            if (arr[mid].equals(target)) {
                return mid;
            }
            else if (arr[mid].compareTo(target) < 0) {
        		left = mid + 1;
        	}
            else {
        		right = mid - 1;
            } 
        }
        return -1;
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
		ancpSearch.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void home(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../home/Home.fxml"));
		ancpSearch.getChildren().setAll(pane);
	}
	
	
	@FXML
	private void logOut(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
		ancpSearch.getChildren().setAll(pane);
	}
	
	
	
	// Get the list of books from the database
	private Vector<Book> getListOfBook() 
	{
		// Get all the books form database
		database.DBConnection();
		
		Vector<Book> list = new Vector<Book>();
		
		try {
			// Executes SQL query
			String query = String.format("SELECT Book_ID, Title FROM books");
		
			database.resultSet = database.statement.executeQuery(query);
			
			// gets the query result
			while(database.resultSet.next())
			{
				// adds each book to the vector array
				list.add(new Book(database.resultSet.getInt("Book_ID"), database.resultSet.getString("Title")));
			}
			
			return list; 
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		finally
		{
			try {
				database.connection.close();
				database.statement.close();
				database.resultSet.close();
			}
			catch(Exception e )
			{
				System.out.println(e);
			}		
		}
		
		return null;
	}
	
}
