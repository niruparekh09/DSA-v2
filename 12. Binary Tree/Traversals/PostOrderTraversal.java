import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    static class TreeNode {
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