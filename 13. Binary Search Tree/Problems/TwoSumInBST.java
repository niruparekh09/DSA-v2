import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TwoSumInBST {

    // Get the inorder traversal and then use two sum to findout.
    //
    public boolean twoSumBSTBrute(TreeNode root, int k) {
        List<TreeNode> inorder = new ArrayList<>();
        return false;
    }

    public boolean twoSumBST(TreeNode root, int k) {
        if (root == null) return false;

        BSTItr l = new BSTItr(root, false);
        BSTItr r = new BSTItr(root, true);

        int i = l.next();
        int j = r.before();

        while (i < j) {
            if (i + j == k) return true;
            else if (i + j > k) j = r.before();
            else i = l.next();
        }

        return false;
    }
}

class BSTItr {
    boolean isReversed = false;
    private final Stack<TreeNode> st;

    public BSTItr(TreeNode root, boolean isReversed) {
        st = new Stack<>();
        this.isReversed = isReversed;
        pushAll(root);
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    public boolean hasBefore() {
        return !st.isEmpty();
    }

    public int next() {
        TreeNode node = st.pop();
        pushAll(node.right); // All left children of node.right
        return node.data;
    }

    public int before() {
        TreeNode node = st.pop();
        pushAll(node.left); // All right children of node.left
        return node.data;
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            st.push(node);
            node = isReversed ? node.right : node.left;
        }
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int _data) {
        this.data = _data;
        this.left = null;
        this.right = null;
    }
}