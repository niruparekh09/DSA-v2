import java.util.TreeMap;

public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        while (!map.isEmpty()) {
            int start = map.firstKey();

            for (int i = 0; i < groupSize; i++) {
                int key = start + i;
                if (!map.containsKey(key)) return false;

                map.merge(key, -1, Integer::sum); // decrement
                if (map.get(key) == 0) map.remove(key);
            }
        }

        return true;
    }
}
