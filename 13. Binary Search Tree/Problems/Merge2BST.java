import java.util.ArrayList;
import java.util.List;

public class Merge2BST {

    // https://www.geeksforgeeks.org/problems/merge-two-bst-s/1
    public ArrayList<Integer> merge(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        inorderTrav(root1, list1);
        inorderTrav(root2, list2);

        return sortAndMerge(list1, list2);
    }

    private ArrayList<Integer> sortAndMerge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> mergedList = new ArrayList<>();

        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) mergedList.add(list1.get(i++));
            else mergedList.add(list2.get(j++));
        }

        // Add the remaining elems
        while (i < list1.size()) mergedList.add(list1.get(i++));
        while (j < list2.size()) mergedList.add(list2.get(j++));

        return mergedList;
    }

    private void inorderTrav(TreeNode root, ArrayList<Integer> list) {
        if (root == null) return;
        inorderTrav(root.left, list);
        list.add(root.data);
        inorderTrav(root.right, list);
    }

    // https://leetcode.com/problems/merge-bsts-to-create-single-bst/
    public TreeNode canMerge(List<TreeNode> trees) {
        return null;
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