import java.util.Arrays;

public class Quick_Sort {
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {   // For more than one element only
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // Taking first element as the pivot
        int i = low;
        int j = high;
        while (i < j) {
            // We are shifting i towards right till we don't find the element greater than pivot
            while (arr[i] <= pivot && i <= high - 1) {
                i++;
            }

            // We are shifting j towards left will we don't find the element lesser than pivot
            while (arr[j] > pivot && j >= low + 1) {
                j--;
            }

            // Once we find out the i and j, we will swap them. Idea is to keep smaller elements to right
            // of pivot and larger elements to left of the pivot.
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Once while loop has ended then 'j' would've exceeded i. So now we will swap the last element j
        // to pivot element so that it is center.
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        // We will return the pivot element's new index i.e. the partition index
        return j;
    }
}
