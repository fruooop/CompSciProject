package karelQuest;

public class Weapon extends Item
{
	private int damageRange;
	private int baseDamage;
	public int range; //how far away a monster can be at max to use item
	
	public Weapon(String newName, int newBaseDamage, int newDamageRange, int newRange)
	{
		super(false, newName);
		baseDamage = newBaseDamage;
		damageRange = newDamageRange;		
		range = newRange;
	}
	//deals damage within basedamage, basedamage + Damage Range to the monsterr
	public int use(Monster m)
	{
		int damage = baseDamage + (int)(Math.random() * (damageRange + 1));
		m.takeDamage(damage);
		return damage;
	}
	//returns Name, BaseDamage, BaseDamage + Damage Range
	public String toString()
	{
		String s = "";
		s += super.getName() +  ", deals " + baseDamage + "+" + damageRange + " damage.";		
		return s;
	}
	//getters
	public int getRange() {return range;}
}
