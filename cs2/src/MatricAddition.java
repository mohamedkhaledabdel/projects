public class MatricAddition {
	public static boolean addition(double[][] A1, double[][] A2,
			double[][] result) {
		boolean exists = false;
		for (int i = 0; i < A1.length; i++) {
			for (int j = 0; j < A2.length; j++) {
				if (A1[i][j] + A2[i][j] == result[i][j]) {
					exists = true;
				} else {
					return false;
				}
			}
		}
		return exists;
	}

	public static double[][] additionExists(double[][] A1, double[][] A2,
			double[][] result) {
		if (addition(A1, A2, result)) {
			return result;
		} else if (addition(A1, result, A2)) {
			return A2;
		} else if (addition(A2, result, A1)) {
			return A1;
		}

	}

	public static void main(String[] args) {

	}
}
