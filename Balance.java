import java.util.EmptyStackException;
import java.util.Stack;

public class Balance {
	private Stack<Object> stk;
	private int length;
	String expression;

	public Balance(String expression) {
		stk = new Stack<Object>();
		this.expression = expression;
		this.length = expression.length();
	}

	// Determine if parentheses are balanced
	boolean isBalance() {
		int index = 0;
		boolean fail = false;

		try {
			while (index < length && !fail) {
				char ch = expression.charAt(index);

				switch (ch) {
				case Constants.LEFT_NORMAL:
					stk.push(new Character(ch));
					break;
				case Constants.RIGHT_NORMAL:
					stk.pop();
					break;
				default:

					break;
				}// end of swtich
				index++;
			} // end of while
		} // end of try
		catch (EmptyStackException e) {
			System.out.println(e.toString());
			fail = true;
		}
		if (stk.empty() && !fail)
			return true;
		else
			return false;
	}
}