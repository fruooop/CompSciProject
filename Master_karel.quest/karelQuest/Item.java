package karelQuest;

public class Item 
{
	private boolean consumable;
	private String name;
	
	public Item(boolean newConsumable, String newName)
	{
		name = newName;
		consumable = newConsumable;
	}
	//use will always return 0 even if int is to be used to subtract
	public int use() { return 0;}
	//getters
	public String getName() { return name;}
	public boolean getConsumable() { return consumable; }
	
}
