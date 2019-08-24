package seema.rpn.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * RPNCalculator - class to implement a 'RPN' (reverse polish notation) mode calculator.
 * @author Seema R.Patel
 *
 */
public class RPNCalculator {

	public static final String SEPARATOR = " ";
	public static final String OPERATION_ADD = "+";
	public static final String OPERATION_SUBTRACT = "-";
	public static final String OPERATION_MULTIPLY = "*";
	public static final String OPERATION_DIVIDE = "/";
	public static final String OPERATION_SQRT = "sqrt";
	public static final String UNDO = "undo";
	public static final String CLEAR = "clear";
	public static final String QUIT = "quit";

	private static List<InputObj> inputStack = new ArrayList<>();
	private static List<Double> theStack = new ArrayList<>();

	/**
	 * main method 
	 * @param args
	 */
	public static void main(String[] args) {
		printWelcomeText();

		Scanner inputScanner = new Scanner(System.in);
		String theCurrLine = inputScanner.nextLine();
		while (theCurrLine != "") {
			theStack.clear();
			String[] inputValues = theCurrLine.split(SEPARATOR);
			int thePosition = 1;
			for (String input : inputValues) {
				input = input.trim();
				if (QUIT.equalsIgnoreCase(input)) {
					System.out.println("Quitting!! Bye!!");
					inputScanner.close();
					theStack.clear();
					inputStack.clear();
					return;
				} else if (CLEAR.equalsIgnoreCase(input)) {
					if (inputStack.size() > 0)
						inputStack.clear();
					continue;
				} else if (UNDO.equalsIgnoreCase(input)) {
					if (inputStack.size() > 0)
						inputStack.remove(inputStack.size() - 1);
					continue;
				}
				InputObj inputObj = new InputObj(input, thePosition);
				inputStack.add(inputObj);
				thePosition = thePosition + 2;// start with 1 and increment by 2
												// ==> separator takes 1
												// position in between
			}
			try {
				process();
			} catch (RPNException e) {
				printStacksContent();
				System.out.println("Exception in RPN Calculator. Exitting!!");
				inputScanner.close();
				theStack.clear();
				inputStack.clear();
				return;
			}
			printStacksContent();
			theCurrLine = inputScanner.nextLine();
		}
		theStack.clear();
		inputStack.clear();
		inputScanner.close();
	}

	/**
	 * prints a welcome message along with directions for usage
	 */
	private static void printWelcomeText() {
		System.out.println("\t *** \t Welcome to RPN Calculator \t ***");
		System.out
				.println("Enter RPN commands to evaluate.\n"
						+ "Possible Operations: \n\n"
						+ "Add : + (ex: 2 5 +),\n"
						+ "Subtract : - (ex: 2 5 -),\n"
						+ "Multiply : * (ex: 2 5 *),\n"
						+ "Divide : / (ex: 2 5 /),\n"
						+ "Sqrt : sqrt (ex: 4 sqrt),\n"
						+ "Undo : undo (ex: undo),\n"
						+ "Clear : clear (ex: clear),\n"
						+ "Quit : quit (ex: quit ) To terminate and exit from application  \n");
	}

	/**
	 * prints the stack content at the execution of each line of input
	 */
	private static void printStacksContent() {
		System.out.print("\nStack: ");
		for (int i = 0; i < theStack.size(); i++) {
			System.out.print(getPrintString(theStack.get(i)) + SEPARATOR);
		}
		System.out.println();
	}

	/**
	 * get the printable value for Double value
	 * @param value
	 * @return String representation of the value
	 */
	private static String getPrintString(Double value) {
		if (value % 1.0 != 0)
		    return String.format("%s", value);
		else
		    return String.format("%.0f",value);
	}

	/**
	 * process - method to process the input
	 * @throws RPNException
	 */
	private static void process() throws RPNException {
		if (inputStack.size() == 0) {
			System.out.println("Nothing to process");
			return;
		}
		if (theStack.size() != 0)
			theStack.clear();
		for (int i = 0; i < inputStack.size(); i++) {
			InputObj input = inputStack.get(i);
			
			if (input.isOperation())
				calculate(input);
			else
				theStack.add(Double.parseDouble(input.getValue()));
		}
	}

	/**
	 * Calculates the result for the input operator
	 * @param input
	 * @throws RPNException
	 */
	private static void calculate(InputObj input)
			throws RPNException {
		String inputValue = input.getValue();
		switch (inputValue) {
		case OPERATION_ADD:
		case OPERATION_SUBTRACT:
		case OPERATION_MULTIPLY:
		case OPERATION_DIVIDE:
			if (theStack.size() > 1) {
				Double result = executeOperation(inputValue,
						theStack.remove(theStack.size() - 1),
						theStack.remove(theStack.size() - 1));
				theStack.add(result);
			} else {
				System.out.println("operator " + inputValue + " (position: "
						+ input.getPosition() + "): insufficient parameters");
				throw new RPNException("operator " + inputValue
						+ " (position: " + input.getPosition()
						+ "): insufficient parameters");
			}
			break;
		case OPERATION_SQRT:
			if (theStack.size() > 0) {
				Double result = Math.sqrt(theStack.remove(theStack.size() - 1));
				theStack.add(result);
			} else {
				System.out.println("operator " + inputValue + " (position: "
						+ input.getPosition() + "): insufficient parameters");
				throw new RPNException("operator " + inputValue
						+ " (position: " + input.getPosition()
						+ "): insufficient parameters");
			}
			break;
		default:
			System.out.println("Unrecognized operator: " + inputValue);
			throw new RPNException("Unrecognized operator: " + inputValue);
		}
	}

	/**
	 * executeOperation executes the given operation based on the provided operator and operands
	 * @param operator
	 * @param operand2
	 * @param operand1
	 * @return result of execution
	 */
	private static Double executeOperation(String operator, Double operand2,
			Double operand1) {
		Double result = 0.0;
		switch (operator) { //default case not required - that gets filtered out before this method is called
		case OPERATION_ADD:
			result = operand1 + operand2;
			break;
		case OPERATION_SUBTRACT:
			result = operand1 - operand2;
			break;
		case OPERATION_MULTIPLY:
			result = operand1 * operand2;
			break;
		case OPERATION_DIVIDE:
			result = operand1 / operand2;
			break;
		}
		return result;
	}
}
