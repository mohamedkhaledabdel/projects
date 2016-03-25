package Lab2;

public class Label {
	State state;
	String lexeme;

	public Label(State state, String lexeme) {
		super();
		this.state = state;
		this.lexeme = lexeme;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

}
