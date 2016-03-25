
public class CharacterCount {

	public static int count(String word, char character) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == character) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(count("mohamed", 'm'));
	}
}
