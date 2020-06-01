package karelQuest;

import java.util.ArrayList;

public class Player extends Entity 
{
	//Variables with player
	private ArrayList<Item> inventory;
	private int slotIndex; //-1 if there are no items
	private int openSlots;
	//private int x;  USE super.getX() instead
	//private int y;  USE super.getY() instead

	public Player(int sendHealth, int newX, int newY, String newString)
	{
		super(sendHealth, newX, newY, newString);
		inventory = new ArrayList<Item>();
		slotIndex = -1;
		openSlots = 4;
		inventory.add(new Weapon("Starting Sword", 15,5,1));
	}

	//pre: switches the currently held item
	//slotNumber < 6
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
	public void use(Floor f)
	{
		if(slotIndex != -1)
		{
			if(inventory.get(slotIndex) instanceof Weapon)
			{
				Weapon w = (Weapon)inventory.get(slotIndex);
				Monster m = findWithinRadius(f, w.getRange());
				if(m != null)
				{
					w.use(m);
				}
			}
		}
	}
	//adds item to inventory
	//switches the new item to equipped
	public boolean pickUp(Item I)
	{
		if(openSlots > 0)
		{
			inventory.add(I);
			slotIndex = inventory.size() - 1;
			openSlots--;
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
			}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}