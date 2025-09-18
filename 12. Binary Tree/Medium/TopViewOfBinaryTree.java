import java.util.*;

public class TopViewOfBinaryTree {
    /*
        _____
         /_\
        //|\\
          1
         / \
        2   3
       /\   /\
      4 5   6 7
       \      /\
        8     9 10
    Top view: [4,2,1,3,7,10]
    */

    public List<Integer> topView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) return ans;

        Queue<Tuple> q = new LinkedList<>();

        //* Using tree map to maintain vertical order of the node data, i.e. from left to right, x-axis = -ve -> 0 -> +ve
        Map<Integer, Integer> verticalLevelMap = new TreeMap<>();

        q.add(new Tuple(root, 0));

        while (!q.isEmpty()) {
            Tuple currentTuple = q.poll();
            TreeNode currentNode = currentTuple.node;
            int vertical = currentTuple.vertical;

            // Adding the unique node data for a Vertical into the map.
            verticalLevelMap.putIfAbsent(vertical, currentNode.data);

            // The vertical or x-axis decrease when we go left and vise versa for right
            if (currentNode.left != null) q.add(new Tuple(currentNode.left, vertical - 1));
            if (currentNode.right != null) q.add(new Tuple(currentNode.right, vertical + 1));
        }

        ans.addAll(verticalLevelMap.values());

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
