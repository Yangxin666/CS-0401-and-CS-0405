import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2presidents.txt") );
		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		


		// ------------------ CODE I ADDED FOR YOU TO USE AND IMITATE -------------------------		
		TreeMap<String,TreeSet<String>> state2Presidents = new TreeMap<String,TreeSet<String>>();
		TreeMap<String,String> presidents2State = new TreeMap<String,String>();
		TreeSet<String> Presidents_BBSF = new TreeSet<String>();
		TreeSet<String> States_NP = new TreeSet<String>();
		
		// LOOK AT HOW MUCH WORK WE SAVE BY SPLITTING LINE INTO ARRAY INTO ARRAYLIST
		// WE DONT HAVE TO WRITE LOOP TO ADD PRESIDENTS INTO THE TREESET 
		// 
		while (state2PresidentsFile.ready())
		{	// WE TRIM THE LINE REMOVING LEAD & TRAIL WHITESPACE (WHICH WOULD MESSE UP THE SPLIT)
			ArrayList<String> presidents = new ArrayList<String>( Arrays.asList( state2PresidentsFile.readLine().trim().split("\\s+")));
			String state = presidents.get(0); 
			presidents.remove(0); // the first element was state - not president
			state2Presidents.put( state, new TreeSet<String>(presidents) ); // YOU CAN FEED AN ARRAYLIST TO A TRESSET CONSTRUCTOR
			for (String president: presidents)
			{
				presidents2State.put(president,state);
			}
		}
		
		while (allPresidentsFile.ready())
		{
			String president=allPresidentsFile.readLine();
			if (!(presidents2State.containsKey(president)))
				Presidents_BBSF.add(president);
		}
		
		while (allStatesFile.ready())
		{
			String state=allStatesFile.readLine();
			if (!(state2Presidents.containsKey(state)))
				States_NP.add(state);
		}

		// MAP IS BUILT. DUMP IT 
		System.out.println( "\nSTATE TO PRESIDENTS BORN IN THAT STATE\n");
		for ( String state : state2Presidents.keySet() )
		{
			System.out.print( state + " ");
			for ( String pres : state2Presidents.get( state ) )
				System.out.print( pres + " ");
			System.out.println();
		}


		System.out.println( "\nPRESIDENT TO STATE BORN IN\n");
		for (String president: presidents2State.keySet())
		{
			System.out.print(president+" "+presidents2State.get(president));
			System.out.println();
		}

		System.out.println( "\nPRESIDENTS BORN BEFORE STATES FORMED\n");
		for (String president: Presidents_BBSF)
		{
			System.out.print(president);
			System.out.println();
		}

		System.out.println( "\nSTATES HAVING NO PRESIDENT BORN IN THEM\n");
		for (String state: States_NP)
		{
			System.out.print(state);
			System.out.println();
		}

	} // END MAIN

}	// END POTUS CLASS