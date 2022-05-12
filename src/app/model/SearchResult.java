package app.model;

public class SearchResult
{
	private int index;
	private int count;

	public SearchResult(int index, int count) 
	{
		this.index = index;
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public int getCount() {
		return count;
	}

}
