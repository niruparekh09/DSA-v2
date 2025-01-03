public class Search_In_Rotated_Array_With_Unique_Elements {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;

            //Checking if left half is sorted i.e. low should be less or equal to mid
            if (arr[low] <= arr[mid]) {
                // Checking if target lies in this sorted left half of the array.
                if (arr[low] <= target && target <= arr[mid]) {
                    // If it lies in the left sorted half of the array than eliminate right half of the array.
                    high = mid - 1;
                } else { // If it doesn't in left sorted half of array then eliminate left sorted half.
                    low = mid + 1;
                }
            } else { // This is means that the right half is sorted
                // We will perform similar operation as above i.e. if target is in right sorted half than eliminate
                // than left half or else eliminate the right sorted half
                if (arr[mid] <= target && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
