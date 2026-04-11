import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;

        int ans = 0;

        while (i < s.length && j < g.length) {
            if (g[j] <= s[i]) {
                ans++;
                j++;
                i++;
            } else {
                i++;
            }
        }
        return ans;
    }
}