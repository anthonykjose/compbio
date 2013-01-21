import java.lang.Math;
import java.util.*;
import java.io.*;
import java.lang.String;

public class GenerateFragments
{
	public static void main( String args[] )
	{
		int noOfFragments=0, fragmentLength=0;
		//Input the fragment length and no. of fragments
		if( args.length < 2 )
		{
			System.out.println("Insufficient Arguments\n SYNTAX : java GenerateFragments <no_of_fragments> <fragment_length>");
			System.exit(-1);	
		}	

    	try 
    	{
			noOfFragments = Integer.parseInt(args[0]);
			fragmentLength = Integer.parseInt(args[1]);
    	} 
    	catch (NumberFormatException e) 
    	{
	        System.err.println("Argument" + " must be an integer");
	        System.exit(1);
    	}

		

		String filename = "input.txt";
		String inputLine="";

		try
		{
			FileReader inputStream = new FileReader( filename );
			BufferedReader inputDocument = new BufferedReader( inputStream );
			inputLine = inputDocument.readLine(); 
		}
		catch( Exception e )
		{
				e.printStackTrace();
		}

		int inputLength = inputLine.length();
		Random rng = new Random();
		int startIndex, endIndex;

		try
		{	
			FileWriter fragmentsStream = new FileWriter( "fragments.txt" );
			BufferedWriter fragmentsOut = new BufferedWriter( fragmentsStream );

			for( int i=0; i < noOfFragments; i++ )
			{
				startIndex = rng.nextInt( inputLength - fragmentLength + 1 );
				endIndex = startIndex + fragmentLength;
			//	System.out.println("Length : "+(inputLength - fragmentLength));
		//		System.out.println(startIndex+" : "+endIndex+" : "+inputLine.substring( startIndex, endIndex )+"\n");
			//	System.out.println( inputLine.substring( startIndex, endIndex )+"\n" );
				fragmentsOut.write( inputLine.substring( startIndex, endIndex )+"\n" );
			}	
			fragmentsOut.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}	
	}
}