package srm665Div2;

import java.util.ArrayList;

public class LuckySum {

	public static long construct(String note) {
		ArrayList<Integer> permutations = getAllPermutations(note);
		long res = -1;
		long diffFours = 0;
		long diffSevens = 0;
		System.out.println(permutations);
		for (int i = 0; i < permutations.size(); i++) {			
			diffFours = (permutations.get(i)) - createFours(note);
			System.out.println(diffFours);
			diffSevens = (permutations.get(i)) - createSevens(note);
			System.out.println(diffSevens);
			if(((diffFours + "").contains("4") && diffFours % 11 == 0) 
					|| ((diffSevens + "").contains("7") && diffSevens % 11 == 0)
					|| ((diffSevens + "").contains("7") && (diffSevens + "").length() == 1)
					|| ((diffFours + "").contains("4") && (diffFours+ "").length() == 1)) {
				res = (permutations.get(i));
				break;
			}
		}
		return res;
	}
	
	public static long createFours(String note) {
		String res = "";
		for (int i = 0; i < note.length() ; i++) {
			res = res + 4 +"";
		}
		return Long.parseLong(res);
	}
	
	public static long createSevens(String note) {
		String res = "";
		for (int i = 0; i < note.length() ; i++) {
			res = res + 7 +"";
		}
		return Long.parseLong(res);
	}
	
	public static int indexOfLastQuestionMark(String note) {
		int res = 0;
		for (int i = note.length() - 1; i > 0; i--) {
			if(note.charAt(i) == '?') {
				res = i;
				break;
			}
		}
		return res;
	}
	
	public static ArrayList<Integer> changeOneMark(String note) {;
		ArrayList<Integer> permutations = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			permutations.add(Integer.parseInt(note.replaceFirst("\\?", i+"")));
		}
		return permutations;
	}
	
	public static ArrayList<ArrayList<Integer>> changeAllMarks(ArrayList<Integer> perm) {
		ArrayList<ArrayList<Integer>> AllPermutations = new ArrayList<ArrayList<Integer>>();
		int i = 0;
		if(perm.get(i).toString().contains("?")) {
			AllPermutations.add(changeOneMark(perm.get(i).toString()));
		}
	}
	public static int loopLimit(String note) {
		String nines = "";
		int numOfQuestionMarks = 0;
		for (int i = 0; i < note.length(); i++) {
			if(note.charAt(i) == '?') {
				numOfQuestionMarks++;
			}
		}
		for (int i = 0; i < numOfQuestionMarks; i++) {
			nines = nines + "8";
		}
		return Integer.parseInt(nines);
	}
	
	/*public static ArrayList<Integer> getAllPermutations(String note) {
		int index = indexOfLastQuestionMark(note);
		ArrayList<Integer> permutations = new ArrayList<Integer>();
		int startNum = Integer.parseInt(note.substring(0, index + 1).replaceAll("\\?", 1 + ""));
		permutations.add(startNum);
		//System.out.println(loopLimit(note));
		for (int i = 0; i < loopLimit(note); i++) {
			startNum++;
			permutations.add(startNum);
		}
		return permutations;
	}*/
	
	public static void main(String[] args) {
		System.out.println(construct("??????????????"));
	}
}
