public class Flatten_LL {

    public static Node flatten(Node root) {
        if (root == null || root.next == null) return root;
        Node mergedHead = flatten(root.next);
        return merge(root, mergedHead);
    }

    public static Node merge(Node head1, Node head2) {
        Node dummyNode = new Node(-1);
        Node res = dummyNode;
        Node temp1 = head1;
        Node temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.data > temp2.data) {
                res.bottom = temp2;
                res = temp2;
                temp2 = temp2.bottom;
            } else {
                res.bottom = temp1;
                res = temp1;
                temp1 = temp1.bottom;
            }
            res.next = null;
        }
        if (temp1 != null) res.bottom = temp1;
        else res.bottom = temp2;
        return dummyNode.bottom;
    }

    public static class Node {
        int data;
        Node next;
        Node bottom;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }
}