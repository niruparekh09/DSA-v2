public class Find_Sqrt {

    public static void main(String[] args) {
        System.out.println(floorSqrt(28));
    }

    public static int floorSqrt(int n) {
        // Your code here
        int ans = -1;
        int low = 1, high = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid * mid <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
