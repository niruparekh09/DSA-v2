public class BinaryTreeImplementation {
    public static void main(String[] args) {

        /*
           [1]
           / \
         [2] [3]
             /
           [5]
        */
        Node root = new Node(1); // Root Test.Node

        root.right = new Node(3); // Right child of root node

        root.left = new Node(2); // Left child of root node

        root.right.left = new Node(5); // Left child of right child of root node.
    }

    static class Node {
        // Data present in the Test.Node
        int data;

        // Reference to Right child of the Test.Node.
        Node right;

        // Reference to Left child of the Test.Node.
        Node left;

        // Init. constructor
        public Node(int _data) {
            this.data = _data;
            right = null;
            left = null;
        }
    }
}