public class MaxScore {

    public static void main(String[] args) {
        System.out.println(maxScore(new int[]{6, 2, 3, 4, 7, 2, 1, 7, 1}, 4));
    }

    public static int maxScore(int[] cardPoints, int k) {
        if (k > cardPoints.length) return -1;

        int maxScoreSum = 0, lSum = 0, rSum = 0;

        // Finding sum of the first k elements i.e. all the left elements.
        for (int i = 0; i < k; i++) lSum += cardPoints[i];

        maxScoreSum = lSum;

        int rIndex = cardPoints.length - 1;

        for (int i = k - 1; i >= 0; i--) {
            lSum = lSum - cardPoints[i]; // Reducing sum of the right most element in lSum.
            rSum = rSum + cardPoints[rIndex]; // Adding the left most element in rSum.
            rIndex--; // Moving right index to left by one position.

            maxScoreSum = Math.max(maxScoreSum, lSum + rSum);
        }

        return maxScoreSum;
    }
}