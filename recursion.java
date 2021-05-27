import java.io.*;
import java.util.*;

public class recursion
{
	public static void main( String args[] )
	{
		f(5);
	}// END MAIN
	
	static voidhg  f(int n)
	{
		if (n==0) return;
		f(--n);
		System.out.print(n);
	}
} // END