import java.util.Stack;

public class PrefixToPostfix {

    public static void main(String[] args) {

    }

    public static String prefixToPostfix(String s) {
        Stack<String> res = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (Character.isLetterOrDigit(c)) res.push(Character.toString(c));
            else {
                String op1 = res.pop();
                String op2 = res.pop();
                String exp = op1 + op2 + c;
                res.push(exp);
            }
        }
        return res.peek();
    }
}
