import java.util.*;

public class ZigZagTraversal {

    // Given the root of a binary tree, return the zigzag level order traversal of its nodes'
    // values. (i.e., from left to right, then right to left for the next level and alternate between).

    /*
    Input : root = [1, 2, 3, null, 4, 8, 5]
    Output : [ [1] , [3, 2], [4, 8, 5] ]
    Explanation : So at root we move from left to right.
    At next level we move in opposite direction i.e. from right to left.
    At next level again reverse the traversal i.e. from left to right.
    */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        int flag = 0; // If 0 then insert L -> R or else R -> L

        Queue<TreeNode> travQ = new LinkedList<>();

        travQ.add(root);

        while (!travQ.isEmpty()) {
            int size = travQ.size();

            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode removedNode = travQ.remove();
                if (removedNode.left != null) travQ.add(removedNode.left);
                if (removedNode.right != null) travQ.add(removedNode.right);
                levelList.add(removedNode.data);
            }

            if (flag == 0) ans.add(levelList);
            else {
                Collections.reverse(levelList);
                ans.add(levelList);
            }

            flag = (flag == 0) ? 1 : 0; // Can also do flag ^= 1;
        }
        return ans;
    }

    // A little different approach with better time complexity
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        int flag = 0; // If 0 then insert L -> R or else R -> L

        Queue<TreeNode> travQ = new LinkedList<>();

        travQ.add(root);

        while (!travQ.isEmpty()) {
            int size = travQ.size();

            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode removedNode = travQ.remove();
                if (removedNode.left != null) travQ.add(removedNode.left);
                if (removedNode.right != null) travQ.add(removedNode.right);

                // Rather than reversing the list and adding to the ans, We are directly insert node data in list as that order
                if (flag == 0) {
                    levelList.addLast(removedNode.data);
                } else {
                    levelList.addFirst(removedNode.data);
                }
            }
            ans.add(levelList);
            flag ^= 1;
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
