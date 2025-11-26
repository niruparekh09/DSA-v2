public class Allocate_Books {
    public static void main(String[] args) {
        System.out.println(findPages(new int[]{25, 46, 28, 49, 24}, 4));
    }

    /*
     * Approach: Binary Search on Answer (Minimize the Maximum)
     * Pattern: Modified Binary Search
     * Time Complexity: O(N * log(Sum - Max)) - Search space is range of pages, N is iteration in predicate.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findPages(int[] arr, int k) {
        // Edge Case: Cannot allocate if there are fewer books than students (assuming 1 book per student min)
        if (k > arr.length) return -1;

        int low = Integer.MIN_VALUE, high = 0;

        // Step 1: Define Search Space
        // Low: Max element (A student must be able to hold the largest book alone).
        // High: Sum of elements (One student takes all books).
        for (int el : arr) {
            low = Math.max(low, el);
            high += el;
        }

        // Step 2: Binary Search for the minimum feasible "Max Pages"
        while (low <= high) {
            int mid = low + (high - low) / 2; // mid represents our hypothetical "Max Page Limit"

            // Logic: If with this limit ('mid'), we need MORE students than 'k',
            // then the limit is too tight (too small). We must increase capacity.
            if (countStudents(arr, mid) > k) {
                low = mid + 1;
            }
            // Logic: If we fit within 'k' students, this limit is possible.
            // But we want the *minimum* maximum, so we try a smaller limit.
            else {
                high = mid - 1;
            }
        }
        // In minimization problems, 'low' ends up pointing to the smallest valid answer.
        return low;
    }

    // Helper: Predicate Function (Greedy Approach)
    // Counts how many students are needed if each can hold at most 'maxPagesPerStudent'
    private static int countStudents(int[] pagesOfBooks, int maxPagesPerStudent) {
        int students = 1, pagesStudent = 0;
        for (int pagesOfBook : pagesOfBooks) {
            // Try adding book to current student
            if (pagesStudent + pagesOfBook <= maxPagesPerStudent) {
                pagesStudent += pagesOfBook;
            } else {
                // Limit exceeded: Assign this book to a NEW student
                students++;
                pagesStudent = pagesOfBook;
            }
        }
        return students;
    }
}