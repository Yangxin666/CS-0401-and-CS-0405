import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////
class BSTNode<T>
{	T key;
	BSTNode<T> left,right;
	BSTNode( T key, BSTNode<T> left, BSTNode<T> right )
	{	this.key = key;
		this.left = left;
		this.right = right;
	}
}

///////////////////////////////////////////////////////////////////////////////
class Queue<T>
{	LinkedList<BSTNode<T>> queue;
	Queue() { queue =  new LinkedList<BSTNode<T>>(); }
	boolean empty() { return queue.size() == 0; }
	void enqueue( BSTNode<T>  node ) { queue.addLast( node ); }
	BSTNode<T>  dequeue() { return queue.removeFirst(); }
	// DEQUEUE THROWS NO SUCH ELEMENT EXCEPTION IF Q EMPTY
}

////////////////////////////////////////////////////////////////////////////////
class BSTreePP<T>
{
	private BSTNode<T> root;
	private int nodeCount;
	private boolean addAttemptWasDupe=false;
	
	public BSTreePP()
	{
		nodeCount=0;
		root=null;
		
	}
	
	@SuppressWarnings("unchecked")
	public BSTreePP( String infileName ) throws Exception
	{
		nodeCount=0;
		root=null;
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
			add( (T) infile.readLine() ); // THIS CAST RPODUCES THE WARNING
		infile.close();
	}

	public int size()
	{
		return nodeCount; // LOCAL VAR KEEPING COUNT
	}

	// DUPES BOUNCE OFF & RETURN FALSE ELSE INCR COUNT & RETURN TRUE
	@SuppressWarnings("unchecked")
	public boolean add( T key )
	{	addAttemptWasDupe=false;
		root = addHelper( this.root, key );
		if (!addAttemptWasDupe) ++nodeCount;
		return !addAttemptWasDupe;
	}
	@SuppressWarnings("unchecked")
	private BSTNode<T> addHelper( BSTNode<T> root, T key )
	{
		if (root == null) return new BSTNode<T>(key,null,null);
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 )
			{ addAttemptWasDupe=true; return root; }
		else if (comp < 0)
			root.left = addHelper( root.left, key );
		else
			root.right = addHelper( root.right, key );

