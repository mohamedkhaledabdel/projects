package Lab2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class MainClass {
	static ArrayList<Label> labels = new ArrayList<Label>();
	static ArrayList<RegularDefinition> rds = new ArrayList<RegularDefinition>();
	static DFA d1;
	static Stack<State> dfaStack = new Stack<State>();
	static int leftPointer = 0;
	static int rightPointer = 0;

	public static String getFirstAcceptState(String s) {
		String firstAcceptState = "";
		for (int i = 0; i < MainClass.rds.size(); i++) {
			if (s.contains(MainClass.rds.get(i).getLexicalCategory())) {
				firstAcceptState = MainClass.rds.get(i).getLexicalCategory();
				break;
			}
		}
		return firstAcceptState;
	}

	public static void fillStack(String inputString) {
		dfaStack.push(d1.startState);
		for (int i = 0; i < inputString.length(); i++) {
			if ((d1.getNextState(dfaStack.peek().getNumber() + ","
					+ inputString.charAt(i))).isEmpty()) {
				State toBePushed = new State();
				toBePushed.setNumber("PHI");
				dfaStack.push(toBePushed);
				// break;
				rightPointer++;
			} else {
				State toBePushed = new State();
				toBePushed.setNumber(d1.getNextState(dfaStack.peek()
						.getNumber() + "," + inputString.charAt(i)));
				dfaStack.push(toBePushed);
				rightPointer++;
			}
		}
	}

	public static String output(String inputString) {
		String out = "";
		if (!inputString.isEmpty()) {
			if (d1.isValidAlphabet(inputString)) {
				fillStack(inputString);
				while (!dfaStack.isEmpty()) {
					if (!d1.contains(dfaStack.peek())) {
						rightPointer--;
						dfaStack.pop();
					} else {
						out = getFirstAcceptState(dfaStack.peek().getNumber())
								+ ","
								+ inputString.substring(leftPointer,
										rightPointer) + " ";
						dfaStack.clear();
						leftPointer = rightPointer;
						inputString = inputString.substring(leftPointer);
						leftPointer = 0;
						rightPointer = 0;
						out = out + output(inputString);
					}

				}
			} else {
				out = "error, there is an input lexeme entered that is not in the language";
			}
		} else {
			out = "";
		}
		return out;
	}

	public static void readFile() throws IOException {
		FileReader in = new FileReader("fallbackdfa.in");
		BufferedReader br = new BufferedReader(in);
		String line = "";
		RegularDefinition rd;
		Label l;
		String[] lineSplitted;
		line = br.readLine();
		while (!line.contains("#label")) {
			line = br.readLine();
		}
		line = br.readLine();
		while (!line.contains("#regulardefinition")) {
			lineSplitted = line.trim().split(",");
			l = new Label(new State(lineSplitted[0]), lineSplitted[1]);
			labels.add(l);
			line = br.readLine();
		}
		line = br.readLine();
		while (line != null) {
			lineSplitted = line.trim().split(",");
			rd = new RegularDefinition(lineSplitted[0], lineSplitted[1]);
			rds.add(rd);
			line = br.readLine();
		}

	}

	public static void main(String[] args) throws IOException {
		d1 = DFA.readFile();
		readFile();
		FileReader in = new FileReader("Lab2.in");
		BufferedReader br = new BufferedReader(in);
		String line = "";
		int i = 1;
		line = br.readLine();
		line = br.readLine();
		String inputString = "";
		PrintWriter writer = new PrintWriter("Lab2.out", "UTF-8");
		while (line != null) {
			inputString = line;
			writer.println("output" + i + ": " + output(inputString));
			// System.out.println("output" + i + ": " + output(inputString));
			line = br.readLine();
			line = br.readLine();
			i++;
		}
		writer.close();
		// System.out.println(rightPointer);
		// System.out.println(output("ababbb"));
	}
}
