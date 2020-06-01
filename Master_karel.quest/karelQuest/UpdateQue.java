package karelQuest;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class UpdateQue {
	private static Player karel = new Player(100,0,0,"Player " + Utilities.randomName());
	private static int fWidth = 50;
	private static int fHeight = 20;
	private static int rooms = 5;
	private static Floor f = new Floor(fWidth,fHeight,rooms);
	Draw dr;
	
	public UpdateQue(int pHealth) {
		//BASICALLY USELESS RN, Need to link to actual player health!
		//karel.setHealth(pHealth);
	}
	
	public void draw(Draw d, Floor f) {
		//Draws the floor using the draw class (Basically a shortcut)
		dr = d;
		d.Drawing(f);
	}
	public boolean regenerateRoom() {
		if(!(dr == null)) {
			f = new Floor(fWidth,fHeight,rooms);
			draw(dr, f);
			return true;
		}
		return false;
		
	}
	public void playerAction(Floor f, String input) {
		//Moves the player on floor f based on keyboard input
		input = input.toLowerCase();
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
			else if(input.equals("q"))
			{
				f.getPlayer().use(f);
			}
			else if(input.equals("r")) {
				if(f.getAt(f.getPlayer().getX(),f.getPlayer().getY()).isStairs()) {
					karel = f.getPlayer();
					regenerateRoom();
				}
			}
			else if(Integer.parseInt(input)>=0 &&
					Integer.parseInt(input) < f.getPlayer().getInventorySize())
			{
				f.getPlayer().switchItem(Integer.parseInt(input));
			}//aaaaa
			
		}
		catch(Exception e){
			System.out.println("Bruh, Something isn't right");
		}
		System.out.println(takeOtherActions(f));
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
		return statusString(f);
	}
	
	public static Floor getFloor() {
		//returns floor
		return f;
	}
	
	private String statusString(Floor f) {
		//Returns a string with a summary of what is going on.
		String s = "";
		s += f.getPlayer().toString();
		return s;
	}
}
