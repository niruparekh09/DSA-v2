import java.util.ArrayList;
import java.util.Arrays;

public class Rearrange_Array_Elements_by_Sign {
    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -5, 2, -4};
//        int[] rearrangedArray = rearrange(arr);
//        System.out.println(Arrays.toString(rearrangedArray));
//        int[] nonoptimalRearrangedArray = rearrangeUnoptimized(arr);
//        System.out.println(Arrays.toString(nonoptimalRearrangedArray));
        int[] arr2 = {1, 2, -4, -5, 3, 6};
        int[] arr3 = rearrangeTwo(arr2);
        System.out.println(Arrays.toString(arr3));
    }

    private static int[] rearrangeUnoptimized(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        for (int element : arr) {
            if (element > 0) {
                positive.add(element);
            } else {
                negative.add(element);
            }
        }

        for (int i = 0; i < n / 2; i++) {
            arr[2 * i] = positive.get(i);
            arr[2 * i + 1] = negative.get(i);
        }

        return arr;
    }

    private static int[] rearrange(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        int positiveIdx = 0;
        int negativeIdx = 1;
        for (int j : arr) {
            if (j > 0) {
                ans[positiveIdx] = j;
                positiveIdx += 2;
            } else {
                ans[negativeIdx] = j;
                negativeIdx += 2;
            }
        }
        return ans;
    }

    private static int[] rearrangeTwo(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        for (int element : arr) {
            if (element > 0) {
                positive.add(element);
            } else {
                negative.add(element);
            }
        }

        if (positive.size() > negative.size()) {
            for (int i = 0; i < negative.size(); i++) {
                arr[i * 2] = positive.get(i);
                arr[i * 2 + 1] = negative.get(i);
            }
            int index = negative.size() * 2;
            for (int i = negative.size(); i < positive.size(); i++) {
                arr[index] = positive.get(i);
                index++;
            }
        } else {
            for (int i = 0; i < positive.size(); i++) {
                arr[i * 2] = positive.get(i);
                arr[i * 2 + 1] = negative.get(i);
            }
            int index = positive.size() * 2;
            for (int i = positive.size(); i < negative.size(); i++) {
                arr[index] = negative.get(i);
                index++;
            }
        }
        return arr;
    }
}
