public class Design_Browser_History {

    /*
     * Approach: Doubly Linked List
     * Pattern: Design / Simulation
     * Time Complexity: Visit: O(1), Back/Forward: O(steps)
     * Space Complexity: O(N) - Stores history of visited URLs.
     */
    public static class BrowserHistory {

        Node current;

        public BrowserHistory(String homepage) {
            // Initialize history with the homepage
            current = new Node(homepage);
        }

        public void visit(String url) {
            // Key Logic: When visiting a new URL, all "forward" history is cleared.
            // We simply link the new node to 'current' and sever the old 'current.next'.
            Node newNode = new Node(url, null, current);
            current.next = newNode;
            current = newNode;
        }

        public String back(int steps) {
            // Logic: Move backward 'steps' times, but stop if we reach the beginning (prev == null).
            while (steps > 0 && current.prev != null) {
                steps--;
                current = current.prev;
            }
            return current.val;
        }

        public String forward(int steps) {
            // Logic: Move forward 'steps' times, but stop if we reach the most recent page (next == null).
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