import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreviousSmallerElement {
    public static void main(String[] args) {
        // A = [4, 5, 2, 10, 8]
        // OP = [-1, 4, -1, 2, 2]
        System.out.println(prevSmaller(new ArrayList<>(List.of(4, 5, 2, 10, 8))));
    }

    public static ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        ArrayList<Integer> pse = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            while (!st.isEmpty() && st.peek() >= A.get(i)) st.pop();

            pse.add(!st.isEmpty() ? st.peek() : -1);

            st.push(A.get(i));
        }
        return pse;
    }
}
