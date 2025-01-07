package BSOnAns;

public class Capacity_To_Ship_Packages_In_D_Days {
    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        /*
        Output: 15
        Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
        1st day: 1, 2, 3, 4, 5 {total: 15}
        2nd day: 6, 7
        3rd day: 8
        4th day: 9
        5th day: 10
        */
    }

    public static int shipWithinDays(int[] weights, int days) {
        int low = Integer.MIN_VALUE, high = 0;
        for (int weight : weights) {
            high += weight;
            low = Math.max(low, weight);
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int daysReq = daysReq(weights, mid);
            if (daysReq <= days) { // Checking if for loading less or equal days are required for a particular capacity
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int daysReq(int[] weights, int capacity) {
        int days = 1, load = 0;
        for (int weight : weights) {
            if (load + weight > capacity) {
                days = days + 1;
                load = weight;
            } else {
                load += weight;
            }
        }
        return days;
    }
}
