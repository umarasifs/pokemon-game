package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

public class Battle extends JPanel {

	/**
	 * Create the panel.
	 */
	
	JButton fight;
	JButton switching;
	JLabel back;
	JTextPane console;
	
	MoveButton move1;
	MoveButton move2;
	MoveButton move3; 
	MoveButton move4;
	
	HealthBar right;
	HealthBar left;
	
	JLabel pokemonRight;
	JLabel pokemonLeft;
	
	Pokemon curr;
	Trainer currTrainer;
	
	SwitchPokemon switchPokemon;
	
	String currConsole = "";
	
	private static ArrayList<String> messages = new ArrayList<String>(); 
	
	private int w;
	private int h;

	
	public Battle(int width, int height, ImageIcon background) {
		setBounds(0,0,width, height);
		setLayout(null);
		initComponents(width, height, background);
		eventsHandlers();
		w = width;
		h = height;
	}
	
	private void initComponents(int width, int height, ImageIcon background)
	{
		
		switchPokemon = new SwitchPokemon(width * 4 / 5, height  / 3);
		switchPokemon.setBounds(width / 10, height *2 / 7, width * 4 / 5, height  / 3);
		switchPokemon.setVisible(false);
		add(switchPokemon);
		
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(GUI.press2P.deriveFont(24f));
		console.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		console.setBounds(0 , height * 8 / 10,  width, height / 5);
		add(console);
		
		pokemonRight = new JLabel();
		pokemonRight.setVisible(false);
		pokemonRight.setBounds(width * 7 / 10, height / 5, width / 5, width / 5);
		add(pokemonRight);
		
		pokemonLeft = new JLabel();
		pokemonLeft.setVisible(false);
		pokemonLeft.setBounds(width * 2 / 10, height / 2, width / 5, width / 5);
		add(pokemonLeft);
		
		right = new HealthBar(width/ 5, height / 7);
		right.setBounds(width *19 / 25, height / 25, width/ 5, height / 7);
		add(right);
		
		left = new HealthBar(width/ 5, height / 7);
		left.setBounds(width / 25, height / 25, width / 5, height / 7);
		add(left);
		
		switching = new JButton();
		switching.setBackground(Color.yellow);
		switching.setFont(GUI.press2P.deriveFont(64f));
		switching.setText("SWITCH");
		switching.setBounds(width / 10, height*14/21, width * 4 / 5, height / 10);
		add(switching);
		
		move1 = new MoveButton(width* 7 / 20, height / 7);
		move1.setBounds(width / 10, height *2 / 7, width* 7 / 20, height / 7);
		move1.setVisible(false);
		add(move1);
		
		move2 = new MoveButton(width* 7 / 20, height / 7);
		move2.setBounds(width / 10, height *10 /21 ,width* 7 / 20, height / 7);
		move2.setVisible(false);
		add(move2);
		
		move3 = new MoveButton(width* 7 / 20, height / 7);
		move3.setBounds(width*11 / 20, height *2 / 7, width* 7 / 20, height / 7);
		move3.setVisible(false);
		add(move3);
		
		move4 = new MoveButton(width* 7 / 20, height / 7);
		move4.setBounds(width*11 / 20, height *10 /21 ,width* 7 / 20, height / 7);
		move4.setVisible(false);
		add(move4);
		
		fight = new JButton();
		fight.setForeground(new Color(220, 220, 220));
		fight.setText("FIGHT");
		fight.setFont(GUI.press2P.deriveFont(64f));
		fight.setBackground(new Color(178, 34, 34));
		fight.setBounds(width / 10, height *2 / 7, width * 4 / 5, height  / 3);
		fight.setVisible(true);
		add(fight);
		
		back = new JLabel();
		back.setBounds(0,0, width, height);
		back.setIcon(background);
		add(back);
	}
	
	public void goBack()
	{
		setConsoleText(currTrainer.getName() + ":  Choose an Action!");
		fight.setVisible(true);
		move1.setVisible(false);
		move2.setVisible(false);
		move3.setVisible(false);
		move4.setVisible(false);
		switching.setBackground(Color.yellow);
		switching.setText("SWITCH");
		switching.setVisible(true);
		switchPokemon.setVisible(false);
		if(currTrainer.getRight())
		{
			right.setBorder();
			left.removeBorder();
		}
		else
		{
			left.setBorder();
			right.removeBorder();
		}
	}
	
	public void setConsoleText(String y)
	{
		currConsole = y;
		console.setText("");
		console.setText(currConsole);
		
	}

