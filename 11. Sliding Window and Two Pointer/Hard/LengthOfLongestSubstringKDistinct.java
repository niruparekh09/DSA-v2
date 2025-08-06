import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringKDistinct {
    public static void main(String[] args) {
        /*
        Input: s = "eceba", k = 2
        Output: 3
        Explanation: The substring "ece" has length 3 and contains exactly two distinct characters.
         */
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
    }

    public static int lengthOfLongestSubstringKDistinctBrute(String s, int k) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> mp = new HashMap<>(); // For Storing K distinct chars and their frequency
            for (int j = i; j < s.length(); j++) {
                char key = s.charAt(j);
                mp.put(key, mp.getOrDefault(key, 0) + 1);
                if (mp.size() <= k) maxLen = Math.max(maxLen, j - i + 1);
                else break; // If the map has more than k distinct chars so break from the loop
            }
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstringKDistinctBetter(String s, int k) {
        int maxLen = 0;
        int l = 0, r = 0;
        Map<Character, Integer> mp = new HashMap<>(); // For Storing K distinct chars and their frequency
        while (r < s.length()) {
            char key = s.charAt(r);
            mp.put(key, mp.getOrDefault(key, 0) + 1);
            if (mp.size() <= k) maxLen = Math.max(maxLen, r - l + 1);
            else {
                while (mp.size() > k) { // Move l to right and remove the key til we again get k distinct elems in mp
                    char lKey = s.charAt(l);
                    int freq = mp.get(lKey) - 1;
                    if (freq == 0) {
                        mp.remove(lKey);
                    } else {
                        mp.put(lKey, freq);
                    }
                    l++;
                }
            }
            r++;
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0;
        int l = 0, r = 0;
        Map<Character, Integer> mp = new HashMap<>(); // For Storing K distinct chars and their frequency
        while (r < s.length()) {
            char key = s.charAt(r);
            mp.put(key, mp.getOrDefault(key, 0) + 1);

            if (mp.size() > k) { // Shrink by One-step iteration
                char lKey = s.charAt(l);
                int freq = mp.get(lKey) - 1;
                if (freq == 0) {
                    mp.remove(lKey);
                } else {
                    mp.put(lKey, freq);
                }
                l++;
            }

            if (mp.size() <= k) maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }
        return maxLen;
    }
}