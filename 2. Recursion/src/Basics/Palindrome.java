package Basics;

public class Palindrome {

    public static boolean check(int i, String s, int n) {
        if (i >= n / 2) return true;

        if (s.charAt(i) != s.charAt(n - i - 1)) return false;
        return check(i + 1, s, n);
    }

    public static void main(String[] args) {
        String s = "\"A man, a plan, a canal: Panama\"";
        String s1 = s.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(s1);
        System.out.println(check(0, s, s.length()));
    }
}
