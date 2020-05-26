package karelQuest;

public class Entity 
{
	private int health;
	private int x;
	private int y;
	//constructor
	public Entity(int sendHealth, int newX, int newY)
	{
		health = sendHealth;
		x = newX;
		y = newY;
	}
	public Entity() {}
	//setters
	public int setHealth(int sendHealth)
	{
		health = sendHealth;
		return health;
	}
	public int setX(int newX)
	{
		x = newX;
		return newX;
	}
	public int setY(int newY)
	{
		y = newY;
		return newY;
	}
	//takes "damage" ammount of damage
	public int takeDamage(int damage) 
	{
		//Pre: an int damage
		//Post: reduces health variable by damage
		if(health - damage > 0) 
		{
			health -= damage;
		}
		else 
		{
			health = 0;
		}
		return health;
	}
	//getters
	public int getHealth() { return health;}
	public int getX() { return x;}
	public int getY() { return y;}
	public void swap(int newX, int newY, Floor f)
	{
		Entity e = f.getAt(x, y).getEntity();
		f.getAt(x, y).setEntity(null);
		f.getAt(newX, newY).setEntity(e);
		setY(newY);
		setX(newX);
	}
}
