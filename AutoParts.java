import java.util.*;
import java.io.*;

public class AutoParts
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader num2nameFile = new BufferedReader( new FileReader( "num2name.txt" ) );
		BufferedReader num2quantFile = new BufferedReader( new FileReader( "num2quant.txt" ) );
		HashMap<Integer,String> num2name = new HashMap<Integer,String>();
		HashMap<Integer,Integer> num2quant = new HashMap<Integer,Integer>();
		
		while (num2nameFile.ready())
		{
			ArrayList<String> combo = new ArrayList<String>( Arrays.asList(num2nameFile.readLine().trim().split("\\s+")));
			num2name.put(Integer.parseInt(combo.get(0)),combo.get(1));
		}
		
		while (num2quantFile.ready())
		{
			ArrayList<String> combo = new ArrayList<String>( Arrays.asList(num2quantFile.readLine().trim().split("\\s+")));
			num2quant.put(Integer.parseInt(combo.get(0)),Integer.parseInt(combo.get(1)));
		}
		
		
		
		System.out.println("PART NUMBER TO PART NAME\n");
		for (Integer s: num2name.keySet())
			System.out.println(s+"\t"+num2name.get(s));
			
		System.out.println("\nJOIN OF PART NUMBER TO NAME TO QUANTITY\n");
		for (Integer s: num2name.keySet())
			System.out.println(s+"\t"+num2name.get(s)+"\t"+num2quant.get(s));
	}
}