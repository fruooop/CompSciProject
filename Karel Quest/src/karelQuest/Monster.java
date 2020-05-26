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
	public void move(Floor f, Entity player)
	{
		Entity e = f.getAt(getX(), getY()).getEntity();
		int pX = player.getX(); //player positions
		int pY = player.getY();
		int mX = getX(); //monster position
		int mY = getY();
		if(Math.abs(mX - pX) >= Math.abs(mY - pY)) //monster is father away on the x axis, so the monster will move this way
		{
			if(mX > pX) //the monster is father to the right than the player
			{
				f.getAt(mX, mY).setEntity(null);
				f.getAt(mX-1, mY).setEntity(e);
				setX(mX -1);
			}
			else //monster is further left than the player
			{
				f.getAt(mX, mY).setEntity(null);
				f.getAt(mX+1, mY).setEntity(e);
				setX(mX+1);
			}
		}
		else
		{
			if(mY > pY) //monster is higher up than the player
			{
				f.getAt(mX, mY).setEntity(null);
				f.getAt(mX, mY-1);
				setY(mY-1);
			}
			else //monster is lower than the player
			{
				f.getAt(mX, mY).setEntity(null);
				f.getAt(mX, mY+1).setEntity(e);
				setY(mY+1);
			}
		}
	}
	public void attack(Entity player) 
	{
		player.takeDamage(baseDamage + (int)(Math.random() * (damageRange + 1)));
	}
}
