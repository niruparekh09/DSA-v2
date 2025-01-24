import java.util.HashMap;

public class Intersection_of_Two_Linked_Lists {

    // TC: O(N+M) SC: O(Max(N,M))
    public ListNode getIntersectionNodeUsingHashing(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> store = new HashMap<>();
        ListNode tempA = headA;
        while (tempA != null) {
            store.put(tempA, 1);
            tempA = tempA.next;
        }

        ListNode tempB = headB;
        while (tempB != null) {
            if (store.containsKey(tempB)) return tempB;
            tempB = tempB.next;
        }
        return null;
    }

    // TC: O(N+M[For Len]+Max(N,M)[For moving pointer to vertical align + For Finding node]) SC: O(1)
    public ListNode getIntersectionNodeUsingLengthOptimization(ListNode headA, ListNode headB) {
        int n1 = 0, n2 = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != null) {
            n1++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            n2++;
            tempB = tempB.next;
        }
        if (n1 > n2) {
            return findCollision(headA, headB, n1, n2);
        } else {
            return findCollision(headB, headA, n2, n1);
        }
    }

    // (bigger LL,smaller LL, bigger LL size, smaller LL size)
    public ListNode findCollision(ListNode headB, ListNode headA, int n2, int n1) {
        ListNode tempB = headB;
        ListNode tempA = headA;

        int lenDiff = n2 - n1;
        while (lenDiff > 0) {
            lenDiff--;
            tempB = tempB.next;
        }

        while (tempA != null && tempB != null) {
            if (tempA.equals(tempB)) return tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }

    // TC: O(2*Max(M,N))  SC: O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
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
