import java.util.Arrays;

public class FractionalKnapsack {
    /*
     * Approach: Greedy Algorithm
     * Pattern: Value Density (Profit/Weight Ratio)
     * Time Complexity: O(N log N) - Due to sorting the items by ratio.
     * Space Complexity: O(N) - To store the ratio and index mapping.
     */
    public double fractionalKnapsack(int[] val, int[] wt, long cap) {
        int n = val.length;
        double total = 0.0;

        // Step 1: Calculate the value-to-weight ratio for each item.
        // This represents the "density" or "efficiency" of each item.
        double[][] ratio = new double[n][2];
        for (int i = 0; i < n; i++)
            ratio[i] = new double[]{(double) val[i] / wt[i], i};


        // Step 2: Sort items in descending order of ratio.
        // Greedy Choice: Always pick the item that gives the most value per unit of weight.
        Arrays.sort(ratio, (a, b) -> Double.compare(b[0], a[0]));

        // Step 3: Iterate through sorted items and fill the knapsack.
        for (double[] r : ratio) {
            int i = (int) r[1];

            // If the whole item fits, take it entirely.
            if (wt[i] <= cap) {
                total += val[i];
                cap -= wt[i];
            }
            // If the whole item doesn't fit, take the remaining capacity as a fraction.
            else {
                total += val[i] * ((double) cap / wt[i]);
                break; // Knapsack is now exactly full.
            }
        }

        return total;
    }
}