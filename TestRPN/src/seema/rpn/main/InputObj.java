package seema.rpn.main;

public class InputObj {
	public InputObj(String input, int position) {
		this.value = input;
		this.position = position;
		try {
			Double.parseDouble(input);
		} catch(NumberFormatException e)
		{
			isOperation = true;
		}
	}
	private String value;
	private boolean isOperation = false;
	private int position =0;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isOperation() {
		return isOperation;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
