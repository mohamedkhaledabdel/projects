public class Tile {
	int type = 0;
	boolean moveable = false;

	public Tile(int type, boolean moveable) {
		this.type = type;
		this.moveable = moveable;
	}

	public Tile() {
		this.type = 0;
		this.moveable = false;
	}

	/*
	 * This method is used to print the grid
	 */
	public static void printGrid(Tile[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].type > 99 && board[i][j].moveable)
					System.out.print(board[i][j].type + "  m |");
				else if ((board[i][j].type > 99 && !board[i][j].moveable)) {
					System.out.print(board[i][j].type + "  n |");
				} else if (board[i][j].moveable) {
					System.out.print(board[i][j].type + "  m  |");

				} else {
					System.out.print(board[i][j].type + "  n  |");
				}
			}
			System.out.println();
		}
	}

	public static boolean equality(Tile[][] a, Tile[][] b) {
		System.out.println(a[0][0].type + "  ==  " + b[0][0].type);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if ((a[i][j].type != b[i][j].type)
						|| ((a[i][j].moveable != b[i][j].moveable))) {
					return false;

				}
			}
		}
		return true;
	}

}
