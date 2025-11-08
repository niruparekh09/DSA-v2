import java.util.HashMap;
import java.util.Map;

public class ConstructionBTFromPostorderAndInorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Map each inorder element to its index for O(1) lookup
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        // Call recursive helper to construct tree. Overloaded Method
        TreeNode root = buildTree(postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1,
                inMap);

        return root;
    }

    private TreeNode buildTree(int[] postorder, int postStart, int postEnd,
                               int[] inorder, int inStart, int inEnd,
                               Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);

        int rootIdx = inMap.get(root.data);

        int leftSubtreeSize = rootIdx - inStart;

        // Build the LEFT subtree
        // → In inorder[]: elements before rootIdx belong to left subtree (inStart → rootIdx - 1)
        // → In postorder[]: left subtree elements occupy the first 'leftSubtreeSize' positions
        root.left = buildTree(
                postorder, postStart, postStart + leftSubtreeSize - 1,   // postorder range for left subtree
                inorder, inStart, rootIdx - 1,                          // inorder range for left subtree
                inMap
        );

        // Build the RIGHT subtree
        // → In inorder[]: elements after rootIdx belong to right subtree (rootIdx + 1 → inEnd)
        // → In postorder[]: right subtree elements come just before the root element
        root.right = buildTree(
                postorder, postStart + leftSubtreeSize, postEnd - 1,     // postorder range for right subtree
                inorder, rootIdx + 1, inEnd,                            // inorder range for right subtree
                inMap
        );

        return root;
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
