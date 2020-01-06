package views;
public class Move
{
	private String name;
	private int type, atk, priority, accuracy, pp, max_pp, physical;
	
	public Move(String name, int type,int atk, int priority, int accuracy, int pp, 
			int physical)
	{
		this.name = name;
		this.type = type;
		this.atk = atk;
		this.priority = priority;
		this.max_pp = pp;
		this.accuracy = accuracy;
		this.pp = pp;
		this.physical = physical;
	}
	
	public String toString()
	{
		String out = this.name + "     ";
		if(name.length() < 8)
		{
			out += "     ";
		}
		out += this.pp + "/" + this.max_pp + "\n";
		return out;
	}
	
	public String getName()
	{
		return this.name;
	}
	public int getAtk()
	{
		return this.atk;
	}
	public int getType()
	{
		return this.type;
	}
	
	public int getPriority()
	{
		return this.priority;
	}
	public int getMaxPP()
	{
		return this.max_pp;
	}
	public int getPP()
	{
		return this.pp;
	}
	public int getAccuracy()
	{
		return this.accuracy;
	}
	public int getPhysical()
	{
		return this.physical;
	}
	public boolean isUsable()
	{
		return (pp!=0);
	}
	public void changePP(int x)
	{
		this.pp+=x;
	}
}