package karelQuest;

import java.util.ArrayList;

public class Floor {

	private Tile[][] floor;
	
	//The x and y coordinates of the room generating seed
	private int seedX;
	private int seedY;
	private int maxRooms;
	private Room[] roomList;
	private final int BASE_ROOM_WIDTH = 5;
	private final int BASE_ROOM_HEIGHT = 5;
	private final int ROOM_SIZE_VARIABILITY = 2;
	
	public Floor(int width, int height, int maxNumRooms) {
		//Pre: width>=2(BASE_ROOM_WIDTH) + 1, height>=2(BASE_ROOM_HEIGHT) + 1
		//initializes variables and creates a random seed to generate rooms from.
		floor = new Tile[height][width];
		for(int c = 0; c<width; c++) {
			for(int r = 0; r<height; r++) {
				floor[r][c] = new Tile(false,r,c);
			}
		}
		maxRooms = maxNumRooms;
		roomList = new Room[maxRooms];
		seedX = (int)(height * Math.random());
		seedY = (int)(width * Math.random());
		generateLayout();
	}
	
	public void generateLayout() {
		//generates entire floor
		
		//create room around seed, checking that the room does not generate out of bounds.
		createRoom(makeSeedRoom(seedX,seedY));
		
	}
	
	public boolean createRoom(Room r) {
		//adds r to roomList, and makes walkable tiles over the room's region. Returns true if successful, false otherwise.

		int cur = 0; //adding r to roomList (if there is room) (haha no pun intended)
		while (cur != roomList.length && roomList[cur]!=null) {
			cur++;
		}
		if(cur < roomList.length) {
			roomList[cur] = r;
		}
		else {
			return false; //too many rooms in roomList!
		}
		
		for (int x = r.getCorner1X(); x<=r.getCorner2X(); x++) {
				for (int y = r.getCorner1Y(); y<= r.getCorner2Y(); y++) {
					floor[x][y].walkOn();
				}
		}
		return true;
	}
	
	public String toString() {
		//returns the layout of the floor. nonwalkable tiles are X, walkable tiles are space.
		String s = "";
		for(int r = 0; r<floor.length; r++) {
			for(int c = 0; c<floor[0].length; c++) {
				if(!floor[r][c].isWalkable()) 
				{
					s += "X";
				}
				if(getAt(r,c).hasEntity())
				{
					s+= "@";
				}
				else 
				{
					s+= " ";
				}
			}
			s += r%10 + "\n";
		}
		for(int c = 0; c<floor[0].length; c++) {
			s += c%10;
		}
		return s;
	}
	
	public Tile getAt(int x, int y) {
		//returns the tile at floor[x][y]
		return floor[x][y];
	}
	
	private Room makeSeedRoom(int centerX, int centerY) {
		//Creates a room around the given point, with size determined from the final variables above.
		int roomCenterX = centerX;
		int roomCenterY = centerY;
		//This room's width and height, calculated from the base width, base height, and variability.
		int roomWidth = (BASE_ROOM_WIDTH - ROOM_SIZE_VARIABILITY) + (int)(Math.random()*(2*ROOM_SIZE_VARIABILITY + 1));
		int roomHeight = (BASE_ROOM_HEIGHT - ROOM_SIZE_VARIABILITY) + (int)(Math.random()*(2*ROOM_SIZE_VARIABILITY + 1));
		//How far the room will extend out from the center, accounting for odd-widthed and -heighted rooms.
		int extendNorth;
		if (roomHeight%2 == 1) {
			extendNorth = roomHeight/2;
		}
		else {
			extendNorth = roomHeight/2 - 1;
		}
		int extendSouth = roomHeight/2;
		int extendWest;
		if (roomWidth%2 == 1) {
			extendWest = roomWidth/2;
		}
		else {
			extendWest = roomWidth/2 - 1;
		}
		int extendEast = roomWidth/2;
		//Check to see if the room built will extend off the map. If so, the center is shifted so that this will not happen.
		if(roomCenterX - extendWest < 0) {
			roomCenterX = extendWest; //roomCenterX is put where extendWest will not extend the room past the west bound.
		}
		if(roomCenterX + extendEast >= floor.length) {
			roomCenterX = floor.length - 1 - extendEast;
		}
		if(roomCenterY - extendNorth < 0) {
			roomCenterY = extendNorth;
		}
		if(roomCenterY + extendSouth >= floor[0].length) {
			roomCenterY = floor[0].length - 1 - extendSouth;
		}
		return new Room(roomCenterX - extendWest, roomCenterY - extendNorth, roomCenterX + extendEast, roomCenterY + extendSouth);
	}
	
	public int getSeedX() {
		return seedX;
	}
	
	public int getSeedY() {
		return seedY;
	}
	public int getHeight() {return floor[0].length;}
	public int getWidth() {return floor.length; }

	
}