
public class Palindrome {
	
	public static String reverse(String org) {
		String reverse = "";
		for (int i = org.length() - 1; i >= 0; i--) {
			reverse = reverse + org.charAt(i);
		}
		return reverse;
	}
	
	public static Boolean isPalind(String word) {
		return word.equals(reverse(word))?true:false;
	}
	
	public static void main(String[] args) {
		System.out.println(isPalind("jhjhjhj"));
	}
}
