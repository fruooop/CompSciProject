package karelQuest;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class UpdateQue {
	private static Player karel = new Player(30,0,0,"Player " + Utilities.randomName());
	private static int fWidth = 50;
	private static int fHeight = 20;
	private static int rooms = 5;
	private static Floor f = new Floor(fWidth,fHeight,rooms);
	private static boolean isDead = false;
	private static int floorNum = 1;
	private static int points = 0;
	Draw dr;
	
	public UpdateQue(int pHealth) {
		//BASICALLY USELESS RN, Need to link to actual player health!
		//karel.setHealth(pHealth);
	}
	
	public void draw(Draw d, Floor f) {
		//Draws the floor using the draw class (Basically a shortcut)
		dr = d;
		d.Drawing(f, isDead());
	}
	public boolean regenerateRoom() {
		if(!(dr == null)) {
			
			f = new Floor(fWidth,fHeight,rooms);
			if(floorNum%3 == 0) {

				f.getPlayer().increaseMaxHealthBy(5);
			}
			f.getPlayer().heal(5);
			draw(dr, f);
			points += 200;
			return true;
		}
		return false;
		
	}
	public boolean regenerateRoomNewPlayer() {
		if(!(dr == null)) {
			karel = new Player(30,0,0,"Player " + Utilities.randomName());
			f = new Floor(fWidth,fHeight,rooms);
			draw(dr, f);
			isDead = false;
			floorNum = 1;
			return true;
		}
		return false;
	}
	public void playerAction(Floor f, String input) {
		//Moves the player on floor f based on keyboard input
		input = input.toLowerCase();
		String actions = "";
		if(!isDead) {
			try {
				if (input.equals("w") ||
						input.equals("a") ||
						input.equals("s") ||
						input.equals("d"))
				{
					f.getPlayer().move(f, input);
				}
				else if (input.equals("e")) {
					if(f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).hasItem() &&
							!f.getPlayer().inventoryFull()) {
						f.getPlayer().pickUp(f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).getItem());
						f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).setItem(null);
					}
				}
				else if(input.equals("q"))
				{
					String temp = f.getPlayer().use(f);
					System.out.println(temp);
					actions += temp;
				}
				else if(input.equals("f"))
				{
					shimmyInventory();
				}
				else if(input.equals("r")) {
					if(f.getAt(f.getPlayer().getX(),f.getPlayer().getY()).isStairs()) {
						karel = f.getPlayer();
						regenerateRoom();
						floorNum++;
					}
				}
				else if(input.equals("z")) {
					if(!f.getAt(f.getPlayer().getX(),f.getPlayer().getY()).isStairs() &&
							!f.getAt(f.getPlayer().getX(), f.getPlayer().getY()).hasItem()) {
						f.getAt(f.getPlayer().getX(),f.getPlayer().getY()).setItem(f.getPlayer().dropItemEquipped());
					}
				}
				else if(Integer.parseInt(input)>=0 &&
						Integer.parseInt(input) <= f.getPlayer().getInventorySize())
				{
					f.getPlayer().switchItem(Integer.parseInt(input));
				}//aaaaa
			}
			catch(Exception e){
				System.out.println("Bruh, Something isn't right");
			}
			f.setPlayerAction(actions);
			f.setMonsterAction(takeOtherActions(f));
		}
	}

	//Getters
	public static Player getPlayer() {
		return karel;
	}
	
	public boolean shimmyInventory() {
		//test
		ArrayList<Item> itemList = karel.getInventoryList();
		Item lastItem = itemList.get(itemList.size() -1);
		itemList.add(0, lastItem);
		itemList.remove(itemList.size() -1);
		//System.out.println(itemList);
		return true;
	}
	
	private String takeOtherActions(Floor f) {
		//takes actions that occur after the player takes an action. eg) monsters move and attack
		//Returns a string with a summary of what is going on.
		f.removeDeadMonsters();
		String mActions = "";
		ArrayList<Monster> temp = f.getMonList();
		for(Monster m : temp) {
			String sTemp = m.act(f,f.getPlayer());
			mActions += sTemp;
			System.out.println(sTemp);
		}
		if(f.getPlayer().getHealth()<=0) {
			isDead = true;
		}
		return mActions;
	}
	
	public static Floor getFloor() {
		//returns floor
		return f;
	}

	
	public static boolean isDead() {
		return isDead;
	}
	
	public static int getFloorNum() {
		return floorNum;
	}
	
	public static int getPoints() {
		//returns num of points
		return points;
	}
	
	public static int addPoints(int numPoints) {
		//adds the number of points to points.
		//returns the new number of points
		points += numPoints;
		return points;
	}
}
