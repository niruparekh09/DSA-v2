import java.util.HashMap;
import java.util.Map;

public class Longest_Sub_Array_with_Sum_K {
    public static void main(String[] args) {
        System.out.println(lenOfLongestSubArray(new int[]{1, 2, 3, 1, 1, 1, 1, 4, 2, 3}, 3));
    }

    // For Array with -ve nums , +ve nums and zeros
    public static int lenOfLongestSubArrayOne(int[] a, int k) {
        int n = a.length; // size of the array.

        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            // calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            // This will work for initial value only for our example. I.e. sum = k will be in second
            // index itself. Then from there on the sum will increase, and we will check for sum using
            // prefix sum methodology i.e. x-k
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            // Calculate the length and update maxLen:
            // If Sum = 9, rem = 6 and i = 5 for [9,5]; we will get [6,2] for .get(rem).
            // So 9-6 = 3 i.e. k and length = 5-2 i.e. 3.
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            // Finally, update the map checking the conditions:
            // If the Map doesn't have the sum previously stored than we will add it accordingly
            // This will add all the key value pair. Until and unless there are zeros present in array
            // in which case the sum will not increase and will be present in the map.
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    public static int lenOfLongestSubArray(int[] a, int k) {
        int sum = a[0];
        int maxLen = 0;
        int left = 0, right = 0;
        int n = a.length;
        while (right < n) {
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if (right < n) sum += a[right];
        }
        return maxLen;
    }
}
