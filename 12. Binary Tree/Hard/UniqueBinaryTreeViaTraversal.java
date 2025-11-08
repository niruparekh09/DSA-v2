public class UniqueBinaryTreeViaTraversal {

    /*
    1. Preorder + Inorder → ✅ works
    - Preorder tells you the root.
    - Inorder tells you left and right subtrees.

    2. Postorder + Inorder → ✅ works
    - Postorder tells you the root (last element).
    - Inorder tells you left/right subtrees.

    Other combinations do NOT guarantee uniqueness:
    - Preorder + Postorder → ❌ may fail for some trees because you don’t know where the root splits left/right subtrees.
    - Inorder + Inorder → ❌ obviously insufficient (you only know relative left/right order, but not the root).
    - Preorder + Preorder → ❌ same issue.
     */
    public boolean uniqueBinaryTree(int a, int b) {
        // Return false if both traversals are the same
        // or if they are preorder and postorder
        // 1 -> Preorder , 2 -> Inorder , 3 -> Postorder.
        return !(a == b || (a == 1 && b == 3) || (a == 3 && b == 1));
    }
}
