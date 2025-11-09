import java.util.Arrays;
import java.util.List;

public class FloorAndCeilInBST {
    public List<Integer> floorCeilOfBST(TreeNode root, int key) {
        int floor = -1, ceil = -1;

        // Pointer to keep the count of the cur and prev node
        TreeNode cur = root;

        // Floor
        while (cur != null) {
            // Check if current data is <= to key
            if (cur.data <= key) {
                floor = cur.data;
                cur = cur.right; // Try finding as bigger floor as possible.
            }
            // If cur.data is > than key, search left subtree for smaller val.
            else {
                cur = cur.left;
            }
        }

        cur = root; // reset the cur to root

        // Ceil
        while (cur != null) {
            // Check if current data is >= key
            if (cur.data >= key) {
                ceil = cur.data;
                cur = cur.left; // Try finding as smaller ceil as possible
            }
            // If cur.data is < than key, search right subtree for bigger val.
            else {
                cur = cur.right;
            }
        }

        return Arrays.asList(floor, ceil);
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
}
