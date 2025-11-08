import java.util.*;

public class NodesAtDistanceK {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();

        // Parent Mapping for a Node; Map<Child,Parent>
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Queue for Level Order Traversal (BFS)
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        // BFS to create child-parent mapping
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode parentNode = q.remove();

                if (parentNode.left != null) {
                    q.offer(parentNode.left);
                    parentMap.put(parentNode.left, parentNode);
                }

                if (parentNode.right != null) {
                    q.offer(parentNode.right);
                    parentMap.put(parentNode.right, parentNode);
                }
            }
        }

        Set<TreeNode> visited = new HashSet<>();
        int distance = 0;

        q.offer(target); // Adding the target node the Q.
        visited.add(target);

        while (!q.isEmpty()) {
            if (distance == k) {
                for (TreeNode node : q) {
                    ans.add(node.data);
                }
                return ans;
            }
            int size
                    = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                // Check left child
                if (node.left != null && !visited.contains(node.left)) {
                    q.add(node.left);
                    visited.add(node.left);
                }
                // Check right child
                if (node.right != null && !visited.contains(node.right)) {
                    q.add(node.right);
                    visited.add(node.right);
                }
                // Check parent
                if (parentMap.containsKey(node) && !visited.contains(parentMap.get(node))) {
                    q.add(parentMap.get(node));
                    visited.add(parentMap.get(node));
                }
            }
            distance++;
        }

        return ans;
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
