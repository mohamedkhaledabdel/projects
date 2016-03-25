
public class LongestPalindrome {

	public static Boolean plaindrom(String c){
		return c.equals(reverse(c))?true:false;
	}
	
	public static String reverse(String org) {
		String reverse = "";
		for (int i = org.length() - 1; i >= 0; i--) {
			reverse = reverse + org.charAt(i);
		}
		return reverse;
	}
	
	public static String longestPalindrom(String word) {
		String longestPalindrome = "";
		for (int i = 0; i < word.length(); i++) {
			for (int j = i + 2; j < word.length() + 1; j++) {
				if (plaindrom(word.substring(i,j))) {
					if(word.substring(i, j).length() > longestPalindrome.length()){
						longestPalindrome = word.substring(i, j);
					}
				}
			}
		}
		return longestPalindrome;
	}
	
	public static void main(String[] args) {
		int n = 3;
		for (int i = 0; i < n*n; i++){
			System.out.println(i / 3 + " " + i % 3);
		}
		String path = "/home/foo/bar";
		String file = "";
		String direc = "";
		for (int i = path.length() - 1; i > 0; i--) {
			if(path.charAt(i) != '/') {
				file = file + path.charAt(i);
			}
			else{
				direc = direc + path.substring(0,i + 1);
				break;
			}
		}
		System.out.println(reverse(file));
		System.out.println(direc);
	}
	
	
}
