package views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class readMon
{
	private static String curr[] = new String[14];
	private static int dexNum = 0;
	
	public readMon()
	{
		
	}
	public void read() throws IOException
	{
		String fileName = "/resources/pokedex.txt";
		InputStream file = getClass().getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(file));

		String line = null;

		int i = 0;
		while((line = br.readLine()) != null)
		{
			curr = line.split("\\s+");
			i++;
		}
		dexNum = i;
		
		br.close();
	}
	
	public static void read(int num) throws IOException
	{
		String fileName = "/resources/pokedex.txt";
		InputStream file = readMon.class.getResourceAsStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(file));

		String line = null;

		int i;
		for(i = 0; i< num; i++)
		{
			line = br.readLine();
		}
		dexNum = i;
		curr = line.split("\\s+");
		br.close();
	}
	
	public String getName()
	{
		return curr[0];
	}
	
	public int getType1()
	{
		return Integer.parseInt(curr[1]);
	}
	
	public int getType2()
	{
		return Integer.parseInt(curr[2]);
	}
	
	public int getHp()
	{
		return Integer.parseInt(curr[3]);
	}
	
	public int getAtk()
	{
		return Integer.parseInt(curr[4]);
	}
	
	public int getDef()
	{
		return Integer.parseInt(curr[5]);
	}
	
	public int getSpa()
	{
		return Integer.parseInt(curr[6]);
	}
	
	public int getSpd()
	{
		return Integer.parseInt(curr[7]);
	}
	public int getSpe()
	{
		return Integer.parseInt(curr[8]);
	}
	public int getDexLine()
	{
		return this.dexNum;
	}
	public Move[] getMoveList()
		{
			Move out[] = new Move[4];
			readMove reader = new readMove();
			reader.read(Integer.parseInt(curr[9]));
			out[0] = new Move(reader.getName(), reader.getType(), reader.getPower(), reader.getPriority(),
								reader.getAcc(), reader.getPP(), reader.getPhysical());
			reader.read(Integer.parseInt(curr[10]));
			out[1] = new Move(reader.getName(), reader.getType(), reader.getPower(), reader.getPriority(),
					reader.getAcc(), reader.getPP(), reader.getPhysical());
			reader.read(Integer.parseInt(curr[11]));
			out[2] = new Move(reader.getName(), reader.getType(), reader.getPower(), reader.getPriority(),
					reader.getAcc(), reader.getPP(), reader.getPhysical());
			reader.read(Integer.parseInt(curr[12]));
			out[3] = new Move(reader.getName(), reader.getType(), reader.getPower(), reader.getPriority(),
					reader.getAcc(), reader.getPP(), reader.getPhysical());
			return out;
		}
	
	public String icon()
	{
		return curr[13] + "Icon.png";	
	}
	
	public String frontStill()
	{
		return curr[13] + "_front.png";
	}
	
	public String front()
	{
		return curr[13] + "Front.gif";
	}
	
	public String backStill()
	{
		return curr[13] + "_back.png";
	}
	
	public String back()
	{
		return curr[13] + "Back.gif";
	}
}