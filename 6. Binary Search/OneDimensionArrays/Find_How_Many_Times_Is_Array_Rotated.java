import java.util.List;

public class Find_How_Many_Times_Is_Array_Rotated {
    public static void main(String[] args) {
        System.out.println(findRotation(List.of(4, 5, 1, 2, 3)));
    }

    public static int findRotation(List<Integer> arr) {
        int ans = Integer.MAX_VALUE;
        int index = 0;
        int low = 0, high = arr.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(low) <= arr.get(mid)) {
                if (ans > arr.get(low)) {
                    index = low;
                    ans = arr.get(low);
                }
                low = mid + 1;
            } else {
                if (ans > arr.get(mid)) {
                    index = mid;
                    ans = arr.get(mid);
                }
                high = mid - 1;
            }
        }
        return index;
    }
}
