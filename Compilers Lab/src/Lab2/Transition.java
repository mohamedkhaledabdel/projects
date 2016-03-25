package Lab2;

public class Transition {
	State previousState;
	Alphabet alphabet;
	State nextState;

	public Transition(State previousState, Alphabet alphabet, State nextState) {
		super();
		this.previousState = previousState;
		this.alphabet = alphabet;
		this.nextState = nextState;
	}

	public State getPreviousState() {
		return previousState;
	}

	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public void setTransition(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

}
