package views;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VersusScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel p1;
	JLabel p2;
	JLabel versus;
	JLabel background;
	JLabel p1name;
	JLabel p2name;
	
	
	public VersusScreen(int width, int height, ImageIcon a, ImageIcon b, ImageIcon back) {
		setBounds(0,0,width, height);
		setVisible(true);
		setLayout(null);
		initComponents(width, height, a, b, back);
	}
	private void initComponents(int width, int height, ImageIcon a, ImageIcon b,ImageIcon back)
	{
	p1 = new JLabel();
	p1.setIcon(a);
	add(p1);	
	
	p2 = new JLabel();
	p2.setIcon(b);
	add(p2);
	
	
	p1name =new JLabel();
	p1name.setFont(GUI.press2P.deriveFont(24f));
	p1name.setBounds(width / 10, height / 30 , width /5, height / 5);
	p1name.setOpaque(false);
	add(p1name);
	
	p2name =new JLabel();
	p2name.setFont(GUI.press2P.deriveFont(24f));
	p2name.setBounds(width * 8 / 10, height / 5 , width /5, height / 5);
	p2name.setOpaque(false);
	add(p2name);
	
	versus = new JLabel();
	versus.setForeground(Color.RED);
	versus.setFont(GUI.press2P.deriveFont(128f));
	versus.setText("VS");
	versus.setVisible(false);
	versus.setOpaque(false);
	versus.setBounds(width *2 / 5, height *2 / 5, width / 5, height / 5);
	add(versus);
	
	background = new JLabel();
	background.setBounds(0,0,width, height);
	background.setIcon(back);
	add(background);
	}

	public void moveRight(int width, int height)
	{
		for(int i = 0; i < width/4 ; i +=2)
		{
			repaint();
			p1.setBounds(i - width / 4, height / 6, width / 4, height / 2);
			p2.setBounds(width - i, height/ 3, width / 4, height / 2);
			if(i > width / 8 )
			{
				versus.setVisible(true);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void setP1name(String x)
	{
		p1name.setForeground(Color.orange);
		p1name.setText(x);
		
	}
	
	public void setP2name(String x)
	{
		p2name.setForeground(Color.orange);
		p2name.setText(x);
	}
}
