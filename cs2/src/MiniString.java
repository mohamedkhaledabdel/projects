public class MiniString {
	char[] characters;

	public MiniString() {
		characters = new char[0];
	}

	public MiniString(char[] c) {
		characters = new char[c.length];
		for (int i = 0; i < c.length; i++) {
			characters[i] = c[i];
		}
	}

	public int length() {
		return this.characters.length;
	}
	
	public char charAt(int i) {
		return this.characters[i];
	}
	
	public boolean equals(MiniString m) {
		boolean isEqual = true;
		if(m.characters == null || m.characters.length != this.characters.length) {
			isEqual = false;
		}
		else {
			for (int i = 0; i < this.characters.length; i++) {
				if(m.characters[i] == this.characters[i]) {
					isEqual = true;
				}
				else {
					isEqual = false;
					break;
				}
			}
		}
		return isEqual;
	}
	
	public static void main(String[] args) {
		char [] car = {'c','a','r'};
		MiniString s = new MiniString(car);
		MiniString s1 = new MiniString(new char['c']);
		System.out.println(s.charAt(1));
	}
}
