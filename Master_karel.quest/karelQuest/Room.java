package karelQuest;

public class Room {
	
	private int corner1X;
	private int corner1Y;
	private int corner2X;
	private int corner2Y;

	public Room(int newCorner1X, int newCorner1Y, int newCorner2X, int newCorner2Y){
		//Pre: corner2X>=corner1X and corner2Y>=corner2X.
		//Constructs a Room object with two opposite corners. Corner1 is on the top left of the room, corner2 is on the bottom right.
		if(newCorner1X > newCorner2X || newCorner1Y > newCorner2Y) {
			System.out.println("bad time gonna have a");
		}
		corner1X = newCorner1X;
		corner1Y = newCorner1Y;
		corner2X = newCorner2X;
		corner2Y = newCorner2Y;
	}
	
	public boolean isInRoom(int X, int Y) {
		//returns true if the coordinates (X,Y) are contained in the rectangle bounded from corner1 to corner2, false otherwise
		return ((X>=corner1X && corner2X>=X) && (Y>=corner1Y && corner2Y>=Y));
	}
	
	public int getCorner1X() {
		return corner1X;
	}
	
	public int getCorner2X() {
		return corner2X;
	}
	
	public int getCorner1Y() {
		return corner1Y;
	}
	
	public int getCorner2Y() {
		return corner2Y;
	}
}
