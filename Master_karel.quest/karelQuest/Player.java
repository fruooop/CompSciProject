package karelQuest;

import java.util.ArrayList;

public class Player extends Entity 
{
	//Variables with player
	private ArrayList<Item> inventory;
	private Item equipped;
	private int slotIndex;
	private int openSlots;
	private int x;
	private int y;

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
		if(f.getAt(x,y).hasEntity())
		{
			Entity p = f.getAt(x,y).getEntity();
			if(s.equals("w") && !f.getAt(x,y-1).hasEntity())
			{
				if(y != 0)
				{
					f.getAt(x,y).setEntity(null);
					setY(y-1);
					return f.getAt(x,y-1).setEntity(p);
				}
				return false;
			}
			if(s.equals("a") && !f.getAt(x-1,y).hasEntity())
			{
				if(x != 0)	
				{
					f.getAt(x,y).setEntity(null);
					setX(x-1);
					return f.getAt(x-1,y).setEntity(p);
				}
				return false;
			}
			if(s.equals("s") && !f.getAt(x,y+1).hasEntity())
			{	
				if(y != f.getHeight() - 1)
				{
					f.getAt(x,y).setEntity(null);
					setY(y+1);
					return f.getAt(x,y+1).setEntity(p);
				}
				return false;
			}
			if(s.equals("d") && !f.getAt(x+1,y).hasEntity())
			{
				if(x != f.getWidth() - 1)
				{
					f.getAt(x,y).setEntity(null);
					setX(x+1);
					return f.getAt(x+1,y).setEntity(p);
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
}