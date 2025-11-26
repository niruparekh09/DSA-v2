public class Second_Largest_Element {
    public static void main(String[] args) {
        System.out.println(getSecondLargest(new int[]{12, 35, 1, 10, 34, 1}));
    }

    // Coding Pattern: None (Simple Iteration)
    // Time Complexity: O(n) - Single pass through the array
    // Space Complexity: O(1) - Constant extra space
    public static int getSecondLargest(int[] arr) {
        int largest = arr[0]; // Initialize largest with the first element
        int slargest = Integer.MIN_VALUE; // Initialize second largest to smallest possible value

        for (int i = 1; i < arr.length; i++) { // Iterate through the array
            if (largest < arr[i]) { // If current element is larger than largest
                slargest = largest; // Previous largest becomes second largest
                largest = arr[i]; // Current element becomes new largest
            } else if (largest > arr[i] && slargest < arr[i]) { // If current element is smaller than largest but larger than second largest
                slargest = arr[i]; // Current element becomes new second largest
            } // Handle edge case: If arr[i] == largest, we don't update anything
        }

        return slargest; // Return the second-largest element
    }
}
