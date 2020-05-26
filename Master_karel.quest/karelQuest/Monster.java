package karelQuest;

public abstract class Monster extends Entity
{
	private int baseDamage;
	private int damageRange;
	
	public Monster(int sendHealth, int newX, int newY, int newBase, int newRange)
	{
		super(sendHealth, newX, newY);
		baseDamage = newBase;
		damageRange = newRange;
	}
	public void act() {}
	//monster will move to minimize the distance between the monster and player
	//pre: distance between monster and player >1 (the tiles are not adjacent)
	//aaaaaaaaaaaaaaaaaaaaaaaaa
	public void move(Floor f, Entity player)
	{
		int pX = player.getX(); //player positions
		int pY = player.getY();
		int mX = getX(); //monster position
		int mY = getY();
		if(Math.abs(mX - pX) >= Math.abs(mY - pY)) //monster is father away on the x axis, so the monster will move this way
		{
			if(mX > pX && f.getAt(mX-1, mY).isWalkable()) //the monster is father to the right than the player
			{
				swap(mX-1, mY, f);
			}
			else if(f.getAt(mX+1, mY).isWalkable()) //monster is further left than the player
			{
				swap(mX+1, mY, f);
			}
		}
		else
		{
			if(mY > pY && f.getAt(mX, mY-1).isWalkable()) //monster is higher up than the player
			{
				swap(mX, mY-1, f);
			}
			else if(f.getAt(mX, mY+1).isWalkable()) //monster is lower than the player
			{
				swap(mX, mY+1, f);
			}
		}
	}
	public void attack(Entity player) 
	{
		player.takeDamage(baseDamage + (int)(Math.random() * (damageRange + 1)));
	}
}
