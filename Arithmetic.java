import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Scanner;

class Arithmetic {
	Stack<Object> stk;
	String expression, postfix;
	int length;
	String results;

	Arithmetic(String s) {
		expression = s;
		postfix = "";
		length = expression.length();
		stk = new Stack<Object>();
	}

	// Validate the expression - make sure parentheses are balanced
	boolean isBalance() {
		// Already defined
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

	// Convert expression to postfix notation
	void postfixExpression() {
		stk.clear(); // Re-using the stack object
		Scanner scan = new Scanner(expression);
		char current;
		// The algorithm for doing the conversion.... Follow the bullets
		while (scan.hasNext()) {
			String token = scan.next();

			if (isNumber(token)) // Bullet # 1
				postfix = postfix + token + " ";
			else {
				current = token.charAt(0);

				if (isParentheses(current)) // Bullet # 2 begins
				{
					if (stk.empty() || current == Constants.LEFT_NORMAL || current == Constants.LEFT_CURLY
							|| current == Constants.LEFT_SQUARE) {
						// push this element on the stack;
						stk.push(current);
					} else if (current == Constants.RIGHT_NORMAL) {
						try {
							/*
							 * Some details ... whatever is popped from the stack is an object, hence you
							 * must cast this object to its proper type, then extract its primitive data
							 * (type) value.
							 */
							Character ch = (Character) stk.pop();
							char top = ch.charValue();

							while (top != Constants.LEFT_NORMAL) {
								/*
								 * Append token popped onto the output string Place at least one blank space
								 * between each token
								 */
								postfix += postfix + top + " ";
								ch = (Character) stk.pop();
								top = ch.charValue();
							}
						} catch (EmptyStackException e) {

						}
					} else if (current == Constants.RIGHT_SQUARE) {
						try {
							Character ch = (Character) stk.pop();
							char top = ch.charValue();

							while (top != Constants.LEFT_SQUARE) {
								postfix += postfix + top + " ";
								ch = (Character) stk.pop();
								top = ch;
							}
						} catch (EmptyStackException e) {

						}
					} else if (current == Constants.RIGHT_CURLY) {
						try {
							Character ch = (Character) stk.pop();
							char top = ch.charValue();

							while (top != Constants.LEFT_CURLY) {
								postfix += postfix + top + " ";
								ch = (Character) stk.pop();
								top = ch.charValue();
							}
						} catch (EmptyStackException e) {

						}
					}
				} // Bullet # 2 ends
				else if (isOperator(current))// Bullet # 3 begins
				{
					if (stk.empty())
						stk.push(new Character(current));
					else {
						try {
							// Remember the method peek simply looks at the top
							// element on the stack, but does not remove it.

							char top = (Character) stk.peek();
							boolean higher = hasHigherPrecedence(top, current);

							while (top != Constants.LEFT_NORMAL && top != Constants.LEFT_SQUARE
									&& top != Constants.LEFT_CURLY && higher) {
								postfix = postfix + stk.pop() + " ";
								top = (Character) stk.peek();
								higher = hasHigherPrecedence(top, current);
							}
							stk.push(new Character(current));
						} catch (EmptyStackException e) {
							stk.push(new Character(current));
						}
					}
				} // Bullet # 3 ends
			}
		} // Outer loop ends

		try {
			while (!stk.empty()) // Bullet # 4
				postfix += stk.pop() + " "; // complete this
		} catch (EmptyStackException e) {

		}
	}

	boolean isNumber(String str) {
		// define this method
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	boolean isParentheses(char current) {
		// define this method;
		if (current == Constants.LEFT_NORMAL || current == Constants.RIGHT_NORMAL || current == Constants.LEFT_CURLY
				|| current == Constants.RIGHT_CURLY || current == Constants.LEFT_SQUARE
				|| current == Constants.RIGHT_SQUARE) {
			return true;
		} else {
			return false;
		}
	}

	boolean isOperator(char ch) {
		// define this method
		switch (ch) {
			case Constants.PLUS:
			case Constants.MINUS:
			case Constants.MULTIPLY:
			case Constants.DIVIDE:
			case Constants.MODULO:
				return true;
			default:
				return false;
		}
	}

	boolean hasHigherPrecedence(char top, char current) {
		// define this method
		int x, y = 0;
		switch (top) {
			case Constants.MULTIPLY:
			case Constants.MODULO:
			case Constants.DIVIDE:
				x = 1;
				break;
			default:
				x = 1;
		}

		switch (current) {
			case Constants.MULTIPLY:
			case Constants.MODULO:
			case Constants.DIVIDE:
				y = 1;
				break;
			default:
				y = 0;
		}

		if (x < y) {
			return false;
		} else {
			return true;
		}
	}

	String getPostfix() {
		// define method
		return postfix;
	}
}