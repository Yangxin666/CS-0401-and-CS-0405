import java.io.*;
import java.util.*;
import java.math.*;
//////////////////////////////////////////////////////////////////////////////////////
class Edge
{
	String key;
	Edge next;
	// a Constructor that takes dest,weight, next why do not need setters and getters now?
	public Edge (String key, Edge next)
	{
		this.key=key;
		this.next=next;
	}
}

//////////////////////////////////////////////////////////////////////////////////////
class MyHashSetP10 
{
	Edge G[];              
	public MyHashSetP10()
	{
		// how to choose the size of array, based on what?
		G=new Edge[12000000];
	}
	
	public boolean add(String s)
	{
		if (contains(s)!=true)
		{
			int index=generateIndex(s);
			// treat G[index] as the head, InsertAtFront()
			G[index]=new Edge(s,G[index]);
			return true;
		}
		return false;
	}
	
	public boolean contains(String s)
	{
		int index=generateIndex(s);
		Edge curr=G[index];
		while (curr!=null)
		{
			if ((curr.key).equals(s))
			{
				return true;
			}
			curr=curr.next;
		}
		return false;
	}
	
	public int generateIndex(String s)
	{
		int l=s.length();
        int sum=0;
        for (int i=0;i<l;i++)
        {
            for (int j=0;j<i;j++)
            {
                sum+=(s.charAt(j))*(s.charAt(j+1))*(s.charAt(i));
            }
        }
        return sum % 12000000;
	}
}
		
		
	
   