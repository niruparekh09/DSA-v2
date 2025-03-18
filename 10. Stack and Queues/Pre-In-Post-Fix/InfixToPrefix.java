import java.util.Stack;

public class InfixToPrefix {
    public static void main(String[] args) {

    }

    public static String infixToPrefix(String s) {
        s = reverseString(s);
        int i = 0;
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) ans.append(c); // In case of an Operand
            else if (c == '(') st.push(c);
            else if (c == ')') { // Empty the operators between ( and )
                while (!st.isEmpty() && st.peek() != '(')
                    ans.append(st.pop());
            } else { // In case operators
                if (c == '^') { // Top precedent operator
                    while (!st.empty() && prec(c) <= prec(st.peek())) // There we will check for equal precedence then add
                        ans.append(st.pop());
                } else { // For rest of the operators, only add to ans if the precedence is greater.
                    while (!st.empty() && prec(c) < prec(st.peek()))
                        ans.append(st.pop());
                }
                st.push(c);
            }
            i++;
        }
        return reverseString(ans.toString());
    }

    public static int prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch == '(') {
                sb.append(')');
            } else if (ch == ')') {
                sb.append('(');
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}