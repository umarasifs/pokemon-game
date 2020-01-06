package views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class readMove
{
	
	
	private static String curr[] = new String[7];
	
	public readMove()
	{
		
	}
	public void read(int num)
	{
		String fileName = "/resources/move.txt";
		InputStream pokedex = (getClass().getResourceAsStream(fileName));
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(pokedex));
		String line = null;

		int counter = -1;
	
		for(int i = 0; i< num; i++)
		{
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		curr = line.split("\\s+");
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName()
	{
		String out = "";
		for(int i = 0; i < curr[0].length(); i ++)
		{
			if(curr[0].charAt(i) != '_')
			{
				out += curr[0].charAt(i);
			}
			else
			{
				out+= ' ';
			}
			
		}
		return out;
	}
	
	public int getPower()
	{
		return Integer.parseInt(curr[1]);
	}
	
	public int getAcc()
	{
		return Integer.parseInt(curr[2]);
	}
	
	public int getPP()
	{
		return Integer.parseInt(curr[3]);
	}
	
	public int getType()
	{
		return Integer.parseInt(curr[4]);
	}
	
	public int getPriority()
	{
		return Integer.parseInt(curr[5]);
	}
	
	public int getPhysical()
	{
		return Integer.parseInt(curr[6]);
	}
}
