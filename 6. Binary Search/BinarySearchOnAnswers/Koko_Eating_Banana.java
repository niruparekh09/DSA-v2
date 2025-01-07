package BSOnAns;

public class Koko_Eating_Banana {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        long low = 1, high = getMax(piles);
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (getHours(piles, mid) > h)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return (int) low;
    }

    public static long getHours(int[] piles, long mid) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (long) Math.ceil((double) pile / (double) mid);
        }
        return totalHours;
    }

    public static int getMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }
}
