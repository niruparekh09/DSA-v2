package Easy;

import java.util.Arrays;

public class Rotate_Array_By_K_Places {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        //leftRotateBrute(arr, 3);
        rightRotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    private static void leftRotate(int[] arr, int k) {
        // Array of length 1 can not be rotated
        if (arr.length == 1 || arr.length == k)
            return;
        // When K is bigger than length than just take the mod.
        if (arr.length < k)
            k = k % arr.length;
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    private static void rightRotate(int[] arr, int k) {
        // Array of length 1 can not be rotated
        if (arr.length == 1 || arr.length == k)
            return;
        // When K is bigger than length than just take the mod.
        if (arr.length < k)
            k = k % arr.length;
        reverse(arr, 0, arr.length - k - 1);
        reverse(arr, arr.length - k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    public static void reverse(int[] arr, int s, int e) {
        if (s > e) {
            return;
        }
        swap(arr, s, e);
        reverse(arr, s + 1, e - 1);
    }

    public static void swap(int[] arr, int s, int e) {
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }


    // Brute
    private static void leftRotateBrute(int[] arr, int k) {
        int n = arr.length;
        int[] temp = new int[k];

        // Storing first k elements in temp array
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        // Shifting array left by k places
        for (int i = k; i < n; i++) {
            arr[i - k] = arr[i];
        }

        // Adding the stored first k elements to end of the array
        for (int i = n - k; i < n; i++) {
            arr[i] = temp[i - (n - k)];
        }
    }


}
