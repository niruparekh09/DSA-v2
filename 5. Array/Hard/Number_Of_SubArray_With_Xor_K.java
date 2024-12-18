package Hard;

import java.util.HashMap;
import java.util.Map;

public class Number_Of_SubArray_With_Xor_K {
    public static void main(String[] args) {
        long xor = subArrayXor(new int[]{4, 2, 2, 6, 4}, 6);
        System.out.println(xor);
    }

    public static long subArrayXor(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        long count = 0;
        map.put(0, 1);
        int xr = 0;
        for (int ele : arr) {
            xr = xr ^ ele;
            int x = xr ^ k;
            if (map.containsKey(x)) {
                count += map.get(x);
            }
            map.put(xr, map.getOrDefault(xr, 0) + 1);
        }
        return count;
    }
}
