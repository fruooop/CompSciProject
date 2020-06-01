package karelQuest;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class UpdateQue {
	private static Player karel = new Player(100,0,0,"Player");
	private static int fWidth = 50;
	private static int fHeight = 20;
	private static int rooms = 5;
	private static Floor f = new Floor(fWidth,fHeight,rooms);
	
	public UpdateQue(int pHealth) {
		//BASICALLY USELESS RN, Need to link to actual player health!
		//karel.setHealth(pHealth);
	}
	
	public void draw(Draw d, Floor f) {
		//Draws the floor using the draw class (Basically a shortcut)
		d.Drawing(f);
	}
	public void playerAction(Floor f, String input) {
		//Moves the player on floor f based on keyboard input
		try {
			if (input.equals("w") ||
					input.equals("a") ||
					input.equals("s") ||
					input.equals("d"))
			{
				f.getPlayer().move(f, input);
			}
			else if (input.equals("e")) {
				if(f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).hasItem()) {
					f.getPlayer().pickUp(f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).getItem());
					f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).setItem(null);
				}
			}
			else if(input.contentEquals("q"))
			{
				f.getPlayer().use(f);
			}
			
		}
		catch(Exception e){
			System.out.println("Bruh, Something isn't right");
		}
		takeOtherActions(f);
	}

	//Getters
	public static Player getPlayer() {
		return karel;
	}
	
	private String takeOtherActions(Floor f) {
		//takes actions that occur after the player takes an action. eg) monsters move and attack
		//Returns a string with a summary of what is going on.
		f.removeDeadMonsters();
		ArrayList<Monster> temp = f.getMonList();
		for(Monster m : temp) {
			m.act(f,f.getPlayer());
		}
		return buildString(f);
	}
	
	public static Floor getFloor() {
		//returns floor
		return f;
	}
	
	private String buildString(Floor f) {
		//Returns a string with a summary of what is going on.
		String s = "";
		return s;
	}
}
