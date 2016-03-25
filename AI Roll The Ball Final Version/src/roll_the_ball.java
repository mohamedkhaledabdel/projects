import java.util.ArrayList;
import java.util.Queue;

/*This class is a representation of 5 tuples of a search problem
 * 
 */

public class roll_the_ball extends Problem {

	extended_node initial_state;
	int path_cost;
	extended_node goal_test;
	String[] operators;
	Queue<extended_node> state_space;
	public static ArrayList<extended_node> All_moves = new ArrayList<extended_node>();

	public roll_the_ball(extended_node initial_state, int path_cost, boolean b,
			Queue<extended_node> state_space, String[] operators) {
		super(initial_state, path_cost, b, state_space, operators);
		All_moves = new ArrayList<extended_node>();
		All_moves.add(initial_state);
		operators = new String[] { "up", "down", "right", "left" };
	}

	/*
	 * move method takes as an input a node, position of a tile in the state of
	 * this node and the position of an adjacent Tile as parameters. The output
	 * of this is a node resulting from swapping a tile in the input node with
	 * adjacent tile
	 */
	public static extended_node move(extended_node e, int a, int b, int c, int d) {
		Tile[][] T = new Tile[((Tile[][]) e.state).length][((Tile[][]) e.state)[0].length];
		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < T[i].length; j++) {
				T[i][j] = new Tile();
				T[i][j].type = ((Tile[][]) e.state)[i][j].type;
				T[i][j].moveable = ((Tile[][]) e.state)[i][j].moveable;
			}
		}
		int sub = T[a][b].type;
		T[a][b].type = T[c][d].type;
		T[c][d].type = sub;
		boolean m = T[a][b].moveable;
		T[a][b].moveable = T[c][d].moveable;
		T[c][d].moveable = m;
		String operator = "";
		if (a == c && b == d + 1) {
			operator = "left from " + a + "," + b + "to " + c + "," + d;
		}
		if (a == c && b == d - 1) {
			operator = "right  from " + a + "," + b + "to " + c + "," + d;
		}

		if (a == c + 1 && b == d) {
			operator = "up  from " + a + "," + b + "to " + c + "," + d;
		}

		if (a == c - 1 && b == d) {
			operator = "down  from " + a + "," + b + "to " + c + "," + d;
		}
		extended_node result = new extended_node(e, T, operator, e.depth + 1,
				e.path_cost + 1);
		result.admissible_heur = admissable_heuristic(result)[2];
		result.not_admissible_heur = no_admissable_heuristic(result)[2];
		return result;
	}

	/*
	 * The possible_moves method takes as an input a node and it outputs for us
	 * the possible children of this node
	 */
	public ArrayList<extended_node> possible_moves(extended_node curr_node) {
		curr_node.state = (Tile[][]) curr_node.state;
		Tile.printGrid((Tile[][]) curr_node.state);
		ArrayList<extended_node> children = new ArrayList<extended_node>();
		for (int i = 0; i < ((Tile[][]) curr_node.state).length; i++) {
			for (int j = 0; j < ((Tile[][]) curr_node.state)[i].length; j++) {
				if (((Tile[][]) curr_node.state)[i][j].type == 7) {
					if (i > 0 && i < ((Tile[][]) curr_node.state).length - 1
							&& j > 0
							&& j < ((Tile[][]) curr_node.state)[i].length - 1) { // away
																					// from
																					// borders
						System.out.println("entered middle");
						if (((Tile[][]) curr_node.state)[i - 1][j].moveable
								&& ((Tile[][]) curr_node.state)[i - 1][j].type != 7) {
							if (!Main.contains(
									(move(curr_node, i, j, i - 1, j)),
									All_moves)) {
								System.out.println("up");
								children.add(move(curr_node, i, j, i - 1, j));
								All_moves.add(move(curr_node, i, j, i - 1, j));
							}

						}
						if (((Tile[][]) curr_node.state)[i][j - 1].moveable
								&& ((Tile[][]) curr_node.state)[i][j - 1].type != 7) {
							if (!Main.contains(
									(move(curr_node, i, j, i, j - 1)),
									All_moves)) {
								System.out.println("left");
								children.add(move(curr_node, i, j, i, j - 1));
								All_moves.add(move(curr_node, i, j, i, j - 1));
							}

						}
						if (((Tile[][]) (curr_node.state))[i][j + 1].moveable
								&& ((Tile[][]) curr_node.state)[i][j + 1].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i, j + 1),
									All_moves)) {
								System.out.println("right");
								// systm.out.println(((Tile[][])curr_node.state)[i][j].type);

								children.add(move(curr_node, i, j, i, j + 1));
								All_moves.add(move(curr_node, i, j, i, j + 1));
							}
						}
						if (((Tile[][]) curr_node.state)[i + 1][j].moveable
								&& ((Tile[][]) curr_node.state)[i + 1][j].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i + 1, j),
									All_moves)) {
								System.out.println("down");
								children.add(move(curr_node, i, j, i + 1, j));
								All_moves
										.add((move(curr_node, i, j, i + 1, j)));
							}
						}
					}
					if (i == 0 || i == ((Tile[][]) curr_node.state).length - 1) {
						if (j == 0) {
							if (((Tile[][]) curr_node.state)[i][j + 1].moveable
									&& ((Tile[][]) curr_node.state)[i][j + 1].type != 7) {
								if (!Main.contains(
										move(curr_node, i, j, i, j + 1),
										All_moves)) {
									children.add(move(curr_node, i, j, i, j + 1));
									All_moves.add(move(curr_node, i, j, i,
											j + 1));
								}
							}
						} else {
							if (j == ((Tile[][]) curr_node.state)[i].length - 1) {
								if (((Tile[][]) curr_node.state)[i][j - 1].moveable
										&& ((Tile[][]) curr_node.state)[i][j - 1].type != 7) {
									if (!Main.contains(
											move(curr_node, i, j, i, j - 1),
											All_moves)) {
										children.add(move(curr_node, i, j, i,
												j - 1));
										All_moves.add(move(curr_node, i, j, i,
												j - 1));
									}
								}
							} else if (j != 0) {
								if (((Tile[][]) curr_node.state)[i][j + 1].moveable
										&& ((Tile[][]) curr_node.state)[i][j + 1].type != 7) {
									if (!Main.contains(
											move(curr_node, i, j, i, j + 1),
											All_moves)) {
										children.add(move(curr_node, i, j, i,
												j + 1));
										All_moves.add(move(curr_node, i, j, i,
												j + 1));
									}
								}
								if (((Tile[][]) curr_node.state)[i][j - 1].moveable
										&& ((Tile[][]) curr_node.state)[i][j - 1].type != 7) {
									if (!Main.contains(
											move(curr_node, i, j, i, j - 1),
											All_moves)) {
										children.add(move(curr_node, i, j, i,
												j - 1));
										All_moves.add(move(curr_node, i, j, i,
												j - 1));
									}
								}
							}
						}
						if (i == 0
								&& ((Tile[][]) curr_node.state)[i + 1][j].moveable
								&& ((Tile[][]) curr_node.state)[i + 1][j].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i + 1, j),
									All_moves)) {
								children.add(move(curr_node, i, j, i + 1, j));
								All_moves.add(move(curr_node, i, j, i + 1, j));
							}
						}
						if ((i == ((Tile[][]) curr_node.state).length - 1)
								&& (((Tile[][]) curr_node.state)[i - 1][j].moveable)
								&& ((Tile[][]) curr_node.state)[i - 1][j].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i - 1, j),
									All_moves)) {
								children.add(move(curr_node, i, j, i - 1, j));
								All_moves.add(move(curr_node, i, j, i - 1, j));
							}
						}
					}
					if ((j == 0 || j == ((Tile[][]) curr_node.state)[i].length - 1)
							&& i > 0
							&& i < ((Tile[][]) curr_node.state).length - 1) {
						if (((Tile[][]) curr_node.state)[i + 1][j].moveable
								&& ((Tile[][]) curr_node.state)[i + 1][j].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i + 1, j),
									All_moves)) {
								children.add(move(curr_node, i, j, i + 1, j));
								All_moves.add(move(curr_node, i, j, i + 1, j));
							}
						}
						if (((Tile[][]) curr_node.state)[i - 1][j].moveable
								&& ((Tile[][]) curr_node.state)[i - 1][j].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i - 1, j),
									All_moves)) {
								children.add(move(curr_node, i, j, i - 1, j));
								All_moves.add(move(curr_node, i, j, i - 1, j));
							}
						}
						if (j == 0
								&& ((Tile[][]) curr_node.state)[i][j + 1].moveable
								&& ((Tile[][]) curr_node.state)[i][j + 1].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i, j + 1),
									All_moves)) {
								children.add(move(curr_node, i, j, i, j + 1));
								All_moves.add(move(curr_node, i, j, i, j + 1));
							}
						}
						if (j == ((Tile[][]) (curr_node.state))[i].length - 1
								&& ((Tile[][]) curr_node.state)[i][j - 1].moveable
								&& ((Tile[][]) curr_node.state)[i][j - 1].type != 7) {
							if (!Main.contains(move(curr_node, i, j, i, j - 1),
									All_moves)) {
								children.add(move(curr_node, i, j, i, j - 1));
								All_moves.add(move(curr_node, i, j, i, j - 1));
							}
						}
					}

				}

			}
		}
		return children;
	}

	/*
	 * The GenGrid method is responsible for generating a random grid as
	 * required in the project description
	 */
	public static Tile[][] GenGrid() {
		int rows = (int) ((Math.random() * 2) + 3);
		int cols = (int) ((Math.random() * 2) + 3);
		Tile[][] gen_board = new Tile[rows][cols];
		int Initialpos1 = (int) (Math.random() * (rows - 1));
		int Initialpos2 = (int) (Math.random() * (cols - 1));
		Tile init = new Tile((int) (Math.random() * 4 + 100), false);
		Tile goal;
		gen_board[Initialpos1][Initialpos2] = init;
		int goalPos1 = (int) (Math.random() * (rows - 1));
		int goalPos2 = (int) (Math.random() * (cols - 1));
		while (true) {
			if (goalPos1 != Initialpos1 || goalPos2 != Initialpos1) {
				goal = new Tile((int) (Math.random() * 4 + 100), false);
				gen_board[goalPos1][goalPos2] = goal;
				break;
			} else {
				goalPos1 = (int) (Math.random() * (rows - 1));
				goalPos2 = (int) (Math.random() * (cols - 1));
			}
		}
		Tile blank = new Tile(8, true);
		int blankPos1 = (int) (Math.random() * (rows - 1));
		int blankPos2 = (int) (Math.random() * (cols - 1));
		for (int i = 0; i < (int) (Math.sqrt(cols * rows)); i++) {
			if (gen_board[blankPos1][blankPos2] == null) {
				gen_board[blankPos1][blankPos2] = blank;
			} else {
				blankPos1 = (int) (Math.random() * (rows - 1));
				blankPos2 = (int) (Math.random() * (cols - 1));
			}
		}
		Tile pathTile;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (gen_board[i][j] == null) {
					pathTile = new Tile((int) ((Math.random() * 7) + 1),
							randBoolean());
					gen_board[i][j] = pathTile;
				}
			}
		}
		return gen_board;
	}

	public static boolean randBoolean() {
		int z = 0;
		z = (int) ((Math.random() * 10));
		return z >= 3 ? true : false;
	}

	public static Tile[][] gen_grid_zero(int r, int c) {
		Tile[][] t = new Tile[r][c];
		Tile one = new Tile(1, true);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				t[i][j] = one;
			}
		}
		return t;
	}

	/*
	 * The get_start_index takes as an input a 2D tile and output for us the
	 * coordinates of the start tile in the 2D tile
	 */
	public static int[] get_start_index(Tile[][] T) {
		int[] a = new int[2];
		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < T[i].length; j++) {
				if (T[i][j].type == 100 || T[i][j].type == 101
						|| T[i][j].type == 102 || T[i][j].type == 103) {
					a[0] = i;
					a[1] = j;
					return a;
				}
			}
		}
		return a;
	}

	/*
	 * The get_start_index takes as an input a 2D tile and output for us the
	 * coordinates of the goal tile in the 2D tile
	 */
	public static int[] get_goal_index(Tile[][] T) {
		int[] a = new int[2];
		int flag = 0;
		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < T[i].length; j++) {
				if (T[i][j].type == 100 || T[i][j].type == 101
						|| T[i][j].type == 102 || T[i][j].type == 103) {
					if (flag == 1) {
						a[0] = i;
						a[1] = j;
						return a;
					} else {
						flag = 1;
					}
				}
			}
		}
		return a;
	}

	/*
	 * goal method take as an input a 2D tile and outputs the result of calling
	 * the method rec_goal, where this output is true if the 2D tile
	 * representation is a goal, or false otherwise
	 */
	public static boolean goal(Tile[][] T) {
		int[] a = new int[2];
		a = get_start_index(T);
		int x = a[0];
		int y = a[1];
		int ro = T.length;
		int co = T[0].length;
		ArrayList<String> indexat = new ArrayList<String>();
		boolean o = rec_goal(T, x, y, 1, ro, co, indexat);
		System.out.println(o);
		return o;
	}

	public static ArrayList<extended_node> tested = new ArrayList<extended_node>();

	/*
	 * This method is a helper method for the goal()function, where in this
	 * method we pass the state of node N to the rec_goal method
	 */
	public boolean goal_test(extended_node N) {
		System.out.println("Testeeeed " + tested.size());
		// if(tested.isEmpty()||!Main.contains(N, tested)){
		System.out.println("Haytest");
		Tile.printGrid((Tile[][]) N.state);
		return goal((Tile[][]) N.state);
		// }else{
		// System.out.println("tested before !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// return false;
		// }
	}

	public static ArrayList<String> path_nodes;

	/*
	 * This method takes as an input a node, and it is used to calculate the
	 * admissible heuristic
	 */

	public static int[] admissable_heuristic(extended_node n) {
		Tile[][] t = new Tile[((Tile[][]) n.state).length][((Tile[][]) n.state)[0].length];
		t = (Tile[][]) n.state;
		int x = get_start_index(t)[0];
		int y = get_start_index(t)[1];
		ArrayList<String> s = new ArrayList<String>();
		int[] g = get_goal_index(t);
		String st = g[0] + "," + g[1];
		s.add(st);
		path_nodes = new ArrayList<String>();
		int[] k = admissable_help(t, x, y, s);
		k = borders(t, k);
		int[] f = new int[3];
		f[0] = k[0];
		f[1] = k[1];
		int min = 10000;
		int last_path_type = t[k[0]][k[1]].type;
		if (last_path_type == 1) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains((k[0] - 1) + "," + (k[1]))
									&& applicable(t[k[0]][k[1]].type,
											t[i][j].type, k[0], k[1], k[0] - 1,
											k[1], t.length, t[0].length) || (!path_nodes
									.contains((k[0] + 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] + 1, k[1], t.length, t[0].length)))
							&& (i != x || j != y)) {
						int c, d;
						if ((!path_nodes.contains((k[0] + 1) + "," + (k[1])) && applicable(
								t[k[0]][k[1]].type, t[i][j].type, k[0], k[1],
								k[0] + 1, k[1], t.length, t[0].length))) {
							c = k[0] + 1;
							d = k[1];
						} else {
							c = k[0] - 1;
							d = k[1];
						}
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}
		}
		if (last_path_type == 2) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] - 1))
									&& applicable(t[k[0]][k[1]].type,
											t[i][j].type, k[0], k[1], k[0],
											k[1] - 1, t.length, t[0].length) || (!path_nodes
									.contains(k[0] + "," + (k[1] + 1)) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0], k[1] + 1, t.length, t[0].length)))
							&& (i != x || j != y)) {
						int c, d;
						if ((!path_nodes.contains(k[0] + "," + (k[1] - 1)) && applicable(
								t[k[0]][k[1]].type, t[i][j].type, k[0], k[1],
								k[0], k[1] - 1, t.length, t[0].length))) {
							c = k[0];
							d = k[1] - 1;
						} else {
							c = k[0];
							d = k[1] + 1;
						}
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}
		}
		if (last_path_type == 3) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] - 1))
									&& applicable(t[k[0]][k[1]].type,
											t[i][j].type, k[0], k[1], k[0],
											k[1] - 1, t.length, t[0].length) || (!path_nodes
									.contains((k[0] + 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] + 1, k[1], t.length, t[0].length)))
							&& (i != x || j != y)) {
						int c, d;
						if ((!path_nodes.contains(k[0] + "," + (k[1] - 1)) && applicable(
								t[k[0]][k[1]].type, t[i][j].type, k[0], k[1],
								k[0], k[1] - 1, t.length, t[0].length))) {
							c = k[0];
							d = k[1] - 1;
						} else {
							c = k[0] + 1;
							d = k[1];
						}
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 4) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] + 1))
									&& applicable(t[k[0]][k[1]].type,
											t[i][j].type, k[0], k[1], k[0],
											k[1] + 1, t.length, t[0].length) || (!path_nodes
									.contains((k[0] + 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] + 1, k[1], t.length, t[0].length)))
							&& (i != x || j != y)) {
						int c, d;
						if ((!path_nodes.contains(k[0] + "," + (k[1] + 1)) && applicable(
								t[k[0]][k[1]].type, t[i][j].type, k[0], k[1],
								k[0], k[1] + 1, t.length, t[0].length))) {
							c = k[0];
							d = k[1] + 1;
						} else {
							c = k[0] + 1;
							d = k[1];
						}
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 5) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] + 1))
									&& applicable(t[k[0]][k[1]].type,
											t[i][j].type, k[0], k[1], k[0],
											k[1] + 1, t.length, t[0].length) || (!path_nodes
									.contains((k[0] - 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] - 1, k[1], t.length, t[0].length)))
							&& (i != x || j != y)) {
						int c, d;
						if ((!path_nodes.contains(k[0] + "," + (k[1] + 1)) && applicable(
								t[k[0]][k[1]].type, t[i][j].type, k[0], k[1],
								k[0], k[1] + 1, t.length, t[0].length))) {
							c = k[0];
							d = k[1] + 1;
						} else {
							c = k[0] - 1;
							d = k[1];
						}
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 6) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] - 1))
									&& applicable(t[k[0]][k[1]].type,
											t[i][j].type, k[0], k[1], k[0],
											k[1] - 1, t.length, t[0].length) || (!path_nodes
									.contains((k[0] - 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] - 1, k[1], t.length, t[0].length)))
							&& (i != x || j != y)) {
						int c, d;
						if ((!path_nodes.contains(k[0] + "," + (k[1] - 1)) && applicable(
								t[k[0]][k[1]].type, t[i][j].type, k[0], k[1],
								k[0], k[1] - 1, t.length, t[0].length))) {
							c = k[0];
							d = k[1] - 1;
						} else {
							c = k[0] - 1;
							d = k[1];
						}
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						// System.out.println("Min : .." + min);

						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 100) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains((k[0] + 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] + 1, k[1], t.length, t[0].length))
							&& (i != x || j != y)) {

						int c, d;
						c = k[0] + 1;
						d = k[1];
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 101) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains((k[0] - 1) + "," + (k[1])) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0] - 1, k[1], t.length, t[0].length))
							&& i != x && j != y) {
						int c, d;
						c = k[0] - 1;
						d = k[1];
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 102) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] - 1)) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0], k[1] - 1, t.length, t[0].length))
							&& i != x && j != y) {
						int c, d;
						c = k[0];
						d = k[1] - 1;
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}
		if (last_path_type == 103) {
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (!path_nodes.contains(i + "," + j)
							&& (!path_nodes.contains(k[0] + "," + (k[1] + 1)) && applicable(
									t[k[0]][k[1]].type, t[i][j].type, k[0],
									k[1], k[0], k[1] + 1, t.length, t[0].length))
							&& i != x && j != y) {
						int c, d;
						c = k[0];
						d = k[1] + 1;
						int re = 0;
						int a = i;
						int b = j;
						while (a != c) {
							if (a > c) {
								a = a - 1;
							} else {
								c = c - 1;
							}
							re++;
						}
						while (b != d) {
							if (b > d) {
								b = b - 1;
							} else {
								d = d - 1;
							}
							re++;
						}
						if (min > re) {
							min = re;
						}
					}

				}
			}

		}

		if (min == 10000) {
			f[2] = 0;
		} else {
			f[2] = min;
		}
		return f;
	}

	private static int[] ParseInt(String[] split) {
		int[] a = new int[2];
		a[0] = Integer.parseInt(split[0]);
		a[1] = Integer.parseInt(split[1]);
		return a;
	}

	/*
	 * This method takes as an input a node, and it is used to calculate the non
	 * admissible heuristic
	 */
	public static int[] no_admissable_heuristic(extended_node n) {
		Tile[][] t = new Tile[((Tile[][]) n.state).length][((Tile[][]) n.state)[0].length];
		t = (Tile[][]) n.state;
		int x = get_start_index(t)[0];
		int y = get_start_index(t)[1];
		ArrayList<String> s = new ArrayList<String>();
		int[] g = get_goal_index(t);
		String st = g[0] + "," + g[1];
		s.add(st);
		int[] k = admissable_help(t, x, y, s);
		k = borders(t, k);
		int result = 0;
		int[] f = new int[3];
		f[0] = k[0];
		f[1] = k[1];
		while (g[0] != k[0]) {
			if (g[0] > k[0]) {
				g[0] = g[0] - 1;
			} else {
				k[0] = k[0] - 1;
			}
			result++;
		}
		while (g[1] != k[1]) {
			if (g[1] > k[1]) {
				g[1] = g[1] - 1;
			} else {
				k[1] = k[1] - 1;
			}
			result++;
		}
		f[2] = result - 1;
		return f;
	}

	static int[] res = new int[2];
	static int prev_x = -100;
	static int prev_y = -100;

	/*
	 * This is a helper method that is used for both admissible and non
	 * admissible methods metioned above.
	 */
	public static int[] admissable_help(Tile[][] t, int x, int y,
			ArrayList<String> s) {
		String st = "";
		st = x + "," + y;
		if (t[x][y].type == 100 && !s.contains(st)) {
			if (x != t.length - 1
					&& applicable(100, t[x + 1][y].type, x, y, x + 1, y,
							t.length, t[0].length)) {
				s.add(st);
				prev_x = x;
				prev_y = y;
				admissable_help(t, x + 1, y, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 101 && !s.contains(st)) {
			if (x != 0
					&& applicable(101, t[x - 1][y].type, x, y, x - 1, y,
							t.length, t[0].length)) {
				s.add(st);
				prev_x = x;
				prev_y = y;
				admissable_help(t, x - 1, y, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 102 && !s.contains(st)) {
			if (y != 0
					&& applicable(102, t[x][y - 1].type, x, y, x, y - 1,
							t.length, t[0].length)) {
				s.add(st);
				prev_x = x;
				prev_y = y;
				admissable_help(t, x, y - 1, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 103 && !s.contains(st)) {
			if (y != t[0].length - 1
					&& applicable(103, t[x][y + 1].type, x, y, x, y + 1,
							t.length, t[0].length)) {
				s.add(st);
				prev_x = x;
				prev_y = y;
				admissable_help(t, x, y + 1, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 1 && !s.contains(st)) {
			if (x != t.length - 1
					&& applicable(1, t[x + 1][y].type, x, y, x + 1, y,
							t.length, t[0].length)
					&& !s.contains((x + 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x + 1, y, s);
			} else if (x != 0
					&& applicable(1, t[x - 1][y].type, x, y, x - 1, y,
							t.length, t[0].length)
					&& !s.contains((x - 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x - 1, y, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 2 && !s.contains(st)) {
			if (x != 0
					&& applicable(2, t[x - 1][y].type, x, y, x - 1, y,
							t.length, t[0].length)
					&& !s.contains((x - 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x - 1, y, s);
			} else if (x != t[0].length - 1
					&& applicable(2, t[x + 1][y].type, x, y, x + 1, y,
							t.length, t[0].length)
					&& !s.contains((x + 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x + 1, y, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 3 && !s.contains(st)) {
			if (x != t.length - 1
					&& applicable(3, t[x + 1][y].type, x, y, x + 1, y,
							t.length, t[0].length)
					&& !s.contains((x + 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x + 1, y, s);
			} else if (y != 0
					&& applicable(3, t[x][y - 1].type, x, y, x, y - 1,
							t.length, t[0].length)
					&& !s.contains(x + "," + (y - 1))) {
				s.add(st);
				admissable_help(t, x, y - 1, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 4 && !s.contains(st)) {
			if (x != t[0].length - 1
					&& applicable(4, t[x + 1][y].type, x, y, x + 1, y,
							t.length, t[0].length)
					&& !s.contains((x + 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x + 1, y, s);
			} else if (y != t[0].length - 1
					&& applicable(4, t[x][y + 1].type, x, y, x, y + 1,
							t.length, t[0].length)
					&& !s.contains(x + "," + (y + 1))) {
				s.add(st);
				admissable_help(t, x, y + 1, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 5 && !s.contains(st)) {
			if (y != t[0].length - 1
					&& applicable(5, t[x][y + 1].type, x, y, x, y + 1,
							t.length, t[0].length)
					&& !s.contains(x + "," + (y + 1))) {
				s.add(st);
				admissable_help(t, x, y + 1, s);
			} else if (x != 0
					&& applicable(5, t[x - 1][y].type, x, y, x - 1, y,
							t.length, t[0].length)
					&& !s.contains((x - 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x - 1, y, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 6 && !s.contains(st)) {
			if (y != 0
					&& applicable(6, t[x][y - 1].type, x, y, x, y - 1,
							t.length, t[0].length)
					&& !s.contains(x + "," + (y - 1))) {
				s.add(st);
				admissable_help(t, x, y - 1, s);
			} else if (x != 0
					&& applicable(6, t[x - 1][y].type, x, y, x - 1, y,
							t.length, t[0].length)
					&& !s.contains((x - 1) + "," + y)) {
				s.add(st);
				admissable_help(t, x - 1, y, s);
			} else {
				res[0] = x;
				res[1] = y;

			}
		} else if (t[x][y].type == 7 && !s.contains(st)) {
			return res;
		} else if (t[x][y].type == 8 && !s.contains(st)) {
			return res;
		}

		if (!s.contains(x + "," + y))
			s.add(x + "," + y);

		path_nodes = s;
		return res;
	}

	/*
	 * This method is used to insure that there is no a blocked path at the
	 * borders
	 */
	public static int[] borders(Tile[][] t, int[] res) {
		int type = t[res[0]][res[1]].type;
		int a = res[0];
		int b = res[1];
		if (type == 1) {
			if (a == 0) {
				res[0] = res[0] + 1;
			}
			if (a == t.length - 1) {
				res[0] = res[0] - 1;
			}
		} else if (type == 2) {
			if (b == 0) {
				res[1] = res[1] + 1;
			}
			if (b == t[0].length - 1) {
				res[1] = res[1] - 1;
			}
		} else if (type == 3) {
			if (b == 0 && a != t.length - 1) {
				res[0] = res[0] + 1;
			}
			if (a == t.length - 1 && b != 0) {
				res[1] = res[1] - 1;
			}
		} else if (type == 4) {
			if (b != t.length - 1 && a == t.length - 1) {
				res[1] = res[1] + 1;
			}
			if (a != t.length - 1 && b == t.length - 1) {
				res[0] = res[0] + 1;
			}
		} else if (type == 5) {
			if (b != t.length - 1 && a == 0) {
				res[1] = res[1] + 1;
			}
			if (a != 0 && b == t.length - 1) {
				res[0] = res[0] - 1;
			}
		} else if (type == 6) {
			if (b != 0 && a == 0) {
				res[1] = res[1] - 1;
			}
			if (a != 0 && b == 0) {
				res[0] = res[0] - 1;
			}
		}
		return res;
	}

	/*
	 * This method is used to identify if a 2D tile is a goal node or not
	 */

	public static boolean rec_goal(Tile[][] T, int x, int y, int f, int ro,
			int co, ArrayList<String> indexat) {
		// systm.out.println(indexat.size() + " _ - _ - ------------");
		// //systm.out.println(x);
		// //systm.out.println(y);
		// boolean result = false;
		if (((T[x][y].type == 101 || T[x][y].type == 100 || T[x][y].type == 102 || T[x][y].type == 103))
				&& f == 0) {
			System.out
					.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			// systm.out.println("finished");
			return true;
		}
		if (x != 0 && y != 0 && x != ro - 1 && y != co - 1) {
			// //systm.out.println("  start ");
			if (!indexat.contains((x + 1) + "," + y)
					&& applicable(T[x][y].type, T[x + 1][y].type, x, y, x + 1,
							y, ro, co)) {
				if (!indexat.contains(x + "," + y)) {
					indexat.add((x) + "," + y);
				}
				return rec_goal(T, x + 1, y, 0, ro, co, indexat);
			}
			if (!indexat.contains((x - 1) + "," + y)
					&& applicable(T[x][y].type, T[x - 1][y].type, x, y, x - 1,
							y, ro, co)) {
				if (!indexat.contains(x + "," + y)) {
					indexat.add((x) + "," + y);
				}
				// systm.out.println(indexat.size() + " _ - _ - ------------");

				return rec_goal(T, x - 1, y, 0, ro, co, indexat);
			}
			if (!indexat.contains(x + "," + (y + 1))
					&& applicable(T[x][y].type, T[x][y + 1].type, x, y, x,
							y + 1, ro, co)) {// right
				if (!indexat.contains(x + "," + y)) {
					indexat.add((x) + "," + y);
				}
				return rec_goal(T, x, y + 1, 0, ro, co, indexat);
			}
			if (!indexat.contains(x + "," + (y - 1))
					&& applicable(T[x][y].type, T[x][y - 1].type, x, y, x,
							y - 1, ro, co)) {
				if (!indexat.contains(x + "," + y)) {
					indexat.add((x) + "," + y);
				}
				return rec_goal(T, x, y - 1, 0, ro, co, indexat);
			}
		} else {
			// //systm.out.println("start not middle");
			if (x == 0) {
				if (applicable(T[x][y].type, T[x + 1][y].type, x, y, x + 1, y,
						ro, co) && !indexat.contains((x + 1) + "," + y)) {
					if (!indexat.contains(x + "," + y)) {
						indexat.add((x) + "," + y);
					}
					// //systm.out.println(" x + 1");
					return rec_goal(T, x + 1, y, 0, ro, co, indexat);
				}
				if (y == 0) {
					if (applicable(T[x][y].type, T[x][y + 1].type, x, y, x,
							y + 1, ro, co)
							&& !indexat.contains(x + "," + (y + 1))) {
						if (!indexat.contains(x + "," + y)) {
							indexat.add((x) + "," + y);
						}

						return rec_goal(T, x, y + 1, 0, ro, co, indexat);
					}
				} else {
					if (y == co - 1) {
						if (applicable(T[x][y].type, T[x][y - 1].type, x, y, x,
								y - 1, ro, co)
								&& !indexat.contains(x + "," + (y - 1))) {
							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							return rec_goal(T, x, y - 1, 0, ro, co, indexat);
						}
					} else {
						if (applicable(T[x][y].type, T[x][y + 1].type, x, y, x,
								y + 1, ro, co)
								&& !indexat.contains(x + "," + (y + 1))) {
							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							return rec_goal(T, x, y + 1, 0, ro, co, indexat);
						}
						if (applicable(T[x][y].type, T[x][y - 1].type, x, y, x,
								y - 1, ro, co)
								&& !indexat.contains(x + "," + (y - 1))) {

							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							return rec_goal(T, x, y - 1, 0, ro, co, indexat);
						}
					}
				}
			} else {
				if (x == ro - 1) {
					if (applicable(T[x][y].type, T[x - 1][y].type, x, y, x - 1,
							y, ro, co) && !indexat.contains((x - 1) + "," + y)) {

						if (!indexat.contains(x + "," + y)) {
							indexat.add((x) + "," + y);
						}
						return rec_goal(T, x - 1, y, 0, ro, co, indexat);
					}
					if (y == 0) {
						if (applicable(T[x][y].type, T[x][y + 1].type, x, y, x,
								y + 1, ro, co)
								&& !indexat.contains(x + "," + (y + 1))) {

							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							return rec_goal(T, x, y + 1, 0, ro, co, indexat);
						}
					} else {
						if (y == co - 1) {
							if (applicable(T[x][y].type, T[x][y - 1].type, x,
									y, x, y - 1, ro, co)
									&& !indexat.contains(x + "," + (y - 1))) {

								if (!indexat.contains(x + "," + y)) {
									indexat.add((x) + "," + y);
								}
								return rec_goal(T, x, y - 1, 0, ro, co, indexat);
							}
						} else {
							if (applicable(T[x][y].type, T[x][y + 1].type, x,
									y, x, y + 1, ro, co)
									&& !indexat.contains(x + "," + (y + 1))) {

								if (!indexat.contains(x + "," + y)) {
									indexat.add((x) + "," + y);
								}
								return rec_goal(T, x, y + 1, 0, ro, co, indexat);
							}
							if (applicable(T[x][y].type, T[x][y - 1].type, x,
									y, x, y - 1, ro, co)
									&& !indexat.contains(x + "," + (y - 1))) {
								if (!indexat.contains(x + "," + y)) {
									indexat.add((x) + "," + y);
								}

								return rec_goal(T, x, y - 1, 0, ro, co, indexat);
							}
						}
					}
				} else {
					if (y == 0) {
						// systm.out.println("y = 0");
						if (applicable(T[x][y].type, T[x][y + 1].type, x, y, x,
								y + 1, ro, co)
								&& !indexat.contains(x + "," + (y + 1))) {

							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							// systm.out.println("ri");
							return rec_goal(T, x, y + 1, 0, ro, co, indexat);
						}
						if (applicable(T[x][y].type, T[x - 1][y].type, x, y,
								x - 1, y, ro, co)
								&& !indexat.contains((x - 1) + "," + y)) {
							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							// systm.out.println("up");
							return rec_goal(T, x - 1, y, 0, ro, co, indexat);
						}
						if (applicable(T[x][y].type, T[x + 1][y].type, x, y,
								x + 1, y, ro, co)
								&& !indexat.contains((x + 1) + "," + y)) {

							if (!indexat.contains(x + "," + y)) {
								indexat.add((x) + "," + y);
							}
							// systm.out.println("dow");
							return rec_goal(T, x + 1, y, 0, ro, co, indexat);
						}
					} else {
						if (y == co - 1) {

							if (applicable(T[x][y].type, T[x][y - 1].type, x,
									y, x, y - 1, ro, co)
									&& !indexat.contains(x + "," + (y - 1))) {
								if (!indexat.contains(x + "," + y)) {
									indexat.add((x) + "," + y);
								}
								return rec_goal(T, x, y - 1, 0, ro, co, indexat);
							}
							if (applicable(T[x][y].type, T[x - 1][y].type, x,
									y, x - 1, y, ro, co)
									&& !indexat.contains((x - 1) + "," + y)) {
								if (!indexat.contains(x + "," + y)) {
									indexat.add((x) + "," + y);
								}

								return rec_goal(T, x - 1, y, 0, ro, co, indexat);
							}
							if (applicable(T[x][y].type, T[x + 1][y].type, x,
									y, x + 1, y, ro, co)
									&& !indexat.contains((x + 1) + "," + y)) {
								if (!indexat.contains(x + "," + y)) {
									indexat.add((x) + "," + y);
								}
								return rec_goal(T, x + 1, y, 0, ro, co, indexat);
							}
						} else {
							return false;
						}
					}
				}
			}

		}
		return false;
	}

	/*
	 * This method is a helper method used in the possible_moves method. This
	 * method is used to insure that a certain move of a tile to an adjacent
	 * tile is valid.
	 */
	public static boolean applicable(int x, int y, int i, int j, int a, int b,
			int ro, int co) {
		boolean result = false;
		if (x == 7 || y == 7 || x == 8 || y == 8) {
			return false;
		} else {
			if (x == 100 && i != ro - 1 && a == i + 1 && j == b
					&& ((y == 1) || (y == 5) || (y == 6) || (y == 101))) {
				return true;
			} else {
				if (x == 101 && i != 0 && a == i - 1 && j == b
						&& ((y == 1) || (y == 3) || (y == 4) || (y == 100))) {
					return true;
				} else {
					if (x == 102 && j != 0 && a == i && b == j - 1
							&& ((y == 2) || (y == 4) || (y == 5) || (y == 103))) {
						return true;
					} else {
						if (x == 103
								&& j != co - 1
								&& a == i
								&& b == j + 1
								&& ((y == 2) || (y == 3) || (y == 6) || (y == 102))) {
							return true;
						} else {
							if (x == 1
									&& i != 0
									&& i != ro - 1
									&& b == j
									&& ((y == 101 && a == i + 1)
											|| (y == 1 && (a == i + 1 || a == i - 1))
											|| (y == 5 && a == i + 1)
											|| (y == 6 && a == i + 1) || (y == 100 && a == i - 1))) {
								return true;
							} else {
								if (x == 2
										&& j != 0
										&& j != co - 1
										&& (b == j + 1 || b == j - 1)
										&& a == i
										&& (y == 2 || y == 5 || y == 6
												|| y == 3 || y == 4 || y == 102 || y == 103)) {
									return true;
								} else {
									if (x == 3
											&& j != 0
											&& i != ro - 1
											&& ((y == 5 && ((a == i + 1 && b == j) || (a == i && b == j - 1)))
													|| (y == 2 && a == i && b == j - 1)
													|| (y == 4 && a == i && b == j - 1)
													|| (y == 1 && a == i + 1 && b == j)
													|| (y == 101 && a == i + 1 && b == j)
													|| (y == 103 && a == i && b == j - 1) || (y == 6
													&& a == i + 1 && b == j))) {
										return true;
									} else {
										if (x == 4
												&& j != co - 1
												&& i != ro - 1
												&& ((y == 2 && a == i && b == j + 1)
														|| (y == 1
																&& a == i + 1 && b == j)
														|| (y == 5
																&& a == i + 1 && b == j)
														|| (y == 6 && ((a == i + 1 && b == j) || (a == i && b == j + 1)))
														|| (y == 3 && a == i && b == j + 1)
														|| (y == 102 && a == i && b == j + 1) || (y == 101
														&& a == i + 1 && b == j))) {
											return true;
										} else {
											if (x == 5
													&& j != co - 1
													&& i != 0
													&& ((y == 1 && a == i - 1 && b == j)
															|| (y == 2
																	&& a == i && b == j + 1)
															|| (y == 3 && ((a == i && b == j + 1) || (a == i - 1 && b == j)))
															|| (y == 4
																	&& a == i - 1 && b == j)
															|| (y == 6
																	&& a == i && b == j + 1)
															|| (y == 102
																	&& a == i && b == j + 1) || (y == 100
															&& a == i - 1 && b == j))) {
												return true;
											} else {
												if (x == 6
														&& j != 0
														&& i != 0
														&& ((y == 5 && a == i && b == j - 1)
																|| (y == 4 && ((a == i - 1 && b == j) || (a == i && b == j - 1)))
																|| (y == 2
																		&& a == i && b == j - 1)
																|| (y == 1
																		&& a == i - 1 && b == j)
																|| (y == 100
																		&& a == i - 1 && b == j) || (y == 103
																&& a == i && b == j - 1))) {
													return true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

}
