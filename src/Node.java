// a class for a node in a linked list
// updated 06-May-2018 cjk for CSC 161
// MODIFIED by Alyssa Gibson, 05.17.2018

public class Node 
{
	// Data members
	private Student data;
	private Test info;
	private Node next;
	
	// Constructors that takes parameters for
	// the data/info and link (next) fields
	public Node(Student i, Node n)
	{
		data = i;
		next = n;
	}
	
	public Node(Test i, Node n) {
		info = i;
		next = n;
	}
	
	public Node(Student s, Test t) {
		data = s;
		info = t;
		next = null;
	}
	
	// Constructor that takes a parameter for
	// the data field only.
	public Node(Student i)
	{
		data = i;
		next = null;
	}
	
	public Node(Test t) {
		info = t;
		next = null;
	}
	
	public Node(Test t, Student s) {
		info = t;
		data = s;
		next = null;
	}

	// set the Node field
	public void setNext(Node n)
	{
		next = n;
	}
	
	// returns Student associated with Node
	public Student getData()
	{
		return data;
	}
	
	// returns Test associated with Node
	public Test getInfo() {
		return info;
	}
	
	// returns the link associated with the Node.
	public Node getNext()
	{
		return next;
	}
}  // end class Node
