import java.util.ArrayList;
import java.util.List;

public class MorrisInorderTraversal {
    /*
     *
     *         1
     *        / \
     *       2   3
     *      / \
     *     4   5
     *
     *   | Step | `cur` | Action                              | Threading / Operation                | `inorder`       | Comment                           |
     *   | ---- | ----- | ----------------------------------- | ------------------------------------ | --------------- | --------------------------------- |
     *   | 1    | 1     | Has left (2)                        | Find predecessor (5) → `5.right = 1` | []              | Create thread, go left            |
     *   | 2    | 2     | Has left (4)                        | Find predecessor (4) → `4.right = 2` | []              | Create thread, go left            |
     *   | 3    | 4     | No left                             | Add `4`                              | [4]             | Visit 4, move right (thread to 2) |
     *   | 4    | 2     | `prev.right == cur` → remove thread | Add `2`                              | [4, 2]          | Visit 2, go right (to 5)          |
     *   | 5    | 5     | No left                             | Add `5`                              | [4, 2, 5]       | Visit 5, right points to 1        |
     *   | 6    | 1     | `prev.right == cur` → remove thread | Add `1`                              | [4, 2, 5, 1]    | Visit 1, go right (3)             |
     *   | 7    | 3     | No left                             | Add `3`                              | [4, 2, 5, 1, 3] | Visit 3, right = null → done      |
     */
    public List<Integer> getInorder(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        TreeNode cur = root;

        while (cur != null) {
            // Case 1: No left child → visit node, move right
            if (cur.left == null) {
                inorder.add(cur.data);
                cur = cur.right;
            }
            // Case 2: Has a left child → find inorder predecessor
            else {
                TreeNode prev = cur.left;

                // Move to the rightmost node in left subtree (inorder predecessor)
                // prev.right == cur for second visit
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                /*
                * 🟢 First Visit
                    - We arrive at cur for the first time.
                    - It has a left child.
                    - We can’t visit it yet because inorder means: Left → Node → Right.
                    - So, we:
                      1. Find its inorder predecessor (prev = rightmost node in left subtree).
                      2. Create a temporary thread from prev.right → cur.
                      3. Move cur = cur.left.
                    - This ensures that after we finish exploring the left subtree, we’ll come back to cur through this thread.

                * 🔵 Second Visit
                    - We reach cur again via the temporary thread (prev.right == cur).
                    - This means the entire left subtree is done.
                    - Now, we can:
                      1. Remove the thread (prev.right = null).
                      2. Visit the current node (inorder.add(cur.data)).
                      3. Move right (cur = cur.right).
                */

                // First Visit
                if (prev.right == null) {
                    prev.right = cur;       // Thread to return after left subtree
                    cur = cur.left;         // Move to left child. So before we can “visit” the current node (before second visit), we must first process its entire left subtree.
                }
                // Second Visit
                else {
                    prev.right = null;      // Remove the temporary thread
                    inorder.add(cur.data);  // Visit current node
                    cur = cur.right;        // Move to right subtree
                }
            }
        }
        return inorder;
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int _data) {
            this.data = _data;
            this.left = null;
            this.right = null;
        }
    }
}
