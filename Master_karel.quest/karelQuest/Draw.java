package karelQuest;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel{
	//THIS IS JUST FOR TESTING
	Floor f;
	Color defPlayerColor = new Color(255, 77, 0);
	Color defBackgroundColor = new Color(209, 209, 224);
	Color defWallColor = new Color(118, 118, 162);
	public void Drawing(Floor floor) {
		//PRE: a Floor object
		//POST: Draws it to a frame (Make sure you add it to a frame with FRAMENAME.add(DRAW_OBJECT_NAME))
		
		
		//THIS IS ALSO JUST FOR TESTING
		//CAN PASS A WORKING FLOOR IN
		//floor.generateLayout();
		f=floor;
		repaint();
	}
	
	//SETTING UP PAINTABLE COMPONENT
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(f.getHeight());
		//
		//System.out.println(f.toString());
		
		//THIS IS WHERE KNOWING THE SIZE OF THE FLOOR COULD HELP!
		for(int i = 0; i< f.getHeight(); i++) {
			
			for(int j = 0; j < f.getWidth(); j++) {
				
				if(f.getAt(j, i).isWalkable()) {
					//If the space is walkable
					if(f.getAt(j, i).hasEntity()) {
						//If the space is a player
						
						//Removes background color from player
						g.setColor(defBackgroundColor);
						g.fillRect(i*10, j*10, 10, 10);
						
						//Makes player
						g.setColor(defPlayerColor);
						g.fillRect(i*10+2, j*10+2, 6, 6);
					}
					else {
						g.setColor(defBackgroundColor);
						g.fillRect(i*10, j*10, 10, 10);
					}
					
				}
				
				else {
					g.setColor(defWallColor);
					g.fillRect(i*10, j*10, 10, 10);
				}
				
			}
		}
	}
}
