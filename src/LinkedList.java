// class definition for a singly-linked list
// updated 06-May-2018 cjk for CSC 161
// MODIFIED by Alyssa Gibson, 05.17.2018

public class LinkedList 
{
	private Node head;
	private int count;  // for size
	
	// constructor that creates an empty linked list
	public LinkedList()
	{
		head = null;
		count = 0;
	}
	
	// constructor that initializes the linked list
	// to a node
	public LinkedList(Node n)
	{
		head = n;
		count = 1;
	}
	
	public boolean isEmpty() {
	// Boolean to check if the list is empty- pretty self explanatory.
		return (count == 0);
	}
	
	// THESE NEXT 4 METHODS ADD ELEMENTS TO THE LIST:
	public void add(Student s) {
		// This add adds a Student to the bottom of the list,
		// then calls add(Node) to correctly add it
		// based on conditions.
		Node n = new Node(s);
		add(n);
	}
	
	public void add(int location, Student i) {
		// This method adds a Student at a specified location,
		// then calls add(int, Node) to correctly add it
		// based on conditions.
		Node n = new Node(i);
		add(location, n);	
	}
	
	public void add(Node n) {
		if (head == null)  // the list is empty
			head = n;
		else
		{
			Node p = head;
			// use node pointer p to
			// 'walk down' the list until
			// it gets to the node whose
			// next field is null
			while(p.getNext() != null)
				p = p.getNext();
			
			// set the next field to be the
			// node that was passed in
			
			p.setNext(n);
			count++;
			
		}  // end else clause
	}  // end method add
	
	// add a node at a designated location in a linked list
	public void add(int location, Node n) {
		if (location == 0)
			// add at beginning of list
		{
			n.setNext(head);
			head = n;
		}
		else
		{
			// Use a pair of node pointers to traverse
			// the list.  One pointer follows behind
			// the other.
			Node p = head;
			Node q = null;
			int count = 0;
			// walk down the list until we get
			// to the correct location
			boolean endOfList = false;
			while ((count < location)&&(!endOfList))
			{
				// determine if we have reached the
				// end of the list prematurely.
				if (p == null)
					endOfList = true;
				else
				{
					q = p;
					p = p.getNext();
					count++;
				}
			}
			// put the new node in the
			// list after p
			q.setNext(n);
			n.setNext(p);
			count++;
			
			
		}
		
		
	}  // end method to add at a location
	
	public void insert(Student s, Test t) {
		Node n = new Node(s, t);
		insertStudent(n);
	}
	
	public void insert(Test t, Student s) {
		Node n = new Node(t, s);
		insertTest(n);
	}
	
	// add a Student node ordered alphabetically
	public void insertStudent(Node n) {
		Node p = head;
		Node q = null;
		
		// Case 1: If the list is empty;
		if (head == null) {
			head = n;
			count++;
		}

		// Case 2:
		// In English: Case 2 is for if the last names are the same but n's first name
		// 			   is lexicographically before p's first name.
		// 			   OR 
		//			   Case 2 is for if n's last name is lexicographically before p's
		//			   last name.
		else if (((n.getData().getLastName().compareTo(p.getData().getLastName()) == 0)
				&&(n.getData().getFirstName().compareTo(p.getData().getFirstName()) < 0)||
				(n.getData().getLastName().compareTo(p.getData().getLastName()) < 0))) {
			// add at beginning of list

			// set the next node of n to 
			//point to what head points to
			n.setNext(head);
			head = n;
			count++;
		}
		
		else {
		// In English: Case 3 is for if the last names are the same but n's first name
		// 			   is lexicographically after p's first name.
		// 			   OR 
		//			   Case 3 is for if n's last name is lexicographically after p's
		//			   last name.
			while ((p!=null)&&((n.getData().getLastName().compareTo(p.getData().getLastName()) == 0)
					&&(n.getData().getFirstName().compareTo(p.getData().getFirstName()) > 0)||
					(n.getData().getLastName().compareTo(p.getData().getLastName()) > 0))) {
					
					q = p;
					p = p.getNext();

			}
			// put the new node in the
			// list after p
			q.setNext(n);
			n.setNext(p);
			count++;
			
			
		}
		
		
	}
	
	// add a Test node ordered from highest to lowest
	public void insertTest(Node n) {
		// Case 1: If the list is empty:
		if (head == null) {
			head = n;
			count++;
		}
		
			
		// Case 2: If n's score is higher p's score:
		else if (n.getInfo().getScore() > head.getInfo().getScore()) {
				// set the next node of n to 
				// point to what head points to
				n.setNext(head);
				head = n;
				count++;
		}
		
		else {
				// Use a pair of node pointers to traverse
				// the list. One pointer follows behind
				// the other.
				Node p = head;
				Node q = null;

				// walk down the list until we get
				// The while loop uses a short-circuit evaluation.
				// Ask if you don't know what that means.
				while ((p!=null)&&(n.getInfo().getScore() < p.getInfo().getScore())) {
					// determine if we have reached the
					// end of the list.
						q = p;
						p = p.getNext();
				}
				// put the new node in the
				// list after p
				q.setNext(n);
				n.setNext(p);
				count++;	
		}	
		
	}
	// End add methods.
	
	int size() {
		// Method to return the number of nodes in the list
		return count;
	}
	
	// Methods to display the contents of 
	// the linked list depending on order wished.
	public String displayAlphabetically() {
		String s = "";
		Node st = head;
		while (st != null)
		{
			s += String.format("%-15s%-17s%-5s%5s  \n", st.getData().getLastName(), 
					st.getData().getFirstName(),
					st.getInfo().getScore(), st.getInfo().getGrade());
			
			st = st.getNext();
		}
		return s;
	}
	
	public String displayNumerically() {
		String s = "";
		Node st = head;
		while (st != null)
		{
			s += String.format("%-15s%-17s%-5s%5s  \n", st.getData().getLastName(), 
				st.getData().getFirstName(), 
				st.getInfo().getScore(), st.getInfo().getGrade());
			
			st = st.getNext();
		}
		return s;
	}
	
	
}  // end class LinkedList
