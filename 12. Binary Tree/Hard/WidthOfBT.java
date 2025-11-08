import java.util.LinkedList;
import java.util.Queue;

public class WidthOfBT {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int width = 1;

        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {

            int minIdxOfLevel = queue.peek().index; // To get the min index of the node for a level

            int first = 0, last = 0;

            int qSize = queue.size();

            // Loop to remove the nodes from (queue) a level and add nodes (to queue) from next level
            for (int i = 0; i < qSize; i++) {
                Pair removedPair = queue.poll();
                TreeNode node = removedPair.node;
                int nodeIdx = removedPair.index;

                // Condition to get first and last to calc width
                if (i == 0) first = nodeIdx;
                if (i == qSize - 1) last = nodeIdx;

                // Adding left and right nodes. Calc idx in such a way to avoid overflow issue
                if (node.left != null) {
                    Pair leftNode = new Pair(node.left, (2 * (nodeIdx - minIdxOfLevel) + 1));
                    queue.add(leftNode);
                }

                if (node.right != null) {
                    Pair rightNode = new Pair(node.right, (2 * (nodeIdx - minIdxOfLevel) + 2));
                    queue.add(rightNode);
                }
            }

            width = Math.max(width, (last - first + 1));

        }

        return width;
    }

    private class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
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
