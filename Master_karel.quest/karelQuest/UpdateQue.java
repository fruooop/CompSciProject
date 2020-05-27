package karelQuest;

import java.awt.*;

import javax.swing.*;

public class UpdateQue {
	private Player karel = new Player();
	
	public UpdateQue(int pHealth) {
		karel.setHealth(pHealth);
	}
	
	public void draw(Draw d, Floor f) {
		d.Drawing(f);
	}
	public void movePlayer(Floor f, String c) {
		((Player) f.getAt(f.getSeedX(), f.getSeedX()).getEntity()).move(f, c);
	}
	
	//Getters
	public Player getPlayer() {
		return karel;
	}
}
