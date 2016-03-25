
public class Blast {

	public static void CountDown(int n) {
		if(n == 0) {
			System.out.println("BlastOff");
		}
		else {
			System.out.println(n);
			CountDown(n - 1);
		}
	}
	public static void main(String[] args) {
		CountDown(6);
	}
	
}
