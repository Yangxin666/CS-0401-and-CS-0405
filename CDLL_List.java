import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	
	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_List( String fileName, String insertionMode ) throws Exception
	{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );	
			while ( infile.ready() )
			{	@SuppressWarnings("unchecked") 
				T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
				if ( insertionMode.equals("atFront") )
					insertAtFront( data ); 	
				else if ( insertionMode.equals( "atTail" ) )
					insertAtTail( data ); 
				else
					die( "FATAL ERROR: Unrecognized insertion mode <" + insertionMode + ">. Aborting program" );
			}
			infile.close();
	}	
	
	private void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}
		
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
	public int size() //O(n)
	{
		int size=0;
		CDLL_Node<T> curr=head;
		do{
			curr=curr.getNext();
			size++;
		} while (curr!=head);
		return size;
	}


	// TACK A NEW NODE ONTO THE FRONT OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtFront(T data) // O(1)
	{
		// BASE CASE WRITTEN FOR YOU
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.setNext( newNode );
			newNode.setPrev( newNode );
			head = newNode;
		}
		else
		{	
			newNode.setPrev(head.getPrev());
			(head.getPrev()).setNext(newNode);
			newNode.setNext(head);
			head.setPrev(newNode);
			head=newNode;
		}
		// NOT EMPTY. INSERT NEW NODE  BETWEEN HEAD POINTER AND 1ST NODE
		// MAKE HEAD POINT TO NEW NODE
	}
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data) // O(1) very similar to insertAtTail the only difference is not changing the head pointer! 
	{
		// BASE CASE WRITTEN FOR YOU
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.setNext( newNode );
			newNode.setPrev( newNode );
			head = newNode;
		}
		else
		{
			newNode.setPrev(head.getPrev());
			(head.getPrev()).setNext(newNode);
			newNode.setNext(head);
			head.setPrev(newNode);	
		}
		// NOT EMPTY. INSERT NEW NODE AFTER THE LAST/TAIL NODE
	}
	
	// RETURN TRUE/FALSE THIS LIST CONTAINS A NODE WITH DATA EQUALS KEY
	public boolean contains( T key ) //O(n)
	{
		return (search(key)!=null);
	}

	// RETURN REF TO THE FIRST NODE (SEARCH CLOCKWISE FOLLOWING next) THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	public CDLL_Node<T> search( T key ) // O(n)
	{
		CDLL_Node<T> curr=head;
		do{
			if (curr.getData().equals(key))
			{
				return curr;
			}
			curr=curr.getNext();
		} while (curr!=head);
		return null;
	}
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()	// O(n)
	{
		String toString="";
		CDLL_Node<T> curr=head;
		do{
			if (curr.getNext()==head)
			{
				toString+=curr.getData();
			}
			else
			{
				toString+=curr.getData()+"<=>";
			}
			curr=curr.getNext();
		} while (curr!=head);
		return toString;
	}
	
} // END CDLL_LIST CLASS