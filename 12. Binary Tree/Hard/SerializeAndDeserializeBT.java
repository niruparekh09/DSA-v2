import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBT {

    /*
     * Your Codec object will be instantiated and called as such:
     * Codec ser = new Codec();
     * Codec deser = new Codec();
     * String tree = ser.serialize(root);
     * TreeNode ans = deser.deserialize(tree);
     * return ans;
     */

    /* Str format: Level Order Traversal of tree:
     *   1
     *  / \
     *  2 13
     *    / \
     *   4   5
     *
     * Str: {1,2,13,#,#,4,5,#,#,#,#}
     * # -> For Null
     */
    public String serialize(TreeNode root) {
        if (root == null) return "#,";

        StringBuilder ser = new StringBuilder();

        Queue<TreeNode> q = new LinkedList<>(); // Q for level order Traversal

        q.add(root);
        ser.append(root.data).append(",");

        // No for loop for level order traversal Because we are not doing any
        // per-level operations (we are just traversing all nodes)
        while (!q.isEmpty()) {
            TreeNode node = q.remove();

            if (node.left == null) {
                ser.append("#,");
            } else {
                ser.append(node.left.data).append(",");
                q.add(node.left);
            }

            if (node.right == null) {
                ser.append("#,");
            } else {
                ser.append(node.right.data).append(",");
                q.add(node.right);
            }
        }

        // Remove trailing comma
        if (!ser.isEmpty()) ser.setLength(ser.length() - 1);

        return ser.toString();
    }

    /* Str format: Level Order Traversal of tree:
     *   1
     *  / \
     *  2 13
     *    / \
     *   4   5
     *
     * Str: {1,2,13,#,#,4,5,#,#,#,#,}
     * # -> For Null
     *
     * Deserialize:
     *   - Reconstructs the tree level by level (BFS).
     *   - Uses a queue to attach children to each parent.
     *   - Reads serialized values one by one:
     *       → '#' means no child (null)
     *       → Number means create a new TreeNode
     *   - Returns the root of the reconstructed binary tree.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("#,")) return null;

        int i = 0; // To keep track of node in vals

        String[] vals = data.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(vals[i++]));

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        // Like in your serializer, the for (int j = 0; j < size; j++)
        // loop is not needed because you’re just processing the queue sequentially.
        while (!q.isEmpty() && i < vals.length) {
            TreeNode node = q.remove();

            // Left child
            if (!vals[i].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                q.add(node.left);
            }
            i++;

            // Right child
            if (i < vals.length && !vals[i].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                q.add(node.right);
            }
            i++;
        }

        return root;
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
