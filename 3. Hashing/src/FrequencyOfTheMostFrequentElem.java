public class FrequencyOfTheMostFrequentElem {
    public int maxFrequency(int[] nums, int k) {
        int cnt = 0;
        int i = 0, j = nums.length - 1;

        while (k > 0 && i < j) {
            if(nums[i]+k>=nums[j]){

                i++;
            } else {
                j--;
            }
        }

        return cnt;
    }
}
