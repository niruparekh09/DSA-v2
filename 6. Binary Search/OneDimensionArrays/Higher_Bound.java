public class Higher_Bound {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 7, 8, 9, 9, 9, 11};
        int hb = findHB(arr, arr.length, 8);
        System.out.println(hb);
    }

    // smallest idx such that arr[idx] > x
    // according to above e.g. the ans. will be 6 and 9 > 8 and idx = 6 [First occurrence of 9].
    public static int findHB(int[] arr, int n, int x) {
        int ans = n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
