package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Victory extends JPanel {

	JTextPane congrats;
	JTextPane playersName;
	JLabel victor;
	JLabel background;
	JLabel pokemon1,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6;
	
	JButton again;
	JButton no;
	/**
	 * Create the panel.
	 */
	public Victory(int width, int height) {
		setSize(width, height);
		setLayout(null);
		initComponents(width, height);
	}

	private void initComponents(int width, int height)
	{
		congrats = new JTextPane();
		congrats.setBounds(width / 5, height / 10, width *3 / 5, height /10 );
		congrats.setFont(GUI.press2P.deriveFont(50f));
		congrats.setOpaque(false);
		congrats.setVisible(true);
		add(congrats);
		
		playersName=new JTextPane();
		playersName.setFont(GUI.press2P.deriveFont(50f));
		playersName.setBounds(width / 10, height *2 / 10, width * 8 / 10, height / 10);
		playersName.setVisible(false);
		playersName.setOpaque(false);
		add(playersName);
		
		victor = new JLabel();
		victor.setBounds(width *3 / 8, height /4 , width/4, height /2);
		victor.setVisible(true);
		add(victor);
		
		pokemon1 = new JLabel();
		pokemon1.setBounds(width / 4, height / 2, width /20, height /20);
		add(pokemon1);
		
		pokemon2 = new JLabel();
		pokemon2.setBounds(width / 4, height * 12 /20, width /20, height /20);
		add(pokemon2);
		
		pokemon3 = new JLabel();
		pokemon3.setBounds(width / 4, height * 14/ 20, width /20, height /20);
		add(pokemon3);
		
		pokemon4 = new JLabel();
		pokemon4.setBounds(width *3 / 4, height / 2, width /20, height /20);
		add(pokemon4);
		
		pokemon5 = new JLabel();
		pokemon5.setBounds(width *3 / 4, height *12 / 20, width /20, height /20);
		add(pokemon5);
		
		pokemon6 = new JLabel();
		pokemon6.setBounds(width *3 / 4, height *14/ 20, width /20, height /20);
		add(pokemon6);
		
		
		again = new JButton();
		again.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
				GUI.reset();
				
			}
		});
		again.setBounds(width/ 10 , height * 4 / 5, width *2 / 6, height /7);
		again.setVisible(false);
		again.setFont(GUI.press2P);
		again.setText("YES");
		add(again);
		
		no = new JButton();
		no.setFont(GUI.press2P);
		no.setText("NO");
		no.setBounds(width * 17 / 30, height * 4 /5, width / 3, height / 7);
		no.setVisible(false);
		add(no);
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(GUI.getSyncObject())
				{
					GUI.getSyncObject().notifyAll();
				}
				GUI.go =  true;
				GUI.game = false;
				
			}
		});
		
		background = new JLabel();
		background.setBounds(0,0, width, height);
		background.setIcon(GUI.background);
		add(background);
	}
	
	public void congratulations(Trainer winner)
	{
		congrats.setText("CONGRATS!!!"  );
		playersName.setText( winner.getName() + " has won!!");
		
		victor.setIcon(GUI.winner.getLook());
		
		pokemon1.setIcon(GUI.winner.getMon(1).getIcon());
		pokemon2.setIcon(GUI.winner.getMon(2).getIcon());
		pokemon3.setIcon(GUI.winner.getMon(3).getIcon());
		pokemon4.setIcon(GUI.winner.getMon(4).getIcon());
		pokemon5.setIcon(GUI.winner.getMon(5).getIcon());
		pokemon6.setIcon(GUI.winner.getMon(6).getIcon());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		congrats.setText("PLAY AGAIN??");
		playersName.setText("");
		again.setVisible(true);
		no.setVisible(true);
	}
	

}
