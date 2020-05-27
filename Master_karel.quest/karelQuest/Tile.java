package karelQuest;

public class Tile 
{

	private int xCoord;
	private int yCoord;
	private boolean walkable;
	private Entity entityOn;
	
	public Tile(boolean walk, int x, int y)
	{
		walkable = walk;
		xCoord = x;
		yCoord = y;
	}
	
	//Setters
	public void walkOn() 
	{
		//turns the walkable state true
		walkable = true;
	}
	
	public void walkOff() 
	{
		//turns the walkable state false
		walkable = false;
	}
	
	public boolean isWalkable() {return walkable;}
	
	public boolean hasEntity() 
	{
		//returns whether a player is on a tile or not
		return entityOn != null;
	}
	//aaaaaaaaaaaaaaa
	public boolean setEntity(Entity newEntity) 
	{
		//sets a new player for the tile. returns true if success, false otherwise.
		//My thought is to use this for moving the player around
		if(!walkable) {
			return false;
		}
		else 
		{
			entityOn = newEntity;
		}
		//\/Commented out for now, Causes generation issues\/
		//walkable = false;//cannot walk on a tile with an entity
		return true;
	}
	public Entity getEntity() {return entityOn;}
	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
}
