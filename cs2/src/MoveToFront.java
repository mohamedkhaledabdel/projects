
public class MoveToFront {
	
	public static String moveFront(String s, char c) {
		String result = "";
		if(s.length() == 0) {
			result = "";
		}
		if(s.contains(c+"")){
			result = result + c + moveFront(deleteFisrtOccurence(s, c), c);
		}
		else {
			result = result + s;
		}
		return result;
	}
	
	public static String moveFrontMod(String s,char c) {
		String result = "";
		if(s.length() == 0) {
			result = "";
		}
		else if(s.charAt(s.length() - 1) == c) {
			result = c + moveFrontMod(s.substring(0,s.length() - 1), c);
		}
		else{
			result = moveFrontMod(s.substring(0,s.length() - 1), c)+ s.charAt(s.length() - 1);
		}
		return result;
	}
	
	public static String deleteFisrtOccurence(String s, char c) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == c) {
				result = result + s.substring(i+1);
				break;
			}
			else {
				result = result + s.charAt(i);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(moveFrontMod("nada",'a'));
	}
}	
