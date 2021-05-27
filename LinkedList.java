import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list, head is just a reference variable 

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	public LinkedList( String fileName, boolean orderedFlag )
	{
		head=null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data) // O(1)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString() // O(n)
	{
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################


	public int size() // OF COURSE MORE EFFICIENT TO MAINTAIN COUNTER. BUT YOU WRITE LOOP! O(n)
	{
		int size=0;
		Node<T> curr=head;
		while (curr!=null)
		{
			size++;
			curr=curr.getNext();
		}
		return size; 
	}
	public boolean empty() // O(1)
	{
		return (size()==0); 
	}
	public boolean contains( T key ) // O(n)
	{
		
		return (search(key)!=null); 
	}
	public Node<T> search( T key ) // O(n)
	{
		for (Node<T> curr=head; curr!=null;curr=curr.getNext())
        {
            if (curr.getData().equals(key)) return curr;
        }   
        return null;  
	}
	public void insertAtTail(T data) //O(n)
	{
		if (head==null) 
        {
            insertAtFront(data);
            return;
        }
        else
        {
            Node<T> curr=head;
            while (curr.getNext()!=null)
            {
                curr=curr.getNext();
            }
            curr.setNext(new Node(data, null));
        }
	}
	public void insertInOrder(T  data) // O(n)
	{
		Comparable dataC = (Comparable) data;
		if (head==null || dataC.compareTo(head.getData())<=0)
		{
			insertAtFront(data);
		}
		else
		{
			Node<T> curr=head; 
			while (curr.getNext()!=null && dataC.compareTo((curr.getNext().getData()))>0)
			{
				curr=curr.getNext();
			}
			if (curr.getNext()==null)
			{
				insertAtTail(data);
			}
			else
			{
				curr.setNext(new Node<T> (data,curr.getNext()));
			}
		}
	}
	public boolean remove(T key) // O(n)
	{
		if (head==null) 
		{
			return false;
		}
		if (head.getData()==key) 
		{
			return removeAtFront();
		}
		Node<T> curr=head;
		while (curr.getNext()!=null && (curr.getNext()).getData()!=key)
		{
			curr=curr.getNext();
		}
		if (curr.getNext()==null) 
		{
			return false;
		}
		curr.setNext((curr.getNext()).getNext());
		return true;
	}
	
	public boolean removeAtTail()	// O(n)
	{
		if (size()==0)	
		{
			return false;
		}
		if (size()==1)
		{
			head=null;
			return true;
		}
		Node<T> curr=head; 
		while ((curr.getNext()).getNext()!=null) 
		{
			curr=curr.getNext();
		}
		curr.setNext(null);
		return true;
	}
	public boolean removeAtFront() // O(1)
	{
		if (size()==0)
		{
			return false;
		}
		else
		{
			head=head.getNext(); // question: pointer to first element, but where is original first element?
			return true;
		}
	}
	public LinkedList<T> union( LinkedList<T> other ) // O(n^3)
	{
		LinkedList<T> NewList=new LinkedList<T>();
		for (Node<T> curr=head;curr!=null;curr=curr.getNext())
		{
			if (!NewList.contains(curr.getData()))
			{
				NewList.insertInOrder(curr.getData());
			}
		}
		for (Node<T> curr=other.head; curr!=null; curr=curr.getNext())
		{
			if (!NewList.contains(curr.getData()))
			{
				NewList.insertInOrder(curr.getData());
			}
		}
		return NewList;
	}
	public LinkedList<T> inter( LinkedList<T> other ) 
	{
		LinkedList<T> NewList=new LinkedList<T>();
		for (Node<T> curr=other.head; curr!=null; curr=curr.getNext())
		{
			if (contains(curr.getData()) && !NewList.contains(curr.getData()))
			{
				NewList.insertInOrder(curr.getData());
			}
		}
		return NewList;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> NewList=new LinkedList<T>();	
		for (Node<T> curr=head; curr!=null; curr=curr.getNext())
		{
			if (!other.contains(curr.getData()) && !NewList.contains(curr.getData()))
			{
				NewList.insertInOrder(curr.getData());
			}
		}
		return NewList;
	}
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return (this.diff(other).union(other.diff(this)));
	}

} //END LINKEDLIST CLASS
