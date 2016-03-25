
public class Fib {

	public static int fib(int n) {
		int result = 0;
		if(n == 0)
			result = 0;
		else if(n == 1)
			result = 1;
		else if(n > 1) {
			result = fib(n - 1) + fib(n - 2);
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(fib(7));
	}
	
}
