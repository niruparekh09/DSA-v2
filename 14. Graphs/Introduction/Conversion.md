Here is a concise cheat sheet for graph construction in Java, tailored for LeetCode interviews.

### 1\. The Standard: 0 to N-1 Nodes (Unweighted)

**Best for:** Most problems given `n` and `edges`.
**Input:** `int n = 5`, `int[][] edges = {{0,1}, {1,2}}`

```java
// 1. Initialize
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < n; i++) {
    adj.add(new ArrayList<>());
}

// 2. Build
for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];
    
    adj.get(u).add(v); 
    adj.get(v).add(u); // REMOVE this line if Directed
}
```

* **Pitfall:** Forgetting the initialization loop results in `IndexOutOfBoundsException`.
* **1-Indexed:** Change loop to `i <= n` (size `n + 1`) to handle node `n` directly without offset math.

-----

### 2\. The Weighted Graph: Dijkstra/MST

**Best for:** Shortest Path algorithms requiring weights.
**Input:** `int n`, `int[][] edges` (e.g., `[u, v, w]`)

```java
// 1. Initialize: List of int arrays (index 0=node, index 1=weight)
List<List<int[]>> adj = new ArrayList<>();
for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

// 2. Build
for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];
    int w = edge[2];

    adj.get(u).add(new int[]{v, w}); 
    adj.get(v).add(new int[]{u, w}); // Remove for Directed
}
```

* **Pitfall:** Confusing `pair[0]` (neighbor) and `pair[1]` (weight) when processing the queue later.
* **Note:** You can use a custom `Pair` class, but `int[]` is faster to type and suffices for interviews.

-----

### 3\. Sparse Nodes / Unknown N (Using Map)

**Best for:** Nodes are not $0 \dots N$ (e.g., node IDs are 5, 100, 2005) or strings.
**Input:** `int[][] edges` (where `n` is implicit or large)

```java
Map<Integer, List<Integer>> adj = new HashMap<>();

for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];

    // computeIfAbsent handles initialization automatically
    adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // Remove for Directed
}
```

* **Pitfall:** `adj.get(key)` returns `null` if a node has no outgoing edges. Always use `adj.getOrDefault(node, new ArrayList<>())` during traversal to avoid NPE.

-----

### 4\. Adjacency Matrix $\to$ Adjacency List

**Best for:** Converting dense matrix inputs ($O(V^2)$) to lists for BFS/DFS.
**Input:** `int[][] graph` (where `graph[i][j] == 1` implies edge)

```java
int n = graph.length;
List<List<Integer>> adj = new ArrayList<>();

for (int i = 0; i < n; i++) {
    adj.add(new ArrayList<>()); // Init
    for (int j = 0; j < n; j++) {
        if (graph[i][j] == 1 && i != j) { // Check for edge existence
            adj.get(i).add(j);
        }
    }
}
```

* **Pitfall:** In weighted matrices, `0` usually means no edge, but sometimes `-1` or `Infinity` is used. Check the problem constraints carefully.

-----

### Quick Reference: Variations

| Scenario | Modification |
| :--- | :--- |
| **Directed Graph** | Only add `u -> v`. Do not add `v -> u`. |
| **1-Indexed Nodes** | Initialize `adj` size to `n + 1`. Access indices directly. |
| **List of Lists vs Map** | Use `List<List<>>` if nodes are $0 \dots N$ (faster). Use `Map` if nodes are sparse or strings. |
| **Object List** | `List<List<Integer>>` handles unweighted. `List<List<int[]>>` handles weighted. |

### Summary Snippet (Copy-Paste)

```java
// Generic 0-indexed Unweighted Construction
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
for (int[] e : edges) {
    adj.get(e[0]).add(e[1]);
    adj.get(e[1]).add(e[0]);
}
```