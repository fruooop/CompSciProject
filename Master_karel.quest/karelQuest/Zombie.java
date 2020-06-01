package karelQuest;

public class Zombie extends Monster
{
	public Zombie(int sendHealth, int newX, int newY, String newString)
	{
		//As of now, zombies have a base damage of 0, a damage range of 0, an attack range of 1, and a vision range of 6.
		super(sendHealth, newX, newY, newString, 0, 0, 1, 6);
		
	}
}
