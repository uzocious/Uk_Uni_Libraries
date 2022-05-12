package app.model;

public class Book
{
	private int id;
	private String title;

	public Book(int id, String title) 
	{
		this.id = id;
		this.title = title;
	}

	public int getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

}
