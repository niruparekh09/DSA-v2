import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{-2, -1, 1, 2})));
    }

    // T.C = O(2n) S.C = O(n)
    public static int[] asteroidCollisionBrute(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (st.empty()) {
                st.add(asteroids[i]);
                continue;
            }
            if (asteroids[i] > 0) {
                st.add(asteroids[i]);
            } else {
                if (st.peek() < 0) {
                    st.add(asteroids[i]);
                } else {
                    if (Math.abs(st.peek()) == Math.abs(asteroids[i])) {
                        st.pop();
                    } else {
                        boolean flag = false;
                        while (!st.empty() && st.peek() > 0 && st.peek() + asteroids[i] <= 0) {
                            if (st.peek() + asteroids[i] == 0) {
                                st.pop();
                                flag = true;
                                break;
                            }
                            st.pop();
                        }

                        if (st.empty() && !flag) st.add(asteroids[i]);
                        else if (!st.empty() && st.peek() < 0 && !flag) st.add(asteroids[i]);

                    }
                }
            }
        }
        int[] arr = new int[st.size()];
        int i = st.size() - 1;
        while (!st.empty()) {
            arr[i] = st.pop();
            i--;
        }
        return arr;
    }

    // T.C = O(2n) S.C = O(n)
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0) st.add(asteroids[i]);
            else {
                while (!st.empty() && st.peek() > 0 && st.peek() < Math.abs(asteroids[i])) st.pop();

                if (!st.empty() && st.peek() == Math.abs(asteroids[i])) st.pop();
                else if (st.empty() || st.peek() < 0) st.add(asteroids[i]);
            }
        }

        int[] arr = new int[st.size()];
        int i = st.size() - 1;
        while (!st.empty()) {
            arr[i] = st.pop();
            i--;
        }
        return arr;
    }
}