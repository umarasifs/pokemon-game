package views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class readType
{
	public static String types[] = new String[18];
	public static double type[][] = new double [18][18];

	public static void read() throws IOException
	{
		String fileName = "/resources/types.txt";
		
		InputStream pokedex = (readType.class.getResourceAsStream(fileName));
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(pokedex));
		String line = null;
		
		int counter = -1;
	
		while((line = br.readLine()) != null){
		     //process the line
			counter++;
		     String splitted[] = line.split("\\s+");
		     types[counter] = splitted[0];
		     for(int i = 1; i < 19; i++)
		     {
		    	  type[counter][i-1] = Double.parseDouble(splitted[i]);
		     }
		}
		

		br.close();
	}
	
	
	
}