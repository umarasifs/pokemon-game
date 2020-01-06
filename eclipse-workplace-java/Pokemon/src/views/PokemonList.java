package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.util.ArrayList; 

public class PokemonList extends JPanel {

	private JPanel tmp;
	private static int numPokemon = 0;
	private static ArrayList<PokemonButton> list = new ArrayList<PokemonButton>();
	public static int numChosen = 0;
	private static ArrayList<PokemonButton> chosen = new ArrayList<PokemonButton>();
	
	/**
	 * Create the panel.
	 */
	public PokemonList(int width, int height) {
		super();
		setBackground(new Color(0, 128, 128));
		setBorder(null);

		setVisible(true);
		setBounds(0,0,width, height);
		initialize( width, height);
		eventHandlers();
	}
	
	private void initialize(int width, int height)
	{
		readMon monReader = new readMon();
		try {
			monReader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numPokemon = monReader.getDexLine();
		
		setLayout(new BorderLayout(50, 50));
		tmp = new JPanel();
		tmp.setBackground(new Color(0, 139, 139));
		tmp.setLayout(new GridLayout((numPokemon+1) / 2, 2, width / 20, height / 30));
		for(int i = 1; i <= (numPokemon) ; i++)
		{
			try {
				monReader.read(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String twoLines = monReader.getName() + '\n' + '\n'+'\n' + '\n';
			 
			
			list.add(new PokemonButton("<html>" + twoLines.replaceAll("\\n", "<br>") + "</html>", monReader.getName(), i));
			tmp.add(list.get(i-1));
		}
		
		add(tmp, BorderLayout.CENTER);
		
	}
	public static void disableOthers()
	{
		for(int i = 0; i < numPokemon; i++)
		{
			if(!list.get(i).wasPressed())
			{
				list.get(i).setEnabled(false);
			}
		}
	}

	public static void enableAll()
	{
		for(int i = 0; i < numPokemon; i++)
		{
			
			list.get(i).setEnabled(true);
			
		}
	}
	public static void addList(PokemonButton x)
	{
		chosen.add(x);
	}
	public static void removeList(PokemonButton x)
	{
		chosen.remove(x);
	}
	public static void changeOrder()
	{
		for(int i = 0; i < chosen.size(); i++)
		{
			chosen.get(i).setString(GUI.positions[i]);
		}
	}
	private void eventHandlers()
	{
		for(int i = 0 ; i < numPokemon; i ++)
		{
			list.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					
				}
			});
		}
	}
	public static int getMonChosen(int x)
	{
		return chosen.get(x).getId();
	}

	public static void reset()
	{
		numChosen = 0;
		chosen.clear();
		for(int i = 0; i < numPokemon; i ++)
		{
			list.get(i).reset();
		}
	}
}
