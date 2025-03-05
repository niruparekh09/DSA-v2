import java.util.Stack;

public class Clear_Digits {
    public static void main(String[] args) {
        System.out.println(clearDigits("cb34"));
    }

    // TC: O(N) SC: O(N)
    public static String clearDigitsUsingStack(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char charAtI = s.charAt(i);
            if (Character.isAlphabetic(charAtI)) {
                st.push(charAtI);
            } else if (Character.isDigit(charAtI)) {
                st.pop();
            }
        }

        if (st.isEmpty()) return "";
        else {
            return st.stream().toString();
        }
    }

    // TC: O(N) SC: O(1)
    public static String clearDigits(String s) {
        int j = s.length() + 1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                j = j - 2;
            }
        }
        return s.substring(0, j);
    }

}
