public class CheckIfBTIsHeap {
    /*
     * Approach: Verification of CBT and Heap Order Properties
     * Pattern: Binary Tree Validation (Max-Heap)
     * Time Complexity: O(N) - Each node is visited to count and validate.
     * Space Complexity: O(H) - Recursive stack depth, where H is the height.
     */
    public boolean isHeap(Node tree) {
        int totalCount = countTotalNodes(tree);
        int index = 0;

        // A Binary Heap must be a Complete Binary Tree (CBT) AND follow Heap Order.
        return isCBT(tree, index, totalCount) && isMaxOrder(tree);
    }

    /**
     * Complete Binary Tree Validation:
     * In a CBT, if we assign indices (0-based), no index can exceed (TotalNodes - 1).
     */

    public boolean isCBT(Node root, int index, int totalCount) {
        if (root == null) return true;

        // If an index is greater than or equal to node count, there's a "gap" in the tree.
        if (index >= totalCount) return false;

        // Recurse for left and right children using standard 2i+1 and 2i+2 logic.
        return isCBT(root.left, 2 * index + 1, totalCount) &&
                isCBT(root.right, 2 * index + 2, totalCount);
    }

    /**
     * Max-Heap Order Validation:
     * Every parent node must be greater than its children.
     */
    public boolean isMaxOrder(Node root) {
        // Base case: null or leaf nodes naturally satisfy heap property.
        if (root == null || (root.left == null && root.right == null)) return true;

        // Case: Only left child exists (Possible in a CBT).
        if (root.right == null) {
            return root.data > root.left.data;
        }
        // Case: Both children exist.
        else {
            return root.data > root.left.data &&
                    root.data > root.right.data &&
                    isMaxOrder(root.left) &&
                    isMaxOrder(root.right);
        }
    }

    // Standard recursive count of all nodes in the tree.
    public int countTotalNodes(Node root) {
        if (root == null) return 0;
        return 1 + countTotalNodes(root.left) + countTotalNodes(root.right);
    }

    class Node {
        int data;
        Node left, right;
        Node(int d) {
            data = d;
            left = right = null;
        }
    }
}