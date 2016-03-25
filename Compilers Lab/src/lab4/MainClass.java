package lab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainClass {
	static ArrayList<Rule> rules = new ArrayList<Rule>();

	public static void readFile() throws IOException {
		FileReader in = new FileReader("Sample5.in");
		BufferedReader br = new BufferedReader(in);
		String line = "";
		Head h;
		line = br.readLine();
		ArrayList<Body> body;
		String[] splittedLine;
		while (line != null) {
			h = new Head(line);
			line = br.readLine();
			splittedLine = line.split("\\|");
			Body b;
			body = new ArrayList<Body>();
			for (int i = 0; i < splittedLine.length; i++) {
				String s = splittedLine[i];
				b = new Body(s);
				body.add(b);
			}
			Rule r = new Rule(h, body);
			rules.add(r);
			line = br.readLine();
		}

	}

	public static void solve() {
		Rule rule;
		Rule newRule;
		for (int j = 0; j < rules.size(); j++) {
			rule = new Rule();
			rule.setRule(rules.get(j));
			for (int i = 0; i < rule.bodyOfRule.size(); i++) {
				if ((rule.bodyOfRule.get(i).getBody()).startsWith(rule
						.getHeadOfRule().getHead())) {
					Body b = new Body(rule.bodyOfRule.get(i + 1).getBody()
							+ rule.getHeadOfRule().getHead() + "'");
					String oldHead = rules.get(j).getHeadOfRule().getHead();
					Body newBody = new Body(rule.bodyOfRule.get(i).getBody()
							.replaceFirst(oldHead, "")
							+ oldHead + "'");
					String body = rules.get(j).bodyOfRule.get(i).getBody();
					rule.bodyOfRule.add(i + 1, b);
					rule.bodyOfRule.remove(i);
					rule.bodyOfRule.remove(i + 1);
					String newHead = oldHead + "'";
					ArrayList<Body> newRuleBody = new ArrayList<Body>();
					newRuleBody.add(newBody);
					newRuleBody.add(new Body("!"));
					newRule = new Rule(new Head(newHead), newRuleBody);
					if (containsHead(rules, oldHead + "'")) {
						for (int k = 0; k < rules.size(); k++) {
							if (rules.get(k).getHeadOfRule().getHead()
									.equals(oldHead + "'")) {
								rules.get(k).bodyOfRule.set(rules.get(k).bodyOfRule.size()-1,newBody);
								rules.get(k).bodyOfRule.add(new Body("!"));
							}
						}
					} else {
						rules.add(newRule);
					}

				}
			}
		}
	}

	public static void solveFirst() {
		Rule rule;
		Rule toBeModified;
		ArrayList<Body> addedBody = new ArrayList<Body>();
		for (int i = 0; i < rules.size(); i++) {
			rule = new Rule();
			rule.setRule(rules.get(i));
			for (int j = i + 1; j < rules.size(); j++) {
				for (int j2 = 0; j2 < rules.get(j).bodyOfRule.size(); j2++) {
					if (rules.get(j).bodyOfRule.get(j2).getBody()
							.contains(rule.getHeadOfRule().getHead())) {
						for (int k = 0; k < rule.bodyOfRule.size(); k++) {
							Body newBody = new Body(rules.get(j).bodyOfRule
									.get(j2)
									.getBody()
									.replace(rule.getHeadOfRule().getHead(),
											rule.bodyOfRule.get(k).getBody()));
							addedBody.add(newBody);
						}
						rules.get(j).bodyOfRule.remove(j2);
						for (int z = 0; z < addedBody.size(); z++) {
							rules.get(j).bodyOfRule.add(addedBody.get(z));
						}
						addedBody.clear();
					}
				}
			}
		}

	}

	public static boolean containsHead(ArrayList<Rule> rules, String head) {
		boolean contains = false;
		for (int i = 0; i < rules.size(); i++) {
			if (rules.get(i).getHeadOfRule().getHead().equals(head)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	public static void main(String[] args) throws IOException {
		readFile();
		solveFirst();
		solve();
		System.out.println(rules.get(1).bodyOfRule.size());
		String body = "";
		// System.out.println(rules.get(0).bodyOfRule);
		PrintWriter writer = new PrintWriter("Lab4.out", "UTF-8");
		for (int i = 0; i < rules.size(); i++) {
			String head = rules.get(i).getHeadOfRule().getHead();
			for (int j = 0; j < rules.get(i).bodyOfRule.size(); j++) {
				if (j < rules.get(i).bodyOfRule.size() - 1) {
					body = body + rules.get(i).bodyOfRule.get(j).getBody()
							+ ",";
				} else {
					body = body + rules.get(i).bodyOfRule.get(j).getBody();

				}
			}
			writer.println(head + " -> " + body);
			body = "";
		}
		writer.close();
	}
}
