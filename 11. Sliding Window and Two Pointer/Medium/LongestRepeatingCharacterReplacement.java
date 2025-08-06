public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        /*
        Input : s = "BAABAABBBAAA" , k = 2
        Output : 6
        Explanation : we can change the B present at index 0 , 3 (0 base indexing) to A. The new string is "AAAAAABBBAAA".
        The substring "AAAAAA" is the longest substring having same letter with length 6.
        */
        System.out.println(characterReplacement("BAABAABBBAAA", 2));
    }

    public static int characterReplacementBrute(String s, int k) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[26];
            int maxFreq = 0;
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'A']++; // Increase the freq. of the Char
                maxFreq = Math.max(maxFreq, hash[s.charAt(j) - 'A']); // Setting the maxFreq of any cap. Alphabet

                int changes = (j - i + 1) - maxFreq; // Conversion need to made to the longest substring

                if (changes <= k) maxLen = Math.max(maxLen, j - i + 1);
                else break;
            }
        }
        return maxLen;
    }

    public static int characterReplacementBetter(String s, int k) {
        int maxLen = 0;
        int maxFreq = 0;
        int l = 0, r = 0;
        int[] hash = new int[26];

        while (r < s.length()) {

            hash[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);

            while ((r - l + 1) - maxFreq > k) { // changes = (r - l + 1) - maxFreq
                hash[s.charAt(l) - 'A']--;
                maxFreq = 0; // Reset maxFreq
                for (int i = 0; i < 26; i++) { // Recalculate maxFreq
                    maxFreq = Math.max(maxFreq, hash[i]);
                }
                l++;
            }

            if ((r - l + 1) - maxFreq <= k) { // changes = (r - l + 1) - maxFreq
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }

    public static int characterReplacement(String s, int k) {
        int maxLen = 0;
        int maxFreq = 0;
        int l = 0, r = 0;
        int[] hash = new int[26];

        while (r < s.length()) {

            hash[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);

            if ((r - l + 1) - maxFreq > k) { // changes = (r - l + 1) - maxFreq
                hash[s.charAt(l) - 'A']--;
                l++;
            }

            if ((r - l + 1) - maxFreq <= k) { // changes = (r - l + 1) - maxFreq
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }
}