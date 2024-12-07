import java.util.HashMap;
import java.util.Map;

public class Majority_Element {
    public static void main(String[] args) {
        System.out.println(getMajorElementUsingMoores(new int[]{2, 2, 3, 3, 1, 2, 2}));

    }

    private static int getMajorElementUsingMoores(int[] arr) {
        int el = 0;
        int count = 0;
        for (int i : arr) {
            if (count == 0) {
                count = 1;
                el = i;
            } else if (el == i) {
                count++;
            } else {
                count--;
            }
        }

        int count1 = 0;
        for (int i : arr) {
            if (i == el) {
                count1++;
            }
        }
        if (count1 > arr.length / 2) return el;
        return -1;
    }

    private static int getMajorElement(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int element : arr) {
/*            if (map.containsKey(element)) {
                map.compute(element,
                        (k, value) -> value + 1);
                continue;
            }
            map.put(element, 1);*/
            int value = map.getOrDefault(element, 0);
            map.put(element, value + 1);
        }
        System.out.println(map.toString());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) return entry.getKey();
        }
        return -1;
    }


}
