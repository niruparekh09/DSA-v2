import java.util.HashMap;

public class Intersection_of_Two_Linked_Lists {

    /*
     * Approach: Hashing (Brute Force)
     * Pattern: Hashing
     * Time Complexity: O(N + M) - Iterate both lists once.
     * Space Complexity: O(N) - Storing all nodes of List A in a Map.
     */
    public ListNode getIntersectionNodeUsingHashing(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> store = new HashMap<>();
        ListNode tempA = headA;

        // Store all node references from List A
        while (tempA != null) {
            store.put(tempA, 1);
            tempA = tempA.next;
        }

        ListNode tempB = headB;
        // Check List B nodes against Map. First match is the intersection.
        while (tempB != null) {
            if (store.containsKey(tempB)) return tempB;
            tempB = tempB.next;
        }
        return null;
    }

    /*
     * Approach: Length Difference Synchronization
     * Pattern: Two Pointers
     * Time Complexity: O(2N + 2M) -> O(N + M) - Calculate lengths + traverse again.
     * Space Complexity: O(1) - No external storage.
     */
    public ListNode getIntersectionNodeUsingLengthOptimization(ListNode headA, ListNode headB) {
        int n1 = 0, n2 = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;

        // Step 1: Calculate length of both lists
        while (tempA != null) {
            n1++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            n2++;
            tempB = tempB.next;
        }

        // Step 2: Call helper, passing the longer list first to handle alignment
        if (n1 > n2) {
            return findCollision(headA, headB, n1, n2);
        } else {
            return findCollision(headB, headA, n2, n1);
        }
    }

    // Helper: Moves the pointer of the longer list ahead by (lenDiff) steps
    public ListNode findCollision(ListNode headB, ListNode headA, int n2, int n1) {
        ListNode tempB = headB; // The longer list
        ListNode tempA = headA; // The shorter list

        int lenDiff = n2 - n1;
        // Align starting positions
        while (lenDiff > 0) {
            lenDiff--;
            tempB = tempB.next;
        }

        // Step 3: Move both pointers simultaneously until they collide (reference equality)
        while (tempA != null && tempB != null) {
            if (tempA.equals(tempB)) return tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }

    /*
     * Approach: Two Pointers (Optimal / Track Switching)
     * Pattern: Two Pointers
     * Time Complexity: O(N + M) - Both pointers traverse (N + M) distance in worst case.
     * Space Complexity: O(1) - Constant space.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;

        // Key Logic: Equalize the distance traveled.
        // Pointer A traverses A then switches to Head B.
        // Pointer B traverses B then switches to Head A.
        // Total distance for both = LenA + LenB. They will meet at the intersection or at null (end).
        while (tempA != tempB) {
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA; // Returns intersection node or null (if no intersection found)
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}