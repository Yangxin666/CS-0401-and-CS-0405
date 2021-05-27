import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		CDLL_Node<T> newNode = new CDLL_Node( data,null,null);
		if (head==null)
		{
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			head=newNode;
		}
		else
		{
			newNode.setPrev(head.getPrev());
			(head.getPrev()).setNext(newNode);
			newNode.setNext(head);
			head.setPrev(newNode);	
		}
	}

	
	public int size()
	{	
		if (head==null) return 0;
		int size=0;
		CDLL_Node<T> curr=head;
		do{
			size++;
			curr=curr.getNext();
		} while (curr!=head);
		return size;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{	
		CDLL_Node<T> curr=head;
		do{
			if ((curr.getData()).equals(key))
			{
				return curr;
			}
			curr=curr.getNext();
		} while (curr!=head);
		return null;
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		if (head==null) return "";
		String toString="";
		CDLL_Node<T> curr=head;
		if (head==null) return "";
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
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		(deadNode.getPrev()).setNext(deadNode.getNext());
		(deadNode.getNext()).setPrev(deadNode.getPrev());
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() < 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		
		// OK THERE ARE AT   2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			T deadName = curr.getData();
			System.out.println("stopping on "+deadName+" to delete "+deadName);
			if ((head.getData()).equals(curr.getData()) && skipCount>0)
			{
				head=head.getNext();
			}
			if ((head.getData()).equals(curr.getData()) && skipCount<0)
			{
				head=head.getPrev();
			}
			this.removeNode(curr);
			System.out.println("deleted. list now: "+this);
			while (size()>1 && skipCount>0)
			{
				int n=skipCount-1;
				curr=curr.getNext(); // ? although the deadName is deleted, but curr still point to it now, can use curr.getNext(), curr.getPrev()
				System.out.println("resuming at "+curr.getData()+","+" skipping "+curr.getData()+" + "+n+" nodes COUNTERCLOCKWISE after");
				for (int i=0;i<skipCount;i++)
				{
					curr=curr.getNext();
				}
				if ((head.getData()).equals(curr.getData()))
				{
					head=head.getNext();
				}
				System.out.println("stopping on "+curr.getData()+" to delete "+curr.getData());
				this.removeNode(curr);
				System.out.println("deleted. list now: "+this);
			}
			while (size()>1 && skipCount<0)
			{
				int n=(-1)*skipCount-1;
				curr=curr.getPrev();
				System.out.println("resuming at "+curr.getData()+","+" skipping "+curr.getData()+" + "+n+" nodes COUNTERCLOCKWISE after");
				for (int i=0;i<(-1)*skipCount;i++)
				{
					curr=curr.getPrev();
				}
				if ((head.getData()).equals(curr.getData()))
				{
					head=head.getPrev();
				}
				System.out.println("stopping on "+curr.getData()+" to delete "+curr.getData());
				this.removeNode(curr);
				System.out.println("deleted. list now: "+this);
			}
		} while (size() > 1 );
	}	
} // END CDLL_LIST CLASS