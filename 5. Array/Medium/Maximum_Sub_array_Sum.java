public class Maximum_Sub_array_Sum {
    public static void main(String[] args) {
        System.out.println(sumKadane(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    private static int sumKadane(int[] arr) {
        int sum = 0;
        int maxi = Integer.MIN_VALUE;
        for (int element : arr) {
            sum = sum + element;
            if (sum > maxi) {
                maxi = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxi;
    }
}
