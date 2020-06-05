package karelQuest;

public class Skeleton extends Monster
{
	public Skeleton(int sendHealth, int newX, int newY, String newString)
	{
		//As of now, zombies have a base damage of 0, a damage range of 0, an attack range of 2, and a vision range of 4.
		super(sendHealth, newX, newY, newString, 2 + UpdateQue.getFloorNum(), 1 + UpdateQue.getFloorNum()/2, 2, 4);
	}
}
