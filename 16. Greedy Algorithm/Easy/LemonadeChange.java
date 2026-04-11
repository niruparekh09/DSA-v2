public class LemonadeChange {
    /*
     * Approach: Greedy Algorithm
     * Pattern: Real-time Resource Management
     * Time Complexity: O(N) - Single pass through the bills array.
     * Space Complexity: O(1) - Constant space used for change counters.
     */
    public boolean lemonadeChange(int[] bills) {
        // We only track $5 and $10 bills because $20 bills cannot be used as change.
        int five = 0, ten = 0;


        for (int bill : bills) {
            // Case 1: Customer pays with $5. No change needed.
            if (bill == 5) {
                five++;
            }
            // Case 2: Customer pays with $10. Must give back one $5.
            else if (bill == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else {
                    return false; // Cannot provide change
                }
            }
            /* * Case 3: Customer pays with $20.
             * Greedy Choice: Always try to use a $10 + $5 first.
             * Why? A $5 bill is more "valuable" for change because it can satisfy
             * both $10 and $20 customers, whereas a $10 only satisfies $20 customers.
             */
            else if (bill == 20) {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false; // Insufficient small bills
                }
            }
        }

        return true;
    }
}