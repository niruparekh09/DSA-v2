import java.util.Arrays;

public class Selection_Sort {
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        sort(arr, arr.length);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min_index = i;
            for (int j = i; j < n; j++) {
                if (arr[min_index] > arr[j]) min_index = j;
            }
            int temp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = temp;
        }
    }
}
