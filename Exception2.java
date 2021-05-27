/*
	ExceptionsDemo2.java

	- illustrates a checked exception
		* FileReader() C'Tor throws IOException
		* IOException is a checked exception
		* checked exceptions must either be
		  - handled with try/catch
		  - or be advertised with a throws IOException clause in the
		    signature of the enclosing method
*/

import java.io.*;
import java.util.*;
public class ExceptionsDemo2 
{
	public static void main( String args[] ) throws IOException
	{
		// the following statement could throw a checked exception
		// since it is a checked exception we must advertise
		// the fact that we are not handling this exception in our code.
		// Instead we throw the Exception. Thus the clause
		// 'throws IOException'  on the end of main's signature.

		File f = new File( args[0] ); // File C'tor thorw IO exception if file does not exist
		Scanner infile = new Scanner (f );

		String token = infile.next(); // read a string from infile
		System.out.println("1st string of the files was: " + token );

	} //END main
} //EOF