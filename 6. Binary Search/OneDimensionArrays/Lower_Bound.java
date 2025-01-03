public class Lower_Bound {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 7, 8, 9, 9, 9, 11};
        int lb = findLB(arr, arr.length, 1);
        System.out.println(lb);

        System.out.println(findFloor(new int[]{10143, 29122, 30010}, 23112));
    }

    // smallest idx such that arr[idx] >= x
    // according to above e.g. the ans. will be 0 and 1 >= 1 and idx = 0.
    public static int findLB(int[] arr, int n, int x) {
        int ans = n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    //    Input: arr[] = [1, 2, 8, 10, 11, 12, 19], k = 5
//    Output: 1
//    Explanation: Largest Number less than 5 is 2 , whose index is 1.
    public static int findFloor(int[] arr, int k) {
        int ans = -1;
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > k) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }
}
