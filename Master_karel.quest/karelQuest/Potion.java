package karelQuest;

public class Potion extends Item
{
	int baseHeals;
	int healRange;
	
	public Potion(int newBase, int newRange)
	{
		super(true, Utilities.randomPosAdj()+ " Potion");
		baseHeals = newBase;
		healRange = newRange;
	}
	public int use(Player p)
	{
		int randomHeals = (int) (baseHeals + (Math.random() * (healRange + 1)));
		return p.takeDamage(-randomHeals);
	}
	
	public String toString() {
		String s = "";
		s+= super.getName() + ", heals " + (baseHeals) + "+" + (healRange) + " HP.";
		return s;
	}
}
