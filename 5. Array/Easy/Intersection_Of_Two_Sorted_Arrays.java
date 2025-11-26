import java.util.ArrayList;

public class Intersection_Of_Two_Sorted_Arrays {
    public static void main(String[] args) {
        ArrayList<Integer> union = findIntersection(new int[]{1, 2, 2, 3, 3, 4, 5, 6}, new int[]{2, 3, 3, 5, 6, 6, 7});

        System.out.println(union);
    }

    // Time Complexity: O(n + m) - Linear, where n and m are the lengths of the arrays.
    // Space Complexity: O(min(n, m)) -  At worst, the intersection could contain all elements of the smaller array.
    public static ArrayList<Integer> findIntersection(int[] a, int[] b) {
        ArrayList<Integer> intersection = new ArrayList<>();
        int i = 0;
        int j = 0;
        int n1 = a.length;
        int n2 = b.length;

        // Iterate while both pointers are within bounds of their respective arrays
        while (i < n1 && j < n2) {
            // If element in 'a' is smaller, advance 'a' pointer.  Elements in a are skipped because they cannot be in intersection.
            if (a[i] < b[j]) {
                i++;
                // If element in 'b' is smaller, advance 'b' pointer. Elements in b are skipped because they cannot be in intersection.
            } else if (a[i] > b[j]) {
                j++;
                // If elements are equal, add to intersection and advance both pointers. Found an element common to both arrays.
            } else {
                intersection.add(a[i]);
                i++;
                j++;
            }
        }
        return intersection;
    }
}
