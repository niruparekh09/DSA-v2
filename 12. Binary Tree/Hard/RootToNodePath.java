import java.util.ArrayList;

public class RootToNodePath {

    /**
           1
          / \
         2   3
          \   \
           5   4

     A = Add of 1
     B = 5
     Final path: [1,2,5] ✓

     dfs(1, 5, [])
     ├── ans.add(1) → [1]
     ├── Check: 1 != 5
     ├── Call dfs(2, 5, [1])
     │   ├── ans.add(2) → [1,2]
     │   ├── Check: 2 != 5
     │   ├── Call dfs(null, 5, [1,2]) → returns false (left child is null)
     │   ├── Call dfs(5, 5, [1,2])
     │   │   ├── ans.add(5) → [1,2,5]
     │   │   ├── Check: 5 == 5 → return true
     │   │   └── (NO BACKTRACKING - found the node!)
     │   └── Received true from right subtree → return true
     └── Received true from left subtree → return true
     Final path: [1,2,5] ✓
     */
    public ArrayList<Integer> solve(TreeNode A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(A, B, ans);
        return ans;
    }

    private boolean dfs(TreeNode root, int nodeData, ArrayList<Integer> ans) {
        if (root == null) {
            return false; // We have reach ahead of leaf node but still have not encountered the req node
        }

        ans.add(root.data);

        // If we get the correct node we will return true up the recursion track which
        if (root.data == nodeData) {
            return true;
        }

        /**
         Short-circuits: If left subtree finds target, doesn't search right subtree
         Propagates success: Returns true all the way up the call stack
         Prevents backtracking: When any call returns true, no remove() happens
         */
        if (dfs(root.left, nodeData, ans) || dfs(root.right, nodeData, ans)) {
            return true;
        }

        /*! Why this was wrong:
        if (root.data == nodeData) {
            return true;
        } else { <----- But parent ignores return value from the child in recursive stack
            dfs(root.left, nodeData, ans);
            dfs(root.right, nodeData, ans);
        }
        */
        ans.remove(ans.size() - 1); // Backtracking in case we have not encountered the correct node

        return false;
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
