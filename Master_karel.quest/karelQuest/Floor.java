package karelQuest;

import java.util.ArrayList;

public class Floor {

	private Tile[][] floor;
	//Test test test
	//The x and y coordinates of the room generating seed
	private int seedX;
	private int seedY;
	private int maxRooms;
	private Room[] roomList;
	private Player player;
	private ArrayList<Monster> monsterList;
	private final int BASE_ROOM_WIDTH = 5;
	private final int BASE_ROOM_HEIGHT = 5;
	private final int ROOM_SIZE_VARIABILITY = 2;
	private final double ITEM_CHANCE = .5; //0 <= ITEM_CHANCE <= 1
	
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
	
	private void generateLayout() {
		//generates entire floor
		
		//create room around seed, checking that the room does not generate out of bounds.
		createRoom(makeRoom(seedX,seedY));
		player = UpdateQue.getPlayer();
		player.setX(seedX);
		player.setY(seedY);
		getAt(seedX,seedY).setEntity(player); //puts player in room at seed
		while(roomList[roomList.length - 1] == null){
			createRoom(makeRoom((int)(floor.length * Math.random()),(int)(floor[0].length* Math.random())));
		}
		connectRooms();
		spawnMonsters();
		getRandomTileInRoom(roomList[roomList.length-1]).setStairs();//create stairs to the next floor
		generateItems();
	}
	
