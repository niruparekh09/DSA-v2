package Subsequence;

import java.util.ArrayList;

public class Subsequences_Of_String {

    public static void main(String[] args) {
        System.out.println(subsequences("abc"));
    }

    public static ArrayList<String> subsequences(String str) {
        ArrayList<String> res = new ArrayList<>();
        generateSubsequence(str, 0, "", res);
        return res;
    }

    public static void generateSubsequence(String str, int index, String current, ArrayList<String> res) {
        if (index == str.length()) {
            res.add(current);
            return;
        }

        // To include all the character
        generateSubsequence(str, index + 1, current + str.charAt(index), res);

        // To exclude all the character
        generateSubsequence(str, index + 1, current, res);
    }
}
