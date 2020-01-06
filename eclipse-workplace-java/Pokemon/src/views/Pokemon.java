package views;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pokemon
{
	String name;
	private int type1, type2, hp, max_hp, atk, def, spatk, spdef, speed;
	private ImageIcon icon, back, backstill, front, frontstill;
	private Move list[];
	public Pokemon(String name, int type1, int type2, int max_hp, 
					int atk, int def, int spA, int spD, int speed,
					Move list[], String icon, String back, String backStill, String front, String frontStill)
	{
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.max_hp = max_hp;
		this.hp = max_hp;
		this.atk = atk;
		this.def = def;
		this.spatk = spA;
		this.spdef = spD;
		this.speed = speed;
		this.list = list;
		
		try {
			this.icon = new ImageIcon(ImageIO.read(ResourceLoader.load(icon)));
			this.back = new ImageIcon( ImageIO.read(ResourceLoader.load(back)).getScaledInstance(GUI.width / 5, GUI.width / 5, Image.SCALE_DEFAULT));
			this.backstill = new ImageIcon(ImageIO.read(ResourceLoader.load(backStill)).getScaledInstance(GUI.width / 5, GUI.width / 5, Image.SCALE_DEFAULT));
			this.front =new ImageIcon( ImageIO.read(ResourceLoader.load(front)).getScaledInstance(GUI.width / 5, GUI.width / 5, Image.SCALE_DEFAULT));
			this.frontstill =new ImageIcon(ImageIO.read(ResourceLoader.load(frontStill)).getScaledInstance(GUI.width / 5, GUI.width / 5, Image.SCALE_DEFAULT));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
	}
	public String getName()
	{
		return this.name;
	}
	public int getType1()
	{
		return this.type1;
	}
	public int getType2()
	{
		return this.type2;
	}
	public int getHp()
	{
		return this.hp;
	}
	public int getMaxHp()
	{
		return this.max_hp;
	}
	public int getAtk()
	{
		return this.atk;
	}
	public int getDef()
	{
		return this.def;
	}
	public int getSpatk()
	{
		return this.spatk;
	}
	public int getSpdef()
	{
		return this.spdef;
	}
	public int getSpeed()
	{
		return this.speed;
	}
	public boolean isFainted()
	{
		return (hp <=0);
	}
	public Move getMove(int i)
	{
		return list[i - 1];
	}
	public void changeHp(int x)
	{
		this.hp += x;
		if(this.hp > this.max_hp)
		{
			this.hp = this.max_hp;
		}
		if(this.hp < 0)
		{
			this.hp =0;
		}
	}
	public void setHp(int x)
	{
		this.hp = x;
		if(hp < 0)
		{
			hp = 0;
		}
		if(hp > max_hp)
		{
			hp = max_hp;
		}
	}
	public boolean Struggle()
	{
		for(int i = 0; i < 4; i++)
		{
			if(list[i].isUsable())
			{
				return false;
			}
		}
		return true;
	}
	
	public String toString()
	{
		String out = this.name + '\t' + '\t' + this.hp + '/' + this.max_hp + '\n';
		for(int i = 0; i < 4; i ++)
		{
			out += list[i].toString();
		}
		return out;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public ImageIcon getBack() {
		return back;
	}
	public ImageIcon getBackstill() {
		return backstill;
	}
	public ImageIcon getFront() {
		return front;
	}
	public ImageIcon getFrontstill() {
		return frontstill;
	}
}