package karelQuest;

public class Weapon extends Item
{
	private int DamageRange;
	private int BaseDamage;
	public int range; //how far away a monster can be at max to use item
	
	public Weapon(String newName, int newBaseDamage, int newDamageRange, int newRange)
	{
		super(false, newName);
		BaseDamage = newBaseDamage;
		DamageRange = newDamageRange;		
		range = newRange;
	}
	//deals damage within basedamage, basedamage + Damage Range to the monster
	public void use(Monster m)
	{
		m.takeDamage(BaseDamage + (int)(Math.random() * (DamageRange + 1)));
	}
	//returns Name, BaseDamage, BaseDamage + Damage Range
	public String toString()
	{
		String s = "";
		s += super.getName() + "\n" + BaseDamage + "\n" + (BaseDamage + DamageRange);		
		return s;
	}
}
