
//This class is basically just a thesaurus
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
		//returns a random adjective that is a synonym for happy. https://www.thesaurus.com/browse/good?s=t.
		int random = (int)(Math.random()*12);
		switch(random) {
			case 0: return "Acceptable";
			case 1: return "Excellent";
			case 2: return "Exceptional";
			case 3: return "Favorable";
			case 4: return "Great";
			case 5: return "Marvelous";
			case 6: return "Positive";
			case 7: return "Satisfactory";
			case 8: return "Satisfying";
			case 9: return "Superb";
			case 10: return "Valuable";
			case 11: return "Wonderful";
			default: return "You Messed Up";
		}
	}
	
	public static String randomNegReaction() {
		//returns a random negative reaction. Sourced from my friends down in accounting
		int random = (int)(Math.random()*8);
		switch(random) {
			case 0: return "Aww man!";
			case 1: return "Darn it!";
			case 2: return "Noooo!";
			case 3: return "Bummer!";
			case 4: return "Curses.";
			case 5: return "Rats!";
			case 6: return "Drat!";
			case 7: return "Hmmmmph...";
			default: return "You Messed Up";
		}
	}
	
	public static String randomPosReaction() {
		//returns a random positive reaction. Sourced from my friends from the land down underground
		int random = (int)(Math.random()*8);
		switch(random) {
			case 0: return "Hurrah!";
			case 1: return "pogChamp";
			case 2: return "Yay!";
			case 3: return "Nice.";
			case 4: return "Awesome!";
			case 5: return "Great!";
			case 6: return "Excellent!";
			case 7: return "Above Average!";				
			default: return "You Messed Up";
		}
	}
	
	public static String randomTastyReaction() {
		//returns a random potion reaction. Contributed by Gordon Ramsey and Sammy Stringer ig
		int random = (int)(Math.random()*8);
		switch(random) {
			case 0: return "Tasty.";
			case 1: return "Delicious!";
			case 2: return "Mmmmmm...";
			case 3: return "Got potion?";
			case 4: return "Sweet!";
			case 5: return "Muy sabroso!";
			case 6: return "Incredible!";
			case 7: return "Flavorfully brilliant~";
			default: return "You Messed Up";
		}
	}
	
	public static String randomHurtReaction() {
		//returns a random hurt reaction. Contribured by a legion of fish
		int random = (int)(Math.random()*8);
		switch(random) {
			case 0: return "Ouch.";
			case 1: return "Ow!!";
			case 2: return "That's gotta hurt.";
			case 3: return "Yowch!";
			case 4: return "Mamma Mia!";
			case 5: return "Owwwwww...";
			case 6: return "That can't feel good.";
			case 7: return "OUCH.";
			default: return "You Messed Up";
		}
	}
}
