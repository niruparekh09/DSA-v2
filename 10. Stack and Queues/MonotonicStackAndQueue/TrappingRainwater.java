public class TrappingRainwater {
    public static void main(String[] args) {
        // height = [0,1,0,2,1,0,1,3,2,1,2,1]
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    // Time: O(2n), Space: O(n)
    public static int trapBrute(int[] height) {
        int total = 0;
        int leftMax = height[0], rightMax = 0;
        int[] suffixMax = findSuffixMax(height);
        for (int i = 0; i < height.length; i++) {
            leftMax = Math.max(leftMax, height[i]);
            rightMax = suffixMax[i];
            if (height[i] < leftMax && height[i] < rightMax) total += Math.min(leftMax, rightMax) - height[i];
        }
        return total;
    }

    public static int[] findSuffixMax(int[] arr) {
        int[] suffixMax = new int[arr.length];
        suffixMax[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], arr[i]);
        }
        return suffixMax;
    }

    // Time: O(n), Space: O(1)
    public static int trap(int[] height) {
        int total = 0;
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (l <= r) {
            if (height[l] <= height[r]) {
                if (height[l] >= leftMax) {
                    leftMax = height[l];
                } else {
                    total += leftMax - height[l];
                }
                l++;
            } else {
                if (height[r] >= rightMax) {
                    rightMax = height[r];
                } else {
                    total += rightMax - height[r];
                }
                r--;
            }
        }
        return total;
    }
}