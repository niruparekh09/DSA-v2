public class LargestBSTInBT {

    // Brute Force Solution TC: O(N*N) Traverses every node and checks if it's a BST or not SC: O(N)
    private int maxBST = Integer.MIN_VALUE;

    public int largestBSTBrute(TreeNode root) {
        traverseNodes(root);
        return maxBST;
    }

    private void traverseNodes(TreeNode root) {
        if (root == null) return;
        traverseNodes(root.left);
        boolean isBST = isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int[] counter = new int[]{0};
        if (isBST) countNodes(root, counter);
        maxBST = Math.max(maxBST, counter[0]);
        traverseNodes(root.right);
    }

    private void countNodes(TreeNode root, int[] counter) {
        if (root == null) return;
        countNodes(root.left, counter);
        counter[0]++;
        countNodes(root.right, counter);
    }

    boolean isBST(TreeNode root, int minVal, int maxVal) {
        if (root == null) return true;

        if (root.data < minVal || root.data > maxVal) return false;

        return isBST(root.left, minVal, root.data) && isBST(root.right, root.data, maxVal);
    }

    /*
    Example tree:
             10
            /  \
           5    15
          / \     \
         1   8     7

    Dry run (post-order, concise):
    | Node | left (min,max,size)         | right (min,max,size)        | isBST? | returned (min,max,size) |
    |------|-----------------------------|-----------------------------|--------|-------------------------|
    | 1    | (INF, -INF, 0)              | (INF, -INF, 0)              | yes    | (1, 1, 1)               |
    | 8    | (INF, -INF, 0)              | (INF, -INF, 0)              | yes    | (8, 8, 1)               |
    | 5    | (1,1,1)                     | (8,8,1)                     | yes    | (1, 8, 3)               |
    | 7    | (INF, -INF, 0)              | (INF, -INF, 0)              | yes    | (7, 7, 1)               |
    | 15   | (INF,-INF,0)                | (7,7,1)                     | no     | (MIN, MAX, 1)          |
    | 10   | (1,8,3)                     | (MIN, MAX, 1)               | no     | (MIN, MAX, 3)          |

    Result: largest BST size = 3  (subtree rooted at 5)
    */
    public int largestBST(TreeNode root) {
        return helper(root).maxSize;
    }

    /* helper returns (minNode, maxNode, maxSize)
       minNode = minimum value in this subtree when it is a BST (otherwise set to MIN)
       maxNode = maximum value in this subtree when it is a BST (otherwise set to MAX)
       maxSize = size of largest BST found in this subtree
    */
    private NodeValue helper(TreeNode root) {
        // empty subtree is a valid BST of size 0
        if (root == null) return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        NodeValue left = helper(root.left);    // info from left subtree
        NodeValue right = helper(root.right);  // info from right subtree

        // current subtree is BST iff left.maxNode < root.data < right.minNode
        if (root.data > left.maxNode && root.data < right.minNode) {
            int minNode = Math.min(root.data, left.minNode);
            int maxNode = Math.max(root.data, right.maxNode);
            int size = left.maxSize + right.maxSize + 1; // total nodes in this BST
            return new NodeValue(minNode, maxNode, size);
        }

        // not a BST: propagate the largest size found so far, mark min/max so parent cannot treat this as BST
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
    }

    private class NodeValue {
        public int minNode, maxNode, maxSize;
        public NodeValue(int minNode, int maxNode, int maxSize) {
            this.minNode = minNode;
            this.maxNode = maxNode;
            this.maxSize = maxSize;
        }
    }
}
