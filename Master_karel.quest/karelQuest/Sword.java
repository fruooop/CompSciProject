package karelQuest;

public class Sword extends Item
{
	private int DamageRange;
	private int BaseDamage;
	
	public Sword(String newName, int newBaseDamage, int newRange)
	{
		super(false, newName);
		BaseDamage = newBaseDamage;
		DamageRange = newRange;		
	}
	//returns an int of the damage dealt
	public int use()
	{
		return BaseDamage + (int)(Math.random() * (DamageRange + 1));
	}
	//returns Name, BaseDamage, BaseDamage + Damage Range
	public String toString()
	{
		String s = "";
		s += super.getName() + "\n" + BaseDamage + "\n" + (BaseDamage + DamageRange);		
		return s;
	}
}
