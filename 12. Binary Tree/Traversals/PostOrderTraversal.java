import java.util.*;

public class PostOrderTraversal {
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    public void helper(TreeNode root, List<Integer> ans) {
        if (Objects.isNull(root)) return;

        helper(root.left, ans);
        helper(root.right, ans);
        ans.add(root.data);
    }

    public List<Integer> postOrderTraversalItr(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        if (root == null) return ans;

        st.push(root);

        while (!st.isEmpty()) {
            TreeNode popped = st.pop();
            ans.add(popped.data);

            if (popped.left != null) st.push(popped.left);
            if (popped.right != null) st.push(popped.right);
        }
        Collections.reverse(ans);

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