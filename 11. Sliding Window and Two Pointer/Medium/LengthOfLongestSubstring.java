import java.util.Arrays;

public class LengthOfLongestSubstring { // Without Duplicate Chars.
    public static void main(String[] args) {
        /*
        Input: s = "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.
        */
        System.out.println(lengthOfLongestSubstringBrute("abcabcbb"));
    }

    public static int lengthOfLongestSubstringBrute(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[256]; // Defined here because we have resetting the hash each.
            for (int j = i; j < s.length(); j++) {
                if (hash[s.charAt(j)] == 1) break;
                int len = j - i + 1;
                maxLen = Math.max(maxLen, len);
                hash[s.charAt(j)] = 1;
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int[] hash = new int[256];
        Arrays.fill(hash, -1); // Because 0 also is an array index, and we are storing index of chars in this Hash.
        int l = 0, r = 0;
        while (r < s.length()) {
            // When the element is present in hash i.e. it's a repeating elem.
            if (hash[s.charAt(r)] != -1) {
                // If the repeating elem is in the window i.e. after l and before r (we won't have to check before r for obvious reasons)
                if (hash[s.charAt(r)] >= l) {
                    // Update the index of l to be after than repeating element as now we have the same elem in r,
                    // so we don't need the older elem in window
                    l = hash[s.charAt(r)] + 1;
                }
            }

            maxLen = Math.max(maxLen, r - l + 1);
            hash[s.charAt(r)] = r; // Update the index (for the already present elems) / add the index for the new elem
            r++;
        }
        return maxLen;
    }
}