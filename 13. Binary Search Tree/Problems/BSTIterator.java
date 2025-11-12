import java.util.Stack;

public class BSTIterator {

    /*
         7
        / \
       3  15
          / \
         9  20

        Input
        ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
        [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
        Output
        [null, 3, 7, true, 9, true, 15, true, 20, false]

        Explanation
        BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
        bSTIterator.next();    // return 3
        bSTIterator.next();    // return 7
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 9
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 15
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 20
        bSTIterator.hasNext(); // return False
    */

    Stack<TreeNode> st = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushAll(root); // Pushes all the left -> left elems of the node
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    public int next() {
        TreeNode poppedNode = st.pop();
        pushAll(poppedNode.right);
        return poppedNode.data;
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            st.push(node);
            node = node.left;
        }
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