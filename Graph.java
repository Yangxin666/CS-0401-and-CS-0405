/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/       

import java.io.*;
import java.util.*;

//////////////////////////////////////////////////////////////////////////////////////
class Edge
{
	int dest, weight;
	Edge next;
	// a Constructor that takes dest,weight, next why do not need setters and getters now?
	public Edge (int dest, int weight, Edge next)
	{
		this.dest=dest;
		this.weight=weight;
		this.next=next;
	}
}



//////////////////////////////////////////////////////////////////////////////////////
class Graph 
{
	final int NO_EDGE = -1; // all real edges are positive
	Edge G[];              // will point to a 2D array to hold our graph data
	int numEdges;
	
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
		
//		T E M P O R A R Y    C O D E    T O    V E R I F Y    P R I V A T E 
// 		M E T H O D S    W E    C A N T    C A L L    F R O M   T E S T E R 
//		      ........R E M O V E   A F T E R   T E S T I N G .......
/*
		for (int node = 0 ; node < G.length ; ++node )
		{
			System.out.format( "DEBUG:: in (%d)=%d  ",node,inDegree(node) );
			System.out.format( "out(%d)=%d  ",node,outDegree(node) );
			System.out.format( "deg(%d)=%d\n",node,degree(node) );
		}
*/
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new Edge[dimension]; 		// N x N ARRAY OF ZERO
		numEdges=0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			// read in the row,col,weight // that eat us this line
			// call add edge
			int fromNode=graphFile.nextInt();
			int toNode=graphFile.nextInt();
			int weight=graphFile.nextInt();
			addEdge(toNode,weight,fromNode);
			
		}

	} // END readGraphFile\
	
	private void addEdge( int toNode, int weight, int fromNode)
	{
		G[fromNode]=new Edge(toNode,weight,G[fromNode]);
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	
	private boolean hasEdge(int fromNode, int toNode)
	{
  		Edge curr=G[fromNode];
  		while (curr.next!=null) 
			if (curr.dest==toNode)	return true;
		return false;
	}

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int count=0;
		for (int i=0;i<G.length;i++)
		{
			Edge curr=G[i];
			while (curr!=null)
			{
				if (curr.dest==node)
					count++;
				curr=curr.next;
			}
		}
		return count;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int count=0;
		Edge curr=G[node];
		while (curr!=null)
		{
			curr=curr.next;
			count++;
		}
		return count;	
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY 
	private int degree(int node)
	{
		return inDegree(node)+outDegree(node);
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		int max=0;
		for (int node=0;node<G.length;node++)
			if (outDegree(node)>=max)	max=outDegree(node);
		return max;
	}

	public int maxInDegree()
	{
		int max=0;
		for (int node=0; node<G.length;node++)
			if (inDegree(node)>=max)	max=inDegree(node);
		return max;
	}
	// CALL OUTDEGREE OF EVERY NODE KEEP THE LOWEST ONE AND UPDATE 
	public int minOutDegree()
	{
		int min=8;
		for (int node=0;node<G.length;node++)
			if (outDegree(node)<min)	min=outDegree(node);
		return min;
	}
	public int minInDegree()
	{
		int min=8;
		for (int node=0;node<G.length;node++)
			if (inDegree(node)<min)	min=inDegree(node);
		return min;
	}	
	
	public int maxDegree()
	{
		int max=0;
		for (int node=0; node<G.length;node++)
			if (degree(node)>=max)	max=degree(node);
		return max;
	}
	// CALL DEGREE ON EVERY NOTE 
	public int minDegree()
	{
		int min=8;
		for (int node=0;node<G.length;node++)
			if (degree(node)<min)	min=inDegree(node);
		return min;	
	}
	
	public void removeEdge(int fromNode, int toNode) throws Exception
	{
		/* if caller passes in a row col pair that 
		   out of bound or has no edge there, you must 
		   throw and catch an exception. See my output.
		   if the edge is there then remove it by writing 
		   in a NO_EDGE value at that coordinate in the grid	
		*/
		try{
			if (fromNode>=0 && fromNode<G.length && toNode>=0 && toNode<=G.length)
			{
				if (G[fromNode].dest==toNode)	
				{
					if (G[fromNode].next==null)
					{
						G[fromNode]=null;
					}
					else
					{
						G[fromNode]=G[fromNode].next;
					}
				}
				else
				{
					Edge curr=G[fromNode];
					while (curr.next!=null && (curr.next).dest!=toNode)
					{
						curr=curr.next;
					}
					if (curr.next==null)
					{
						throw new Exception("Non Existent Edge Exception: removeEdge("+fromNode+","+toNode+")");
					}
					else
					{
						curr.next=(curr.next).next;	
					}
				}
			}
			else
			{
				throw new Exception("Non Existent Edge Exception: removeEdge("+fromNode+","+toNode+")");
			
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		
	}
	
	public String toString()
	{	
		String the2String="";
		for (int r=0 ; r < G.length ;++r )
		{
			the2String += String.format("%3s",r+": ");
			Edge curr=G[r]; 
			while (curr!=null)
			{
				int w=curr.weight;
				int out=curr.dest;
				curr=curr.next;
				the2String += String.format("%3s"," -> "+"["+out+","+w+"]");
			}
			the2String+="\n";
		}
		return the2String;
	} // END TOSTRING
	
	
	
	
} //EOF
