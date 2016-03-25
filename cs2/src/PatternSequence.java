
public class PatternSequence {
	
	public static boolean isPattern(int[][] seq) {
		boolean pattern = false;
		for (int i = 0; i < seq.length; i++) {
			for (int j = 0; j < seq[i].length ; j++) {
				if(seq[i][0] == seq[i][j] && seq[i].length == seq[i][0]) {
					pattern = true;
				}
				else{
					return false;
				}
			}
		}
		return pattern;
	}
	
	public static void main(String[] args) {
		int[][] array = {{1},{2,2},{3,3,3},{4,4,4}};
		System.out.println(isPattern(array));
	}
		
}
