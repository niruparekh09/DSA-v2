public class Get_Days_To_Make_Bouquets {
    public static void main(String[] args) {
        System.out.println(minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        if ((long) bloomDay.length < ((long) m * k)) return -1; // Converting to long to avoid overflow

        int ans = getMiniOrMaxi(bloomDay, false);
        int low = getMiniOrMaxi(bloomDay, true), high = getMiniOrMaxi(bloomDay, false);
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If possible than set the ans as the current mid and further check if ans. can be reduced
            if (isPossible(bloomDay, mid, m, k)) {
                ans = Math.min(ans, mid);
                high = mid - 1;
            } else { // If not possible than check for bigger days. I.e. in right side of the arr
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int getMiniOrMaxi(int[] bloomDay, boolean isMini) {
        if (isMini) {
            int mini = Integer.MAX_VALUE;
            for (int el : bloomDay) {
                mini = Math.min(mini, el);
            }
            return mini;
        } else {
            int maxi = Integer.MIN_VALUE;
            for (int el : bloomDay) {
                maxi = Math.max(maxi, el);
            }
            return maxi;
        }
    }

    public static boolean isPossible(int[] bloomDay, int mid, int m, int k) {
        int cnt = 0, noOfB = 0;
        for (int el : bloomDay) {
            if (el <= mid) cnt++;
            else {
                noOfB += cnt / k;
                cnt = 0;
            }
        }
        noOfB += cnt / k;
        return noOfB >= m;
    }
}
