public abstract class Node {
	Node parent;
	Object state;
	Object operator;
	int depth;
	int path_cost;
	public int admissible_heur;
	public int not_admissible_heur;

	public Node(Node parent, Object state, Object operator, int depth,
			int path_cost) {
		super();
		this.parent = parent;
		this.state = state;
		this.operator = operator;
		this.depth = depth;
		this.path_cost = path_cost;
		this.admissible_heur = 0;
		this.not_admissible_heur = 0;
	}

	public Node() {
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Object getState() {
		return state;
	}

	public void setState(Object state) {
		this.state = state;
	}

	public Object getOperator() {
		return operator;
	}

	public void setOperator(Object operator) {
		this.operator = operator;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getPath_cost() {
		return path_cost;
	}

	public void setPath_cost(int path_cost) {
		this.path_cost = path_cost;
	}

}
