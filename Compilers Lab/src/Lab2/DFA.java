package Lab2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DFA {
	static ArrayList<State> states;
	static ArrayList<Alphabet> alphabet;
	static ArrayList<Transition> transitions;
	static State startState;
	static ArrayList<State> acceptStates;

	public DFA(ArrayList<State> states, ArrayList<Alphabet> alphabet,
			ArrayList<Transition> transitions, State startState,
			ArrayList<State> acceptStates) {
		super();
		this.states = states;
		this.alphabet = alphabet;
		this.transitions = transitions;
		this.startState = startState;
		this.acceptStates = acceptStates;
	}

	public static DFA readFile() throws IOException {
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<Alphabet> alphabet = new ArrayList<Alphabet>();
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		ArrayList<State> acceptStates = new ArrayList<State>();
		State startState = new State();
		FileReader in = new FileReader("fallbackdfa.in");
		BufferedReader br = new BufferedReader(in);
		String line = "";
		line = br.readLine();
		line = br.readLine();
		while (!line.equals("#alphabet")) {
			String[] splittedLine = line.trim().split(",");
			State s;
			for (int i = 0; i < splittedLine.length; i++) {
				s = new State();
				s.setNumber(splittedLine[i]);
				states.add(s);
				// System.out.println(states.get(i).getNumber());
			}
			line = br.readLine();
			// System.out.println("-------------------");
		}
		line = br.readLine();
		while (!line.equals("#transitions")) {
			String[] splittedLine = line.trim().split(",");
			Alphabet a;
			for (int i = 0; i < splittedLine.length; i++) {
				a = new Alphabet(splittedLine[i]);
				alphabet.add(a);
				// System.out.println(alphabet.get(i).getAlphabet());
			}
			line = br.readLine();
			// System.out.println("-------------------");
		}
		line = br.readLine();
		while (!line.equals("#start state")) {
			String[] splittedLine = line.trim().split(",");
			State previousState = new State();
			State nextState = new State();
			Alphabet a = new Alphabet(splittedLine[1]);
			previousState.setNumber(splittedLine[0]);
			nextState.setNumber(splittedLine[2]);
			Transition t = new Transition(previousState, a, nextState);
			transitions.add(t);
			// System.out.println(transitions.size());
			// System.out.println(line);
			// System.out.println(transitions.get(0).previousState.getNumber());
			line = br.readLine();
			// System.out.println("-------------------");
		}
		line = br.readLine();
		startState.setNumber(line);
		line = br.readLine();
		line = br.readLine();
		while (!line.equals("#label")) {
			String[] splittedLine = line.trim().split(",");
			State s;
			for (int i = 0; i < splittedLine.length; i++) {
				s = new State();
				s.setNumber(splittedLine[i]);
				acceptStates.add(s);
				// System.out.println(acceptStates.get(i).getNumber());
			}
			line = br.readLine();
			// System.out.println("-------------------");
		}
		DFA d1 = new DFA(states, alphabet, transitions, startState,
				acceptStates);
		return d1;
	}

	public static String getNextState(String s) {
		String nextState = "";
		for (int i = 0; i < DFA.transitions.size(); i++) {
			if ((DFA.transitions.get(i).previousState.getNumber() + "," + DFA.transitions
					.get(i).alphabet.getAlphabet()).matches(s)) {
				String ss = DFA.transitions.get(i).previousState.getNumber()
						+ "," + DFA.transitions.get(i).alphabet.getAlphabet();
				nextState = DFA.transitions.get(i).getNextState().getNumber();
				break;
			}
		}
		return nextState;
	}

	public static boolean isValidAlphabet(String s) {
		boolean isValid = false;
		for (int i = 0; i < DFA.alphabet.size(); i++) {
			if ((s.charAt(0) + "").equals(alphabet.get(i).getAlphabet())) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	public static boolean contains(State s) {
		boolean contains = false;
		for (int i = 0; i < DFA.acceptStates.size(); i++) {
			if (s.getNumber().equals(DFA.acceptStates.get(i).getNumber())) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	public static void main(String[] args) throws IOException {
	}
}
