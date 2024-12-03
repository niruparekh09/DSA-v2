import java.util.Arrays;

public class Bubble_Sort {
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        sort(arr, arr.length);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr, int n) {
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
