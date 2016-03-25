import java.awt.AlphaComposite;
import java.util.ArrayList;


public class ccipher {
	
	static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	
	
	public static String decode(String cipherText, int shift) {
		ArrayList<Character> alphabetList = new ArrayList<Character>();
		for (int i = 0; i < alphabet.length; i++) {
			alphabetList.add(alphabet[i]);
		}
		int index = 0;
		String decoded = "";
		for(int i = 0; i < cipherText.length(); i++){
			index = alphabetList.indexOf(cipherText.charAt(i)) - shift;
			if(index >= 0) {
				decoded = decoded + alphabetList.get(index);
			}
			else{
				decoded = decoded + alphabetList.get(alphabetList.size() + index);
			}
		}
		return decoded;
	}
	
	public static void main(String[] args) {
		System.out.println(decode("ABCDEFGHIJKLMNOPQRSTUVWXYZ",10));
	}

	
}
