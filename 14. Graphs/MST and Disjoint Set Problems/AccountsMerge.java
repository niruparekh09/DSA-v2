import java.util.*;

public class AccountsMerge {

    /*
     * Approach: Disjoint Set (Union-Find) + Email Mapping
     * Pattern: Connected Components via Shared Identifiers
     *
     * Key Idea:
     * - Each account is treated as a node.
     * - Emails act as connectors between accounts.
     * - If two accounts share an email, they belong to the same person → union them.
     *
     * Steps:
     * 1. Traverse accounts and map each email to its first seen account index.
     * 2. If an email is seen again → union the current account with the previous one.
     * 3. After DSU unions, group emails by their ultimate parent account.
     * 4. Sort emails and build final merged account list.
     *
     * Time Complexity: O(N * M * α(N))
     * N = number of accounts, M = emails per account.
     * Space Complexity: O(N + totalEmails)
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();

        // Create Disjoint Set for accounts
        DS ds = new DS(n);

        // Map email -> account index
        // Helps detect when two accounts share the same email
        Map<String, Integer> mapMailNode = new HashMap<>();

        /*
         * Step 1: Build DSU by connecting accounts that share emails
         */
        for (int i = 0; i < n; i++) {

            List<String> userAccounts = accounts.get(i);

            // Start from index 1 because index 0 contains username
            for (int j = 1; j < userAccounts.size(); j++) {

                String mail = userAccounts.get(j);

                // First time seeing this email
                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode.put(mail, i);
                } else {
                    // Email already exists → union current account with previous one
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        /*
         * Step 2: Group emails based on their ultimate parent account
         */
        List<List<String>> mergedMail = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>());
        }

        // Iterate over email -> account mapping
        for (Map.Entry<String, Integer> entry : mapMailNode.entrySet()) {

            String mail = entry.getKey();

            // Find ultimate parent of account
            int node = ds.findUPar(entry.getValue());

            // Add email to its parent account's list
            mergedMail.get(node).add(mail);
        }

        /*
         * Step 3: Build final result
         */
        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            // Skip accounts that have no merged emails
            if (mergedMail.get(i).isEmpty())
                continue;

            // Sort emails lexicographically
            Collections.sort(mergedMail.get(i));

            List<String> temp = new ArrayList<>();

            // Add username at first position
            temp.add(accounts.get(i).get(0));

            // Add sorted emails
            temp.addAll(mergedMail.get(i));

            ans.add(temp);
        }

        // Optional: sort final accounts by username
        ans.sort(Comparator.comparing(list -> list.get(0)));

        return ans;
    }

    /*
     * Disjoint Set (Union-Find)
     * Supports:
     * - Path Compression
     * - Union by Rank
     * - Union by Size
     */
    class DS {

        int[] rank;
        int[] size;
        int[] parent;

        DS(int n) {

            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];

            // Initialize each account as its own parent
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /*
         * Find Ultimate Parent with Path Compression
         */
        int findUPar(int node) {

            if (parent[node] != node) {
                parent[node] = findUPar(parent[node]);
            }

            return parent[node];
        }

        /*
         * Union by Rank (height-based merging)
         */
        void unionByRank(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        /*
         * Union by Size (component-size-based merging)
         */
        void unionBySize(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
}