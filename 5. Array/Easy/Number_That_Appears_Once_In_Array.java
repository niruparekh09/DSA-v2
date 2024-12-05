package Easy;

public class Number_That_Appears_Once_In_Array {
    public static void main(String[] args) {
        System.out.println(getNumber(new int[]{1, 1, 2, 2, 3, 4, 4}));
    }

    private static int getNumber(int[] nums) {
        int number = 0;
        for (int num : nums) {
            number = number ^ num;
        }
        return number;
    }
}