		return root;
    } // END addHelper


	// ITS A SEARCH - ONE OF FEW OPS YOU CAN DO ITERATIVELY
	public boolean contains( T key )
	{
		return contains( this.root, key  );
	}
	@SuppressWarnings("unchecked")
	private boolean contains( BSTNode<T> root, T key  )
	{
		if (root == null) return false;
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 ) return true;
		if (comp < 0) return contains(root.left, key );
		return contains(root.right, key );
	}


	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
	// DO NOT MODIFY ANYTHING ABOVE THIS LINE.  YOU FILL IN ALL THE CODE BELOW
	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

	// COUNT NODES REQUIRES RECURSION
	public int countNodes() // what is the purpose of making a public and private method?
	{
		return countNodes( this.root );
	}
	private int countNodes( BSTNode<T> root )
	{	
		if (root==null)	return 0;
		return 1+countNodes(root.left)+countNodes(root.right);
		// YOUR CODE HERE. MUST USE RECURSION
	}

	// INORDER TRAVERSAL REQUIRES RECURSION
	public void printInOrder()
	{	printInOrder( this.root );
		System.out.println();
	}
	private void printInOrder( BSTNode<T> root )
	{	// IN ORDER = LVR
		// YOUR CODE HERE. MUST USE RECURSION
		if (root==null)	return;
		printInOrder(root.left);
		System.out.print(root.key+" ");
		printInOrder(root.right);
	}

	// PRE ORDER TRAVERSAL REQUIRES RECURSION
	public void printPreOrder()
	{	printPreOrder( this.root );
		System.out.println();
	}
	private void printPreOrder( BSTNode<T> root )
	{	// POSTORDER = VLR
		// YOUR CODE HERE. MUST USE RECURSION
		if (root==null)	return;
		System.out.print(root.key+" ");
		printPreOrder(root.left);
		printPreOrder(root.right);
	}

	// POST ORDER TRAVERSAL REQUIRES RECURSION
	public void printPostOrder()
	{	printPostOrder( this.root );
		System.out.println();
	}
	private void printPostOrder( BSTNode<T> root )
	{	// POSTORDER = LRV
		// YOUR CODE HERE. MUST USE RECURSION
		if (root==null) return;
		printPostOrder(root.left);
		printPostOrder(root.right);
		System.out.print(root.key+" ");
	}
	
	private void levelOfnodes( BSTNode<T> root, ArrayList<T> levels)
	{
		if (root==null) return;
		Queue<T> Q=new Queue<T>();
		Q.enqueue(root);
		while (Q.empty()==false) 
		{
			BSTNode<T> curr=Q.dequeue();
			levels.add(curr.key);
			if (curr.left!=null)
			{
				Q.enqueue(curr.left);
			}
			if (curr.right!=null)
			{
				Q.enqueue(curr.right);
			}
		}
	}
	
	private void inOrderHelper (BSTNode<T> thisRoot, ArrayList<T> keys)
	{
		 if (thisRoot==null)	return;
		 inOrderHelper(thisRoot.left, keys);
		 keys.add(thisRoot.key);
		 inOrderHelper(thisRoot.right, keys);
	}
	
	public int countLevels()
  	{
    	return countLevels( root ); 
  	}
  	
  	private int countLevels( BSTNode root)
  	{
    	if (root==null) return 0;
    	return 1 + Math.max( countLevels(root.left), countLevels(root.right) );
  	}
  
  	public int[] calcLevelCounts()
  	{
    	int levelCounts[] = new int[countLevels()];
    	calcLevelCounts( root, levelCounts, 0 );
    	return levelCounts;
  	}
  	
  	private void calcLevelCounts( BSTNode root, int levelCounts[], int level )
  	{
    	if (root==null)return;
    	++levelCounts[level];
    	calcLevelCounts( root.left, levelCounts, level+1 );
    	calcLevelCounts( root.right, levelCounts, level+1 );
	}
			
	private void addKeysInBalanceOrder ( ArrayList<T> keys, int lo, int hi, BSTreePP<T> balancedBST )
	{
		if (lo>hi)	return;
		int index=(lo+hi)/2;
		balancedBST.add(keys.get(index));
		addKeysInBalanceOrder(keys, lo, index-1, balancedBST);
		addKeysInBalanceOrder(keys, index+1, hi, balancedBST);		
	}
			
	public void prettyPrint()
	{
		ArrayList<T> keys = new ArrayList<T>();
		ArrayList<T> levels = new ArrayList<T>();
		inOrderHelper(this.root, keys);
		levelOfnodes(this.root, levels);
		int[] array=calcLevelCounts();
		int index=0;
		for (int l=0; l<array.length; l++)
		{
			int count=0;
			int numberlevel=array[l];
			while (count<numberlevel)
			{
				count++;
				if (count==1)
				{
					T key=levels.get(index);
					int num=keys.indexOf(key);
					index++;
					for (int m=0; m<num;m++)
					{
						System.out.print(" ");
					}
					System.out.print(key);
				}
				if (count>1)
				{
					T oldkey=levels.get(index-1);
					int oldindex=keys.indexOf(oldkey);
					T newkey=levels.get(index);
					int newindex=keys.indexOf(newkey);
					index++;
					for (int n=0; n<newindex-oldindex-1;n++)
					{
						System.out.print(" ");
					}
					System.out.print(newkey);
				}
			}	
			System.out.println();	
		}
	}

	public BSTreePP<T> makeBalancedCopyOf( )
	{
		ArrayList<T> keys = new ArrayList<T>();
		inOrderHelper(this.root, keys);
		BSTreePP<T> balancedBST = new BSTreePP<T>();
		int lo=0;
		int hi=keys.size()-1;
		addKeysInBalanceOrder(keys, lo, hi, balancedBST);
		return balancedBST;   
	}
	
		
		
}