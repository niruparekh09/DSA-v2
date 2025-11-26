import java.util.ArrayList;

public class Intersection_Of_Two_Sorted_Arrays {
    public static void main(String[] args) {
        ArrayList<Integer> union = findIntersection(new int[]{1, 2, 2, 3, 3, 4, 5, 6}, new int[]{2, 3, 3, 5, 6, 6, 7});

        System.out.println(union.toString());
    }

    public static ArrayList<Integer> findIntersection(int[] a, int[] b) {
        ArrayList<Integer> intersection = new ArrayList<>();
        int i = 0;
        int j = 0;
        int n1 = a.length;
        int n2 = b.length;
        while (i < n1 && j < n2) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                intersection.add(a[i]);
                i++;
                j++;
            }
        }
        return intersection;
    }

}
