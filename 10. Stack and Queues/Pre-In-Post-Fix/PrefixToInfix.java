import java.util.Stack;

public class PrefixToInfix {
    public static void main(String[] args) {

    }

    public static String prefixToInfix(String s) {
        Stack<String> stack = new Stack<>();
        int length = s.length();
        for (int i = length - 1; i >= 0; i--) {
            char c = s.charAt(i);

            // If an operand than push to stack
            if (Character.isLetterOrDigit(c)) stack.push(Character.toString(c));

                // If an operator than pop both operand, add the operator between them and push it to stack
            else if (isOperator(c)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String exp = "(" + op1 + c + op2 + ")";
                stack.push(exp);
            }
        }

        // Stack will only have one elem i.e. the infix expression.
        return stack.peek();
    }

    public static boolean isOperator(char c) {
        switch (c) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
            case '%':
                return true;
        }
        return false;
    }
}
