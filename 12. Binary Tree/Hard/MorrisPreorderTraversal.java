import java.util.ArrayList;
import java.util.List;

public class MorrisPreorderTraversal {
    public List<Integer> preorder(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        TreeNode cur = root;

        while (cur != null) {
            // Case 1: If there is no left child
            if (cur.left == null) {
                preorder.add(cur.data);
                cur = cur.right;
            }

            // Case 2: If left child exists
            else {
                TreeNode prev = cur.left;

                // prev.right == cur for second visit
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                // First visit
                // We are accessing the cur in this case because Preorder follows RoLeRi,
                // so we need to add the cur (root) in first visit itself.
                if (prev.right == null) {
                    prev.right = cur;
                    preorder.add(cur.data);
                    cur = cur.left;
                }

                // Second visit
                // In Morris Inorder, we add during the second visit (after left subtree),
                // because inorder follow LeRoRe
                else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }

        return preorder;
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
