public class ReservationStation {
	static ReservationStationRow[] rs;
	static int[] registersList = new int[8];

	public ReservationStation(String[] operations) {
		for (int i = 0; i < operations.length; i++) {
			this.rs[i] = new ReservationStationRow(operations[i], false, " ",
					-1, -1, -1, -1, -1, -1);
		}
	}

	public static void addInstruction(String instruction, int tail) {
		boolean isFree = false;
		String inst = instruction.split(",")[0].split(" ")[0];
		int operand1 = 0;
		int operand2 = 0;
		int destination = 0;
		int index = 0;
		for (int i = 0; i < rs.length; i++) {
			if (rs[i].name == inst && !rs[i].busy) {
				isFree = true;
				index = i;
				break;
			}
		}
		// we need to set operand 1 and 2, and destination in each if condtion
		if (isFree) {
			if (instruction.equals("ADD") || instruction.equals("SUB")
					|| instruction.equals("NAND") || instruction.equals("MUL")) {
				operand1 = Integer.parseInt(instruction.split(",")[1].charAt(1)
						+ "");
				operand2 = Integer.parseInt(instruction.split(",")[2].charAt(1)
						+ "");
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				if (registersList[operand2] == -1) {
					rs[index].vk = operand2;
					registersList[operand2] = tail;
				} else {
					rs[index].qk = operand2;
				}
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("SUBI") || instruction.equals("ADDI")) {
				operand1 = Integer.parseInt(instruction.split(",")[1].charAt(1)
						+ "");
				operand2 = Integer.parseInt(instruction.split(",")[2]);
				rs[index].op = instruction;
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				rs[index].vk = operand2;
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("LW")) {
				operand1 = Integer.parseInt(instruction.split(",")[1].charAt(1)
						+ "");
				operand2 = Integer.parseInt(instruction.split(",")[2]);
				rs[index].op = instruction;
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				rs[index].address = operand2;
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("SW")) {
				operand1 = Integer.parseInt(instruction.split(",")[1].charAt(1)
						+ "");
				operand2 = Integer.parseInt(instruction.split(",")[2]);
				rs[index].op = instruction;
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				if (registersList[operand2] == -1) {
					rs[index].vk = operand2;
					registersList[operand2] = tail;
				} else {
					rs[index].qk = operand1;
				}
				rs[index].address = operand2;
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("JMP")) {
				operand1 = Integer.parseInt(instruction.split(",")[0]
						.split(" ")[1].charAt(1) + "");
				operand2 = Integer.parseInt(instruction.split(",")[1]);

				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				rs[index].vk = operand2;
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("BEQ")) {
				operand1 = Integer.parseInt(instruction.split(",")[0]
						.split(" ")[1].charAt(1) + "");
				operand2 = Integer.parseInt(instruction.split(",")[1].charAt(1)
						+ "");
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				rs[index].vk = operand2;
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("JALR")) {
				operand1 = Integer.parseInt(instruction.split(",")[1].charAt(1)
						+ "");
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
			if (instruction.equals("RET")) {
				operand1 = Integer.parseInt(instruction.split(" ")[1].charAt(1)
						+ "");
				if (registersList[operand1] == -1) {
					rs[index].vj = operand1;
					registersList[operand1] = tail;
				} else {
					rs[index].qj = operand1;
				}
				rs[index].busy = true;
				rs[index].op = instruction;
				rs[index].destination = tail;
			}
		}
	}
}
