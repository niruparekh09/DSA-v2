import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class InOrderTraversal {

    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }

    public void helper(TreeNode root, List<Integer> ans) {
        if (Objects.isNull(root)) return;

        helper(root.left, ans);
        ans.add(root.data);
        helper(root.right, ans);
    }

    public List<Integer> inorderTraversalItr(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (Objects.isNull(root)) return ans;

        Stack<TreeNode> st = new Stack<>();

        TreeNode node = root;

        while (true) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                if (st.isEmpty()) break;
                TreeNode popped = st.pop();
                ans.add(popped.data);
                node = popped.right;
            }
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