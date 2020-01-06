package views;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class MoveButton extends JButton
{
	
	MoveButton(int width, int height)
	{
		setFont(GUI.press2P.deriveFont(14f));
		setSize(width, height);
		setIcon(null);
	}
	
	public void update(Move x)
	{

		if(x.getPP() * 100 / x.getMaxPP() > 50)
		{
			setForeground(Color.black);
		}
		else if(x.getPP() * 100 / x.getMaxPP() > 25)
		{
			setForeground(Color.yellow);
		}
		else
		{
			setForeground(Color.RED);
		}
		
		String file = "/resources/types/Type_" + readType.types[x.getType() - 1] + ".gif";

		try {
			setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(file))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}