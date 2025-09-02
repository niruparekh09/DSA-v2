import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int size = queue.size();
            // Create a list to hold the values of nodes at the current level
            List<Integer> list = new ArrayList<>();

            // Process all nodes at the current level
            while (size > 0) {
                // Remove the front node from the queue
                TreeNode popped = queue.remove();
                // Add its value to the current level's list
                list.add(popped.data);

                if (popped.left != null) queue.add(popped.left);
                if (popped.right != null) queue.add(popped.right);

                // Decrement the counter for the current level
                size--;
            }
            ans.add(list); // Add the current level's list to the final result
        }
        return ans;
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
