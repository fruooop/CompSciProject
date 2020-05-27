package karelQuest;

import java.awt.*;

import javax.swing.*;

public class UpdateQue {
	private static Player karel = new Player(100,0,0,"Player");
	
	public UpdateQue(int pHealth) {
		//BASICALLY USELESS RN, Need to link to actual player health!
		//karel.setHealth(pHealth);
	}
	
	public void draw(Draw d, Floor f) {
		//Draws the floor using the draw class (Basically a shortcut)
		d.Drawing(f);
	}
	public void movePlayer(Floor f, String c) {
		//SUPPOSE TO FIND PLAYER, but something isn't right
		try {
			((Player) f.getAt(f.getSeedX(), f.getSeedX()).getEntity()).move(f, c);
		}
		catch(Exception e){
			System.out.println("Bruh, Something isnt right");
		}
		
	}
	
	//Getters
	public static Player getPlayer() {
		return karel;
	}
}
