/*
	ExceptionsDemo3.java

	- handles the exception thrown by Scanner with a try catch block
*/

import java.io.*;
import java.util.*;
public class ExceptionsDemo3
{
	public static void main( String args[] )
	{
		// Fhe following statement could throw a checked exception
		// since it is a checked exception we must &quot;advertise&quot;
		// the fact that we are not handling this exception in our code.
		// Instead we &quot;throw&quot; the Exception. Thus the clause
		// throws FileNotFoundException after the main method's signature.

		
		Scanner infile=null;
		String filename = args[0];
		do{
			try 
			{
				infile = new Scanner (new File( filename) );
			}
			//put more specific exceptions first 
			catch (FileNotFoundException fnfe )
			{
				System.out.println(fnfe + "\n");
				System.exit(0);
			}
			catch (Exception e ) // YOU SHOULD EXIT WHEN YOU CATCH A GENERAL EXCEPTION THAT DID NOT MATCH ANY EXPECTED ERROR
			{
				System.out.println("Exception other than FileNotFoundException caught: " + e + "\n");
				System.exit(0); 
			}
			}while (!filename.equals("input1.txt");

		String token = infile.next(); // read a string from infile
		System.out.println("1st string of the files was: " + token );

	} //END main
} //EOF