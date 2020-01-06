package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class GUI extends JFrame {


	
	public static Font press2P; 
	
	public static ImageIcon background;
	public static ImageIcon warning;
	public static ImageIcon p1;
	public static ImageIcon p2;
	public static Image ballImage;


	public static int height = 720;
	public static int width = 1280;
	
	
	private static GUI frame;
	
	
	private static StartMenu opening;
	private static EnterName enterName;
	private static PokemonSelect pokemonSelect;
	private static VersusScreen versusScreen;
	private static Battle battle;
	private static Victory victory;
	
	private static CardLayout card;
	static JLayeredPane layeredPane;
	private static String P1name = "";
	public static boolean game = true;
	
	public static boolean go = false;
	public static boolean inBattle = true;
	
	public static String positions[]= {
			"first", "second", "third", "fourth", "fifth", "sixth"
		};
	
	private static readMon monReader = new readMon();
	public static Object syncObject = new Object();
	
	public static Random rand = new Random();
	
	public static Trainer winner;
	
	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		
		loadResources();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
		while (game)
		{
			
			//checks if player 1 has inputted a name
			synchronized(getSyncObject())
			{
				while(P1name.equals(""))
				{
					try {
						getSyncObject().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			goToNext();
			
			//player 1 pokemon selection
			synchronized(getSyncObject())
			{
				while(!go)
				{
					try {
						getSyncObject().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			enterName.setName("Player 2");
			goToPrior();
			
			//create trainer 1
			go = false;
			Pokemon[] list = new Pokemon [6];
			for(int i = 0; i < 6; i ++)
			{
				list[i] = createPokemon(PokemonList.getMonChosen(i));
			}
			Trainer player1 = new Trainer(P1name, list);
			player1.setRight(false);
			P1name = "";
			pokemonSelect.reset();
			pokemonSelect.getList().reset();
			
			//checks if player 2 has inputted a name
			synchronized(getSyncObject())
			{
				while(P1name.equals(""))
				{
					try {
						getSyncObject().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			goToNext();
			
			
			//player 1 pokemon selection
			synchronized(getSyncObject())
			{
				while(!go)
				{
					try {
						getSyncObject().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
			//create trainer 2
			go = false;
			Pokemon [] list2 = new Pokemon[6];
			for(int i = 0; i < 6; i ++)
			{
				list2[i] = createPokemon(PokemonList.getMonChosen(i));
			}
			Trainer player2 = new Trainer(P1name, list2);
			player2.setRight(true);
			P1name = "";
			pokemonSelect.reset();
			pokemonSelect.getList().reset();
		
			goToNext();
		
		
			//vs
			versusScreen.setP1name(player1.getName());
			versusScreen.setP2name(player2.getName());
			versusScreen.moveRight(width, height);
			goToNext();
			
			
			player1.setLook(p1);
			player2.setLook(p2);
			
			
			
			
			//BATTLE
			while(inBattle)
			{
				battle.setCurrPokemon(player1.getCurr());
				battle.setCurrTrainer(player1);
				battle.healthLeft(player1.getCurr(), player1);
				battle.healthRight(player2.getCurr(), player2);

				battle.goBack();
				
				synchronized(getSyncObject())
				{
					while(!go)
					{
						try {
							getSyncObject().wait();
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
				}
				
				go = false;
				battle.setCurrPokemon(player2.getCurr());
				battle.setCurrTrainer(player2);
				battle.goBack();
				
				synchronized(getSyncObject())
				{
					while(!go)
					{
						try {
							getSyncObject().wait();
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
				}
				
				go = false;
				battle.battlePhase();
				
				battle(player1, player2);
				
				checkDeath(player1,player2);
				checkDeath(player2,player1);
				

				
				battle.endBattlePhase();
			}
			
			goToNext();
			victory.congratulations(winner);
			synchronized(getSyncObject())
			{
				while(!go)
				{
					try {
						getSyncObject().wait();
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
				}
			}
			go = false;
		}
		frame.dispose();
		
	}
	
	private static Pokemon createPokemon(int x)
	{
		try {
			monReader.read(x);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		Pokemon curr = new Pokemon(monReader.getName(), monReader.getType1(), monReader.getType2(), monReader.getHp(), 
									monReader.getAtk(),monReader.getDef(), monReader.getSpa(), monReader.getSpd(),
									monReader.getSpe(), monReader.getMoveList(), monReader.icon(), monReader.back(), monReader.backStill(),
									monReader.front(), monReader.frontStill()); 
		return curr;
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		initComponents();
		eventHandlers();
	}

	public void initComponents()
	{
		
		setSize(new Dimension(width, height));
		setTitle("Umar's Pokemon Game");
		setResizable(false);
		setIconImage(ballImage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		card = new CardLayout(0, 0);
		layeredPane.setLayout(card);
		
		opening = new StartMenu(0,0,width,height, background);
		layeredPane.add(opening, "opening");
		layeredPane.setComponentZOrder(opening, 0);
		

		
		enterName = new EnterName(0,0, width, height, background, "PLAYER 1");
		layeredPane.add(enterName, "enter name");
		layeredPane.setComponentZOrder(enterName, 1);
		
		pokemonSelect = new PokemonSelect(width, height, background);
		layeredPane.add(pokemonSelect, "pokemonSelection");
		layeredPane.setComponentZOrder(pokemonSelect, 2);

		versusScreen = new VersusScreen(width,height, p1, p2, background);
		layeredPane.add(versusScreen,"versusScreen");
		layeredPane.setComponentZOrder(versusScreen, 3);
		
		battle = new Battle(width, height, background);
		layeredPane.add(battle, "battle");
		layeredPane.setComponentZOrder(battle, 4);
		
		victory = new Victory(width, height);
		layeredPane.add(victory,"victory");
		layeredPane.setComponentZOrder(victory, 5);
	}
	
	public static void goToNext()
	{
		card.next(layeredPane);
	}
	
	public static void goToPrior()
	{
		card.previous(layeredPane);
	}
	public void eventHandlers()
	{
		
		
		opening.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToNext();
			}
		});
		
		
		enterName.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(enterName.getName().equals(""))
				{
					JOptionPane.showMessageDialog(enterName, "We have to call u something, \n please enter a name!");
				}
				else
				{
					
					P1name = enterName.getName();
					enterName.reset();
					pokemonSelect.setName(P1name);
					synchronized(getSyncObject())
					{
						getSyncObject().notifyAll();
					}
					
					
				}
			
			}
		});
		
		pokemonSelect.submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

					synchronized(getSyncObject())
					{
						getSyncObject().notifyAll();
					}
					go =  true;

			}
		});
	}
	
	public static void loadResources()
	{
		
		try {
			background= new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/background.png")).getScaledInstance(width, height, Image.SCALE_DEFAULT));
			p1 = new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/player1.png")).getScaledInstance(width/ 4, height / 2,Image.SCALE_DEFAULT));
			p2 = new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/player2.png")).getScaledInstance(width/ 4, height / 2,Image.SCALE_DEFAULT));
			warning = new ImageIcon(ImageIO.read(ResourceLoader.load("/resources/warning.png")));
			ballImage = ImageIO.read(ResourceLoader.load("/resources/ball.png"));
			
			
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		
		try {
		    //create the font to use. Specify the size!
		    press2P = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("/resources/PressStart2P-Regular.ttf"));
		    press2P = press2P.deriveFont(48f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(press2P);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		try {
			readType.read();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public static Object getSyncObject() {
		return syncObject;
	}

	public static void setSyncObject(Object syncObject) {
		GUI.syncObject = syncObject;
	}

	
	
	
	
	
	
	
	
	
	
	
	private static boolean goesFirst(Trainer p1, Trainer p2)
	{
		if(p1.getLastMove().getPriority() > p2.getLastMove().getPriority())
		{
			return true;
		}
		else if(p1.getLastMove().getPriority() < p2.getLastMove().getPriority())
		{
			return false;
		}
		if(p1.getCurr().getSpeed() > p2.getCurr().getSpeed())
		{
			return true;
		}
		else if(p2.getCurr().getSpeed() > p1.getCurr().getSpeed())
		{
			return false;
		}
		else
		{
			if (rand.nextInt(100) > rand.nextInt(100))
			{
				return true;
			}
		}
		return false;
	}
	
	
	private static int calcDamage(Trainer attacker, Trainer defender)
	{
		
		//used a move // console
		battle.setConsoleText(attacker.getCurr().getName() +" used " + attacker.getLastMove().getName());
		attacker.getLastMove().changePP(-1);
		if(rand.nextInt(100 ) + 1 > attacker.getLastMove().getAccuracy() )
		{
			battle.setMessage(attacker.getCurr().getName() + "'s " + attacker.getLastMove().getName() + " missed!");
			//miss //console
			return 0;
		}
		else
		{
			
			double dam = 22;
			double atk;
			double def;
			if(attacker.getLastMove().getPhysical() == 0)
			{
				atk = (double)attacker.getCurr().getAtk();
				def = (double)defender.getCurr().getDef();
			}
			else
			{
				atk = (double)attacker.getCurr().getSpatk();
				def = (double)defender.getCurr().getSpdef();
			}
			
			dam *=(atk/def);
			dam *= (double)attacker.getLastMove().getAtk();
			dam /= 50.0;
			
			double mod = 1.0;
			
			double stab = 1.0;
			if(attacker.getLastMove().getType() == attacker.getCurr().getType1() 
					|| attacker.getLastMove().getType() == attacker.getCurr().getType2())
			{
				stab = 1.5;
			}
			
			double rando = (double)rand.nextInt(16) + 85;
			
			double super_effective = readType.type[attacker.getLastMove().getType() -1]
													[defender.getCurr().getType1() - 1];
			if(defender.getCurr().getType2() != 0)
			{
				super_effective*= readType.type[attacker.getLastMove().getType() - 1]
						[defender.getCurr().getType2() - 1];
			}
			
			if(super_effective > 1)
			{
				battle.setMessage("It's Super Effective!");
			}
			if(super_effective < 1)
			{
				battle.setMessage("It's not very effective.");
			}
			
			
			double crit = 1.0;
			if(rand.nextInt(10000) + 1 <= 625)
			{
				crit = 1.5;
				battle.setMessage("It's a critical Hit!");
			}
			
			mod = super_effective *rando * stab *crit / 100.0;
			
			dam *= mod;
			return (int)dam;
		}
	}
	
	public static void battle(Trainer p1, Trainer p2)
	{
		battle.setSprite(p1, true);
		battle.setSprite(p2, true);
		if(p1.getSwitch() && p2.getSwitch())
		{
			//switch//
			changePokemon(p1);
			//switch//
			changePokemon(p2);
		}
		else if(p1.getSwitch() && !p2.getSwitch())
		{
			//add switch//
			changePokemon(p1);
			//damage//
			changeDamage(calcDamage(p2,p1),p1);
			
			
			checkDeath(p2,p1);
			checkDeath(p1,p2);
		}
		else if(!p1.getSwitch() && p2.getSwitch())
		{
			//add switch//
			changePokemon(p2);
			//damage//
			changeDamage(calcDamage(p1,p2),p2);
			checkDeath(p1,p2);
			checkDeath(p2,p1);
		}
		else if(!p1.getSwitch() && !p2.getSwitch())
		{
			//goes first//
			Trainer first, second;
			 if(goesFirst(p1, p2))
			 {
				 first = p1;
				 second = p2;
			 }
			 else
			 {
				 first = p2;
				 second = p1;
			 }
			
			
			//battle//
			changeDamage(calcDamage(first,second),second);
			
			checkDeath(first,second);
			
			
			if(!second.getCurr().isFainted())
			{
				changeDamage(calcDamage(second,first),first);
			}
			else
			{
				checkDeath(second,first);

			}
			
		}
		
	}
	public static void checkDeath(Trainer x, Trainer y)
	{
		
		battle.printMessages();
		if(x.getCurr().isFainted())
		{
			battle.setMessage(x.getCurr().getName()+ " has fainted");
			battle.printMessages();
			if(x.isStillIn())
			{
				forceSwitch(x);
			}
			else
			{
				winner = y;
				inBattle =false;
			}
		}
	}
	
	public static void forceSwitch(Trainer x)
	{
		battle.setCurrTrainer(x);
		battle.death(x);
		
		synchronized(getSyncObject())
		{
			while(!go)
			{
				try {
					getSyncObject().wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
		x.setCurr(x.getMon(x.getToSwitch()));
		x.setToSwitch(0);
		x.setSwitch(false);
		battle.setSprite(x, true);
		go = false;
		battle.battlePhase();
		battle.setConsoleText(x.getName() +" sends out "+ x.getCurr().getName());
		if(x.getRight())
		{
			battle.enterRight();
		}
		else
		{
			battle.enterLeft();
		}

	}

	public static void changePokemon(Trainer x)
	{
		battle.setCurrTrainer(x);
		battle.setConsoleText("");
		battle.setConsoleText(x.getName() + " calls back "+x.getCurr().getName());
		if(x.getRight())
		{
			battle.exitRight();
		}
		else
		{
			battle.exitLeft();
		}
		
		x.setCurr(x.getMon(x.getToSwitch()));
		x.setToSwitch(0);
		x.setSwitch(false);
		battle.setSprite(x, true);
		battle.setConsoleText(x.getName() + " sends out "+x.getCurr().getName());
		if(x.getRight())
		{
			battle.enterRight();
		}
		else
		{
			battle.enterLeft();
		}
		battle.setConsoleText("");
	}
	public static void changeDamage(int x, Trainer defender)
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		double inc;
		if(x > 20)
		{
			inc = x / 20.0;
		}
		else
		{
			inc = 1.1;
		}
		int hpOG = defender.getCurr().getHp();
		if(defender.getRight())
		{
			for(double i = 0; i < x ; i += inc)
			{
				defender.getCurr().changeHp(-(int)inc);
				battle.healthRight(defender.getCurr(),defender);
				battle.flicker(defender);
				if(defender.getCurr().getHp() == 0)
				{
					break;
				}
			}
			if(defender.getCurr().getHp() !=  (hpOG - x))
			{
				defender.getCurr().setHp(hpOG - x);
				battle.healthRight(defender.getCurr(),defender);
			}
		}
		else
		{
			for(double i = 0; i < x ; i += inc)
			{
				defender.getCurr().changeHp(-(int)inc);
				battle.flicker(defender);
				battle.healthLeft(defender.getCurr(),defender);
				if(defender.getCurr().getHp() == 0)
				{
					break;
				}
			}
			if(defender.getCurr().getHp() !=  (hpOG - x))
			{
				defender.getCurr().setHp(hpOG - x);
				battle.healthLeft(defender.getCurr(),defender);
			}
		}
		
		
	}
	
	public static void reset()
	{
		card.first(layeredPane);
		opening.reset();
		enterName.setName("player 2");
		enterName.repaint();
		enterName.reset();
		pokemonSelect.reset();
		pokemonSelect.repaint();
		battle.reset();
		battle.repaint();
		game = true;
		go  =false;
		inBattle = true;
		winner = null;
		P1name = "";
	}

}
