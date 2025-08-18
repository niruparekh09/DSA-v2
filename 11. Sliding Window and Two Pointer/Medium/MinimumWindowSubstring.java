public class MinimumWindowSubstring {
    public static void main(String[] args) {
        /*
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
        */
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    // Brute Force
    public static String minWindowBrute(String s, String t) {
        int minLen = Integer.MAX_VALUE;

        int sIdx = -1;

        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[256];
            for (char c : t.toCharArray()) {
                hash[c]++;
            }

            int count = 0;

            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (hash[c] > 0) {
                    count++;
                }
                hash[c]--;

                if (count == t.length()) {
                    /* Update minLen and sIndex
                    if current window is smaller*/
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        sIdx = i;
                    }
                    break;
                }
            }
        }
        return (sIdx == -1) ? "" : s.substring(sIdx, sIdx + minLen);
    }

    public static String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        int sIdx = -1;
        int count = 0;
        int[] hash = new int[256];
        int l = 0, r = 0;

        // Pre-Storing the Chars in Hash.
        for (char c : t.toCharArray()) {
            hash[c]++;
        }

        // Traversing the Window
        while (r < s.length()) {
            char c = s.charAt(r);
            if (hash[c] > 0) {
                count++;
            }
            hash[c]--;

            /* If all characters from t
            are found in current window */
            while (count == t.length()) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIdx = l;
                }

                // Remove leftmost character from window. Add again to the Hash array.
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0) {
                    count--;
                }
                l++;
            }
            r++;
        }
        return (sIdx == -1) ? "" : s.substring(sIdx, sIdx + minLen);
    }
}