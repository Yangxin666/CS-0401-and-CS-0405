import java.io.*;
import java.util.*;


public class Swamp
{
	static int[][] swamp;  

 	public static void main(String[] args) throws Exception
	{
		int[] dropInPt = new int[2]; 
		swamp = loadSwamp( args[0], dropInPt );
		int row=dropInPt[0], col = dropInPt[1];
		String path = ""; 
		dfs( row, col, path );
	} 

	private static void printSwamp(String label, int[][] swamp )
	{
		System.out.println( label );
		System.out.print("   ");
		for(int c = 0; c < swamp.length; c++)
			System.out.print( c + " " ) ;
		System.out.print( "\n   ");
		for(int c = 0; c < swamp.length; c++)
			System.out.print("- ");
		System.out.print( "\n");

		for(int r = 0; r < swamp.length; r++)
		{	System.out.print( r + "| ");
			for(int c = 0; c < swamp[r].length; c++)
				System.out.print( swamp[r][c] + " ");
			System.out.println("|");
		}
		System.out.print( "   ");
		for(int c = 0; c < swamp.length; c++)
			System.out.print("- ");
		System.out.print( "\n");
	}

	private static int[][] loadSwamp( String infileName, int[] dropInPt  ) throws Exception
	{
		Scanner infile = new Scanner( new File (infileName) );
		while ( infile.hasNext() )
		{
			int Rows=infile.nextInt();
			dropInPt[0]=infile.nextInt();
			dropInPt[1]=infile.nextInt();
			swamp=new int[Rows][Rows];
			for (int i=0;i<Rows;++i)
			{
				for (int j=0;j<Rows;++j)
				{
					swamp[i][j]=infile.nextInt();
					
				}
			}
		}
		infile.close();
		return swamp;		
	}

	static void dfs( int row, int col, String path ) // dfs = DEPTH FIRST SEARCH
	{
		path+="["+row+","+col+"]";
		if ((row==0)||(col==0)||(row==swamp.length-1)||(col==swamp.length-1))
		{             
			System.out.println(path);
			return ;
		}
		if (swamp[row-1][col]==1)
		{
			swamp[row][col]=-1;
			dfs(row-1,col,path);
			swamp[row][col]=1;
		}
		if (swamp[row-1][col+1]==1)
		{
			swamp[row][col]=-1;
			dfs(row-1,col+1,path);
			swamp[row][col]=1;
		}
		if (swamp[row][col+1]==1)
		{
			swamp[row][col]=-1;
			dfs(row,col+1,path);
			swamp[row][col]=1;
		}
		if (swamp[row+1][col+1]==1)
		{
			swamp[row][col]=-1;
			dfs(row+1,col+1,path);
			swamp[row][col]=1;
		}
		if (swamp[row+1][col]==1)
		{
			swamp[row][col]=-1;
			dfs(row+1,col,path);
			swamp[row][col]=1;
		}
		if (swamp[row+1][col-1]==1)
		{
			swamp[row][col]=-1;
			dfs(row+1,col-1,path);
			swamp[row][col]=1;
		}
		if (swamp[row][col-1]==1)
		{
			swamp[row][col]=-1;
			dfs(row,col-1,path);
			swamp[row][col]=1;
		}
		if (swamp[row-1][col-1]==1)
		{
			swamp[row][col]=-1;
			dfs(row-1,col-1,path);
			swamp[row][col]=1;
		}
	}
}