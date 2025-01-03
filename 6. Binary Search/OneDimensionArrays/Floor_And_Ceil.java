public class Floor_And_Ceil {
    public static void main(String[] args) {
        int[] floorAndCeil = getFloorAndCeil(3, new int[]{1, 2, 8, 10, 11, 12, 19});
        System.out.println(floorAndCeil[0] + " " + floorAndCeil[1]);
    }

    public static int[] getFloorAndCeil(int x, int[] arr) {
        int floor = -1, ceil = -1;
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= x) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        low = 0;
        high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{floor, ceil};
    }
}
