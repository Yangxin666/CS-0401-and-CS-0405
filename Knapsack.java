import java.io.*;
import java.util.*;
public class Knapsack
{
	public static void main( String[] args ) throws Exception
	{
		int number; // java's char type is just a 16 bit number
		int i;
		int[] array=new int[16];
		Scanner infile = new Scanner (new File( args[0] ));
		int count=0;
		while (infile.hasNext() && count<=15)
		{
			array[count]=infile.nextInt();
			System.out.print(array[count]+" ");
			count++;
		}
		System.out.println();
		int sum=infile.nextInt();
		System.out.println(sum);
		for (number=0 ; number<0xFFFF  ; ++number)
		{
			int n=0;
			for (i = 15 ; i >=0  ; --i )
			{
				/* shift i'th bit to the end position */
				if ( (number >> i) % 2 == 1 ) /* true iff i'th bit is a 1 */
				{
					n+=array[15-i];	
				}
			}
			int m=15;
			if (sum==n) 
			{
				while (m>=0)
				{
					if ((number >> m)%2==1)
					{
						System.out.print(array[15-m]+" ");
					}
					m--;
				}
				System.out.println();
			}		
		}
	} // END MAIN
} // END 
