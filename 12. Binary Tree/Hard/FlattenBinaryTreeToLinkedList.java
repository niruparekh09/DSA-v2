import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {
    // Keeps track of the previously processed node (acts like a tail pointer in the flattened list)
    TreeNode prev = null;

    public void flattenBrute(TreeNode root) {
        // Base case: if current node is null, nothing to flatten
        if (root == null) return;

        // Step 1: Recursively flatten the right subtree first
        flattenBrute(root.right);

        // Step 2: Recursively flatten the left subtree next
        flattenBrute(root.left);

        // Step 3: Connect current node’s right pointer to the previously processed node
        root.right = prev;

        // Step 4: Set left child to null (since we want a right-skewed linked list)
        root.left = null;

        // Step 5: Move 'prev' to current node — this node becomes the new head of the flattened list
        prev = root;
    }

    public void flattenBruteItr(TreeNode root) {
        prev = null; //As we have used it before
        //Base Case
        if (root == null) return;

        Stack<TreeNode> st = new Stack<>();

        st.push(root);

        while (!st.isEmpty()) {
            TreeNode cur = st.pop();

            // Add Right Child first so that it is processed after left child
            if (cur.right != null) st.push(cur.right);

            if (cur.left != null) st.push(cur.left);

            // So that the right of current now is connected to the left child
            // (right child is disconnected but now worries as it is stack)
            if (!st.isEmpty()) cur.right = st.peek();

            cur.left = null; // Disconnecting the left child as it is connected to right now.
        }
    }

    // Using Morris PreOrder
    /*
            1
           / \
          2   5
         / \   \
        3   4   6

    * | Step | `cur` (before) | `pre` (found)                                    | Actions performed                                                                      | Resulting (right-chain from root) / notes                                                               |
      | ---- | -------------- | ------------------------------------------------ | -------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
      | 1    | `1`            | `pre = 2` → advance to `4` (rightmost of 1.left) | `4.right = 1.right(5)` → `1.right = 1.left(2)` → `1.left = null` → `cur = 1.right (2)` | Chain from root: `1 -> 2 -> 4 -> 5 -> 6` (but next step will change 2's right). `2.left=3` still exists |
      | 2    | `2`            | `pre = 3` (3 is rightmost of 2.left)             | `3.right = 2.right(4)` → `2.right = 2.left(3)` → `2.left = null` → `cur = 2.right (3)` | Chain from root: `1 -> 2 -> 3 -> 4 -> 5 -> 6` (`all left` of 1 and 2 are now `null`)                    |
      | 3    | `3`            | — (3.left is null)                               | no linking; `cur = cur.right` → `cur = 4`                                              | Chain unchanged: `1 -> 2 -> 3 -> 4 -> 5 -> 6`                                                           |
      | 4    | `4`            | — (4.left is null)                               | no linking; `cur = cur.right` → `cur = 5`                                              | Chain unchanged                                                                                         |
      | 5    | `5`            | — (5.left is null)                               | no linking; `cur = cur.right` → `cur = 6`                                              | Chain unchanged                                                                                         |
      | 6    | `6`            | — (6.left is null)                               | no linking; `cur = cur.right` → `cur = null` (end)                                     | Final chain: `1 -> 2 -> 3 -> 4 -> 5 -> 6` (every `left = null`)                                         |
    */
    public void flatten(TreeNode root) {
        TreeNode cur = root;

        while (cur != null) {

            // If Left Child Exists, Link the right most node of left subtree to cur.right and then cur.left = null
            if (cur.left != null) {
                TreeNode pre = cur.left;

                // Finding right most node in the left subtree
                while (pre.right != null) pre = pre.right;

                // Link the right most child of the left subtree to cur right
                pre.right = cur.right;

                // We will break the cur from right subtree and prev is pointing to the right subtree now.
                // We will transfer the right subtree chain to left subtree and disconnect the left chain.
                cur.right = cur.left;

                // Disconnect the left subtree from the current
                cur.left = null;
            }

            // Cur will move to right subtree as the cur doesn't have a left subtree anymore.
            cur = cur.right;
        }
    }

    private class TreeNode {
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
