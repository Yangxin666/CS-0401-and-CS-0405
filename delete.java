import java.io.*;
import java.util.*;
public class delete
{
	public static void main( String[] args ) throws Exception
	{
		// scan in the file contaning the list of all the identifiers of the clients
		Scanner infile = new Scanner (new File( args[0] ));
		// declare a hashset afterdelete which has built-in method to  
		HashSet<String> afterdelete=new HashSet<String>();
		while (infile.hasNext())
		{
			// store each client's identifier as a string
			String identifier=infile.next();
			// add identifier to afterdelete, if it's included, it returned false and not added it
			afterdelete.add(identifier);
		}	
		for (String s: afterdelete)
		{
			System.out.println(s);
		}
	}
}