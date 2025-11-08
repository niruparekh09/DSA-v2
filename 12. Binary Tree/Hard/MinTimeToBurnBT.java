import java.util.*;

public class MinTimeToBurnBT {
    public int timeToBurnTree(TreeNode root, int start) {
        int time = 0;

        Map<TreeNode, TreeNode> pMap = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode startNode = new TreeNode(Integer.MIN_VALUE);

        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.remove();

                if (node.data == start) startNode = node; // Get the start node reference

                if (node.left != null) {
                    q.offer(node.left);
                    pMap.put(node.left, node);
                }

                if (node.right != null) {
                    q.offer(node.right);
                    pMap.put(node.right, node);
                }
            }
        }

        Set<TreeNode> visited = new HashSet<>();

        q.add(startNode);
        visited.add(startNode);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.remove();

                // Left Node
                if (node.left != null && !visited.contains(node.left)) {
                    q.add(node.left);
                    visited.add(node.left);
                }

                // Right Node
                if (node.right != null && !visited.contains(node.right)) {
                    q.add(node.right);
                    visited.add(node.right);
                }

                // Parent Node
                if (pMap.containsKey(node) && !visited.contains(pMap.get(node))) {
                    q.add(pMap.get(node));
                    visited.add(pMap.get(node));
                }
            }

            time++; // Every time the path reaches outward from target node we increase time
        }

        // Subtract 1 because the last increment happens after burning the final level,
        // which makes 'time' one step greater than the actual total burn time.
        return time - 1;
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