	private boolean createRoom(Room r) {
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
				else if(getAt(r,c).hasEntity())
				{
					if(getAt(r,c).getEntity().getName().toLowerCase().contains("player")) {
						s+= "@";
					}
					else if(getAt(r,c).getEntity().getName().toLowerCase().contains("skeleton")) {
						s+= "S";
					}
					else if(getAt(r,c).getEntity().getName().toLowerCase().contains("zombie")) {
						s+= "Z";
					}
				}
				else if(getAt(r,c).isStairs()) {
					s+= "/";
				}
				else if(getAt(r,c).hasItem()) {
					if(getAt(r,c).getItem().getName().toLowerCase().contains("sword")) {
						s+= "s";
					}
					else if(getAt(r,c).getItem().getName().toLowerCase().contains("bow")) {
						s+= ")";
					}
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
	//returns the room at x, y
	//pre: x, y are inside the floor and there are rooms on the floor
	//post returns the room at x,y if x,y are in a room or null otherwise
	public Room getRoomAt(int x, int y)
	{
		if(roomList.length != 0 && x < floor.length && y < floor[0].length)//there are rooms, and the x and y coords are inside the floor
		{
			boolean found = false;
			int i = 0;
			while(!found && i < roomList.length)
			{
				found = roomList[i].isInRoom(x,y);
				if(!found)
					i++;
			}
			if(found)
			{
				return roomList[i];
			}
			return null;
		}
		return null;
	}
	
	private Room makeRoom(int centerX, int centerY) {
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

	private Tile getRandomEdgeCoord(Room r){
		//Pre: this room does not take up the entire floor (why would you do that)
		//returns a Tile that is on the edge of the room but not the edge of the floor.
		ArrayList<int[]> edgeCoordsList = new ArrayList<int[]>();
		//check top of room
		if (r.getCorner1Y() != 0) {
			for (int x = r.getCorner1X(); x <= r.getCorner2X(); x++) {
				int[] temp = {x, r.getCorner1Y()};
				edgeCoordsList.add(temp);
			}
		}
		//check bottom of room
		if (r.getCorner2Y() != floor[0].length - 1) {
			for (int x = r.getCorner1X(); x <= r.getCorner2X(); x++) {
				int[] temp = {x, r.getCorner2Y()};
				edgeCoordsList.add(temp);
			}
		}
		//check left side of room
		if (r.getCorner1X() != 0) {
			for (int y = r.getCorner1Y() + 1; y < r.getCorner2Y(); y++) {
				int[] temp = {r.getCorner1X(), y};
				edgeCoordsList.add(temp);
			}
		}
		//check right side of room
		if (r.getCorner2X() != floor.length - 1) {
			for (int y = r.getCorner1Y() + 1; y < r.getCorner2Y(); y++) {
				int[] temp = {r.getCorner2X(), y};
				edgeCoordsList.add(temp);
			}
		}
		int[] random = edgeCoordsList.get((int)(Math.random()*edgeCoordsList.size()));
		return getAt(random[0],random[1]);
	}
	
	private void connectRooms() {
		//Pre: roomList has at least 2 nonnull elements.
		//creates corridors between all rooms on the floor.
		//corridors will traverse from roomList[0] to roomList[1] to ... to roomList[roomList.length - 1].
		
		for (int roomNum = 1; roomNum < roomList.length; roomNum++) {
			Tile t1 = getRandomEdgeCoord(roomList[roomNum - 1]);
			Tile t2 = getRandomEdgeCoord(roomList[roomNum]);
			boolean acrossFirst = (Math.random() < .5); //whether or not the path will go across first to get from t1 go t2.
			if (t1.getX() >= t2.getX() && t1.getY() >= t2.getY()) {
				if(acrossFirst) {
					for(int x = t1.getX();x>=t2.getX();x--) {
						getAt(x,t1.getY()).walkOn();
					}
					for(int y = t1.getY(); y>=t2.getY(); y--) {
						getAt(t2.getX(),y).walkOn();
					}
				}
				else {
					for(int y = t1.getY(); y>=t2.getY(); y--) {
						getAt(t1.getX(),y).walkOn();
					}
					for(int x = t1.getX();x>=t2.getX();x--) {
						getAt(x,t2.getY()).walkOn();
					}
				}
			}
			else if (t1.getX() <= t2.getX() && t1.getY() >= t2.getY()) {
				if(acrossFirst) {
					for(int x = t1.getX();x<=t2.getX();x++) {
						getAt(x,t1.getY()).walkOn();
					}
					for(int y = t1.getY(); y>=t2.getY(); y--) {
						getAt(t2.getX(),y).walkOn();
					}
				}
				else {
					for(int y = t1.getY(); y>=t2.getY(); y--) {
						getAt(t1.getX(),y).walkOn();
					}
					for(int x = t1.getX();x<=t2.getX();x++) {
						getAt(x,t2.getY()).walkOn();
					}
				}
			}
			else if (t1.getX() >= t2.getX() && t1.getY() <= t2.getY()) {
				if(acrossFirst) {
					for(int x = t1.getX();x>=t2.getX();x--) {
						getAt(x,t1.getY()).walkOn();
					}
					for(int y = t1.getY(); y<=t2.getY(); y++) {
						getAt(t2.getX(),y).walkOn();
					}
				}
				else {
					for(int y = t1.getY(); y<=t2.getY(); y++) {
						getAt(t1.getX(),y).walkOn();
					}
					for(int x = t1.getX();x>=t2.getX();x--) {
						getAt(x,t2.getY()).walkOn();
					}
				}
			}
			else if (t1.getX() <= t2.getX() && t1.getY() <= t2.getY()) {
				if(acrossFirst) {
					for(int x = t1.getX();x<=t2.getX();x++) {
						getAt(x,t1.getY()).walkOn();
					}
					for(int y = t1.getY(); y<=t2.getY(); y++) {
						getAt(t2.getX(),y).walkOn();
					}
				}
				else {
					for(int y = t1.getY(); y<=t2.getY(); y++) {
						getAt(t1.getX(),y).walkOn();
					}
					for(int x = t1.getX();x<=t2.getX();x++) {
						getAt(x,t2.getY()).walkOn();
					}
				}
			}
		}
	}
	
	public int[] getDim(){
			//return dimensions of floor
			//index 0 has length, index 1 has width
			int[] temp = {floor.length,floor[0].length};
			return temp;
	}
	
	private Tile getRandomTileInRoom(Room r) {
		//returns a random Tile in r
		int x = (int)(Math.random()*(r.getCorner2X()-r.getCorner1X()) + r.getCorner1X());
		int y = (int)(Math.random()*(r.getCorner2Y()-r.getCorner1Y()) + r.getCorner1Y());
		return getAt(x,y);
	}
	
	private void spawnMonsters() {
		//Pre: there is more than one room
		//Spawns one monster per room on a random tile in that room, except not in the starting room
		//and adds each to the newly initialized monsterList
		monsterList = new ArrayList<Monster>();
		for (int roomNum = 1; roomNum < roomList.length; roomNum++) {
			int monsterID = (int) (Math.random() * 2);
			Tile t = getRandomTileInRoom(roomList[roomNum]);
			while (t.hasEntity()) {
				t = getRandomTileInRoom(roomList[roomNum]);
			}
			switch(monsterID) {
				case 0: Monster m0 = new Skeleton(20,t.getX(),t.getY(),"Skeleton " + Utilities.randomName());
				t.setEntity(m0);
				monsterList.add(m0);
				break;
				case 1: Monster m1 = new Zombie(20,t.getX(),t.getY(),"Zombie " + Utilities.randomName());
				t.setEntity(m1);
				monsterList.add(m1);
				break;												
			}
		}
	}
		
	
	public Player getPlayer() {
		//returns player
		return player;
	}
	
	public ArrayList<Monster> getMonList() {
		//returns monsterList
		return monsterList;
	}
	
	private void generateItems() {
		//generates items randomly in rooms.
		for (Room r: roomList) {
			if(Math.random()<ITEM_CHANCE) {
				Item i;
				int itemID = (int)(Math.random()*2);
				switch (itemID) {
					case 0: i = new Weapon("Sword " + Utilities.randomName(), 5, 3, 1); //sword with base damage 5,
																	//damage range 3, and attack range 1.
																	//Probably should change based on which room number you are in.
					getRandomTileInRoom(r).setItem(i);
					break;
					case 1: i = new Weapon("Bow " + Utilities.randomName(), 5, 3, 3); //bow with base damage 3,
					//damage range 2, and attack range 3. Yes it outranges the skeleton.
					//Probably should change based on which room number you are in.
					getRandomTileInRoom(r).setItem(i);
					break;
				}
			}
		}
	}
	

	public void removeDeadMonsters() {
		//removes monsters whose health is zero from monsterList and the board.
		for (Monster m: monsterList) {
			if (m.getHealth() <= 0) {
				getAt(m.getX(),m.getY()).setEntity(null);
				monsterList.remove(m);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
