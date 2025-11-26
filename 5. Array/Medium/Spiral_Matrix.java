import java.util.ArrayList;
import java.util.List;

public class Spiral_Matrix {
    public static void main(String[] args) {
        List<Integer> spiralOrder = spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println(spiralOrder);
    }

    /*
     * Approach: Simulation (Layer-by-Layer)
     * Pattern: Matrix Traversal
     * Time Complexity: O(N * M) - We traverse every element exactly once.
     * Space Complexity: O(1) - Excluding the output list; pointers use constant space.
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Define boundaries for the current unvisited layer
        int top = 0, bottom = n - 1;
        int left = 0, right = m - 1;
        List<Integer> ans = new ArrayList<>();

        while (top <= bottom && left <= right) {
            // 1. Traverse Top Row (Left -> Right)
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++; // Top layer processed, move boundary down

            // 2. Traverse Right Column (Top -> Bottom)
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--; // Right layer processed, move boundary left

            // 3. Traverse Bottom Row (Right -> Left)
            // Critical Edge Case: Check `top <= bottom`. If boundaries crossed after step 1,
            // we might print the same row again (e.g., single row matrix).
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 4. Traverse Left Column (Bottom -> Top)
            // Critical Edge Case: Check `left <= right`. Prevents re-traversing a column
            // if the spiral has already finished horizontally.
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }
        return ans;
    }
}