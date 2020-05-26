package karelQuest;

public class Zombie extends Monster
{
	public Zombie(int sendHealth, int newX, int newY, String newString, int newBase, int newRange)
	{
		super(sendHealth, newX, newY, newString, newBase, newRange);
	}
	//zombie has 2 move as it can only attack if it is adjacent to the player
	//zombie will act
	public void act(Floor f, Entity p)
	{
		int pX = p.getX();
		int pY = p.getY();
		int mX = getX();
		int mY = getY();
		int numMoves = 2;
		//if the Zombie is already close enough to attack, it will only attack
		if(Math.abs(mX - pX) == 1 || Math.abs(mY - pY) == 1)
		{
			attack(p);
		}
		else
		{
			//if the zombie isnt close enough, it will move towards player until it is close enough to attack
			while(numMoves > 0 && (Math.abs(mX - pX) > 1 && Math.abs(mY - pY) > 1))
			{
				move(f,p);
				numMoves--;
			}
			if(Math.abs(mX - pX) > 1 || Math.abs(mY - pY) > 1)
			{
				attack(p);
			}
		}
	}
}
