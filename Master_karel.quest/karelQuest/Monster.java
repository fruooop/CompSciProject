package karelQuest;

public  class Monster extends Entity
{
	private int baseDamage;
	private int damageRange;
	private int range;
	private int aggroRange;//how far away the player must be for the monster to "see" them
	private double fumbleChance = 1.0/(UpdateQue.getFloorNum() + 2);//how likely it is that the attack will miss
	
	public Monster(int sendHealth, int newX, int newY, String newString, int newBase, int newDamageRange, int newRange, int newAggro)
	{
		super(sendHealth, newX, newY, newString);
		baseDamage = newBase;
		damageRange = newDamageRange;
		range= newRange;
		aggroRange = newAggro;
	}
	//if the monster and the player occupy the same room, the mosnter will move into range and attack the player if they are close enough 
	//pre: floor has rooms & monster and player are on the same floor
	//post: the monster will move towards the player and attempt to attack. A status String is output.
	public String act(Floor f, Player player) 
	{
		if(!isInRange(player))
		{
			boolean moved = move(f, player);
			if (moved) {
				return super.getName() + " is on your trail.";
			}
		}
		if(isInRange(player))
		{
			int damage = attack(player);
			if(damage != 0) {
				return super.getName() + " attacks you, dealing " + damage + " damage! " + Utilities.randomHurtReaction();
			}
			else {
				return super.getName() + " tries to attack, but fumbles! " + Utilities.randomPosReaction();
			}
		}
		return "";
	}
	//monster will move to minimize the distance between the monster and player
	//pre: distance between monster and player >1 (the tiles are not adjacent)
	//post: returns whether the monster moved
	private boolean move(Floor f, Player p)
	{
		int pX = p.getX(); //player positions
		int pY = p.getY();
		int mX = super.getX(); //monster position
		int mY = super.getY();
		int xDis = mX - pX;//horizotnal distance between player and monster
		int yDis = mY - pY;//vertical distance between player and monster
		int dX = directionX(p);
		int dY = directionY(p);
		int newX = mX + dX;//desired new x coord
		int newY = mY + dY;//desired new y coord
		boolean moved = false;
		if(Math.abs(xDis) < aggroRange && Math.abs(yDis) < aggroRange)// within "detection Range" of the Player
		{
			if(Math.abs(xDis) >= Math.abs(yDis))//farther away on "x" axis, will try to move this way
			{
				if(newX > 0 && newX < f.getWidth())//wont cause out of bounds error
				{
					Tile t = f.getAt(newX, mY);
					if(t.isWalkable() && !t.hasEntity())//the monster can walk on the new desired tile
					{
						swap(newX, mY, f);
						moved = true;
					}
							
				}
				if(!moved)//monster didnt get to move in the desired x direection
				{
					if(newY > 0 && newY < f.getHeight())
					{
						Tile t = f.getAt(mX, newY);
						if(t.isWalkable() && !t.hasEntity())
						{
							swap(mX, newY, f);
							moved = true;
						}
					}
				}
			}	
			else// would rather move in y direction
			{
				if(newY > 0 && newY < f.getHeight())
				{
					Tile t = f.getAt(mX, newY);
					if(t.isWalkable() && !t.hasEntity())
					{
						swap(mX, newY, f);
						moved = true;
					}
				}
				if(!moved)
				{
					if(newX > 0 && newX < f.getWidth())//wont cause out of bounds error
					{
						Tile t = f.getAt(newX, mY);
						if(t.isWalkable() && !t.hasEntity())//the monster can walk on the new desired tile
						{
							swap(newX, mY, f);
							moved = true;
						}
					}
				}
			}
		}
		return moved;
	}
	//the player is close enough to attack
	private boolean isInRange(Entity player)
	{
		return Math.abs(getX() - player.getX()) < range + 1 && Math.abs(getY() - player.getY()) < range + 1;
	}
	private int attack(Entity player) 
	{
		//returns the amount of damage taken, if the attack was not fumbled.
		if(Math.random() > fumbleChance) {
			int damage = baseDamage + (int)(Math.random() * (damageRange + 1));
			player.takeDamage(damage);
			return damage;
		}
		return 0;
	}
	//move helper methods
	//returns the number to be added to the monsters current X coord to make it closer to the player, 0 if they share a X coord
	public int directionX(Player p)
	{
		if(super.getX() - p.getX() > 0)
			return -1;
		if(super.getX() - p.getX() < 0)
			return 1;
		return 0;
	}
	//returns the number to be added to the monsters current Y coord to make it closer to the player, 0 if they share a Y coord
	public int directionY(Player p)
	{
		if(super.getY() - p.getY() > 0)
			return -1;
		if(super.getY() - p.getY() < 0)
			return 1;
		return 0;
	}
	
}
