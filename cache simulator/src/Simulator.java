import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Simulator {
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	static ArrayList<Cache> allCaches = new ArrayList<Cache>();
	static ArrayList<Cache> allCaches2 = new ArrayList<Cache>();
	static int memory[] = new int[1 << 15];
	static int cyclesCounter = 0;
	static int PC;
	static int pc;
	static int[] registers = new int[8];
	static HashMap<Integer, String> insts = new HashMap<Integer, String>();
	static int memoryCycle;

	public void init() throws NumberFormatException, IOException{
		int numOfCaches;
		String line = "";
		registers[0] = 0;
		System.out.println("Please enter the number of caches you want: ");
		numOfCaches = Integer.parseInt(br.readLine());
		int i = 0;
		while (i < numOfCaches) {
			System.out.println("Please fill the information of cache no."
					+ (i + 1));
			System.out.println("Cache size: ");
			line = br.readLine();
			int S;
			if (line != null && isNumber(line)) {
				S = Integer.parseInt(line);
			} else {
				System.out
						.println("Please run the program again and enter a valid number,"
								+ "resuming the program will result in wrong values");
				S = 0;
			}
			System.out.println("Line size: ");
			line = br.readLine();
			int L;
			if (line != null && isNumber(line)) {
				L = Integer.parseInt(line);
			} else {
				System.out
						.println("Please run the program again and enter a valid number,"
								+ " resuming the program will result in wrong values");
				L = 0;
			}
			System.out.println("Number of associativity ways: ");
			line = br.readLine();
			int m;
			if (line != null && isNumber(line)) {
				m = Integer.parseInt(line);
			} else {
				System.out
						.println("Please run the program again and enter a valid number, "
								+ "resuming the program will result in wrong values");
				m = 0;
			}
			System.out.println("Allocation(write true or false): ");
			line = br.readLine();
			boolean allocate;
			if (line.equals("true")) {
				allocate = line.equals("true");
			} 
			else if (line.equals("false")){
				allocate = line.equals("true");
			}
			else {
				System.out
						.println("Please run the program again and enter true or false, "
								+ "resuming the program will result in wrong values");
				allocate = false;
			}
			System.out.println("Through(write true or false): ");
			line = br.readLine();
			boolean through;
			if (line.equals("true")) {
				through = line.equals("true");
			} 
			else if (line.equals("false")){
				through = line.equals("true");
			}
			else {
				System.out
						.println("Please run the program again and enter true or false, "
								+ "resuming the program will result in wrong values");
				through = false;
			}
			System.out.println("Number of cycles in cache access: ");
			line = br.readLine();
			int cacheCycle;
			if (line != null && isNumber(line)) {
				cacheCycle = Integer.parseInt(line);
			} else {
				System.out
						.println("Please run the program again and enter a valid number, "
								+ "resuming the program will result in wrong values");
				cacheCycle = 0;
			}
		
			// we need to add the memory initialization
			Cache c = new Cache(S, L, m, cacheCycle, allocate, through, memory);
			allCaches.add(c);

			Cache c2 = new Cache(S, L, m, cacheCycle, allocate, through, memory);
			allCaches2.add(c2);

			i++;
		}
		
		System.out.println("Number of cycles in memory access: ");
		line = br.readLine();
		if (line != null && isNumber(line)) {
			memoryCycle = Integer.parseInt(line);
		} else {
			System.out
					.println("Please run the program again and enter a valid number, "
							+ "resuming the program will result in wrong values");
			memoryCycle = 0;
		}
		System.out.println("Size of Reorder buffer: ");
		line = br.readLine();
		if(line != null && isNumber(line)) {
			ROB rob = new ROB(Integer.parseInt(line));
		}
		else {
			System.out.println("please run the program agian and enter a number");
		}
		System.out.println("Please enter the size of the Reservaton Station");
		line = br.readLine();
		String [] operations = new String [Integer.parseInt(line)];
		System.out.println("Enter Reservation station, # of reservation station, and cycles needed to execute: ");
		for(int j = 0; j < Integer.parseInt(line); j++) {
			if(line != null) {
				for (int x = 0; x < Integer.parseInt(line.split(",")[1]); x++) {
					operations[j] = line.split(",")[0] + x;
				}
			}
			line = br.readLine();
		}
		ReservationStation reservationStation = new ReservationStation(operations);
	}
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Simulator s = new Simulator();
		s.simulate();
			}
	
	public void printOutput() {
		double[] hitRate = new double[allCaches.size()];
		double[] missRate = new double[allCaches.size()];
		int numberOfCaches = allCaches.size();
		for(int i = 0; i < numberOfCaches; i++) {
			int h1 = allCaches.get(i).hit;
			int m1 = allCaches.get(i).miss;
			int h2 = allCaches2.get(i).hit;
			int m2 = allCaches2.get(i).miss;
			int den = h1 + m1 + h2+ m2;
			if(den == 0) {
				hitRate[i] = missRate[i] = 0.0;
				continue;
			}
			double th = 1.0*(h1+h2)/den;
			hitRate[i] = th;
			missRate[i] = 1.0 - th;
		}
		double Amat = 0.0;
		double[] penalty = new double[numberOfCaches];
		penalty[numberOfCaches - 1] = (double) memoryCycle;
		for(int i = numberOfCaches-2; i >= 0; i--) {
			penalty[i] = allCaches.get(i).cycle + penalty[i+1]*missRate[i];
		}
		Amat = penalty[0];
		if(numberOfCaches == 1) {
						Amat = allCaches.get(0).cycle + missRate[0]*memoryCycle;
					}
		System.out.println("Here is the hit rate for all Cache levels :");
		for(int i = 0; i < numberOfCaches; i++) {
		System.out.println("Hit Rate of L" + (i+1) + " : " + hitRate[i]);
		}
		System.out.println("AMAT of the program : " + Amat);
		for(int i = 0; i < numberOfCaches; i++) {
			Cache ld = allCaches.get(i);
			Cache li = allCaches2.get(i);
		}

	}

	public static boolean isValidProgram() throws NumberFormatException,
			IOException {
		File input = new File("test1.txt");
		BufferedReader brFile = new BufferedReader(new FileReader(input));
		pc = Integer.parseInt(brFile.readLine());
		PC = pc;
		String s;
		while ((s = brFile.readLine())!= null) {
			String[] cmd = s.split(",");
			if (cmd.length > 3) {
				return false;
			}
			if (cmd.length == 1) {
				String[] ret = cmd[0].split(" ");
				if (ret.length != 2) {
					return false;
				}
				if (!ret[0].equals("RET")) {
					return false;
				}
				if (!isRegister(ret[1])) {
					return false;
				}
				if (isNumber(ret[1])) {
					int imm = Integer.parseInt(cmd[1]);
					if (imm < -64 && imm > 63) {
						return false;
					}
				}

			}
			if (cmd.length == 2) {
				String[] jump = cmd[0].split(" ");
				if (jump.length != 2) {
					return false;
				}
				int j = -1;
				
				if (jump[0].equals("JMP")) {
					j = 0;
				}

				if (jump[0].equals("JALR")) {
					j = 1;
				}
				if (j != 1 && j != 0) {
					return false;
				}

				if (!isNumber(cmd[1]) && j == 0) {
					return false;
				}

				if (!isRegister(cmd[1]) && j == 1) {
					return false;
				}
				if (!isRegister(jump[1])) {
					return false;
				}
				if (isNumber(cmd[1])) {
					int imm = Integer.parseInt(cmd[1]);
					if (imm < -64 && imm > 63) {
						return false;
					}
				}

			}
			if (cmd.length == 3) {
				String[] prefix = cmd[0].split(" ");
				if (prefix.length != 2) {
					return false;
				}
				int i = -1;
				if (prefix[0].equals("SW") || prefix[0].equals("BEQ")
						|| prefix[0].equals("ADDI")) {
					i = 0;
				}
				if (prefix[0].equals("ADD") || prefix[0].equals("SUB")
						|| prefix[0].equals("NAND") || prefix[0].equals("MUL")) {
					i = 1;
				}
				if (!isRegister(prefix[1])) {
					return false;
				}
				if (!isRegister(cmd[1])) {
					return false;
				}
				if (!isNumber(cmd[2]) && i == 0) {
					return false;
				}
				if (!isRegister(cmd[2]) && i == 1) {
					return false;
				}
				if (isNumber(cmd[1])) {
					int imm = Integer.parseInt(cmd[1]);
					if (imm < -64 && imm > 63) {
						return false;
					}
				}
				
			}
			insts.put(pc, s);
			pc++;
		}

		return true;
	}

	public static boolean isRegister(String r) {
		if (r.equals("R0") || r.equals("R1") || r.equals("R2") || r.equals("R3")
				|| r.equals("R4") || r.equals("R5") || r.equals("R6")
				|| r.equals("R7"))
			return true;
		else
			return false;

	}

	public static boolean isNumber(String x) {
		try {
			Integer.parseInt(x);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public void simulateInstruction() {
		String instruction = insts.get(pc);
		String operation = instruction.split(",")[0].split(" ")[0];
		int i = 0;
		int operand1 = 0;
		int operand2 = 0;
		boolean isFound = false;
		while (i < allCaches.size()) {
			if (allCaches.get(i).findInstruction(pc, cyclesCounter)) {
				isFound = true;
				break;
			} else {
				i++;
			}
		}
		if (!isFound) {
			for (int j = 0; j < allCaches.size(); j++) {
				if (!allCaches.get(j).addInstruction(pc, cyclesCounter)) {
					allCaches.get(j)
							.addAndReplaceInstruction(pc, cyclesCounter);
				}
			}
		}
		if (operation.equals("ADD")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = registers[Integer.parseInt(instruction.split(",")[2]
					.charAt(1) + "")];
			registers[Integer.parseInt(instruction.split(",")[0].split(" ")[1]
					.charAt(1) + "")] = operand1 + operand2;
		}
		if (operation.equals("SUB")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = registers[Integer.parseInt(instruction.split(",")[2]
					.charAt(1) + "")];
			registers[Integer.parseInt(instruction.split(",")[0].split(" ")[1]
					.charAt(1) + "")] = operand2 - operand1;
		}
		if (operation.equals("ADDI")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = Integer.parseInt(instruction.split(",")[2]);
			registers[Integer.parseInt(instruction.split(",")[0].split(" ")[1]
					.charAt(1) + "")] = operand1 + operand2;
		}
		if (operation.equals("MUL")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = registers[Integer.parseInt(instruction.split(",")[2]
					.charAt(1) + "")];
			registers[Integer.parseInt(instruction.split(",")[0].split(" ")[1]
					.charAt(1) + "")] = operand1 * operand2;
		}
		if (operation.equals("NAND")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = registers[Integer.parseInt(instruction.split(",")[2]
					.charAt(1) + "")];
			registers[Integer.parseInt(instruction.split(",")[0].split(" ")[1]
					.charAt(1) + "")] = ~(operand1 & operand2);
		}
		if (operation.equals("LW")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = Integer.parseInt(instruction.split(",")[2]);
			int address = operand1 + operand2;
			Boolean isFoundData = false;
			for (int j = 0; j < allCaches2.size(); j++) {
				if (allCaches2.get(j).findData(address, cyclesCounter)) {
					registers[Integer.parseInt(instruction.split(",")[0]
							.split(" ")[1].charAt(1) + "")] = allCaches.get(i)
							.getData(address, cyclesCounter);
					isFound = true;
					break;
				}
			}
			if (!isFoundData) {
				for (int x = 0; x < allCaches2.size(); x++) {
					if (!allCaches2.get(x).readData(address, cyclesCounter)) {
						allCaches2.get(x).readAndReplaceData(address,
								cyclesCounter);
					}
				}
				registers[Integer
						.parseInt(instruction.split(",")[0].split(" ")[1]
								.charAt(1) + "")] = memory[address];
			}
		}
		if (operation.equals("SW")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			operand2 = Integer.parseInt(instruction.split(",")[2]);
			int address = operand1 + operand2;
			Boolean isFoundData = false;
			int toBeSavedInMem = registers[Integer.parseInt(instruction
					.split(",")[0].split(" ")[1].charAt(1) + "")];
			for (int j = 0; j < allCaches2.size(); j++) {
				if (allCaches2.get(j).findData(address, cyclesCounter)) {
					isFoundData = true;
					allCaches2.get(j).writeDataOnHit(address, toBeSavedInMem,
							cyclesCounter);
					break;
				}
			}
			if (!isFoundData) {
				for (int j2 = 0; j2 < allCaches.size(); j2++) {
					if (!allCaches2.get(j2).writeDataOnMiss(address,
							toBeSavedInMem, cyclesCounter)) {
						allCaches2.get(j2).WriteDataAndReplace(address,
								toBeSavedInMem, cyclesCounter);
					}
				}
			}
		}
		if (operation.equals("JMP")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[0]
					.split(" ")[1].charAt(1) + "")];
			operand2 = Integer.parseInt(instruction.split(",")[1]);
			pc = pc + operand1 + operand2;
		}
		if (operation.equals("BEQ")) {
			operand1 = registers[Integer.parseInt(instruction.split(",")[0]
					.split(" ")[1].charAt(1) + "")];
			operand2 = registers[Integer.parseInt(instruction.split(",")[1]
					.charAt(1) + "")];
			if (operand1 == operand2) {
				pc = pc + Integer.parseInt(instruction.split(",")[2]);
			}
		}
		if (operation.equals("JALR")) {
			registers[Integer.parseInt(instruction.split(",")[0].split(" ")[1]
					.charAt(1) + "")] = pc + 1;
			pc = registers[Integer.parseInt(instruction.split(",")[1].charAt(1)
					+ "")] - 1;
		}
		if (operation.equals("RET")) {
			pc = registers[Integer.parseInt(instruction.split(" ")[1].charAt(1)
					+ "")] - 1;
			
		}
		pc++;
		cyclesCounter++;
		registers[0] = 0;
	}
	public void simulate() throws NumberFormatException, IOException {
		init();
		if (isValidProgram()){
			pc = PC;
			while (insts.get(pc) != null) {
				simulateInstruction();
			}
			printOutput();
		}
		else
			System.out.println("Invalid Program , Please Check your file and fix it");
	}
	public void assure() {
		System.out.println("Registers Values : ");
		for(int i = 0; i < 8; i++) {
			System.out.println("R"+ i + " : "+ registers[i]);
		}
		System.out.println("Memory Values : ");
		for(int i = 0; i < memory.length; i++) {
			if(memory[i] != 0) {
				System.out.println("MEM["+i+"] = "+ memory[i]);
			}
		}
	}
}
