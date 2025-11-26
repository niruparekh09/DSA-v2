import java.util.HashMap;
import java.util.Map;

public class Number_Of_SubArray_With_Xor_K {
    public static void main(String[] args) {
        long xor = subArrayXor(new int[]{4, 2, 2, 6, 4}, 6);
        System.out.println(xor);
    }

    // Coding Pattern: Hashing
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static long subArrayXor(int[] arr, int k) {
        // Map to store XOR prefix counts.
        Map<Integer, Integer> map = new HashMap<>();
        long count = 0;
        // Initialize XOR of 0 to have a count of 1, for cases where the subarray starts from index 0.
        map.put(0, 1);
        int xr = 0;
        // Iterate through the array.
        for (int ele : arr) {
            // Calculate prefix XOR.
            xr = xr ^ ele;
            // Calculate the required XOR value.
            int x = xr ^ k;
            // If the required XOR exists in the map, increment count. Why?  xr ^ k ^ xr = k.
            if (map.containsKey(x)) {
                count += map.get(x);
            }
            // Update XOR prefix count in the map.  getOrDefault handles cases where 'xr' is new.
            map.put(xr, map.getOrDefault(xr, 0) + 1);
        }
        return count;
    }
}
