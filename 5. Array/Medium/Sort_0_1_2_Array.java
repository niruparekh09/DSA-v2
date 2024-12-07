import java.util.Arrays;

public class Sort_0_1_2_Array {
    public static void main(String[] args) {
        // sortOne(new int[]{0, 0, 0, 1, 2, 0, 2, 2, 1, 0, 1, 1, 1, 2, 0, 0});
        sortDNF(new int[]{0, 0, 0, 1, 2, 0, 2, 2, 1, 0, 1, 1, 1, 2, 0, 0});
    }

    // Using Dutch National Flag
    private static void sortDNF(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high);
                high--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void sortOne(int[] a) {
        int count0 = 0, count1 = 0, count2 = 0;
        int n = a.length;
        for (int j : a) {
            if (j == 0) {
                count0++;
            } else if (j == 1) {
                count1++;
            } else if (j == 2) {
                count2++;
            }
        }

        for (int i = 0; i < count0; i++) {
            a[i] = 0;
        }
        for (int i = count0; i < count0 + count1; i++) {
            a[i] = 1;
        }
        for (int i = count0 + count1; i < n; i++) {
            a[i] = 2;
        }
        System.out.println(Arrays.toString(a));
    }
}
