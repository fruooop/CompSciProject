package karelQuest;

public class Skeleton extends Monster
{
	public Skeleton(int sendHealth, int newX, int newY, String newString, int newBase, int newRange)
	{
		super(sendHealth, newX, newY, newString, newBase, newRange);
	}
	//skeltons have a bow, so they can attack from two blocks away
	//since they have a ranged attack they can only move once test
	public void act(Floor f, Entity p)
	{
		int pX = p.getX();
		int pY = p.getY();
		int mX = getX();
		int mY = getY();
		if(Math.abs(mX - pX) < 3 || Math.abs(mY - pY) < 3)
		{
			attack(p);
		}
		else
		{
			move(f, p);
			if(Math.abs(mX - pX) < 3 || Math.abs(mY - pY) < 3)
			{
				attack(p);
			}
		}
	}
}
