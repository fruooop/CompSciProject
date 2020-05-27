package karelQuest;

import java.util.ArrayList;

public class Player extends Entity 
{
	//Variables with player
	private ArrayList<Item> inventory;
	private Item equipped;
	private int slotIndex;
	private int openSlots;
	//private int x;  USE super.getX() instead
	//private int y;  USE super.getY() instead

	public Player(int sendHealth, int newX, int newY, String newString)
	{
		super(sendHealth, newX, newY, newString);
		inventory = new ArrayList<Item>();
		equipped = null;
		slotIndex = 0;
		openSlots = 5;
	}

	//pre: switches the currently held item
	//slotNumber < 6
	//post returns the item switched to
	public Item switchItem(int slotNumber)
	{
		if((slotNumber <= inventory.size()))
		{
			slotIndex = slotNumber - 1;
			equipped = inventory.get(slotIndex);
			return equipped;
		}
		else
			return null;
	}
	public int use()
	{
		if(equipped != null)
			return equipped.use();
		else 
			return -1;
	}
	public boolean pickUp(Item I)
	{
		if(openSlots > 0)
		{
			inventory.add(I);
			openSlots--;
			return true;
		}
		return false;
	}
	//x and y are the coords of the player to be moved
	public boolean move(Floor f, String s)
	{
		System.out.println(super.getX());
		System.out.println(super.getY());
		if(f.getAt(super.getX(),super.getY()).hasEntity())
		{
			Entity p = f.getAt(super.getX(),super.getY()).getEntity();
			if(s.equals("w") && !f.getAt(super.getX(),super.getY()-1).hasEntity())
			{
				if(super.getY() != 0)
				{
					f.getAt(super.getX(),super.getY()).setEntity(null);
					setY(super.getY()-1);
					return f.getAt(super.getX(),super.getY()-1).setEntity(p);
				}
				return false;
			}
			if(s.equals("a") && !f.getAt(super.getX()-1,super.getX()).hasEntity())
			{
				if(super.getX() != 0)	
				{
					f.getAt(super.getX(),super.getY()).setEntity(null);
					setX(super.getX()-1);
					return f.getAt(super.getX()-1,super.getY()).setEntity(p);
				}
				return false;
			}
			if(s.equals("s") && !f.getAt(super.getX(),super.getY()+1).hasEntity())
			{	
				if(super.getY() != f.getHeight() - 1)
				{
					f.getAt(super.getX(),super.getY()).setEntity(null);
					setY(super.getY()+1);
					return f.getAt(super.getX(),super.getY()+1).setEntity(p);
				}
				return false;
			}
			if(s.equals("d") && !f.getAt(super.getX()+1,super.getY()).hasEntity())
			{
				if(super.getX() != f.getWidth() - 1)
				{
					f.getAt(super.getX(),super.getY()).setEntity(null);
					setX(super.getX()+1);
					return f.getAt(super.getX()+1,super.getY()).setEntity(p);
				}
				return false;
			}
			return false;
		}
		return false;
	}

}