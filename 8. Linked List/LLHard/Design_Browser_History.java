public class Design_Browser_History {
    public static class BrowserHistory {

        Node current;

        public BrowserHistory(String homepage) {
            current = new Node(homepage);
        }

        public void visit(String url) {
            Node newNode = new Node(url, null, current);
            current.next = newNode;
            current = newNode;
        }

        public String back(int steps) {
            while (steps > 0 && current.prev != null) {
                steps--;
                current = current.prev;
            }
            return current.val;
        }

        public String forward(int steps) {
            while (steps > 0 && current.next != null) {
                steps--;
                current = current.next;
            }
            return current.val;
        }
    }

    public static class Node {
        String val;
        Node next;
        Node prev;

        public Node(String val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }

        public Node(String val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}
