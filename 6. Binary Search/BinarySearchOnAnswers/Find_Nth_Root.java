package BSOnAns;

public class Find_Nth_Root {

    public static void main(String[] args) {
        System.out.println(nthRoot(4, 49));
    }

    public static int nthRoot(int n, int m) {
        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midN = pow(mid, n, m);
            if (midN == 1) {
                return mid;
            } else if (midN == 2) { // midN == 2 i.e. mid^n > m so we will find the number in left side of the array
                high = mid - 1;
            } else { // midN == 0 i.e. mid^n < m so we will find the number in right side of the array
                low = mid + 1;
            }
        }
        return -1;
    }

    // ret 1 if == m, ret 2 if > m and ret 0 if < m
    public static int pow(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) return 2;
        }
        if (ans == m) return 1;
        return 0;
    }
}
