package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FirstAndFollowSets {
	static HashMap<String, Object[]> rules = new HashMap<String, Object[]>();
	static String[] splittedBody;
	static ArrayList<String> terminals = new ArrayList<String>();
	static ArrayList<String> nonTerminals = new ArrayList<String>();
	static HashMap<String, ArrayList<String>> firstSet = new HashMap<String, ArrayList<String>>();
	static HashMap<String, ArrayList<String>> FollowSet = new HashMap<String, ArrayList<String>>();

	public static void readFile() throws IOException {
		FileReader in = new FileReader("Sample5.in");
		BufferedReader br = new BufferedReader(in);
		String[] splitNonTerminals = br.readLine().split(",");
		for (int i = 0; i < splitNonTerminals.length; i++) {
			nonTerminals.add(splitNonTerminals[i]);
			FollowSet.put(splitNonTerminals[i], null);
		}
		String[] splitTerminals = br.readLine().split(",");
		for (int i = 0; i < splitTerminals.length; i++) {
			terminals.add(splitTerminals[i]);
			ArrayList<String> terminalItself = new ArrayList<String>();
			terminalItself.add(splitTerminals[i]);
			firstSet.put(splitTerminals[i], terminalItself);
		}
		terminals.add("!");
		String head = br.readLine();
		String body = br.readLine();
		ArrayList<String> dollarSign = new ArrayList<String>();
		dollarSign.add("$");
		FollowSet.put(head, dollarSign);
		while (head != null) {
			splittedBody = body.split("\\|");
			rules.put(head, splittedBody);
			firstSet.put(head, null);
			head = br.readLine();
			body = br.readLine();
		}
	}

	public static ArrayList<String> returnFirstFromCons(Object[] body) {
		ArrayList<String> foundTerminals = new ArrayList<String>();
		for (int i = 0; i < body.length; i++) {
			if (terminals.contains(body[i].toString().charAt(0) + "")) {
				foundTerminals.add((body[i].toString().charAt(0) + ""));
			} else {
				if (terminals.contains(body[i].toString())) {
					foundTerminals.add(body[i].toString());
				}
			}
		}
		return foundTerminals;
	}

	public static ArrayList<String> returnFirstFromVar(String head) {
		ArrayList<String> availableTerminals = new ArrayList<String>();
		availableTerminals = returnFirstFromCons(rules.get(head));
		if (availableTerminals.isEmpty()) {
			for (int i = 0; i < rules.get(head).length; i++) {
				availableTerminals = returnFirstFromVar((rules.get(head)[i]
						.toString().charAt(0) + ""));
			}
		}
		return availableTerminals;
	}

	public static ArrayList<String> handeEpsilon(String head) {
		ArrayList<String> availableTerminals = new ArrayList<String>();
		availableTerminals = returnFirstFromVar(head);
		ArrayList<String> extra = new ArrayList<String>();
		if (availableTerminals.contains("!") && availableTerminals.size() == 1) {
			for (int i = 0; i < rules.get(head).length - 1; i++) {
				extra = returnFirstFromVar((rules.get(head)[i].toString()
						.charAt(1) + ""));
				if (extra.contains("!")) {
					availableTerminals.addAll(handeEpsilon(rules.get(head)[i]
							.toString().charAt(2) + ""));
					availableTerminals.remove("!");
				}
				availableTerminals.addAll(extra);
				availableTerminals.remove("!");
			}
		}
		return availableTerminals;
	}

	public static void findFirst() {
		Set<String> keySet = firstSet.keySet();
		for (int i = 0; i < keySet.size(); i++) {
			if (!terminals.contains((String) keySet.toArray()[i]))
				firstSet.put((String) keySet.toArray()[i],
						handeEpsilon((String) keySet.toArray()[i]));
		}
	}

	// public static void removeRed() {
	// for (int i = 0; i < nonTerminals.size(); i++) {
	// for (int j = 0; j < rules.get(nonTerminals.get(i)).length; j++) {
	// for (int j2 = 0; j2 < rules.get(nonTerminals.get(i)).length; j2++) {
	// if(rules.get(nonTerminals.get(i))[j].equals(rules.get(nonTerminals.get(i))[j2]))
	// {
	// rules.get(nonTerminals.get(i))[j2] = "";
	// }
	// }
	// }
	// }
	// }

	public static void printFirstSet() throws FileNotFoundException,
			UnsupportedEncodingException {
		Set<String> keySet = firstSet.keySet();
		String out2 = "";
		PrintWriter writer = new PrintWriter("Lab5First.out", "UTF-8");

		for (int i = 0; i < keySet.size(); i++) {
			String out = "First(" + (keySet.toArray()[i]) + "):";
			for (int j = 0; j < firstSet.get(keySet.toArray()[i]).size(); j++) {
				out2 = out2 + firstSet.get(keySet.toArray()[i]).get(j) + ", ";
			}
			System.out.println(out + " [" + out2 + "]");
			writer.println(out + " [" + out2 + "]");
			out2 = "";
			System.out.println();
			System.out.println("-------");
		}
		writer.close();
	}

	public static void computeFollowOnce(String body) {
		ArrayList<String> follow = new ArrayList<String>();
		for (int i = 0; i < body.length() - 1; i++) {
			if (nonTerminals.contains(body.charAt(i) + "")) {
				ArrayList<String> previous = new ArrayList<String>();
				follow = firstSet.get(body.charAt(i + 1) + "");
				previous = FollowSet.get(body.charAt(i) + "");
				if (previous != null) {
					follow.addAll(previous);
				}
				FollowSet.put(body.charAt(i) + "", follow);
			}
		}
	}

	public static void computeFollowComplete() {
		Set<String> keySet = FollowSet.keySet();
		for (int i = 0; i < keySet.size(); i++) {
			String[] body = (String[]) rules.get(keySet.toArray()[i]);
			for (int j = 0; j < body.length; j++) {
				if (!body[j].equals("!")) {
					computeFollowOnce(body[j]);
				}
			}
		}
	}

	public static void printFollowSet() throws FileNotFoundException,
			UnsupportedEncodingException {
		Set<String> keySet = FollowSet.keySet();
		String out2 = "";
		PrintWriter writer = new PrintWriter("Lab5Follow.out", "UTF-8");
		for (int i = 0; i < keySet.size(); i++) {
			String out = "Follow(" + (keySet.toArray()[i]) + "):";
			if (FollowSet.get(keySet.toArray()[i]) != null) {
				for (int j = 0; j < FollowSet.get(keySet.toArray()[i]).size(); j++) {
					out2 = out2 + FollowSet.get(keySet.toArray()[i]).get(j)
							+ ", ";
				}
			}
			System.out.println(out + " [" + out2 + "]");
			writer.println(out + " [" + out2 + "]");
			out2 = "";
			System.out.println();
			System.out.println("-------");
		}
		writer.close();
	}

	
	public static void main(String[] args) throws IOException {
		readFile();
		// System.out.println(handeEpsilon("F"));
		// System.out.println(firstSet.keySet());
		findFirst();
		printFirstSet();
		//computeFollowComplete();
		//printFollowSet();
	}

}
