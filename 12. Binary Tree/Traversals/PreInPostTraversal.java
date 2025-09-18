import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PreInPostTraversal {

    List<List<Integer>> treeTraversal(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        Stack<NodeState> st = new Stack<>();

        st.push(new NodeState(root, 1));

        while (!st.isEmpty()) {
            NodeState popped = st.pop();
            TreeNode node = popped.node;
            int state = popped.state;

            if (state == 1) {
                pre.add(node.data);

                st.push(new NodeState(node, 2));

                if (node.left != null) st.push(new NodeState(node.left, 1));
            } else if (state == 2) {
                in.add(node.data);

                st.push(new NodeState(node, 3));

                if (node.right != null) st.push(new NodeState(node.right, 1));
            } else {
                post.add(node.data);
            }
        }
        return Arrays.asList(pre, in, post);
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

    private class NodeState {
        TreeNode node;
        int state;

        public NodeState(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }
}
