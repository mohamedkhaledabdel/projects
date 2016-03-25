public abstract class Problem {
	Object initial_state;
	int path_cost;
	Object goal_test;
	Object state_space;
	Object operators;

	public Problem(Object initial_state, int path_cost, Object goal_test,
			Object state_space, Object operators) {
		this.initial_state = initial_state;
		this.path_cost = path_cost;
		this.goal_test = goal_test;
		this.state_space = state_space;
		this.operators = operators;
	}

	public Object getInitial_state() {
		return initial_state;
	}
	public boolean goal_test (extended_node N){
		return true;
	}

	public void setInitial_state(Object initial_state) {
		this.initial_state = initial_state;
	}

	public int getPath_cost() {
		return path_cost;
	}

	public void setPath_cost(int path_cost) {
		this.path_cost = path_cost;
	}

	public Object getState_space() {
		return state_space;
	}

	public void setState_space(Object state_space) {
		this.state_space = state_space;
	}

	public Object getOperators() {
		return operators;
	}

	public void setOperators(String[] operators) {
		this.operators = operators;
	}
	

}
