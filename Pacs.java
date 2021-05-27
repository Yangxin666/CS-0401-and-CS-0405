/*	Pacs.java */

import java.io.*;
import java.util.*;

public class Pacs
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader pacsFile= new BufferedReader(new FileReader("pacs.txt"));
		BufferedReader membersFile= new BufferedReader(new FileReader("members.txt"));
		TreeMap<String,TreeSet<String>> member2pacs=new TreeMap<String,TreeSet<String>>();
		TreeMap<String,TreeSet<String>> pacs2member=new TreeMap<String,TreeSet<String>>();
		while (pacsFile.ready())
		{
			pacs2member.put(pacsFile.readLine(), new TreeSet<String>());
		}
		while (membersFile.ready())
		{
			String[] tokens = membersFile.readLine().trim().split("\\s+");
			String member = tokens[0];
			TreeSet<String> pacs=new TreeSet<String>();
			TreeSet<String> members=new TreeSet<String>();
			for (int i=1; i<tokens.length; i++)
			{
				pacs2member.get(tokens[i]).add(member);
				pacs.add(tokens[i]);
			}
			member2pacs.put(member,pacs);
		}
		
		for (String p: pacs2member.keySet())
		{
			System.out.print(p+" ");
			for (String s: pacs2member.get(p))
			{
				System.out.print(s+" ");
			}
			System.out.println();
		}
		
	} // END MAIN
} // PACS CLASS