import java.util.HashMap;
import java.util.Map;

public class ConstructionBTFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map each inorder element to its index for O(1) lookup
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        // Call recursive helper to construct tree. Overloaded Method
        TreeNode root = buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                inMap);

        return root;
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd,
                               Map<Integer, Integer> inMap) {
        // Base case: no elements to process
        if (preStart > preEnd || inStart > inEnd) return null;

        // Root is first element in current preorder range
        TreeNode root = new TreeNode(preorder[preStart]);

        // Find root index in inorder array
        int rootIdx = inMap.get(root.data);

        // Number of nodes in left subtree
        int leftSubtreeSize = rootIdx - inStart;

        // Recursively build left subtree
        // preorder range: next element after root to end of left subtree
        // inorder range: from inStart to root index - 1 (elements left of root)
        // inMap is used for quick root lookups
        root.left = buildTree(
                preorder, preStart + 1, preStart + leftSubtreeSize,
                inorder, inStart, rootIdx - 1,
                inMap);

        // Recursively build right subtree
        // preorder range: start after left subtree to preEnd
        // inorder range: root index + 1 to inEnd (elements right of root)
        root.right = buildTree(
                preorder, preStart + leftSubtreeSize + 1, preEnd,
                inorder, rootIdx + 1, inEnd,
                inMap);

        // Return constructed subtree rooted at current root
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
