package Medium;

import java.util.ArrayList;
import java.util.Collections;

public class Leaders_In_An_Array {
    public static void main(String[] args) {
        ArrayList<Integer> ans = find(new int[]{10, 22, 12, 5, 0, 6});
        System.out.println(ans.toString());
    }

    private static ArrayList<Integer> find(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = arr.length;
        int maxi = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            //if (arr[i] >= maxi) if question says that equal occurrence can be also consider a leader
            if (arr[i] > maxi) {
                ans.add(arr[i]);
            }
            maxi = Math.max(arr[i], maxi);
        }
        Collections.sort(ans);
        return ans;
    }
}
