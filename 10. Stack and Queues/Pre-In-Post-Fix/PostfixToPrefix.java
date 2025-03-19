import java.util.Stack;

public class PostfixToPrefix {
    public static void main(String[] args) {

    }

    public static String postfixToPrefix(String s) {
        Stack<String> res = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLetterOrDigit(c)) res.push(Character.toString(c));
            else {
                String op1 = res.pop();
                String op2 = res.pop();
                String exp = c + op2 + op1;
                res.push(exp);
            }
        }
        return res.peek();
    }
}
