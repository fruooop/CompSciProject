package karelQuest;

public class Item 
{
	private boolean consumable;
	private String Name;
	
	public Item(boolean newConsumable, String newName)
	{
		Name = newName;
		consumable = newConsumable;
	}
	//use will always return 0 even if int is to be used to subtract
	public int use() { return 0;}
	//getters
	public String getName() { return Name;}
	public boolean getConsumable() { return consumable; }
	
}
