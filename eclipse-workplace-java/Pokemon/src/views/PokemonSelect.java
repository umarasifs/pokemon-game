package views;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class PokemonSelect extends JLayeredPane {

	/**
	 * Create the panel.
	 */
	private JScrollPane scroll;
	private PokemonList select;
	private JLabel icon;
	public static JButton submit;
	private JTextPane instructions;
	
	public PokemonSelect(int width, int height, ImageIcon background) {
		super();
		setOpaque(true);
		setVisible(true);
		initComponents(width, height, background);
		
		
	}
	
	private void initComponents(int width, int height, ImageIcon background)
	{
		select = new PokemonList(width, height);
		select.setBackground(new Color(0, 128, 128));
		select.setBorder(null);
		
		scroll = new JScrollPane(select);
		scroll.setBackground(new Color(0, 128, 128));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setVisible(true);
		setLayout(null);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setOpaque(true);
		scroll.setBounds(width / 10, height / 10, width  * 8/ 10, height *3 / 5);
		add(scroll);
		
		submit = new JButton();
		submit.setToolTipText("Choose 6 Pokemon");
		submit.setFont(GUI.press2P.deriveFont(24f));
		submit.setText("SUBMIT");
		submit.setEnabled(false);
		submit.setBounds( width*7 /10, height*8/10, width /6, height / 10);
		add(submit);
		
		instructions = new JTextPane();
		instructions.setFont(GUI.press2P.deriveFont(24f));
		instructions.setBounds(width/10, height / 50, width * 8 / 10, height / 15);
		
		
		add(instructions);
		
		
		icon = new JLabel();
		icon.setOpaque(true);
		icon.setBounds(0, 0, width, height);
		icon.setIcon(background);
		icon.setVisible(true);
		add(icon);
		
		


	}
	
	public void reset()
	{	
		submit.setEnabled(false);
	}
	
	public PokemonList getList()
	{
		return select;
	}
	public void setName(String a)
	{
		String text = a + ":  Choose 6 Pokemon. ";
		instructions.setText(text);
	}
}
