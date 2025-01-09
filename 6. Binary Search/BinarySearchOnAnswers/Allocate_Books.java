public class Allocate_Books {
    public static void main(String[] args) {
        System.out.println(findPages(new int[]{25, 46, 28, 49, 24}, 4));
    }

    public static int findPages(int[] arr, int k) {
        int low = Integer.MIN_VALUE, high = 0;
        if (k > arr.length) return -1;
        for (int el : arr) {
            low = Math.max(low, el);
            high += el;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countStudents(arr, mid) > k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // Let's say mid = 71
    private static int countStudents(int[] pagesOfBooks, int maxPagesPerStudent) {
        int students = 1, pagesStudent = 0;
        for (int pagesOfBook : pagesOfBooks) {
            if (pagesStudent + pagesOfBook <= maxPagesPerStudent) { // 25 + 46 <= 71
                pagesStudent += pagesOfBook;
            } else { // 28 + 49 > 71
                students++;
                pagesStudent = pagesOfBook; // Resetting pagesStudent i.e. counting pages for new stud
            }
        }
        return students; // Sending total student between whom books were distributed according to maxPagesPerStudent
    }
}
