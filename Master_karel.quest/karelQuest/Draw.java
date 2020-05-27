package karelQuest;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel{
	//THIS IS JUST FOR TESTING BACKUP BRANCH
	Floor f;
	Color BLACK = new Color(0,0,0);
	Color defStairColor = new Color(125, 93, 38);
	Color defPlayerColor = new Color(255, 77, 0);
	Color defBackgroundColor = new Color(209, 209, 224);
	Color defWallColor = new Color(118, 118, 162);
	Color defZombieColor = new Color(0, 255, 102);
	Color defSkeletonColor = new Color(65, 85, 95);
	int blockScale = 20;
	int smallBlockScale = (int) ((.6)*blockScale);
	int offsetScale = (int) ((.2)*blockScale);
	
	public void setBlockScale(int scale) {
		blockScale = scale;
		smallBlockScale = (int) ((.6)*blockScale);
		offsetScale = (int) ((.2)*blockScale);
	}
	public void Drawing(Floor floor) {
		//PRE: a Floor object
		//POST: Draws it to a frame (Make sure you add it to a frame with FRAMENAME.add(DRAW_OBJECT_NAME))
		
		
		//THIS IS ALSO JUST FOR TESTING
		//CAN PASS A WORKING FLOOR IN
		//floor.generateLayout();
		f=floor;
		System.out.println(f.toString());
		repaint();
	}
	
	//SETTING UP PAINTABLE COMPONENT
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Generating and Drawing floor and players
		for(int i = 0; i< f.getHeight(); i++) {
			
			for(int j = 0; j < f.getWidth(); j++) {
				
				if(f.getAt(j, i).isWalkable()) {
					//If the space is walkable
					if(f.getAt(j, i).hasEntity()) {
						//If the space is an entity
						if(f.getAt(j,i).getEntity().getName().toLowerCase().contains("player")) {
							//Removes background color from player
							g.setColor(defBackgroundColor);
							g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
							
							//Makes player
							g.setColor(defPlayerColor);
							g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
						}
						
						if(f.getAt(j,i).getEntity().getName().toLowerCase().contains("skeleton")) {
							//Removes background color from entity
							g.setColor(defBackgroundColor);
							g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
							
							//Makes player
							g.setColor(defSkeletonColor);
							g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
						}
						
						if(f.getAt(j,i).getEntity().getName().toLowerCase().contains("zombie")) {
							//Removes background color from player
							g.setColor(defBackgroundColor);
							g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
							
							//Makes player
							g.setColor(defZombieColor);
							g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
						}
						
						
					}
					else if(f.getAt(j,i).isStairs()) {
						//Removes background color from entity
						g.setColor(BLACK);
						g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
						
						//Makes player
						g.setColor(defStairColor);
						g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
					}
					else {
						g.setColor(defBackgroundColor);
						g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
					}
					
				}
				
				else {
					g.setColor(defWallColor);
					g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
				}
				
			}
		}
	}
}
