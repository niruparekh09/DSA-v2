import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FruitsIntoBaskets {
    public static void main(String[] args) {
        System.out.println(totalFruit2(new int[]{1, 2, 3, 2, 2}));
    }

    public static int totalFruitBrute(int[] fruits) {
        int maxLen = 0;
        for (int i = 0; i < fruits.length; i++) {
            Set<Integer> st = new HashSet<>();
            for (int j = i; j < fruits.length; j++) {
                st.add(fruits[j]);
                if (st.size() <= 2) maxLen = Math.max(maxLen, j - i + 1);
                else break;
            }
        }
        return maxLen;
    }

    public static int totalFruit(int[] fruits) {
        int maxLen = 0;
        int l = 0, r = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        while (r < fruits.length) {
            mp.put(fruits[r], mp.getOrDefault(fruits[r], 0) + 1);
            if (mp.size() <= 2) {
                maxLen = Math.max(maxLen, r - l + 1);
            } else {
                while (mp.size() > 2) {
                    int freq = mp.get(fruits[l]) - 1;
                    if (freq == 0) {
                        mp.remove(fruits[l]); // Remove the element if its frequency is 0
                    } else {
                        mp.put(fruits[l], freq); // Update the frequency
                    }
                    l++; // Move to right
                }
            }
            r++;
        }
        return maxLen;
    }

    public static int totalFruit2(int[] fruits) {
        int maxLen = 0;
        int l = 0, r = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        while (r < fruits.length) {
            mp.put(fruits[r], mp.getOrDefault(fruits[r], 0) + 1);
            if (mp.size() > 2) {
                int freq = mp.get(fruits[l]) - 1;
                if (freq == 0) {
                    mp.remove(fruits[l]); // Remove the element if its frequency is 0
                } else {
                    mp.put(fruits[l], freq); // Update the frequency
                }
                l++; // Move to right
            }
            if (mp.size() <= 2) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }
}