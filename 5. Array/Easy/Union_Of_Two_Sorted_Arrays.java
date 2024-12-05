package Easy;

import java.util.ArrayList;

public class Union_Of_Two_Sorted_Arrays {
    public static void main(String[] args) {
        ArrayList<Integer> union = findUnion(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 6, 7});

        System.out.println(union.toString());
    }

    public static ArrayList<Integer> findUnion(int[] a, int[] b) {
        ArrayList<Integer> unionList = new ArrayList<>();
        int n1 = a.length;
        int n2 = b.length;
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (a[i] < b[j]) {
                if (unionList.isEmpty() || unionList.getLast() != a[i]) {
                    unionList.add(a[i]);
                }
                i++;
            } else {
                if (unionList.isEmpty() || unionList.getLast() != b[j]) {
                    unionList.add(b[j]);
                }
                j++;
            }
        }

        while (i < n1) {
            if (unionList.isEmpty() || unionList.getLast() != a[i]) {
                unionList.add(a[i]);
            }
            i++;
        }

        while (j < n2) {
            if (unionList.isEmpty() || unionList.getLast() != b[j]) {
                unionList.add(b[j]);
            }
            j++;
        }

        return unionList;
    }
}
