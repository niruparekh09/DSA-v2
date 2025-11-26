import java.util.ArrayList;
import java.util.List;

public class Pascal_Triangle {
    public static void main(String[] args) {
        int row = 5, col = 3;
        int element = getElement(row, col);
        //printNthRow(row);
        List<List<Integer>> generatedPascalTriangle = generate(row);
        System.out.println(generatedPascalTriangle.toString());
    }

    // Pattern: Mathematical (Iterative Combination)
    // Time: O(N) - Depends on the row number.
    // Space: O(1) - No auxiliary data structure used.
    private static void printNthRow(int row) {
        long ans = 1;
        System.out.print(ans + " ");
        // Logic: Calculate next element using the previous element (Current = Previous * (N-i) / i).
        // This avoids computing full factorials (O(N!)) which is computationally expensive and prone to overflow.
        for (int i = 1; i < row; i++) {
            ans = ans * (row - i);
            ans = ans / i;
            System.out.print(ans + " ");
        }
    }

    // Pattern: Combinatorics (nCr Formula)
    // Time: O(C) - Loop runs 'col' times.
    // Space: O(1) - Constant space.
    public static int getElement(int row, int col) {
        int r = row - 1;
        int c = col - 1;
        int res = 1;
        // Logic: Calculate nCr = (r!) / (c! * (r-c)!).
        // Simplified to iterative multiplication to prevent intermediate overflow.
        for (int i = 0; i < c; i++) {
            res = res * (r - i); // Multiply numerator (decreasing sequence)
            res = res / (i + 1); // Divide denominator (increasing sequence)
        }
        return res;
    }

    // Pattern: Iterative Construction
    // Time: O(N^2) - Total elements in Pascal's triangle is N*(N+1)/2.
    // Space: O(N^2) - Used to store the resulting list of lists.
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            pascalTriangle.add(getRow(i));
        }
        return pascalTriangle;
    }

    // Pattern: Mathematical (Optimized Row Generation)
    // Time: O(N) - Linear scan to build one row.
    // Space: O(N) - Stores the generated row.
    public static List<Integer> getRow(int row) {
        long ans = 1;
        List<Integer> generatedRow = new ArrayList<>();
        generatedRow.add(1); // Base case: Row always starts with 1

        // Logic: Use the relationship between adjacent terms in a row.
        // Formula: val = val * (row - col) / col
        for (int col = 1; col < row; col++) {
            ans = ans * (row - col);
            ans = ans / col;
            generatedRow.add((int) ans);
        }
        return generatedRow;
    }
}