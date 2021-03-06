package karelQuest;

import java.util.ArrayList;

public class Player extends Entity 
{
	//Variables with player
	private ArrayList<Item> inventory;
	private int slotIndex; //-1 if there are no items
	private int openSlots;
	private int inventoryMaxSize = 2;
	//private int x;  USE super.getX() instead
	//private int y;  USE super.getY() instead

	public Player(int sendHealth, int newX, int newY, String newString)
	{
		super(sendHealth, newX, newY, newString);
		inventory = new ArrayList<Item>();
		inventory.add(new Weapon("Starting Sword", 4,2,1));
		slotIndex = 0;
		openSlots = 3;
	}

	//pre: switches the currently held item
	//slotNumber < 5
	//post returns the item switched to
	public Item switchItem(int slotNumber)
	{
		if((slotNumber <= inventory.size()))
		{
			slotIndex = slotNumber - 1;
			return inventory.get(slotIndex);
		}
		else
			return null;
	}
	public String use(Floor f)
	{
		//returns a text string saying what happened.dsa
		String s = "";
		if(slotIndex != -1)
		{
			if(inventory.get(slotIndex) instanceof Weapon)
			{
				Weapon w = (Weapon)inventory.get(slotIndex);
				Monster m = findWithinRadius(f, w.getRange());
				if(m != null)
				{
					s += "You attacked " + m.getName() + " for " + w.use(m) + " damage! " + Utilities.randomPosReaction();
					if(m.getHealth()<=0) {
						s+= "\n" +  m.getName() + " slank to the floor in defeat. " + Utilities.randomPosReaction();
					}
				}
				else {
					s += "You flailed your weapon wildly, but there was nobody in the area! " + Utilities.randomNegReaction();
				}
			}
			if(inventory.get(slotIndex) instanceof Potion)
			{
				Potion p = (Potion)inventory.get(slotIndex);
				s += "You quaffed the potion and recovered " + (super.heal(p.use())) + " health. " + Utilities.randomTastyReaction();
				inventory.remove(slotIndex);
				if(inventory.size() > 0)
					slotIndex = 0;
				else
					slotIndex = -1;
				openSlots++;
			}
			return s;
		}
		return "You tried to use something, but you didn't have anything to use! " + Utilities.randomNegReaction();
	}
	//adds item to inventory
	//switches the new item to equipped
	public boolean pickUp(Item i)
	{
		if(openSlots > 0) {
			inventory.add(i);
			openSlots--;
			if(slotIndex == -1)//item picked up was first item in an empty invetory
			{
				slotIndex = 0;
			}
			return true;
		}
		return false;
	}
	//x and y are the coords of the player to be moved
	public boolean move(Floor f, String s)
	{

		if(f.getAt(super.getX(),super.getY()).hasEntity())
		{
			Entity p = f.getAt(super.getX(),super.getY()).getEntity();
			if(s.equals("w")
					&& f.getAt(super.getX()-1,super.getY()) != null 
					&& !f.getAt(super.getX()-1,super.getY()).hasEntity() 
					&& f.getAt(super.getX()-1,super.getY()).isWalkable())
			{
				f.getAt(super.getX(),super.getY()).setEntity(null);
				setX(super.getX()-1);
				return f.getAt(super.getX(),super.getY()).setEntity(p);
			}
			if(s.equals("a")
					&& f.getAt(super.getX(),super.getY()-1) != null
					&& !f.getAt(super.getX(),super.getY()-1).hasEntity() 
					&& f.getAt(super.getX(),super.getY()-1).isWalkable())
			{
				f.getAt(super.getX(),super.getY()).setEntity(null);
				setY(super.getY()-1);
				return f.getAt(super.getX(),super.getY()).setEntity(p);
			}	
			if(s.equals("s")
					&& f.getAt(super.getX()+1,super.getY()) != null
					&& !f.getAt(super.getX()+1,super.getY()).hasEntity() 
					&& f.getAt(super.getX() + 1,super.getY()).isWalkable())
			{
				f.getAt(super.getX(),super.getY()).setEntity(null);
				setX(super.getX()+1);
				return f.getAt(super.getX(),super.getY()).setEntity(p);
			}//hi sam
			if(s.equals("d")
					&& f.getAt(super.getX(),super.getY()+1) != null
					&& !f.getAt(super.getX(),super.getY()+1).hasEntity()
					&& f.getAt(super.getX(),super.getY()+1).isWalkable())
			{	
				f.getAt(super.getX(),super.getY()).setEntity(null);
				setY(super.getY()+1);
				return f.getAt(super.getX(),super.getY()).setEntity(p);
			}
			return false;
		}
		return false;
	}
	//finds entities within a radius r of the player
	//pre: player is on floor f
	//post: returns null if no entity is found or the first entity found if there is one
	public Monster findWithinRadius(Floor f, int r)
	{
		ArrayList<Monster> monsters = f.getMonList();
		if(monsters.size() > 0)
		{
			for(int i = 0; i < monsters.size(); i++)
			{
				Monster temp = monsters.get(i);
				if(Math.abs(temp.getX() - super.getX()) < r + 1 && Math.abs(temp.getY() - super.getY()) < r + 1)
				{
					return temp;
				}
			}
				return null;
		}
		else
		{
			return null;
		}
		
	}
	
	public String toString() {
		String s = getName() + "\tFloor " + UpdateQue.getFloorNum() + "\n";
		s += "HP " + getHealth() + "/" + getMaxHealth() + "\n";
		s += "Inventory:\n";
		for (int num = 0; num<inventory.size(); num++) {
			s += num+ 1 + ": ";
			s += inventory.get(num);
			if (num == slotIndex) {
				s += " EQUIPPED";
			}
			s += "\n";
		}
		return s;
		
	}//aaa
	
	public ArrayList<Item> getInventoryList(){
		return inventory;
	}

	public int getInventorySize() {
		return inventory.size();
	}
	
	public boolean inventoryFull() {
		return openSlots == 0;
	}

	public Item dropItemEquipped() {
		Item temp = inventory.remove(slotIndex);
		if(slotIndex>=inventory.size()) {
			slotIndex--;
		}
		openSlots++;
		return temp;
	}
	
	public String itemNumString(int num){
		//returns the item at a certain inventory number
		String s = "";
		if(slotIndex > -1)
		{
			if(num<= inventory.size()) {
				//s += num+ 1 + ": ";
				s += inventory.get(num);
				if (num == slotIndex) {
					s = "[EQUIPPED]";
					s += inventory.get(num);
				}
			}
		}
		return s;
	}
	
	public String itemsExceptEquipped() {
		String s = "| ";
		if(slotIndex > -1)
		{
			for(int i = 0; i < inventory.size(); i++) {
				if(!(i == 0)) {
					s += inventory.get(i) + " | ";
				}
				
			}
		}
		return s += "";
	}
	
	
	
	
	
	
	
	
	
	
}