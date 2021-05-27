import java.io.*;
import java.util.*;

public class Project2
{
	public static void main( String args[] ) throws Exception 
	{
	    int number=-1;
		do{
			System.out.print("Enter int in range 1..100 inclusive: ");
			Scanner kbd=new Scanner(System.in);
			try
			{
				number=kbd.nextInt();
				if (number<1 || number>100) 	
				{
					throw new NumberOutOfRangeException();
				}
			}
			catch (	InputMismatchException e )
			{
				System.out.println("Input was not an integer");
				kbd.nextLine();
			}
			catch (	NumberOutOfRangeException e )
			{
				System.out.println(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println(e);
				System.exit(0);
			}
		} while (number<1 || number>100);
		System.out.format("Thank you. You entered %d\n", number );
	}
	public static class NumberOutOfRangeException extends Exception //why this class should be static?
	{
		public NumberOutOfRangeException()
		{
			super("Number out of range. Must be in 1..100");
		}
	}
}

	