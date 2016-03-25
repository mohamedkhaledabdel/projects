
public class Quipu {

	public static int readKnots(String knots) {
		int zeros = 0;
		int xs = 0;
		String result = "";
		int i = 0;
		while(i < knots.length()) {
			if(knots.charAt(i) != 'X') {
				if(xs >= 0) {
					result = result + xs;
				}
				zeros++;

				xs = 0;
				i++;
			}
			else{
				if(zeros - 1 > 0 ){
					while(zeros > 3) {
						result = result + 0;
						zeros--;
					}
				}
				
				zeros = 0;
				xs++;
				i++;
			}
		}
		return Integer.parseInt(result);
	}
	public static void main(String[] args) {
		System.out.println(readKnots("-X-------"));
	}
}
