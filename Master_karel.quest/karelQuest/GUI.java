package karelQuest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI {
	static Floor f = new Floor(50,20,3);
	public static void main(String[] args) {
		//---------------------------------------------------------//
		
		//Settings Variables
		int height = 139; int width = 290;
		int gHeight = 238; int gWidth = 516;
		int startingHealth = 100;
		
		//Class declaration
		JFrame frame = new JFrame("Info");
		frame.setLayout(null);
		
		
		
		
		UpdateQue Que = new UpdateQue(startingHealth);
		
		//---------------------------------------------------------//
		//---STUFF FOR frame WINDOW--------------------------------//
		
		//setting window size
		frame.setPreferredSize(new Dimension(width, height));
		
		//Damage Karel button
		JButton testHealth = new JButton("Damage Karel");
		testHealth.setBounds(0, 0, 125, 50);
		
		//Regenerate Map button
		JButton regen = new JButton("Regenerate Map");
		regen.setBounds(125, 0, 150, 50);
		
		//Karel health(numerical)
		JTextField kHealth = new JTextField();
		kHealth.setBounds(0,50,100,50);
		
		//Karel health(bar)
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBounds(100, 50, 175, 50);
		
		
		
		//Element Setup
		healthBar.setMaximum(startingHealth);
		healthBar.setValue(Que.getPlayer().getHealth());
		kHealth.setEditable(false);
		kHealth.setText(Integer.toString(Que.getPlayer().getHealth()));
		//Adding Elements to Frame
		//frame.add(contentPane);
		frame.add(regen);
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
		Que.draw(d, f);
		gameWindow.pack();
		
		
		
		//---------------------------------------------------------//
		
		//ActionListener Events
		regen.addActionListener(new ActionListener() {
			//Regenerate Map Button Actions
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(frame.getHeight());
				//System.out.println(frame.getWidth());
				f = new Floor(50,20,3);
				Que.draw(d, f);
			}
			
		});
		testHealth.addActionListener(new ActionListener() {
			//Regenerate Map Button Actions
			@Override
			public void actionPerformed(ActionEvent e) {
				takeDamage(Que, healthBar, kHealth, 8);
			}
			
		});
		
		 gameWindow.addKeyListener(new KeyListener() {

	          public void keyTyped(KeyEvent e) {
	        	  
	          }

	          public void keyReleased(KeyEvent e) {}

	          public void keyPressed(KeyEvent e) {
	        	  System.out.println("Pressed " + e.getKeyChar());
	        	  Que.movePlayer(f, Character.toString(e.getKeyChar()));
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
