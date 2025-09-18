import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class PreOrderTraversal {
    public static List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    // Recursive helper function for preorder traversal
    public static void helper(TreeNode root, List<Integer> ans) {
        // Base case: if current node is null, return
        if (Objects.isNull(root)) {
            return;
        }

        ans.add(root.data);       // Step 1: Visit current node (process data)
        helper(root.left, ans);   // Step 2: Traverse left subtree recursively
        helper(root.right, ans);  // Step 3: Traverse right subtree recursively
    }

    public List<Integer> preorderTraversalItr(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // If the tree is empty, return empty list
        if (Objects.isNull(root)) return ans;

        Stack<TreeNode> traversalStack = new Stack<>();

        traversalStack.push(root); // Pushing the root node inside the stack

        while (!traversalStack.isEmpty()) {
            TreeNode poppedRootNode = traversalStack.pop();
            ans.add(poppedRootNode.data);

            /* Push right before left because Stack has LIFO.
            So left will be popped before right */
            /* Pushing right/left child if only they aren't null*/
            if (!Objects.isNull(poppedRootNode.right)) traversalStack.push(poppedRootNode.right);
            if (!Objects.isNull(poppedRootNode.left)) traversalStack.push(poppedRootNode.left);
        }
        return ans;
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