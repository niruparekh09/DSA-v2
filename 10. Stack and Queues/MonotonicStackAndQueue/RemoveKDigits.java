import java.util.Stack;

public class RemoveKDigits {
    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3)); // O/P: 1219
    }

    public static String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);
            if (st.empty()) {
                st.push(digit);
                continue;
            }
            while (!st.empty() && (int) st.peek() > (int) digit && k > 0) {
                st.pop();
                k--;
            }
            st.push(digit);
        }

        // Converting Stack to String
        StringBuilder sb = new StringBuilder();
        while (!st.empty()) {
            // If there are still more digits to be removed we will directly remove it from stack.
            if (k != 0) {
                k--;
                st.pop();
                continue;
            }
            sb.append(st.pop());
        }

        // Checking for leading zeros
        int count = sb.length();
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) > '0') break;
            count--;
        }
        if (count == 0) sb = new StringBuilder("0"); // If count =0 then all the elements are 0
        else sb.delete(count, sb.length());

        return sb.reverse().toString(); // As pop-ing from stack and storing sb will reverse the order of the number
    }
}