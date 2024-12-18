import java.util.Arrays;

public class Shell_Sort {
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        sort(arr, arr.length);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr, int length) {
        int gap = length / 2 + length % 2;
        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < length) {
                if (arr[left] > arr[right]) {
                    int temp = arr[right];
                    arr[right] = arr[left];
                    arr[left] = temp;
                }
                left++;
                right++;
            }
            if (gap == 1) break;
            gap = gap / 2 + gap % 2;
        }
    }
}
