public class Max_Profit {
    public static void main(String[] args) {
        System.out.println(BuySell(new int[]{7, 1, 5, 3, 6, 4}));
    }

    private static int BuySell(int[] arr) {
        int mini = arr[0], profit = 0;
        for (int i = 1; i < arr.length; i++) {
            int cost = arr[i] - mini;
            profit = Math.max(cost, profit);
            mini = Math.min(arr[i], mini);
        }
        return profit;
    }
}
