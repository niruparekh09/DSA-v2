public class CountCompleteTreeNodes {
    public int countNodesBrute(TreeNode root) {
        int[] arr = new int[1];
        count(root, arr);
        return arr[0];
    }

    private void count(TreeNode root, int[] arr) {
        if (root == null) return;

        arr[0] = arr[0] + 1;
        count(root.left, arr);
        count(root.right, arr);
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // find left and right height
        int lh = findLeftHeight(root);
        int rh = findRightHeight(root);

        if (lh == rh) {
            return (int) (Math.pow(2, lh) - 1);
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int findLeftHeight(TreeNode root) {
        int h = 0;

        while (root != null) {
            root = root.left;
            h++;
        }

        return h;
    }

    private int findRightHeight(TreeNode root) {
        int h = 0;

        while (root != null) {
            root = root.right;
            h++;
        }

        return h;
    }

    public int countNodesLeet(TreeNode root) {
        if (root == null) return 0;

        return 1 + countNodesLeet(root.left) + countNodesLeet(root.right);
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
