import java.util.Scanner;


public class minesweeper {
	String [][] table;
	
	public static String[][] mine(String [][] mine) {
		int sum = 0;
		int iplus = 0;
		int jplus = 0;
		int iminus = 0;
		int jminus = 0;
		for (int i = 0; i < mine.length; i++) {
			for (int j = 0; j < mine.length; j++) {
				iplus = i++;
				jplus = j++;
				iminus = i--;
				jminus = j--;
				if(mine[i][j] == "." && iplus < mine.length && jplus < mine.length) {
					if(mine[iplus][j] == "*"){
						sum++;
						mine[i][j] = "" + sum;
					}
					if(mine[iplus][jplus] == "*") {
						sum++;
						mine[i][j] = "" + sum;
					}
					if(mine[i][jplus] == "*") {
						sum++;
						mine[i][j] = "" + sum;
					}
					if(iminus > 0 && jminus > 0) {
						if(mine[i][jminus] == "*") {
							sum++;
							mine[i][j] = "" + sum;					
						}
						if(mine[iminus][jplus] == "*") {
							sum++;
							mine[i][j] = "" + sum;
						}
						if(mine[iminus][j] == "*") {
							sum++;
							mine[i][j] = "" + sum;
						}
						if(mine[iplus][jminus] == "*") {
							sum++;
							mine[i][j] = "" + sum;
						}
						if(mine[iminus][jminus] == "*") {
							sum++;
							mine[i][j] = "" + sum;
						}
					}
					else {
						continue;
					}
				}
			}
		}
		return mine;
		
	}
	
	public static void main(String[] args) {
		/*Scanner sc = new Scanner(System.in);
		int length = sc.nextInt();
		int width = sc.nextInt();*/
		String [][] mine = new String[4][4];
		mine [0][0] = "*";
		mine [0][1] = ".";
		mine [0][2] = ".";
		mine [0][3] = ".";
		mine [1][0] = ".";
		mine [1][1] = ".";
		mine [1][2] = ".";
		mine [1][3] = ".";
		mine [2][0] = ".";
		mine [2][1] = "*";
		mine [2][2] = ".";
		mine [2][3] = ".";
		mine [3][0] = ".";
		mine [3][1] = ".";
		mine [3][2] = ".";
		mine [3][3] = ".";
		String [][] result = mine(mine);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(result[i][j]);
			}
		}
	}
}
