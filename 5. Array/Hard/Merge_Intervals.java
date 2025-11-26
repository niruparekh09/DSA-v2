import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge_Intervals {
    public static void main(String[] args) {
        int[][] merge = mergeOptimal(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        System.out.println(Arrays.deepToString(merge));
    }

    public static int[][] merge(int[][] arr) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int start = arr[i][0]; // First element of each pair
            int end = arr[i][1]; // Second element of each pair
            // Get the second element of the last pair and check if the new pairs end is smaller or not.
            // If it is smaller than you don't need to check further just continue with next pair.
            if (!ans.isEmpty() && end <= ans.getLast()[1]) continue;
            for (int j = i + 1; j < n; j++) {
                //If first element of the next pair(j = i+1->n) is smaller or equal than the end of the ith pair
                // then only of the second element of the next pair.
                if (arr[j][0] <= end) {
                    // Check if the next pair's end is bigger than the current end than update or else don't
                    end = Math.max(end, arr[j][1]);
                } else {
                    break;
                }
            }
            ans.add(new int[]{start, end});
        }
        return ans.toArray(new int[0][]);
    }

    public static int[][] mergeOptimal(int[][] arr) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (ans.isEmpty()) {
                ans.add(new int[]{arr[0][0], arr[0][1]});
                continue;
            }
            int[] lastElement = ans.getLast();
            if (lastElement[1] >= arr[i][0]) {
                if (lastElement[1] < arr[i][1]) lastElement[1] = arr[i][1];
            } else {
                ans.add(new int[]{arr[i][0], arr[i][1]});
            }
        }
        return ans.toArray(new int[0][]);
    }
}

