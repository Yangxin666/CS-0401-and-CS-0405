import java.io.*;
import java.util.*;
public class Exercise1
{
	public static void main( String args[] ) 
	{
		if (args.length < 1)
		{
			System.out.println("\nYou must enter an input filename on cmd line!\n");
			System.exit(0);
		}
		// MODIFY, REPLACE, ADD LOOP CODE, ADD TRY CATCH BLOCK(S) AS NEEDED BELOW
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
				Scanner keyboard=new Scanner(System.in);
				System.out.print(filename + " not found. "+"Enter valid filename: ");
				filename=keyboard.nextLine();
			}
		}while (infile==null);
		// DONT PUT THIS IN THE LOOP AND DONT USE TRY CATCH IN/ON THIS WHILE LOOP
		while (infile.hasNext())
		{
			String token = infile.next(); // read a string from infile
			System.out.printf("%s\n", token);
		}
	}
} //END CLASS