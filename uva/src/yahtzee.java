import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;


public class yahtzee {
	static Stack ones = new Stack();
	static Stack twos =  new Stack();
	static Stack threes =  new Stack();
	static Stack fours =  new Stack();
	static Stack fives =  new Stack();
	static Stack sixes =  new Stack();
	static int chance =  0;
	
	
	public static int ones(String dice) {
		for (int i = 0; i < dice.length(); i++) {
			if(dice.charAt(i) == '1') {
				ones.push(1);
			}
		}
		return 1 * ones.size();
	}
	
	public static int twos (String dice) {
		for (int i = 0; i < dice.length(); i++) {
			if(dice.charAt(i) == '2') {
				twos.push(2);
			}
		}
		return 2 * twos.size();
	}
	
	public static int threes (String dice) {
		for (int i = 0; i < dice.length(); i++) {
			if(dice.charAt(i) == '3') {
				threes.push(3);
			}
		}
		return 3 * threes.size();
	}
	
	public static int fours (String dice) {
		for (int i = 0; i < dice.length(); i++) {
			if(dice.charAt(i) == '4') {
				fours.push(4);
			}
		}
		return fours.size() * 4;
	}
	
	public static int fives (String dice) {
		for (int i = 0; i < dice.length(); i++) {
			if(dice.charAt(i) == '5') {
				fives.push(5);
			}
		}
		return fives.size() * 5;
	}
	
	public static int sixes (String dice) {
		for (int i = 0; i < dice.length(); i++) {
			if(dice.charAt(i) == '6') {
				sixes.push(6);
			}
		}
		return sixes.size() * 6;
	}
	
	public static int chance (String dice) {
		for (int i = 0; i < dice.split(" ").length; i++) {
			chance = chance + Integer.parseInt(dice.split(" ")[i]);
		}
		return chance;
	}

	public static int twoOfAKind(String dice) {
		boolean two = false;
		int sumOFTwo = 0;
		for (int i = 0; i < dice.length(); i++) {
			for (int j = 0; j < dice.length(); j++) {
				if(dice.charAt(i) == dice.charAt(j)) {
					sumOFTwo++;
				}
				if(sumOFTwo == 2) {
					two = true;
					break;
				}
			}
		}
		if(two) {
			sumOFTwo = 2;
		}
		else {
			sumOFTwo = 0;
		}
		return sumOFTwo;
	}
	
	public static int threeOfAKind(String dice) {
		boolean three = false;
		int sumOFThree = 0;
		for (int i = 0; i < dice.length(); i++) {
			for (int j = 0; j < dice.length(); j++) {
				if(dice.charAt(i) == dice.charAt(j) && dice.charAt(i) != ' ' && dice.charAt(j) != ' ') {
					sumOFThree++;
				}
				if(sumOFThree == 3) {
					three = true;
					break;
				}
			}
		}
		if(three) {
			sumOFThree = 3;
		}
		else {
			sumOFThree = 0;
		}
		return sumOFThree;
	}
	
	public static int fourOfAKind(String dice) {
		boolean four = false;
		int sumOFFour = 0;
		for (int i = 0; i < dice.length(); i++) {
			for (int j = 0; j < dice.length(); j++) {
				if(dice.charAt(i) == dice.charAt(j) && dice.charAt(i) != ' ' && dice.charAt(j) != ' ') {
					sumOFFour++;
				}
				if(sumOFFour == 4) {
					four = true;
					break;
				}
			}
		}
		if(four) {
			sumOFFour= 4;
		}
		else {
			sumOFFour = 0;
		}
		return sumOFFour;
	}
	
	public static int fiveOfAKind(String dice) {
		boolean five = false;
		int sumOFFive = 0;
		for (int i = 0; i < dice.length(); i++) {
			for (int j = 0; j < dice.length(); j++) {
				if(dice.charAt(i) == dice.charAt(j)) {
					sumOFFive++;
				}
				if(sumOFFive == 5) {
					five = true;
					break;
				}
			}
		}
		if(five) {
			sumOFFive= 5;
		}
		else {
			sumOFFive = 0;
		}
		return sumOFFive;
	}
	
	public static int shortStraight (String dice) {
		boolean shortStraight = false;
		int bonus = 0;
		for (int i = 0; i < 2; i++) {
				if(dice.charAt(i) == dice.charAt(i+1) - 1) {
					shortStraight = true;
				}
				else if (dice.substring(i + 1).length() > 2){
					shortStraight = false;
					shortStraight(dice.substring(i + 1));
				}
				else {
					shortStraight = false;
					break;
				}
			
		}
		if (shortStraight) {
			bonus = 25; 
		}
		return bonus;
	}
	
	public static int longStraight (String dice) {
		boolean longStraight = false;
		int bonus = 0;
		for (int i = 0; i < 3; i++) {
			if(dice.charAt(i) == dice.charAt(i+1) - 1) {
				longStraight = true;
			}
			else {
				longStraight = false;
				break;
			}
		}
		if (longStraight) {
			bonus = 35; 
		}
		return bonus;
	}
	
	public static int fullHouse (String dice) {
		boolean three = false;
		boolean two = false;
		int bonus = 0;
		if((twoOfAKind(dice) == 2) && (threeOfAKind(dice) == 3)) {
			bonus = 40;
		}
		return bonus;
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("/home/mohamed/Desktop/yahtzee");
		BufferedReader br = new BufferedReader(new FileReader(file));	
		String line = br.readLine();
		Stack sum = new Stack();
		/*System.out.println(ones("1 2 3 4 5"));
		System.out.println(twos("1 2 3 4 5"));
		System.out.println(threes("1 2 3 4 5"));
		System.out.println(fours("1 2 3 4 5"));
		System.out.println(fives("1 2 3 4 5"));
		System.out.println(sixes("1 2 3 4 5"));
		System.out.println(chance("1 2 3 4 5"));*/
		//System.out.println(threeOfAKind("1 2 3 4 5"));
		/*System.out.println(fourOfAKind("1 2 3 4 5"));
		System.out.println(fiveOfAKind("1 2 3 4 5"));
		System.out.println(shortStraight("1 2 3 4 5"));
		System.out.println(longStraight("1 2 3 4 5"));
		System.out.println(fullHouse("1 2 3 4 5"));*/
		System.out.println(fourOfAKind("1112"));

		
	}
}	
