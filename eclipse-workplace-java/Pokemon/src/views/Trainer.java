package views;

import javax.swing.ImageIcon;

public class Trainer
{
	
	private String name;
	private Pokemon list[];
	private Move lastMove;
	private boolean switching;
	private int toSwitch = 0;
	private Pokemon curr;
	private boolean right;
	private ImageIcon look;

	public Trainer(String name, Pokemon list[])
	{
		this.name = name;
		this.list = list;
		this.curr = list[0];
	}
	
	public String getName()
	{
		return this.name;
	}
	
	Pokemon getMon(int num)
	{
		return this.list[num-1];
	}
	
	public boolean canSwitch(int num)
	{
		for(int i = 0; i < num-1; i++)
		{
			if(!list[i].isFainted())
			{
				return true;
			}
		}
		for (int i = 0; i < 6; i++)
		{
			if(!list[i].isFainted())
			{
				return true;
			}
		}
		return true;
	}
	
	public Pokemon[] getList()
	{
		return list;
	}
	
	public Move getLastMove()
	{
		return lastMove;
	}
	
	public void setLastMove(Move x)
	{
		lastMove = x;
	}
	
	public  boolean getSwitch()
	{
		return switching;
	}
	
	public void setSwitch(boolean a)
	{
		switching = a;
	}
	public boolean isStillIn()
	{
		boolean yes = false;
		for(int i= 0; i < 6; i ++)
		{
			if(!list[i].isFainted())
			{
				yes = true;
			}
		}
		return yes;
	}
	
	public Pokemon getCurr()
	{
		return curr;
	}
	public void setCurr(Pokemon x)
	{
		curr = x;
	}
	public void setRight(boolean x)
	{
		right = x;
	}
	public boolean getRight()
	
	{
		return right;
	}

	public int getToSwitch() {
		return toSwitch;
	}

	public void setToSwitch(int toSwitch) {
		this.toSwitch = toSwitch;
	}

	public ImageIcon getLook() {
		return look;
	}

	public void setLook(ImageIcon look) {
		this.look = look;
	}
}