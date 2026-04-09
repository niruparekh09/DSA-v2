import java.util.ArrayList;
import java.util.List;

public class ConvertBSTToMinHeap {
    /*
     * Approach: Inorder Traversal + Preorder Re-filling
     * Pattern: Structure Transformation (BST to Min-Heap)
     * Time Complexity: O(N) - Two full traversals of the tree.
     * Space Complexity: O(N) - To store the elements in a list + recursion stack.
     */
    public static BinaryTreeNode convertBST(BinaryTreeNode root) {
        // Step 1: Extract elements from BST in sorted order.
        List<Integer> sortedArr = new ArrayList<>();
        inorder(root, sortedArr);

        int[] index = {0};

        /* * Step 2: Fill the tree in Preorder.
         * For a Min-Heap, the root must be smaller than its children.
         * Since sortedArr is sorted, filling in preorder (Root -> Left -> Right)
         * ensures the parent always receives a smaller value than its descendants.
         */
        preorderFill(root, sortedArr, index);

        return root;
    }

    /**
     * Inorder traversal (Left, Root, Right) of a BST always yields sorted data.
     */
    private static void inorder(BinaryTreeNode root, List<Integer> sortedArr) {
        if (root == null) return;

        inorder(root.left, sortedArr);
        sortedArr.add(root.data);
        inorder(root.right, sortedArr);
    }

    /**
     * Preorder fill (Root, Left, Right) ensures the Min-Heap property is satisfied:
     * Node < Left Child and Node < Right Child.
     */
    private static void preorderFill(BinaryTreeNode root, List<Integer> sortedArr, int[] index) {
        if (root == null || index[0] >= sortedArr.size()) return;

        // Assign the next smallest element to the current root
        root.data = sortedArr.get(index[0]++);

        // Recurse to children
        preorderFill(root.left, sortedArr, index);
        preorderFill(root.right, sortedArr, index);
    }

    class BinaryTreeNode {
        int data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}