package karelQuest;

public class Potion extends Item
{
	int baseHeals;
	int healRange;
	
	public Potion(int newBase, int newRange)
	{
		super(true, "Potion");
		baseHeals = newBase;
		healRange = newRange;
	}
	public void use(Player p)
	{
		int randomHeals = (int) (baseHeals + (Math.random() * (healRange + 1)));
		p.takeDamage(-randomHeals);
	}
}
