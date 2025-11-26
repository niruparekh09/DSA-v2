public class Count_Consecutive_Ones {
    public static void main(String[] args) {
        System.out.println(count(new int[]{1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1}));
    }

    public static int count(int[] arr) {
        int max = 0, count = 0;
        for (int j : arr) {
            if (j == 1) {
                count++;
            } else {
                count = 0;
            }

            if (max < count) max = count;
        }
        return max;
    }
}
