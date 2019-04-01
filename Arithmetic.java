import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Scanner;

class Arithmetic
{
	Stack stk;
	String expression,
			postfix;
	int length;

	Arithmetic(String s)
	{
		expression = s;
		postfix ="";
		length = expression.length();
		stk = new Stack();
	}
	// Validate the expression - make sure parentheses are balanced
	boolean isBalance()
	{
		// Already defined
	}

	// Convert expression to postfix notation
	void postfixExpression()
	{
		stk.clear(); // Re-using the stack object
		Scanner scan = new Scanner(expression);
		char current;
		// The algorithm for doing the conversion.... Follow the bullets
		while (scan.hasNext())
		{
			String token = scan.next();

			if (isNumber(token)) // Bullet # 1
				postfix = postfix + token + " ";
			else
			{
				current = token.charAt(0);

				if (isParentheses(current)) // Bullet # 2 begins
				{
					if(stk.empty() || current == Constants.LEFT_NORMAL)
					{

						// push this element on the stack;
					}
					else if (current == Constants.RIGHT_NORMAL)
					{
						try
						{
						   /* Some details ... whatever is popped from the
 						    * stack is an object, hence you must cast this
 						    * object to its proper type, then extract its
 						    * primitive data (type) value.
 						    */
							Character ch = (Character)stk.pop();
							char top = ch.charValue();


							while (top != Constants.LEFT_NORMAL)
							{
							 /* Append token popped onto the output string
							  * Place at least one blank space between 
							  * each token
							*/
							}
						}
						catch(EmptyStackException e)
						{

						}
					}
				}// Bullet # 2 ends
				else if (isOperator(current))// Bullet # 3 begins
				{
					if(stk.empty())
						stk.push(new Character(current));
					else
					{
						try
						{
							 // Remember the method peek simply looks at the top
 							// element on the stack, but does not remove it.

							char top = (Character)stk.peek();
							boolean higher = hasHigherPrecedence(top, current);

							while(top != Constants.LEFT_NORMAL && higher)
							{
								postfix = postfix + stk.pop() + " ";
								top = (Character)stk.peek();
							}
							stk.push(new Character(current));
						}
						catch(EmptyStackException e)
						{
							stk.push(new Character(current));
						}
					}
				}// Bullet # 3 ends
			}
		} // Outer loop ends

		try
		{
			while(!stk.empty()) // Bullet # 4
			;	// complete this
		}
		catch(EmptyStackException e)
		{

		}
	}

	boolean isNumber(String str)
	{
		//define this method
	}

	boolean isParentheses(char current)
	{
		// define this method;
	}

	boolean isOperator(char ch)
	{
		// define this method
	}

	boolean hasHigherPrecedence(char top, char current)
	{
		// define this method
	}

	String getPostfix()
	{
		// define method
	}
}