import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	roll_the_ball problem;
	extended_node N = new extended_node();
	static int l = 0;
	static ArrayList<extended_node> nodes = new ArrayList<extended_node>();
	public static ArrayList<extended_node> all;
	Queue<extended_node> a = new LinkedList<extended_node>();
	Queue<extended_node> Main_Queue = new LinkedList<extended_node>();
	String[] ar = { "up", "down", "right", "left" };
	static ArrayList<extended_node> path = new ArrayList<extended_node>();
	static extended_node initial_node;

	/*
	 * Search method takes as an input a 2D array of tiles and a string strategy
	 * and returns the result of applying Generic_search(Problem p, search
	 * strategy)
	 */
	public boolean Search(Tile[][] t, String strategy) {
		initial_node = new extended_node(null, t, "", 0, 0);
		initial_node.admissible_heur = roll_the_ball
				.admissable_heuristic(initial_node)[2];
		initial_node.not_admissible_heur = roll_the_ball
				.no_admissable_heuristic(initial_node)[2];
		roll_the_ball new_problem = new roll_the_ball(initial_node,
				initial_node.path_cost, false, a, ar);
		if (strategy.equals("BFS")) {
			System.out.println("Bfs entered...");
			all = new ArrayList<extended_node>();
			all.add(initial_node);
			Generic_Search(new_problem, new BF());
			all = new ArrayList<extended_node>();
			all.add(initial_node);
			new_problem.All_moves.clear();
			return Generic_Search(new_problem, new ID());
		}
		return false;

	}

	public interface Strategy {
		public Queue<extended_node> execute(Queue<extended_node> a,
				ArrayList<extended_node> nodes);

		public Queue<extended_node> execute(Queue<extended_node> a,
				extended_node n);

	}

	/*
	 * The BF class is a class which responsible for executing the quing
	 * function of BFS startegy
	 */
	public class BF implements Strategy {
		@Override
		/*
		 * The execute method is resposnible for the execution of the quing
		 * function
		 */
		public Queue<extended_node> execute(Queue<extended_node> q,
				ArrayList<extended_node> nodes) {
			System.out.println("BFS");
			for (int i = 0; i < nodes.size(); i++) {
				q.add(nodes.get(i));
			}
			return q;
			// TODO Auto-generated method stub

		}

		@Override
		public Queue<extended_node> execute(Queue<extended_node> q,
				extended_node n) {
			System.out.println("BFS");
			q.add(n);
			return q;
			// TODO Auto-generated method stub

		}

	}

	/*
	 * The DF class is a class which responsible for executing the quing
	 * function of DFS startegy
	 */
	public class DF implements Strategy {
		@Override
		/*
		 * The execute method is resposnible for the execution of the quing
		 * function
		 */
		public Queue<extended_node> execute(Queue<extended_node> q,
				ArrayList<extended_node> nodes) {
			System.out.println("DFS");

			Deque<extended_node> a = new LinkedList<extended_node>();
			a.addAll(q);
			for (int i = nodes.size() - 1; i >= 0; i--) {
				a.addFirst(nodes.get(i));
			}
			q = Collections.asLifoQueue(a);
			return q;

		}

		@Override
		public Queue<extended_node> execute(Queue<extended_node> q,
				extended_node n) {
			System.out.println("BFS");
			q.add(n);
			return q;
			// TODO Auto-generated method stub

		}

	}

	/*
	 * The ID class is a class which responsible for executing the quing
	 * function of IDP startegy
	 */
	public class ID implements Strategy {

		@Override
		/*
		 * The execute method is resposnible for the execution of the quing
		 * function
		 */
		public Queue<extended_node> execute(Queue<extended_node> q,
				ArrayList<extended_node> nodes) {
			Deque<extended_node> a = new LinkedList<extended_node>();
			a.addAll(q);

			if (nodes.get(0).depth > l) {
				for (int i = nodes.size() - 1; i >= all.size() - nodes.size()
						&& all.size() > nodes.size(); i--) {
					all.remove(i);
				}
				if (queue.isEmpty()) {
					roll_the_ball.All_moves.clear();
					all.clear();
					l++;

				}
			} else {
				for (int i = nodes.size() - 1; i >= 0; i--) {
					a.addFirst(nodes.get(i));
				}
			}
			q = Collections.asLifoQueue(a);
			return q;

		}

		@Override
		public Queue<extended_node> execute(Queue<extended_node> q,
				extended_node n) {
			q.add(n);
			return q;
			// TODO Auto-generated method stub
		}

	}

	/*
	 * The GR1 class is a class which responsible for executing the quing
	 * function of admissible greedy startegy
	 */
	public class GR1 implements Strategy {
		@Override
		/*
		 * The execute method is resposnible for the execution of the quing
		 * function
		 */
		public Queue<extended_node> execute(Queue<extended_node> q,
				ArrayList<extended_node> nodes) {
			cost_compare.s = "adm";
			Comparator<extended_node> comparator = new cost_compare();
			PriorityQueue<extended_node> a = new PriorityQueue<extended_node>(
					10, comparator);
			for (int i = 0; i < nodes.size(); i++) {
				a.add(nodes.get(i));
			}
			q.addAll(a);
			// q = a;
			return q;

		}

		@Override
		public Queue<extended_node> execute(Queue<extended_node> q,
				extended_node n) {
			q.add(n);
			// Collections.reverse((List<?>) q);
			return q;
		}

	}

	/*
	 * The GR2 class is a class which responsible for executing the quing
	 * function of non admissible greedy startegy
	 */
	public class GR2 implements Strategy {
		@Override
		/*
		 * The execute method is resposnible for the execution of the quing
		 * function
		 */
		public Queue<extended_node> execute(Queue<extended_node> q,
				ArrayList<extended_node> nodes) {
			cost_compare.s = "not_adm";
			Comparator<extended_node> comparator = new cost_compare();
			PriorityQueue<extended_node> a = new PriorityQueue<extended_node>(
					10, comparator);

			for (int i = nodes.size() - 1; i >= 0; i--) {
				a.add(nodes.get(i));
			}
			q.addAll(a);
			// q = a;
			// q = Collections.;
			return q;

		}

		@Override
		public Queue<extended_node> execute(Queue<extended_node> q,
				extended_node n) {
			q.add(n);
			return q;
		}

	}

	/*
	 * The AS class is a class which responsible for executing the quing
	 * function of admissible A* startegy
	 */
	public class AS implements Strategy {
		@Override
		/*
		 * The execute method is resposnible for the execution of the quing
		 * function
		 */
		public Queue<extended_node> execute(Queue<extended_node> q,
				ArrayList<extended_node> nodes) {
			cost_compare.s = "AS";
			Comparator<extended_node> comparator = new cost_compare();
			PriorityQueue<extended_node> a = new PriorityQueue<extended_node>(
					10, comparator);
			for (int i = 0; i < nodes.size(); i++) {
				a.add(nodes.get(i));
			}
			q.addAll(a);
			return q;
		}

		@Override
		public Queue<extended_node> execute(Queue<extended_node> q,
				extended_node n) {
			q.add(n);
			return q;
		}
	}

	Queue<extended_node> queue = new LinkedList<extended_node>();

	/*
	 * This method takes as input a problem and a startegy and returns true if a
	 * solution resulting from the search is found or false otherwise
	 */
	public boolean Generic_Search(Problem p, Strategy command) {
		all.add(initial_node);
		queue = command.execute(queue, (extended_node) p.initial_state);
		while (!queue.isEmpty() || all.isEmpty()) {
			if (all.isEmpty() || all == null) {
				return Generic_Search(p, command);
			} else {
				N = queue.peek();
				queue.remove();
				if (p.goal_test(N)) {
					System.out.println("testing goal");
					ArrayList<extended_node> n = new ArrayList<extended_node>();
					System.out.println("Printing path.....");
					n = print_path(N);
					for (int i = n.size() - 1; i >= 0; i--) {
						System.out.println(n.get(i).operator);
					}
					Tile.printGrid((Tile[][]) N.state);
					return true;
				} else {
					System.out.println("Possible moves........");
					ArrayList<extended_node> no = ((roll_the_ball) p)
							.possible_moves(N);
					System.out.println("Found possible moves.......... "
							+ no.size());
					if (no.isEmpty()) {
						System.out.println("No moves........");
					} else {
						for (int i = no.size() - 1; i >= 0; i--) {
							if (!contains(no.get(i), all)) {
								System.out
										.println(".....Added Node...............................");
								Tile.printGrid((Tile[][]) no.get(i).state);
								for (int z = 0; z < 100000; z++) {
								}
								System.out.println("admissable :  "
										+ no.get(i).admissible_heur);
								System.out.println("Not admissable :  "
										+ no.get(i).not_admissible_heur);
								System.out
										.println("..........................Added Node....");
								all.add(no.get(i));
							}
						}
						queue = command.execute(queue, no);

					}
				}
			}
		}

		return false;
	}

	/*
	 * contains is a helper method to check if a child of a parent is already
	 * been explored before to avoid cycles
	 */
	public static boolean contains(extended_node N,
			ArrayList<extended_node> Nodes) {
		for (int i = 0; i < Nodes.size(); i++) {
			int flag = 0;
			for (int k = 0; k < ((Tile[][]) (Nodes.get(i).state)).length; k++) {
				for (int l = 0; l < ((Tile[][]) (Nodes.get(i).state))[k].length; l++) {
					if ((((Tile[][]) (Nodes.get(i).state))[k][l].type) == (((Tile[][]) N.state)[k][l].type)
							&& (((Tile[][]) (Nodes.get(i).state))[k][l].moveable) == ((((Tile[][]) N.state)[k][l].moveable))) {
						flag = flag + 1;
					}
				}

			}
			if (flag == (((Tile[][]) (Nodes.get(i).state)).length * ((Tile[][]) (Nodes
					.get(i).state))[0].length)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * print_path method is used to print the path from the start to the goal
	 */

	public static ArrayList<extended_node> print_path(extended_node s) {
		if (s.parent == null) {
			path.add(s);
			return path;
		} else {
			path.add((extended_node) s);
			return print_path((extended_node) s.parent);
		}
	}

	public static void main(String[] args) {
		Tile[][] t = new Tile[4][4];
		t = roll_the_ball.GenGrid();
		// Tile a = new Tile(103, false);
		// Tile b = new Tile(7, true);
		// Tile c = new Tile(3, true);
		// Tile d = new Tile(1, true);
		// Tile e = new Tile(102, false);
		// Tile f = new Tile(5, true);
		// Tile g = new Tile(2, true);
		// for (int i = 0; i < 4; i++) {
		// for (int j = 0; j < 4; j++) {
		// t[i][j].type = 1;
		// t[i][j].moveable = false;
		// }
		// }
		// t[0][0] = a;
		// t[0][1] = b;
		// t[1][1] = g;
		// t[0][2] = b;
		// t[1][2] = b;
		// t[0][3] = c;
		// t[1][3] = d;
		// t[2][3] = e;
		// t[2][2] = f;
		Tile.printGrid(t);
		Main M = new Main();
		System.out.println(M.Search(t, "BFS"));
	}

}
