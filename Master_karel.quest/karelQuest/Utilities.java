package karelQuest;

public class Utilities {
	public static String randomName() {
		//returns a random name. From https://www.weddingvendors.com/baby-names/popular/1975/.
		int random = (int)(Math.random()*40);
		switch(random) {
			case 0: return "Michael";
			case 1: return "Jason";
			case 2: return "Christopher";
			case 3: return "James";
			case 4: return "David";
			case 5: return "Robert";
			case 6: return "John";
			case 7: return "Brian";
			case 8: return "Matthew";
			case 9: return "William";
			case 10: return "Daniel";
			case 11: return "Joseph";
			case 12: return "Eric";
			case 13: return "Jeffrey";
			case 14: return "Kevin";
			case 15: return "Richard";
			case 16: return "Scott";
			case 17: return "Steven";
			case 18: return "Mark";
			case 19: return "Thomas";
			case 20: return "Jennifer";
			case 21: return "Amy";
			case 22: return "Heather";
			case 23: return "Melissa";
			case 24: return "Angela";
			case 25: return "Michelle";
			case 26: return "Kimberly";
			case 27: return "Lisa";
			case 28: return "Stephanie";
			case 29: return "Nicole";
			case 30: return "Rebecca";
			case 31: return "Christina";
			case 32: return "Jessica";
			case 33: return "Amanda";
			case 34: return "Elizabeth";
			case 35: return "Kelly";
			case 36: return "Sarah";
			case 37: return "Julie";
			case 38: return "Mary";
			case 39: return "Laura";
			default: return "You Messed Up";
		}
	}
	
	public static String randomPosAdj() {
		//returns a random positive adjective. https://www.thesaurus.com/browse/good?s=t.
		int random = (int)(Math.random()*40);
		switch(random) {
			case 0: return "acceptable";
			case 1: return "Jason";
			case 2: return "Christopher";
			case 3: return "James";
			case 4: return "David";
			case 5: return "Robert";
			case 6: return "John";
			case 7: return "Brian";
			case 8: return "Matthew";
			case 9: return "William";
			case 10: return "Daniel";
			case 11: return "Joseph";
			case 12: return "Eric";
			case 13: return "Jeffrey";
			case 14: return "Kevin";
			case 15: return "Richard";
			case 16: return "Scott";
			case 17: return "Steven";
			case 18: return "Mark";
			case 19: return "Thomas";
			case 20: return "Jennifer";
			case 21: return "Amy";
			case 22: return "Heather";
			case 23: return "Melissa";
			case 24: return "Angela";
			case 25: return "Michelle";
			case 26: return "Kimberly";
			case 27: return "Lisa";
			case 28: return "Stephanie";
			case 29: return "Nicole";
			case 30: return "Rebecca";
			case 31: return "Christina";
			case 32: return "Jessica";
			case 33: return "Amanda";
			case 34: return "Elizabeth";
			case 35: return "Kelly";
			case 36: return "Sarah";
			case 37: return "Julie";
			case 38: return "Mary";
			case 39: return "Laura";
			default: return "You Messed Up";
		}
	}
}
