import java.util.Stack;

public class PostfixToInfix {
    public static void main(String[] args) {

    }

    public static String postfixToInfix(String s) {
        Stack<String> res = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) res.push(Character.toString(c));
            else { // In case of an operator
                String op2 = res.pop(); // Top elem will be second operand
                String op1 = res.pop();
                String exp = "(" + op1 + c + op2 + ")";
                res.push(exp);
            }
        }
        return res.peek();
    }
}
