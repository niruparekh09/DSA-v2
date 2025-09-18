import java.util.*;

public class VerticalOrderTraversal {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        Queue<Tuple> q = new LinkedList<>();

        // Map<Vertical,Map<Level,PQ<Node.Data>>> A vertical has many nodes at diff level and 2 nodes can
        // also have same vertical and level so storing nodes in PQ for storing in asc order.
        // Using Tree map Because it will store the order in sorted order based on keys
        // Map<x,Map<y,PQ<data>>>
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> mpp = new TreeMap<>();

        q.add(new Tuple(root, 0, 0));

        // Traversing the nodes via Level Order Traversal
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode polledNode = tuple.node;
            int x = tuple.vertical;
            int y = tuple.level;

            // Add the node's value to the map at the correct x and y
            mpp.putIfAbsent(x, new TreeMap<>());
            mpp.get(x).putIfAbsent(y, new PriorityQueue<>());
            mpp.get(x).get(y).offer(polledNode.data);

            if (polledNode.left != null) {
                q.offer(new Tuple(polledNode.left, x - 1, y + 1));
            }

            if (polledNode.right != null) {
                q.offer(new Tuple(polledNode.right, x + 1, y + 1));
            }
        }

        // Taking out Map containing all nodes from Particular vertical. All nodes at particular x
        for (TreeMap<Integer, PriorityQueue<Integer>> yMap : mpp.values()) {
            List<Integer> xVals = new ArrayList<>();

            // All Nodes at particular x,y
            // Taking out all the nodes data present in a vertical, priority q might contain 2 nodes which overlap,
            // or else will normally have only 1 data for particular x,y
            for (PriorityQueue<Integer> nodes : yMap.values()) {
                while (!nodes.isEmpty()) xVals.add(nodes.poll());
            }
            ans.add(xVals);
        }

        return ans;
    }

    // Solution 2
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();

        // Step 1: DFS to collect all (col, row, val)
        dfs(root, 0, 0, nodes);

        // Step 2: Sort by col, then row, then value
        nodes.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);     // col
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);     // row
            return Integer.compare(a[2], b[2]);                       // value
        });

        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;

        // Step 3: Group by column
        for (int[] node : nodes) {
            int col = node[0], val = node[2];
            if (col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = col;
            }
            result.get(result.size() - 1).add(val);
        }

        return result;
    }

    // DFS helper to collect column, row, and value
    private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) return;
        nodes.add(new int[]{col, row, node.data});
        dfs(node.left, row + 1, col - 1, nodes);   // Left child
        dfs(node.right, row + 1, col + 1, nodes);  // Right child
    }

    private class Tuple {
        TreeNode node;
        int vertical;
        int level;

        public Tuple(TreeNode node, int vertical, int level) {
            this.node = node;
            this.vertical = vertical;
            this.level = level;
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
