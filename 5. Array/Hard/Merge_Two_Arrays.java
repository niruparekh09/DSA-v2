package Hard;

import java.util.Arrays;

public class Merge_Two_Arrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 7};
        int[] nums2 = {0, 2, 6, 8, 9};
        mergerOptimal1(nums1, nums1.length, nums2, nums2.length);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergedArray = new int[m + n];
        int left = 0;
        int right = 0;
        int idx = 0;
        while (left < m && right < n) {
            if (nums1[left] >= nums2[right]) {
                mergedArray[idx] = nums2[right];
                right++;
            } else {
                mergedArray[idx] = nums1[left];
                left++;
            }
            idx++;
        }
        while (left < m) {
            mergedArray[idx] = nums1[left];
            left++;
            idx++;
        }
        while (right < n) {
            mergedArray[idx] = nums2[right];
            right++;
            idx++;
        }
        Arrays.sort(mergedArray);
        if (m >= 0) System.arraycopy(mergedArray, 0, nums1, 0, m);
        if (n >= 0) System.arraycopy(mergedArray, m, nums2, 0, n);
    }

    public static void mergerOptimal1(int[] nums1, int m, int[] nums2, int n) {
        /*for (int i = 0; i < n; i++) {
            int idx = m - i - 1;
            if (nums1[idx] > nums2[i]) {
                int temp = nums2[i];
                nums2[i] = nums1[idx];
                nums1[idx] = temp;
            } else {
                break;
            }
        }*/
        int left = m - 1;
        int right = 0;
        while (left >= 0 && right < n) {
            if (nums1[left] > nums2[right]) {
                int temp = nums2[right];
                nums2[right] = nums1[left];
                nums1[left] = temp;
                left--;
                right++;
            } else {
                break;
            }
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums1) + "----" + Arrays.toString(nums2));
    }

    public static void mergerOptimal2(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int gap = len / 2 + len % 2;
        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < len) {
                // left in arr1 and right in arr2
                if (left < m && right >= m) {
                    swapIfGreater(nums1, nums2, left, right - m); // right-m because the right is corresponding to total length not the length of arr2
                } else if (left >= m) { // both left and right in arr2
                    swapIfGreater(nums2, nums2, left - m, right - m); // as both l and r in arr2 to we have to adjust both by -m
                } else { // both left and right in arr1
                    swapIfGreater(nums1, nums1, left, right);
                }
                left++;
                right++;
            }
            if (gap == 1) break;
            gap = gap / 2 + gap % 2;
        }
    }

    private static void swapIfGreater(int[] nums1, int[] nums2, int i1, int i2) {
        if (nums1[i1] > nums2[i2]) {
            int temp = nums2[i2];
            nums2[i2] = nums1[i1];
            nums1[i1] = temp;
        }
    }

    public void mergeLeetcode(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0 || n == 0) {
            if (n != 0) {
                System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            }
            return;
        }
        int left = m;
        int right = 0;
        while (left < m + n) {
            nums1[left] = nums2[right];
            left++;
            right++;
        }
        Arrays.sort(nums1);
    }
}
