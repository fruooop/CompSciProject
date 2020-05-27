package karelQuest;

public  class Monster extends Entity
{
	private int baseDamage;
	private int damageRange;
	private int range;
	private int moves;
	
	public Monster(int sendHealth, int newX, int newY, String newString, int newBase, int newDamageRange, int newRange, int newMoves)
	{
		super(sendHealth, newX, newY, newString);
		baseDamage = newBase;
		damageRange = newDamageRange;
		range= newRange;
		moves = newMoves;			
	}
	//if the monster and the player occupy the same room, the mosnter will move into range and attack the player if they are close enough 
	//pre: floor has rooms & monster and player are on the same floor
	//post: the monster will move towards the player and attempt to attack
	public void act(Floor f, Entity player) 
	{
		int pX = player.getX(); //player positions
		int pY = player.getY();
		int mX = getX(); //monster position
		int mY = getY();
		if(f.getRoomAt(mX, mY).equals(f.getRoomAt(pX, pY)))//they are in the same room
		{
			int numMoves = moves;
			while(moves > 0 && !isInRange(player))
			{
				move(f, player);
				numMoves--;
			}
			if(isInRange(player))
			{
				attack(player);
			}
		}
	}
	//monster will move to minimize the distance between the monster and player
	//pre: distance between monster and player >1 (the tiles are not adjacent)
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
	//the player is close enough to attack
	public boolean isInRange(Entity player)
	{
		return Math.abs(getX() - player.getX()) < range + 1 && Math.abs(getY() - player.getY()) < range + 1;
	}
	public void attack(Entity player) 
	{
		player.takeDamage(baseDamage + (int)(Math.random() * (damageRange + 1)));
	}
}
