// Data structure for storing nodes of a graph
public class Node {
	
	//Private instance variables
	private int id;
	private boolean mark;
	
	//Public methods
	public Node(int id)
	{
	    this.id = id;
	    this.mark = false;
	}
	
	//Set the value of mark
	public void markNode(boolean mark)
	{
	    this.mark = mark;
	}
	
	//Get the value of mark
	public boolean getMark()
	{
	    return mark;
	}
	
	//Returns the name
	public int getId()
	{
	    return id;
	}
}