package views;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu extends JPanel {
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	//private ImageIcon background;

	
	/**
	 * Create the panel.
	 */
	public StartMenu(int x, int y, int width, int height, ImageIcon icon) {
		
		
		initComponents(x, y, width, height, icon);
		eventHandlers();
		
	}
	public void initComponents(int x, int y, int width, int height, ImageIcon background)
	{
		
		setVisible(true);
		setBounds(x,y,width, height);
		setLayout(null);

		
		btnNewButton = new JButton("Start");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Press this to play the game");
		
		btnNewButton.setFont(GUI.press2P);
		btnNewButton.setBounds( width /3,(height *2) /5, width/3, height / 5);
		add(btnNewButton);

		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, width, height);
		lblNewLabel.setIcon(background);
		add(lblNewLabel);

	}
	
	public void eventHandlers() {
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("Hello");
//				GUI.doneOpening();
//			}
//		});
	}
	
	public JButton getButton()
	{
		return btnNewButton;
	}

	public void reset()
	{
		repaint();
	}
}
