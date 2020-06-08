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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI {
	static int blockScale = 10;
	
	
	public static void main(String[] args) {
		//---------------------------------------------------------//
		
		//Settings Variables
		int height = 139; int width = 290;
		
		int startingHealth = 100;
		
		//Class declaration
		JFrame frame = new JFrame("Info");
		frame.setLayout(null);
		//test
		
		
		
		UpdateQue que = new UpdateQue(startingHealth);
		int gHeight = (UpdateQue.getFloor().getWidth()*blockScale)+UpdateQue.getFloor().getHeight()+blockScale*3; //Fancy math for calculating gameWindow Height
		int gWidth = (UpdateQue.getFloor().getHeight()*blockScale) + UpdateQue.getFloor().getWidth()+blockScale; //Fancy math for calculating gameWindow Widths
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
		
		JComboBox items = new JComboBox();
		items.setBounds(100, 50, 175, 50);
		items.addItem("test");
		
		/*
		//Karel health(bar)
		JProgressBar healthBar = new JProgressBar();
		healthBar.setBounds(100, 50, 175, 50);
		*/
		
		
		//Secret Spinner, Dont touch!
		JSpinner secretSpinner = new JSpinner();
		secretSpinner.setBounds(0, 200, 50, 50);
		secretSpinner.setValue(Integer.valueOf(10));
		
		//Secret Button, Dont touch!
		JButton ehButton = new JButton("eh");
		ehButton.setBounds(50, 200, 200, 50);
		
		//Element Setup
		//healthBar.setMaximum(startingHealth);
		//healthBar.setValue(Que.getPlayer().getHealth());
		kHealth.setEditable(false);
		//kHealth.setText(Integer.toString(Que.getPlayer().getHealth()));
		//Adding Elements to Frame
		//frame.add(contentPane);
		frame.add(regen);
		frame.add(items);
		//frame.add(healthBar);
		frame.add(kHealth);
		frame.add(testHealth);
		frame.add(secretSpinner);
		frame.add(ehButton);
		
		
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
		que.draw(d, UpdateQue.getFloor());
		gameWindow.setResizable(false);
		gameWindow.pack();
		
		
		
		//---------------------------------------------------------//
		
		//ActionListener Events
		regen.addActionListener(new ActionListener() {
			//Regenerate Map Button Actions
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(frame.getHeight());
				System.out.println(frame.getWidth());
				que.regenerateRoom();
			}
			
		});
		testHealth.addActionListener(new ActionListener() {
			//TestHealth Button Actions
			@Override
			public void actionPerformed(ActionEvent e) {
				//takeDamage(que, healthBar, kHealth, 8);
			}
			
		});
		items.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        
		    }
		});
		ehButton.addActionListener(new ActionListener() {
			//eh Button Actions
			public void actionPerformed(ActionEvent e) {
				//sets blockscale to spinner value
				blockScale = (Integer)secretSpinner.getValue();
				d.setBlockScale(blockScale);
				
				//sets height based on new scale
				int newHeight = (UpdateQue.getFloor().getWidth()*blockScale)+UpdateQue.getFloor().getHeight()+blockScale*10; //Fancy math for calculating gameWindow Height
				int newWidth = (UpdateQue.getFloor().getHeight()*blockScale) + UpdateQue.getFloor().getWidth()+blockScale;
				
				//Generates new floor based on new scale and size
				//f = new Floor(fWidth,fHeight,rooms);fdsaf
				
				//resizes window
				gameWindow.setSize(new Dimension(newWidth, newHeight));
				
				//Redraws Stuffs
				que.draw(d, UpdateQue.getFloor());
			}
			
		});
		
		 gameWindow.addKeyListener(new KeyListener() {

	          public void keyTyped(KeyEvent e) {
	        	  
	          }

	          public void keyReleased(KeyEvent e) {}

	          public void keyPressed(KeyEvent e) {
	        	  if(!UpdateQue.isDead()) {
	        		  System.out.println("Pressed " + e.getKeyChar());
		        	  que.playerAction(UpdateQue.getFloor(), Character.toString(e.getKeyChar()));
		        	  que.shimmyInventory();
		        	  que.draw(d, UpdateQue.getFloor());
	        	  }
	        	  else {
	        		  System.out.println("GAME OVER MAN! " + Utilities.randomNegReaction());
	        	  }
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
