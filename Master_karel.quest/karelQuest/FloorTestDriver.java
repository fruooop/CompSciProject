package karelQuest;

public class FloorTestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Floor f = new Floor(50,20,3);
			System.out.println(f.getSeedX());
			System.out.println(f.getSeedY());
			System.out.println(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
