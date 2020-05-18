package karelQuest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI {

	public static void main(String[] args) {
		//---------------------------------------------------------//
		
		//Settings Variables
		int height = 300; int width = 400;
		int gHeight = 238; int gWidth = 516;
		int startingHealth = 100;
		
		//Class declaration
		JFrame frame = new JFrame("Info");
		frame.setLayout(null);
		
		
		
		
		UpdateQue Que = new UpdateQue(startingHealth);
		
		//---------------------------------------------------------//
		//---STUFF FOR frame WINDOW--------------------------------//
		
		frame.setPreferredSize(new Dimension(width, height));
		
		//Elements
		JButton testHealth = new JButton("Damage Karel");
		testHealth.setBounds(0, 0, 200, 50);
		JTextField kHealth = new JTextField();
		kHealth.setBounds(0,50,100,50);
		JProgressBar healthBar = new JProgressBar();
		
		//Element Setup
		healthBar.setMaximum(startingHealth);
		healthBar.setValue(Que.getPlayer().getHealth());
		kHealth.setEditable(false);
		kHealth.setText(Integer.toString(Que.getPlayer().getHealth()));
		//Adding Elements to Frame
		//frame.add(contentPane);
		frame.add(healthBar);
		frame.add(kHealth);
		frame.add(testHealth);
		
		
		//Setting frame behavior
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame.setSize(width, height);
		frame.setVisible(true);
		frame.pack();
		
		
		
		//---------------------------------------------------------//
		//---STUFF FOR gameWindow WINDOW---------------------------//
		
		JFrame gameWindow = new JFrame("Game"); //!
		gameWindow.setPreferredSize(new Dimension(gWidth, gHeight));//!
		Draw d = new Draw();
		
		gameWindow.add(d);
				
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setVisible(true);
		Que.draw(d, new Floor(50,20,3));
		gameWindow.pack();
		
		
		
		//---------------------------------------------------------//
		
		//ActionListener Events
		testHealth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				takeDamage(Que, healthBar, kHealth, 8);
				System.out.println(gameWindow.getHeight());
				System.out.println(gameWindow.getWidth());
				Que.draw(d, new Floor(50,20,3));
			}
			
		});
	}
	public static void takeDamage(UpdateQue Que, JProgressBar healthBar, JTextField health, int amount) {
		//Pre: an UpdateQue, JProgressBar, JTextField, and an Integer
		//Post: Uses UpdateQue player's health to reduce the JProgressBar and JTextField values by 'amount'
		Que.getPlayer().takeDamage(amount);
		health.setText(Integer.toString(Que.getPlayer().getHealth()));
		healthBar.setValue(Que.getPlayer().getHealth());
	}

}
