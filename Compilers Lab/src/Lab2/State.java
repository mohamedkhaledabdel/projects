package Lab2;

public class State {
	String number;

	public State(String number) {
		this.number = number;
	}

	public State() {
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setStateToState(State s) {
		this.setNumber(s.getNumber());
	}
}
