package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import lab4.Body;
import lab4.Head;

public class LeftFactor {
	static String[] splittedBody;
	static HashMap<String, Object[]> rules = new HashMap<String, Object[]>();

	public static void readFile() throws IOException {
		FileReader in = new FileReader("Sample3.in");
		BufferedReader br = new BufferedReader(in);
		String head = "";
		head = br.readLine();
		String body = br.readLine();
		//System.out.println(body);
		splittedBody = body.split("\\|");
		rules.put(head, splittedBody);
	}

	public static String longestPrefixHelper(String[] strings) {
		String toBeReturned = "";
		if (strings.length == 1) {
			toBeReturned = "";
		} else {
			toBeReturned = longestCommonPrefix(strings);
			if (toBeReturned.isEmpty()) {
				String[] newArray = Arrays.copyOfRange(strings, 0,
						strings.length - 1);
				toBeReturned = longestPrefixHelper(newArray);
			}
		}
		return toBeReturned;
	}

	public static String longestCommonPrefix(String[] strings) {
		if (strings.length == 1) {
			return "";
		}
		String toBeReturned = "";
		for (int prefixLen = 0; prefixLen < strings[0].length(); prefixLen++) {
			char c = strings[0].charAt(prefixLen);
			for (int i = 1; i < strings.length; i++) {
				if (prefixLen >= strings[i].length()
						|| strings[i].charAt(prefixLen) != c) {
					return strings[i].substring(0, prefixLen);
				}
			}
		}
		return strings[0];
	}

	public static void solveHelper() {
		int currentSizeOfKeySet = rules.keySet().size();
		solve();
		if(currentSizeOfKeySet != rules.keySet().size()) {
			solveHelper();
		}

	}
	
	public static void solve() {
		Set<String> keySet = rules.keySet();
		int cut = 0;
		int sum = 0;
		for (int i = 0; i < keySet.size(); i++) {
			String[] body = (String[]) rules.get(keySet.toArray()[0]);
			String head = keySet.toArray()[0].toString();
			//System.out.println(head);
			String commonPrefix = longestPrefixHelper(body);
			if (!commonPrefix.isEmpty()) {
				String newHead = head + "'";
				ArrayList<String> newBody = new ArrayList<String>();
				for (int j = 0; j < body.length; j++) {
					if (((String) body[j]).startsWith(commonPrefix)) {
						if (((String) body[j]).equals(commonPrefix)) {
							newBody.add("!");
							body[j] = "";
							cut = j + 1;
							break;
						} else {
							if(sum == 0) {
							newBody.add(((String) body[j])
									.substring(commonPrefix.length()));
							body[j] = ((String) body[j]).substring(0,
									commonPrefix.length()) + newHead;
							sum++;
							}
							else{
								newBody.add(((String) body[j])
										.substring(commonPrefix.length()));
								body[j] = "";
							}
							
						}
						
					} else {
						break;
					}
				}
				rules.put(head, body);
				rules.put(newHead, fromListToArray(newBody));
				keySet = rules.keySet();
				sum = 0;
			}
		}
	}

	public static void printFromArray(String[] arrray) {
		for (int i = 0; i < arrray.length; i++) {
			System.out.println(arrray[i]);
		}
	}
	
	public static String [] fromListToArray(ArrayList<String> conv) {
		String [] list = new String[conv.size()];
		for (int i = 0; i < conv.size(); i++) {
			list[i] = conv.get(i);
		}
		return list;
	}
	
	public static void printFactor() throws FileNotFoundException, UnsupportedEncodingException{
		Set<String> keySet = rules.keySet();
		String head = "";
		String out = "";
		PrintWriter writer = new PrintWriter("Lab5.out", "UTF-8");
		for (int i = 0; i < keySet.size(); i++) {
			head = (String) keySet.toArray()[i];
			for (int j = 0; j < rules.get(keySet.toArray()[i]).length; j++) {
				String [] body = (String[]) rules.get(keySet.toArray()[i]);
				if(body[j]!="")
					out = out + body[j] + ",";
			}
			System.out.println(head + "->" + "[" + out + "]");
			writer.println(head + "->" + "[" + out + "]");
			head = "";
			out = "";
		}
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		readFile();
		solve();
		printFactor();
		String[] tst = {"","","cy","cx"};
		//System.out.println(longestPrefixHelper(tst));
	}

}
