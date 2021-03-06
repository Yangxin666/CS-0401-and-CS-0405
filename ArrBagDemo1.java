public class ArrBagDemo1
{
 
    public static void main(String a[])
	{
		// INSTANCE AN OBECT OF OUR GENERIC CLASS AND SPECIFY IT AS A CONTAINER OF TYPE ARRAY OF STRING
		ArrBag<String> bag1 = new ArrBag<String>();
		String[] words = {"alpha", "bravo", "charlie",  "delta",  "echo" };
		for ( String word : words )
			bag1.add( word );
		System.out.println( "bag1 contains these strings: " + bag1 );
		String[] keys = {"alpha", "bristo", "charlie",  "dakota",  "echo" };
		for ( String key : keys )
			if ( bag1.contains( key ) )
				System.out.println( key + " found" ); 
			else
				System.out.println( key + " NOT found" ); 
    }
}


// T IS PLACEHOLDER FOR ACTUAL TYPE SPECIFIED AT COMPILE TIME OF CLIENT CODE; think it as a template 
class ArrBag<T>
{	final int NOT_FOUND = -1;
	final int INITIAL_CAPACITY = 10;
	private int count; // LOGICAL SIZE
	
    // DEFINE & INITIALIZE REF TO OUR ARRAY OF T OBJECTS
	@SuppressWarnings("unchecked") T[] theArray = (T[]) new Object[INITIAL_CAPACITY];
    //@SuppressWarnings("unchecked") cast ex: (int) 3.2--3, any type could be a child of object
    
	public int size()
	{
		return count; 
	}
    // ACCEPTS REF TO REAL INSTANCE OF T TYPE. ASSIGNS IT INTO OUR PRIVATE MEMBER VAR
    public ArrBag(  )
	{
		count = 0; // i.e. logical size, actual number of elems in the array
    }

	public boolean add( T element )
	{	if (element == null) return false;
		if (size() == theArray.length) return false; // FAILS IF WE ARE ALREADY FULL
		theArray[ count++] = element; // WE RELAX RULE ABOUT ASSIGNING INTO COUNT OTHER THAN IN SETTER
		return true; // success. it was added
	}
	
	// SEARCHES FOR THE KEY. TRUE IF FOUND, OTHERWISE FALSE
	public boolean contains( T key )
	{	if (key == null) return false;
		for ( int i=0 ; i < size() ; ++i )
			if ( get(i).equals( key ) ) // WE ARE MAKING AN ASSUMPTION ABOUT TYPE T... WHAT IS IT?
				return true;
		return false;
	}

	public String toString()
	{
		String toString  = ""; // YES YOU ARE ALLOWED TO NAME VAR SAME AS METHOD IT's IN
		for ( int i=0 ; i < size() ; ++i  )
		{
			toString += theArray[i];
			if ( i < size()-1 ) 	// DONT PUT SPACE AFTER LAST ELEM
				toString += " ";
		}
		return toString;
	}	
		
}