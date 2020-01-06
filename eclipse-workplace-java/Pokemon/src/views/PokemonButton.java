package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class PokemonButton extends JButton
{
	private boolean pressed = false;
	private String realName;
	private int identifier;
	
	public PokemonButton(String name, String real, int x)
	{
		
		super();
		realName = real;
		this.setFont(GUI.press2P.deriveFont(24f));
		this.setText(name);
		eventHandlers();
		setBackground(new Color(240, 240, 240));
		String file = "/resources/" + real.toLowerCase() + "/" + real.toLowerCase() + "Icon.png";
		try {
			setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load(file))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		identifier = x;
	}
	
	public int getId()
	{
		return identifier;
	}
	private PokemonButton re()
	{
		return this;
	}

	public boolean wasPressed()
	{
		return pressed;
	}
	private void eventHandlers()
	{
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pressed = !pressed;
				if(pressed)
				{
					PokemonList.addList(re());
					PokemonList.numChosen ++;
					setBackground(Color.lightGray);
					PokemonList.changeOrder();
				}
				else
				{
					PokemonList.removeList(re());
					PokemonList.numChosen --;
					setBackground(new Color(240, 240, 240));
					PokemonList.changeOrder();
					setString("");
				}
				if(PokemonList.numChosen==6)
				{
					PokemonList.disableOthers();
					PokemonSelect.submit.setEnabled(true);
				}
				else
				{
					PokemonList.enableAll();
					PokemonSelect.submit.setEnabled(false);
				}

			}
		});
	}
	
	public void setString(String curr)
	{
		String twoline = realName + '\n' + '\n'+'\n'  + curr;
		setText("<html>" + twoline.replaceAll("\\n", "<br>") + "</html>");
	}
	
	public void reset()
	{
		this.setString("");
		setEnabled(true);
		setBackground(new Color(240, 240, 240));
		pressed = false;
	}
	
}