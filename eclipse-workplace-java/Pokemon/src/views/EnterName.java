package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.Font;

public class EnterName extends JPanel {

	private JLabel lblNewLabel;
	private JButton enterButton;
	private JTextField textField;
	private JTextPane textPane;
	private JLabel error;

	/**
	 * Create the panel.
	 */
	public EnterName(int x, int y, int width, int height, ImageIcon icon, String player) {
		initComponents(x, y, width, height, icon, player);
	}
	public void initComponents(int x, int y, int width, int height, ImageIcon background, String player)
	{
		setVisible(true);
		setBounds(x,y,width, height);
		setLayout(null);
		
		enterButton = new JButton("Enter");
		
		enterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enterButton.setToolTipText("Press this to play the game");
		
		
		enterButton.setFont(GUI.press2P);
		enterButton.setBounds( width /4,(height *3) /5, width/2, height / 5);
		add(enterButton);
		
		
		error = new JLabel();
		error.setFont(new Font("Tahoma", Font.PLAIN, 18));
		error.setForeground(new Color(255, 255, 255));
		error.setText("Name too long, over 12 characters!!! Are you Sri Lankan?");
		//"Name too long, over 12 characters!!! Are you Sri Lankan?", getWidth() /2, getHeight()/2
		error.setBounds(width/8, height*11/30, width, height /10);
		error.setIcon(GUI.warning);
		error.setVisible(false);
		
		add(error);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		
		textPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textPane.setFont(GUI.press2P.deriveFont(32f));
		
		textPane.setBounds(width/8, 5, width * 6/ 8, height / 6);
		textPane.setForeground(Color.RED);
		
		textPane.setText( "\n    "+ player+ ": Tell us what \n you would like to be called");
		add(textPane);
		
		
		
		textField = new JTextField();
		textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(textField.getText().length()> 12)
				{
					error.setVisible(true);
				}
				else
				{
					error.setVisible(false);
				}
			}
		});
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setSelectedTextColor(new Color(255, 0, 0));
		textField.setFont(GUI.press2P);
		textField.setBounds(width/8, height/5, width * 6/ 8, height / 6);
		add(textField);
		textField.setColumns(12);
		textField.setVisible(true);
		
		
		lblNewLabel = new JLabel();
		
		lblNewLabel.setBounds(0, 0, width, height);
		lblNewLabel.setIcon(background);
		add(lblNewLabel);
		lblNewLabel.setVisible(true);
		

		
		
		
	}
	
	
	public void setName(String player)
	{
		textPane.setText( "\n    "+ player+ ": Tell us what \n you would like to be called");
	}
	public String getName()
	{
		String out = textField.getText();
		if(out.length() > 12)
		{
			return out.substring(0,12);
		}
		
		return out;
	}
	
	public JButton getButton()
	{
		return enterButton;
	}
	public void reset()
	{
		textField.setText("");
		repaint();
	}
}
