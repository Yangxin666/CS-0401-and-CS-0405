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

////////////////////////////////////////////////////////////////////////////////
class BSTreeP6<T>
{
	private BSTNode<T> root;
	private int nodeCount;
	private boolean addAttemptWasDupe=false;
	@SuppressWarnings("unchecked")
	public BSTreeP6( String infileName ) throws Exception
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

	public int countNodes()
	{
		return countNodes( root );
	}
	private int countNodes( BSTNode root)
	{
		if (root==null) return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
	// DO NOT MODIFY ANYTHING ABOVE THIS LINE.  YOU FILL IN ALL THE CODE BELOW
	// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

	public int countLeaves()
	{	return countLeaves( root );
	}
	private int countLeaves( BSTNode root)
	{	
		if (root==null)	return 0;
		else if (root.left==null && root.right==null) return 1;
		return countLeaves(root.left)+countLeaves(root.right);
	}
	public int countInteriorNodes()
	{	return countInteriorNodes( this.root );
	}
	private int countInteriorNodes( BSTNode root)
	{	
		if (root==null)	return 0;
		if (root.left==null && root.right==null) return 0;	
		if (root==this.root) return countInteriorNodes(root.left)+countInteriorNodes(root.right);
		return 1+countInteriorNodes(root.left)+countInteriorNodes(root.right);
	}
	public int countLevels()
	{
		return countLevels( root );
	}
	private int countLevels( BSTNode root)
	{	
		int L=0, R=0;
		if (root==null) return 0;
		L=countLevels(root.left);
		R=countLevels(root.right);
		return (1+Math.max(L,R));
	}
	public int[] calcLevelCounts()
	{	int levelCounts[] = new int[countLevels()];
		calcLevelCounts( root, levelCounts, 0 );
		return levelCounts;
	}
	private void calcLevelCounts( BSTNode root, int levelCounts[], int level )
	{
		if (root==null) return;
		levelCounts[level]++;
		calcLevelCounts(root.left,levelCounts,level+1);
		calcLevelCounts(root.right,levelCounts,level+1);	
	}
	// COPY CONSTRUCTOR CREATES BST IDENTICAL IN EVERY RESPECT TO OTHER
	public BSTreeP6(  BSTreeP6<T> other )
	{
		if (other.root==null)	root=null;
		else
			copy(other.root);
	}
	
	private void copy( BSTNode<T> Node)
	{
		if (Node!=null)
		{
			add(Node.key);
			copy(Node.left);
			copy(Node.right);
		}
	}
}