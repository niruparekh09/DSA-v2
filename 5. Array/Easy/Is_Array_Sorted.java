public class Is_Array_Sorted {
    public static void main(String[] args) {
        System.out.println(isSorted(new int[]{1, 3, 4, 5, 4, 7}));
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (!(arr[i] <= arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

}
