package karelQuest;

import java.awt.*;

import javax.swing.*;

public class UpdateQue {
	private Room r;
	private Room r1;
	private Player karel = new Player();
	
	public UpdateQue(int pHealth) {
		karel.setHealth(pHealth);
	}
	
	public void draw(Draw d, Floor f) {
		d.Drawing(f);
	}
	
	//Getters
	public Player getPlayer() {
		return karel;
	}
}
