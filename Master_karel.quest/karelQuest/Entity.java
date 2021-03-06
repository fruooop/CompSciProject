package karelQuest;

public class Entity 
{
	private int health;
	private int maxHealth;
	private int x;
	private int y;
	private String name;
	//constructor
	public Entity(int sendHealth, int newX, int newY, String newString)
	{
		health = sendHealth;
		maxHealth = health;
		x = newX;
		y = newY;
		name = newString;
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
	//aaaaaaaaaaaa
	public int setY(int newY)
	{
		y = newY;
		return newY;
	}
	
	public String getName() {
		//returns String name
		return name;
	}
	//takes "damage" ammount of damage
	//passing in a negative damge heals player
	public int takeDamage(int damage) 
	{
		//Pre: an int damage
		//Post: reduces health variable by damage. returns damage
		if(health - damage > 0 && health - damage < maxHealth) 
		{
			health -= damage;
		}
		else if(health - damage > maxHealth)
		{
			health = maxHealth;
		}
		else 
		{
			health = 0;
		}
		return damage;
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
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int heal(int amount) {
		health += amount;
		if (health > maxHealth) {
			health = maxHealth;
		}
		return amount;
	}
	
	public int increaseMaxHealthBy(int amount) {
		//increases max health by amount, returns the new maxHealth
		maxHealth += amount;
		return maxHealth;
	}
}
