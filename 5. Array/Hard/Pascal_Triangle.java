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

    private static void printNthRow(int row) {
        long ans = 1;
        System.out.print(ans + " ");
        // We can use nCr for every element, but it will take a lot of time
        // but every ans has something common from previous ans.
        // for 5th row ans goes like 1 4 6 4 1.
        // i.e. 1...5/1...(5*4)/(1*2)...(5*4*3)/(1*2*3)...(5*4*3*2)/(1*2*3*1)...(5*4*3*2*1)/(1*2*3*4*5)
        for (int i = 1; i < row; i++) {
            ans = ans * (row - i);
            ans = ans / i;
            System.out.print(ans + " ");
        }
    }

    public static int getElement(int row, int col) {
        int r = row - 1;
        int c = col - 1;
        int res = 1;
        // Calc row-1 C col-1 (nCr)
        // Consider for 4C2
        for (int i = 0; i < c; i++) {
            res = res * (r - i); // res * 4 * 3
            res = res / (i + 1); // res / (1 * 2)
        }
        return res;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            pascalTriangle.add(getRow(i));
        }
        return pascalTriangle;
    }

    public static List<Integer> getRow(int row) {
        long ans = 1;
        List<Integer> generatedRow = new ArrayList<>();
        generatedRow.add(1);
        for (int col = 1; col < row; col++) {
            ans = ans * (row - col);
            ans = ans / col;
            generatedRow.add((int) ans);
        }
        return generatedRow;
    }
}
