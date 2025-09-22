import java.util.*;

public class RightViewOfBinaryTree {

        /*
          1 <------
         / \
        2   3 <------
       /\
      4 5 <------
    Right View: [1,3,5]
    */

    public List<Integer> rightSideViewOptimal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) return ans;

        // q<node,x,y>/q<node,vertical,level>
        Queue<Tuple> q = new LinkedList<>();

        // mpp<level,node.data> We will store the last node of every level
        Map<Integer, Integer> mpp = new TreeMap<>();

        q.add(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {
            Tuple currentTuple = q.poll();
            TreeNode currentNode = currentTuple.node;
            int vertical = currentTuple.vertical;
            int level = currentTuple.level;

            // As we are putting left nodes in queue before right nodes, we do not need to put the check as
            // we will encounter the right nodes after the left, so we can replace them.
            if (mpp.containsKey(level)) {
                mpp.replace(level, currentNode.data);
            } else {
                mpp.put(level, currentNode.data);
            }

            if (currentNode.left != null) q.add(new Tuple(currentNode.left, vertical - 1, level + 1));
            if (currentNode.right != null) q.add(new Tuple(currentNode.right, vertical + 1, level + 1));
        }

        ans.addAll(mpp.values());

        return ans;
    }

    // Optimal Solution. Much Simpler way
    //* Don't think too much. As you can see that the above solution was very overcomplicated.
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // traverseTree(node, level, ans)
        traverseTree(root, 0, ans);
        return ans;
    }

    private void traverseTree(TreeNode node, int lvl, List<Integer> ans) {
        if (node == null) return;

        // Storing only 1 node/lvl
        if (lvl == ans.size()) ans.add(node.data);

        // Traversing the root -> right -> left subtree of because we need to right view
        traverseTree(node.right, lvl + 1, ans);
        traverseTree(node.left, lvl + 1, ans);
    }

    private class Tuple {
        TreeNode node;
        int vertical;
        int level;

        public Tuple(TreeNode node, int vertical, int level) {
            this.node = node;
            this.vertical = vertical;
            this.level = level;
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