	private void eventsHandlers()
	{
		fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(curr.Struggle())
				{
					currTrainer.setLastMove(null);
					synchronized(GUI.getSyncObject())
					{
						GUI.getSyncObject().notifyAll();
					}
					GUI.go =  true;
					
				}
				else
				{
					fight.setVisible(false);
					updateMoves();
					move1.setVisible(true);
					move2.setVisible(true);
					move3.setVisible(true);
					move4.setVisible(true);
					switching.setBackground(Color.RED);
					switching.setText("Back");
				}
			}
		});
		
		switching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!fight.isVisible())
				{
					goBack();
				}
				else
				{
					fight.setVisible(false);
					switchPokemon.update(currTrainer);
					switchPokemon.setVisible(true);
					switching.setBackground(Color.RED);
					switching.setText("Back");
					setConsoleText(currTrainer.getName() + ": switch Pokemon ");
				}
			}
		});
		move1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setLastMove(curr.getMove(1));
				currTrainer.setSwitch(false);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
				
			}
		});
		
		move2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setLastMove(curr.getMove(2));
				currTrainer.setSwitch(false);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		
		move3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setLastMove(curr.getMove(3));
				currTrainer.setSwitch(false);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		
		move4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setLastMove(curr.getMove(4));
				currTrainer.setSwitch(false);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		
		switchPokemon.getP1().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setSwitch(true);
				currTrainer.setToSwitch(1);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		
		switchPokemon.getP2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setToSwitch(2);
				currTrainer.setSwitch(true);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		switchPokemon.getP3().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setSwitch(true);
				currTrainer.setToSwitch(3);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		switchPokemon.getP4().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setSwitch(true);
				currTrainer.setToSwitch(4);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		switchPokemon.getP5().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setSwitch(true);
				currTrainer.setToSwitch(5);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		switchPokemon.getP6().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currTrainer.setSwitch(true);
				currTrainer.setToSwitch(6);
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
			}
		});
		
		
	}
	
	public void updateMoves()
	{
		setConsoleText(currTrainer.getName() + ":  Choose a Move!");
		move1.update(curr.getMove(1));
		move2.update(curr.getMove(2));
		move3.update(curr.getMove(3));
		move4.update(curr.getMove(4));
		
		move1.setText(curr.getMove(1).toString());
		if(curr.getMove(1).isUsable())
		{
			move1.setEnabled(true);
		}
		else
		{
			move1.setEnabled(false);
		}
		move2.setText(curr.getMove(2).toString());
		if(curr.getMove(2).isUsable())
		{
			move2.setEnabled(true);
		}
		else
		{
			move2.setEnabled(false);
		}
		move3.setText(curr.getMove(3).toString());
		if(curr.getMove(3).isUsable())
		{
			move3.setEnabled(true);
		}
		else
		{
			move3.setEnabled(false);
		}
		move4.setText(curr.getMove(4).toString());
		if(curr.getMove(4).isUsable())
		{
			move4.setEnabled(true);
		}
		else
		{
			move4.setEnabled(false);
		}
	}
	
	public void healthRight(Pokemon x, Trainer y)
	{
		right.setName(x);
		right.setBalls(y);
	}
	
	public void healthLeft(Pokemon x, Trainer y)
	{
		left.setName(x);
		left.setBalls(y);
	}
	
	public void setCurrPokemon(Pokemon x)
	{
		this.curr= x; 
	}
	
	public void setCurrTrainer(Trainer x)
	{
		this.currTrainer = x;
		setConsoleText(currTrainer.getName() + ":  Choose an Action!");
	}
	
	public void battlePhase()
	
	{
		fight.setVisible(false);
		switching.setVisible(false);
		
		move1.setVisible(false);
		move2.setVisible(false);
		move3.setVisible(false);
		move4.setVisible(false);
		
		pokemonLeft.setVisible(true);
		pokemonRight.setVisible(true);
		
		right.removeBorder();
		left.removeBorder();
		
		switchPokemon.setVisible(false);
	}
	
	public void endBattlePhase()
	
	{
		goBack();
		
		pokemonLeft.setVisible(false);
		pokemonRight.setVisible(false);
		
	}
	
	public void setSprite(Trainer x, boolean still)
	{
		if(x.getRight())
		{
			if(still == false)
			{
				pokemonRight.setIcon(x.getCurr().getFront());
			}
			else
			{
				pokemonRight.setIcon(x.getCurr().getFrontstill());
			}
			
		}
		else
		{
			if(still == true)
			{
				pokemonLeft.setIcon(x.getCurr().getBackstill());
			}
			else
			{
				pokemonLeft.setIcon(x.getCurr().getBack());
			}
			
		}
	}
	
	public void exitLeft()
	{
		for(int i =  w * 2 / 10; i > -w / 5; i -= 3)
		{
			repaint();
			pokemonLeft.setBounds(i , h / 2, w / 5, w / 5);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void enterLeft()
	{
		healthLeft(currTrainer.getCurr(),currTrainer);
		for(int i =  -w / 5; i < w / 5; i += 3)
		{
			repaint();
			pokemonLeft.setBounds(i , h / 2, w / 5, w / 5);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void exitRight()
	{
		for(int i =  w * 7 / 10; i < w; i += 3)
		{
			repaint();
			pokemonRight.setBounds(i, h / 5, w / 5, w / 5);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void enterRight()
	{
		healthRight(currTrainer.getCurr(),currTrainer);
		for(int i =  w; i > w * 7 / 10; i -= 3)
		{
			repaint();
			pokemonRight.setBounds(i, h / 5, w / 5, w / 5);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void death(Trainer x)
	{
		if(x.getRight())
		{
			pokemonRight.setVisible(false);
			switchPokemon.update(currTrainer);
			switchPokemon.setVisible(true);
			for(int i = h/4; i < h; i += 10)
			{
				repaint();
				pokemonRight.setBounds(w*7/10,i, w / 5, w / 5);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			for(int i = h/2; i < h * 4 / 5; i += 3)
			{
				repaint();
				pokemonLeft.setBounds(w/5 , i , w / 5, w / 5);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pokemonLeft.setVisible(false);
			switchPokemon.update(currTrainer);
			switchPokemon.setVisible(true);
		}
	}
	
	public void reset()
	{
		currConsole = "";
		curr = null;
		currTrainer = null;
	}
	
	public void printMessages()
	{
		for(int i = 0; i < messages.size(); i ++ )
		{
			setConsoleText(messages.get(i));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		messages.clear();
		setConsoleText("");
	}

	public void setMessage(String x)
	{
		messages.add(x);
	}

	public String getCurrConsole()
	{
		return currConsole;
	}
	
	public void flicker(Trainer x)
	{
		if(x.getRight())
		{
			for(int i = 0; i < 6; i++)
			{
				pokemonRight.setVisible(!pokemonRight.isVisible());
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else
		{
			for(int i = 0; i < 6; i++)
			{
				pokemonLeft.setVisible(!pokemonLeft.isVisible());
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		
	}
}
