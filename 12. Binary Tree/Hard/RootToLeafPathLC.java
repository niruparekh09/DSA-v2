import java.util.ArrayList;
import java.util.List;

public class RootToLeafPathLC {

    /*? Input: root = [1,2,3,null,5]
    Output: ["1->2->5","1->3"]
    */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();

        if (root != null) dfs(root, "", ans);

        return ans;
    }

    private void dfs(TreeNode root, String path, List<String> ans) {

        if (root.left == null && root.right == null) ans.add(path + root.val);

        if (root.left != null) dfs(root.left, path + "->" + root.val, ans);

        if (root.right != null) dfs(root.right, path + "->" + root.val, ans);

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
