package Basics;

import java.util.Arrays;

public class ReversingArray {

    public static void swap(int[] arr, int s, int e) {
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }

    public static void reverse(int[] arr, int s, int e) {
        if (s > e) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        swap(arr, s, e);
        reverse(arr, s + 1, e - 1);
    }

    public static void reverse2(int i, int[] arr, int n) {
        if (i >= n / 2) return;
        swap(arr, i, n - i - 1);
        reverse2(i + 1, arr, n);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arr));
        //reverse(arr, 0, arr.length - 1);
        reverse2(0, arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

}
