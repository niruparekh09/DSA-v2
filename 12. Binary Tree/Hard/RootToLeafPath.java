import java.util.ArrayList;
import java.util.List;

public class RootToLeafPath {

    /**
     * 1
     * / \
     * 2   3
     * \   \
     * 5   4
     * Path = [[1,2,5],[1,3,4]]
     * <p>
     * Visualization:
     * First Branch (Left Subtree)
     * dfs(1, [], [])
     * ├── currPath = [1]
     * ├── Check: node 1 has children → proceed
     * ├── Call dfs(2, [1], [])
     * │   ├── currPath = [1,2]
     * │   ├── Check: node 2 has right child → proceed
     * │   ├── Call dfs(5, [1,2], [])
     * │   │   ├── currPath = [1,2,5]
     * │   │   ├── Check: node 5 is leaf (no children)
     * │   │   ├── ADD TO RESULT: allPaths = [[1,2,5]]
     * │   │   └── BACKTRACK: currPath = [1,2] (remove 5)
     * │   └── BACKTRACK: currPath = [1] (remove 2)
     * <p>
     * Second Branch (Right Subtree)
     * dfs(1, [1], [[1,2,5]])
     * ├── currPath = [1]
     * ├── Call dfs(3, [1], [[1,2,5]])
     * │   ├── currPath = [1,3]
     * │   ├── Check: node 3 has right child → proceed
     * │   ├── Call dfs(4, [1,3], [[1,2,5]])
     * │   │   ├── currPath = [1,3,4]
     * │   │   ├── Check: node 4 is leaf (no children)
     * │   │   ├── ADD TO RESULT: allPaths = [[1,2,5], [1,3,4]]
     * │   │   └── BACKTRACK: currPath = [1,3] (remove 4)
     * │   └── BACKTRACK: currPath = [1] (remove 3)
     * └── BACKTRACK: currPath = [] (remove 1)
     * <p>
     * Time:      Path State      Action
     * t0:        []              Start
     * t1:        [1]             Add root
     * t2:        [1,2]           Go left to node 2
     * t3:        [1,2,5]         Go right to leaf node 5 → Save path
     * t4:        [1,2]           Backtrack from 5
     * t5:        [1]             Backtrack from 2
     * t6:        [1,3]           Go right to node 3
     * t7:        [1,3,4]         Go right to leaf node 4 → Save path
     * t8:        [1,3]           Backtrack from 4
     * t9:        [1]             Backtrack from 3
     * t10:       []              Backtrack from root
     */

    public List<List<Integer>> allRootToLeaf(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();

        List<Integer> currPath = new ArrayList<>();

        if (root == null) return allPaths;

        dfs(root, currPath, allPaths);

        return allPaths;
    }

    private void dfs(TreeNode root, List<Integer> currPath, List<List<Integer>> allPaths) {
        if (root == null) return;

        currPath.add(root.data);

        if (root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(currPath));
        } else {
            dfs(root.left, currPath, allPaths);
            dfs(root.right, currPath, allPaths);
        }

        currPath.remove(currPath.size() - 1);
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
