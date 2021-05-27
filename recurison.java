import java.io.*;
import java.util.*;

public class recursion
{
	public void f(int n)
	{
		if (n==0); return
		f(--n);
		System.out.println(n);
	}// END MAIN
} // END