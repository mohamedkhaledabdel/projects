/* This class is a representation of the 5 tuple node in search tree
 * 
 */
public class extended_node extends Node {

	public extended_node(extended_node parent, Object state, String operator,
			int depth, int path_cost) {
		super(parent, state, operator, depth, path_cost);
	}

	public extended_node() {
		super();
	}

	public static void main(String[] args) {
		Tile[][] t;
		t = roll_the_ball.GenGrid();
		Tile.printGrid(t);
		extended_node p = null;
		extended_node s = new extended_node((extended_node) p, t, "move", 1, 1);
		System.out.println();
		Tile.printGrid((Tile[][]) s.state);
	}
}
