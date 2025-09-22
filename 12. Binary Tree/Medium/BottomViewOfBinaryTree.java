import java.util.*;

public class BottomViewOfBinaryTree {

    /*
          1
         / \
        2   3
       /\
      4 5
         \\|//
          \-/
    Bottom View: [4,2,5,3]
    */

    public List<Integer> bottomView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) return ans;

        Queue<Tuple> q = new LinkedList<>();
        Map<Integer, Integer> mpp = new TreeMap<>();

        q.add(new Tuple(root, 0));

        while (!q.isEmpty()) {
            Tuple currentTuple = q.poll();
            TreeNode currentNode = currentTuple.node;
            int vertical = currentTuple.vertical;

            // We need the lowest available value for bottom view, so we will replace the node data if we find it in
            // same vertical but the below level i.e. x-axis is same but y-axis is more.
            if (mpp.containsKey(vertical)) {
                mpp.replace(vertical, currentNode.data);
            } else {
                mpp.put(vertical, currentNode.data);
            }

            // The vertical or x-axis decrease when we go left and vise versa for right
            if (currentNode.left != null) q.add(new Tuple(currentNode.left, vertical - 1));
            if (currentNode.right != null) q.add(new Tuple(currentNode.right, vertical + 1));
        }

        ans.addAll(mpp.values());

        return ans;
    }

    private class Tuple {
        TreeNode node;
        int vertical;

        public Tuple(TreeNode node, int vertical) {
            this.node = node;
            this.vertical = vertical;
        }
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
