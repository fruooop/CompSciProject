package karelQuest;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel{
	Floor f;
	
	//Color Declaration
	private Color PINK = new Color(255, 153, 153);
	private Color RED = new Color (255, 0 , 0);
	private Color WHITE = new Color(255, 255, 255);
	private Color GRAY = new Color(100, 100, 100);
	private Color GRAY2 = new Color(175, 175, 175);
	private Color BLACK = new Color(0,0,0);
	private Color defStairColor;
	private Color defPlayerColor;
	private Color defBackgroundColor;
	private Color defWallColor;
	private Color defZombieColor;
	private Color defSkeletonColor;
	private Color defItemColor;

	//Pixel/block size
	int blockScale = 10;
	int smallBlockScale = (int) ((.6)*blockScale);//
	int offsetScale = (int) ((.2)*blockScale);
	
	//Background Width/Height
	Font aSans = new Font("Comic Sans MS", Font.PLAIN, blockScale);
	Font dSans = new Font("Comic Sans MS", Font.BOLD, blockScale*2);
	int bgW;
	int bgH;
	
	//Other
	boolean dead;
	
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
		//aSans = new Font("Papyrus", Font.PLAIN, blockScale);
	}
	public void Drawing(Floor floor , boolean deadCheck) {
		//PRE: a Floor object
		//POST: Draws it to a frame (Make sure you add it to a frame with FRAMENAME.add(DRAW_OBJECT_NAME))
		
		
		//THIS IS ALSO JUST FOR TESTING
		//CAN PASS A WORKING FLOOR IN
		//floor.generateLayout();
		dead = deadCheck;
		f=floor;
		setMapBGSize();
		System.out.println(f.toString());
		repaint();
	}
	
	//SETTING UP PAINTABLE COMPONENT
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!dead) {
			defStairColor = new Color(125, 93, 38);
			defPlayerColor = new Color(255, 77, 0);
			defBackgroundColor = new Color(209, 209, 224);
			defWallColor = new Color(118, 118, 162);
			defZombieColor = new Color(0, 255, 102);
			defSkeletonColor = new Color(65, 85, 95);
			defItemColor = new Color(255, 162, 18);
		}
		else {
			defStairColor = GRAY2;
			defPlayerColor = GRAY2;
			defBackgroundColor = GRAY;
			defWallColor = BLACK;
			defZombieColor = RED;
			defSkeletonColor = RED;
			defItemColor = RED;
		}
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
				//Floor num
				g.setColor(BLACK);
				g.setFont(aSans);
				g.drawString("Floor " + UpdateQue.getFloorNum(), blockScale/10, blockScale);
				
				//PLAYER HEALTHBAR/Status String
				if(!dead) {
					g.setColor(WHITE);
				}
				else {
					g.setColor(BLACK);
				}
				
				g.fillRect(0, f.getWidth()*blockScale+blockScale, bgW, blockScale*10);
				
				String statusBarL1 = f.getPlayer().getName() + " HP: " + f.getPlayer().getHealth() + "/" + f.getPlayer().getMaxHealth() + "  "; 
				String statusBarL2 = f.getPlayer().itemNumString(0);
				String statusBarL3 = f.getPlayer().itemsExceptEquipped();
				String deadLine = f.getPlayer().getName() + ".died() = true;";
				String scoreLine = f.getPlayer().getName() + ".getScore() = " + UpdateQue.getPoints();
				g.setColor(BLACK);
				g.drawString(statusBarL1, blockScale/10, f.getWidth()*blockScale + (f.getWidth()));
				if(!dead) {
					g.drawString(statusBarL2, blockScale/10, (f.getWidth()*(blockScale))+blockScale + f.getWidth());
					g.drawString(statusBarL3, blockScale/10, (f.getWidth()*(blockScale))+blockScale*2 + f.getWidth());
				}
				else {
					g.setFont(dSans);
					g.setColor(PINK);
					g.drawString(deadLine, blockScale/10, (f.getWidth()*(blockScale))+blockScale*2 + f.getWidth());
					g.drawString(scoreLine, blockScale/10, (f.getWidth()*(blockScale))+blockScale*5 + f.getWidth());
				}
				
				if(!dead) {
					g.setColor(PINK);
					g.fillRect(0, (f.getWidth()*blockScale)+blockScale*4+2, 100, blockScale);
					g.setColor(RED);
					g.fillRect(0, (f.getWidth()*blockScale)+blockScale*4+2, 100*f.getPlayer().getHealth()/f.getPlayer().getMaxHealth(), blockScale);
				}
				
				
			}
		}
	}
}
