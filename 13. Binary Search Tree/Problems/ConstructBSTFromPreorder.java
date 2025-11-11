import java.util.Stack;

public class ConstructBSTFromPreorder {

    // Brute TC: O(N) and SC: O(N)
    public TreeNode bstFromPreorderBrute(int[] preorder) {
        Stack<TreeNode> st = new Stack<>();
        int i = 0;
        TreeNode root = new TreeNode(preorder[i++]);

        st.push(root);

        while (!st.isEmpty() && i < preorder.length) {
            TreeNode node = new TreeNode(preorder[i++]);

            if (st.peek().data > node.data) {
                st.peek().left = node;
                st.push(node);
            } else {
                TreeNode cur = st.peek();
                while (!st.isEmpty() && st.peek().data < node.data) {
                    cur = st.pop();
                }
                cur.right = node;
                st.push(node);
            }
        }

        return root;
    }

    // Better
    // Inorder = Sort(Preorder). Now use both of them to construct the BST (We have done this before:
    // https://github.com/niruparekh09/DSA-v2/blob/main/12.%20Binary%20Tree/Hard/ConstructionBTFromPreorderAndInorder.java)
    // TC: O(N) + O(NLogN) SC: O(N)

    // Optimal
    // TC: O(N) and SC: O(N)
    public TreeNode bstFromPreorder(int[] preorder) {
        // Build BST from preorder using upper bound and index
        return bstFromPreorder(preorder, Integer.MAX_VALUE, new int[]{0});
    }

    private TreeNode bstFromPreorder(int[] preorder, int upperBound, int[] i) {
        // Base case: no elements left or current value exceeds allowed upper bound
        if (preorder.length == i[0] || preorder[i[0]] > upperBound) return null;

        // Create root for current subtree
        TreeNode root = new TreeNode(preorder[i[0]++]);

        // Build left subtree: values < root
        root.left = bstFromPreorder(preorder, root.data, i);

        // Build right subtree: values > root and <= upper bound
        root.right = bstFromPreorder(preorder, upperBound, i);

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
