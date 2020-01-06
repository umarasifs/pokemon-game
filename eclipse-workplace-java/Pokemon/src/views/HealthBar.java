package views;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.border.MatteBorder;

public class HealthBar extends JPanel {

	private JLabel name;
	private JLabel HP;
	private JProgressBar progressBar;

	
	public static ImageIcon ball;
	public static ImageIcon ballDead;
	public static ImageIcon ballCurrent;
	
	private JLabel[] balls = new JLabel[6];

	/**
	 * Create the panel.
	 */
	public HealthBar(int width, int height) {
		setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.RED));
		setBackground(new Color(245, 222, 179));

		setSize(width, height);
		initComponents(width, height);
	}
	
	private void initComponents(int width, int height)
	{
		
		setLayout(null);
		name = new JLabel();
		name.setBounds(width / 25, height / 25, width * 4 /5, height/5);
		add(name);
		
		
		HP = new JLabel();
		HP.setBounds(width *2/ 3, height/ 2, width / 2 , height / 5);
		add(HP);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(width / 10 , height / 3, width * 4 / 5, height / 8);
		progressBar.setForeground(Color.GREEN);
		add(progressBar);
		
		for(int i = 1; i < 7; i++)
		{
			balls[i-1] = new JLabel();
			balls[i-1].setBounds(width * i / 8 , height *2 /3, width/10, width/10);
			add(balls[i-1]);
		}
		try {
			ball = new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/ball.png")).getScaledInstance(width / 10, width / 10, Image.SCALE_DEFAULT));
			ballCurrent = new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/ballCurrent.png")).getScaledInstance(width / 10, width / 10, Image.SCALE_DEFAULT));
			ballDead = new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/ballDead.png")).getScaledInstance(width / 10, width / 10, Image.SCALE_DEFAULT));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void setName(Pokemon x)
	{
		name.setText(x.getName());
		HP.setText(x.getHp() + "/" + x.getMaxHp());
		progressBar.setValue(x.getHp()*100 / x.getMaxHp());
		
		if(x.getHp()*100 / x.getMaxHp() > 50)
		{
			progressBar.setForeground(Color.GREEN);
		}
		else if(x.getHp()*100 / x.getMaxHp() > 25)
		{
			progressBar.setForeground(Color.yellow);
		}
		else
		{
			progressBar.setForeground(Color.RED);
		}
	}
	
	public void setBalls(Trainer x)
	{
		for(int i = 1; i < 7; i++)
		{
			if(x.getMon(i).isFainted())
			{
				balls[i-1].setIcon(ballDead);
			}
			else
			{
				if (x.getCurr().getName().equals(x.getMon(i).getName()))
				{
					balls[i-1].setIcon(ballCurrent);
				}
				else
				{
					balls[i-1].setIcon(ball);
				}
			}
		}
		

	}
	
	public void setBorder()
	{
		setBorder(new MatteBorder(5, 5, 5, 5, (Color) Color.RED));
	}
	
	public void removeBorder()
	{
		setBorder(null);
	}
}
