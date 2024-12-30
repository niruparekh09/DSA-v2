public class Binary_Search {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int num = recursiveSearch(arr, 0, arr.length - 1, 9);
        System.out.println(num);
    }

    private static int search(int[] arr, int key) {
        int low = 0, high = arr.length - 1, mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int recursiveSearch(int[] arr, int low, int high, int key) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == key) return mid;
        else if (arr[mid] > key) return recursiveSearch(arr, low, mid - 1, key);
        else return recursiveSearch(arr, mid + 1, high, key);
    }
}
