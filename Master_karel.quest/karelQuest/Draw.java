package karelQuest;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel{
	Floor f;
	
	//Color Declaration
	Color PINK = new Color(255, 153, 153);
	Color RED = new Color (255, 0 , 0);
	Color WHITE = new Color(255, 255, 255);
	Color BLACK = new Color(0,0,0);
	Color defStairColor = new Color(125, 93, 38);
	Color defPlayerColor = new Color(255, 77, 0);
	Color defBackgroundColor = new Color(209, 209, 224);
	Color defWallColor = new Color(118, 118, 162);
	Color defZombieColor = new Color(0, 255, 102);
	Color defSkeletonColor = new Color(65, 85, 95);
	Color defItemColor = new Color(255, 255, 0);
	
	//Pixel/block size
	int blockScale = 10;
	int smallBlockScale = (int) ((.6)*blockScale);
	int offsetScale = (int) ((.2)*blockScale);
	
	//Background Width/Height
	Font cSans = new Font("Comic Sans MS", Font.PLAIN, blockScale);
	int bgW = (50*blockScale)+100;
	int bgH = (20*blockScale)+100;
	
	public void setBlockScale(int scale) {
		//Sets the scale of the pixels (default scale: 10 -> 10px X 10px)
		blockScale = scale;
		smallBlockScale = (int) ((.6)*blockScale);
		offsetScale = (int) ((.2)*blockScale);
		setMapBGSize();
	}
	//test
	
	private void setMapBGSize() {
		//Resizes the background of the map to fit the screen size (the +100 is arbitrary,
		//I was just annoyed at it not working)
		bgH = (f.getWidth()*blockScale)+f.getHeight()+100;
		bgW = (f.getHeight()*blockScale)+f.getWidth()+100;
		cSans = new Font("Comic Sans MS", Font.PLAIN, blockScale);
	}
	public void Drawing(Floor floor) {
		//PRE: a Floor object
		//POST: Draws it to a frame (Make sure you add it to a frame with FRAMENAME.add(DRAW_OBJECT_NAME))
		
		
		//THIS IS ALSO JUST FOR TESTING
		//CAN PASS A WORKING FLOOR IN
		//floor.generateLayout();
		f=floor;
		setMapBGSize();
		System.out.println(f.toString());
		repaint();
	}
	
	//SETTING UP PAINTABLE COMPONENT
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Sets up map wall backgrounds
		g.setColor(defWallColor);
		g.fillRect(0, 0, bgW, bgH);
		
		//Generating and Drawing floor and players
		for(int i = 0; i< f.getHeight(); i++) {
			
			for(int j = 0; j < f.getWidth(); j++) {
				
				if(f.getAt(j, i).isWalkable()) {
					//If the space is walkable
					if(f.getAt(j, i).hasEntity()) {
						//If the space is an entity
						if(f.getAt(j,i).getEntity().getName().toLowerCase().contains("player")) {
							if(f.getAt(j, i).isStairs()) {
								g.setColor(BLACK);
							}
							else {
								g.setColor(defBackgroundColor);
							}
							g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
							
							//Makes player
							g.setColor(defPlayerColor);
							g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
						}
						
						if(f.getAt(j,i).getEntity().getName().toLowerCase().contains("skeleton")) {
							//Removes background color from entity
							g.setColor(defBackgroundColor);
							g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
							
							//Makes entity
							g.setColor(defSkeletonColor);
							g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
						}
						
						if(f.getAt(j,i).getEntity().getName().toLowerCase().contains("zombie")) {
							//Removes background color from entity
							g.setColor(defBackgroundColor);
							g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
							
							//Makes entity
							g.setColor(defZombieColor);
							g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
						}
						
						
					}
					else if(f.getAt(j, i).hasItem())
					{
						g.setColor(defBackgroundColor);
						g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
						
						//Makes entity
						g.setColor(defItemColor);
						g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
					}
					else if(f.getAt(j,i).isStairs()) {
						//Removes background color from entity
						g.setColor(BLACK);
						g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
						
						//Makes entity
						g.setColor(defStairColor);
						g.fillRect(i*blockScale+offsetScale, j*blockScale+offsetScale, smallBlockScale, smallBlockScale);
					}
					else {
						//Makes empty color
						g.setColor(defBackgroundColor);
						g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
					}
					
				}
				
				else {
					//Wall colors
					g.setColor(defWallColor);
					g.fillRect(i*blockScale, j*blockScale, blockScale, blockScale);
				}
				
				
				//PLAYER HEALTHBAR
				g.setColor(WHITE);
				g.fillRect(0, f.getWidth()*blockScale+blockScale, bgW, blockScale*3);
				g.setFont(cSans);
				g.setColor(BLACK);
				g.drawString(f.getPlayer().toString(), 2, f.getWidth()*blockScale+f.getWidth());
				
				
				g.setColor(PINK);
				g.fillRect(2, (f.getWidth()*blockScale)+blockScale*3, 100, blockScale);
				g.setColor(RED);
				g.fillRect(2, (f.getWidth()*blockScale)+blockScale*3, f.getPlayer().getHealth(), blockScale);
				
			}
		}
	}
}
