public class Second_Largest_Element {
    public static void main(String[] args) {
        System.out.println(getSecondLargest(new int[]{12, 35, 1, 10, 34, 1}));
    }

    public static int getSecondLargest(int[] arr) {
        int largest = arr[0]; // First element
        int slargest = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            if (largest < arr[i]) {
                slargest = largest;
                largest = arr[i];
            } else if (largest > arr[i] && slargest < arr[i]) {
                slargest = arr[i];
            }
        }

        return slargest;
    }
}
