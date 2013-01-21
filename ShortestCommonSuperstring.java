//Will check for overlap and return the index of the point from which overlap begins
import java.lang.Math;
import java.util.*;
import java.io.*;
import java.lang.String;

public class ShortestCommonSuperstring
{

	public int numberOfFragments;
	public int maxOverlap;
	public int maxOverlapfragment1;
	public int maxOverlapfragment2;
	public ArrayList<String> fragments;

	public ShortestCommonSuperstring()
	{
		this.numberOfFragments = 0;
		this.fragments = new ArrayList<String>();
	}

	public int checkForOverlap( String fragment1, String fragment2 )
	{
		int i,j,k; 
	
		for( i=0; i<fragment1.length(); i++ )
		{
			j=0;
			k=i;
			while(true)
			{
				if( k == fragment1.length() )
				{
					return i;	
				}	
				else if( fragment1.charAt(k) == fragment2.charAt(j) )
				{
					k++;
					j++;	
				}	
				else
				{
					break;
				}	
			}	
		}
		return -1;	
	}

	// Calculates the total number of overlaps and stores in a 2D arraylist

	public void calculateOverlaps( int fragmentLength )
	{
		int overlapIndex, overlap;

		this.maxOverlap = 0;

		for(int i=0; i<this.numberOfFragments; i++ )
		{	
			
			for(int j=0; j<this.numberOfFragments; j++ )
			{
				if( i == j || 
					this.fragments.get( i ) == null || 
					this.fragments.get( j ) == null )
				{
					continue;
				}	
				else
				{
					overlapIndex = checkForOverlap( this.fragments.get( i ), this.fragments.get( j ) );
					if( overlapIndex >= 0 )
					{
						overlap = this.fragments.get( i ).length() - overlapIndex;
					}
					else
					{
						overlap = 0;
					}		
					if( overlap > this.maxOverlap )
					{
						this.maxOverlap = overlap;
						this.maxOverlapfragment1 = i;
						this.maxOverlapfragment2 = j;
					}	
				}	
			}
		}		

	}


	public int mergeStrings( int fragmentLength )
	{
		//Merge the two strings and remove them from the fragments ArrayList
		String fragment1 = this.fragments.get( this.maxOverlapfragment1 ); 
		String fragment2 = this.fragments.get( this.maxOverlapfragment2 );

		int overlapIndex = checkForOverlap( fragment1, fragment2 );	
		String mergedFragment = fragment1.substring( 0, overlapIndex ) + fragment2;

		// Remove the higher of the 2 indices and place the merged fragment in the lower idex. 
		this.fragments.set( Math.min( this.maxOverlapfragment1, this.maxOverlapfragment2 ), mergedFragment );
		this.fragments.set( Math.max( this.maxOverlapfragment1, this.maxOverlapfragment2 ), null ); 
		return Math.min( this.maxOverlapfragment1, this.maxOverlapfragment2 );
	}

	public static void main( String args[] )
	{
		//read file here and store fragments as arraylist
		String filename = "fragments.txt";
		ShortestCommonSuperstring scs = new ShortestCommonSuperstring();
		
		try
		{
			FileReader inputStream = new FileReader( filename );
			BufferedReader inputDocument = new BufferedReader( inputStream );
			String inputLine;

			while ( ( inputLine = inputDocument.readLine() ) != null ) 
			{
				scs.fragments.add( inputLine );
				scs.numberOfFragments++;
			}
		}
		catch( Exception e )
		{
				e.printStackTrace();
		}	

		int mergedStringIndex=-1;
		int fragmentLength = scs.fragments.get(0).length();

		for( int i=0; i<scs.numberOfFragments; i++ )
		{
			scs.calculateOverlaps( fragmentLength );
			if( scs.maxOverlap == 0 )
			{
				break;
			}	
			mergedStringIndex = scs.mergeStrings( fragmentLength );
		}	
//		System.out.println(scs.fragments.get(0));
//		System.out.println("Index : "+mergedStringIndex);
			
		System.out.println( "Shortest Common SuperString : "+scs.fragments.get( mergedStringIndex )+"\n" );

	}
}	