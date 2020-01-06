package views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwitchPokemon extends JPanel {

	/**
	 * Create the panel.
	 */
	private JButton p1,p2,p3,p4,p5,p6;
	private Trainer now;
	
	public SwitchPokemon(int width, int height) {
		setSize(width, height);
		setLayout(null);
		initComponents(width, height);
		setBackground(new Color(0, 128, 128));
	}
	public void initComponents(int width, int height)
	{
		
		p1 = new JButton();
		p1.setFont(GUI.press2P.deriveFont(12f));
		p1.setBounds(width / 15, height / 20, width * 2 / 5, height *4 /15);
		add(p1);
		
		p2 = new JButton();
		p2.setFont(GUI.press2P.deriveFont(12f));
		p2.setBounds(width * 8 / 15, height / 20, width * 2 / 5,height *4 /15);
		add(p2);
		
		p3 = new JButton();
		p3.setFont(GUI.press2P.deriveFont(12f));
		p3.setBounds(width / 15, height * 11 / 30, width * 2 / 5, height *4 /15);
		add(p3);
		
		p4 = new JButton();
		p4.setFont(GUI.press2P.deriveFont(12f));
		p4.setBounds(width * 8 / 15, height * 11 / 30, width * 2 / 5, height *4 /15);
		add(p4);
		
		p5 = new JButton();
		p5.setFont(GUI.press2P.deriveFont(12f));
		p5.setBounds(width / 15, height * 41 / 60, width * 2 / 5, height *4 /15);
		add(p5);
		
		p6 = new JButton();
		p6.setFont(GUI.press2P.deriveFont(12f));
		p6.setBounds(width * 8 / 15, height * 41 / 60, width * 2 / 5, height *4 /15);
		add(p6);
	}
	
	public void update(Trainer x)
	{
		
		now = x;
		setString(x.getList()[0],p1);
		setString(x.getList()[1],p2);
		setString(x.getList()[2],p3);
		setString(x.getList()[3],p4);
		setString(x.getList()[4],p5);
		setString(x.getList()[5],p6);
		
	}
	public void setString(Pokemon out, JButton x)
	{
		String curr;
		x.setIcon(out.getIcon());
		if(now.getCurr().getName().equalsIgnoreCase(out.getName())) {
			curr = out.getName() +  "\n" + "\n"  + out.getHp() + " / " + out.getMaxHp();
			x.setText("<html>" + curr.replaceAll("\\n", "<br>") + "</html>");
			x.setToolTipText("Already Out");
			x.setEnabled(false);
		}
		else if(out.isFainted())
		{
			curr = out.getName() + "\n" + "\n" + "IS FAINTED!";
			x.setText("<html>" + curr.replaceAll("\\n", "<br>") + "</html>");
			x.setToolTipText("Fainted: \n Unable to battle");
			x.setEnabled(false);
		}

		else
		{
			curr = out.getName() +  "\n" + "\n"  + out.getHp() + " / " + out.getMaxHp();
			x.setText("<html>" + curr.replaceAll("\\n", "<br>") + "</html>");
			String text = out.getName() + ": \n" + readType.types[out.getType1() - 1] + " \n";
			if(out.getType2() != 0)
			{
				text +=  readType.types[out.getType2() - 1];
			}
			x.setToolTipText(text);
			x.setEnabled(true);
		}
		
	}
	
	public JButton getP1()
	{
		return p1;
	}
	public JButton getP2()
	{
		return p2;
	}
	public JButton getP3()
	{
		return p3;
	}
	public JButton getP4()
	{
		return p4;
	}
	public JButton getP5()
	{
		return p5;
	}
	public JButton getP6()
	{
		return p6;
	}
	
	
}
