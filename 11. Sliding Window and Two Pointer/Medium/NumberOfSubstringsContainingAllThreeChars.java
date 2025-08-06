import java.util.Arrays;

public class NumberOfSubstringsContainingAllThreeChars {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("aaabc"));
    }

    public static int numberOfSubstringsBrute(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[3];
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'a'] = 1; // a=0, b=1 and c=2; This will tell if any of the chars are present or not
                if (hash[0] + hash[1] + hash[2] == 3) cnt++; // If all chars are present
            }
        }
        return cnt;
    }

    public static int numberOfSubstringsBrute2(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[3];
            for (int j = i; j < s.length(); j++) {
                hash[s.charAt(j) - 'a'] = 1; // a=0, b=1 and c=2; This will tell if any of the chars are present or not

                // If all chars are present
                if (hash[0] + hash[1] + hash[2] == 3) {
                    cnt += s.length() - j;
                    break;
                }
            }
        }
        return cnt;
    }

    public static int numberOfSubstrings(String s) {
        int cnt = 0;
        int[] hash = new int[3];
        Arrays.fill(hash, -1); // Fill up the array with -1 because we will be keeping indexes in it.
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i; // Last seen index of a particular character
            if (hash[0] != -1 && hash[1] != -1 && hash[2] != -1) { // All the elems are present
                int minIdx = Math.min(hash[0], Math.min(hash[1], hash[2])); // Getting the min index of the element last seen
                cnt = cnt + minIdx + 1;
            }
        }
        return cnt;
    }
}
