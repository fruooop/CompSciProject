package karelQuest;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel{
	//THIS IS JUST FOR TESTING
	Floor f;
	
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
		
		//
		//System.out.println(f.toString());
		
		//THIS IS WHERE KNOWING THE SIZE OF THE FLOOR COULD HELP!
		for(int i = 0; i< 50; i++) {
			
			for(int j = 0; j < 20; j++) {
				
				if(f.getAt(j, i).isWalkable()) {
					g.setColor(new Color(209, 209, 224));
					g.fillRect(i*10, j*10, 10, 10);
				}
				else {
					g.setColor(new Color(118, 118, 162));
					g.fillRect(i*10, j*10, 10, 10);
				}
				
			}
		}
	}
}
