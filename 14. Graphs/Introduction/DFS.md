# DFS — Dry Run & Explanation

## 1) Handling disconnected components (short)

```
/*
  If the graph has multiple components, the outer loop (i = 0..V-1)
  starts a DFS from every unvisited vertex. Each dfs(i) explores one
  connected component fully and marks its nodes visited. Repeating
  ensures all components are covered.
*/
```

---

## 2) Example Graph (V = 4) — two components

```
/*
   Component 1:    Component 2:
        0 -- 1         2 -- 3

   Adjacency:
     0: [1]
     1: [0]
     2: [3]
     3: [2]

   Process:
     i=0 -> dfs(0) visits: 0, 1
     i=1 -> visited -> skip
     i=2 -> dfs(2) visits: 2, 3
     i=3 -> visited -> skip

   Result: DFS order = [0, 1, 2, 3]
*/
```

---

## 3) Visual & dry run for the `main` example in the code

```
/*
  Visual of the graph used in main():

        1
        |
        0 -- 3
        |
        2
        |
        4

  Adjacency (from main):
    0: [2, 3, 1]
    1: [0]
    2: [0, 4]
    3: [0]
    4: [2]

  DFS starting at node 0 (neighbors visited in listed order):

  Recursive steps (concise):
   - call dfs(0): mark 0, add 0
     - neighbor 2 -> dfs(2): mark 2, add 2
         - neighbor 0 (visited) -> skip
         - neighbor 4 -> dfs(4): mark 4, add 4
             - neighbor 2 (visited) -> return
         - return from dfs(4)\     - return from dfs(2)
     - next neighbor 3 -> dfs(3): mark 3, add 3
         - neighbor 0 (visited) -> return
     - next neighbor 1 -> dfs(1): mark 1, add 1
         - neighbor 0 (visited) -> return
   - return from dfs(0)

  Final DFS order: [0, 2, 4, 3, 1]
*/
```
---

## 4) Key points & complexity

- **Order depends on adjacency list order.** DFS explores the first neighbor fully before moving to the next.
- **Time Complexity:** `O(V + E)` — every vertex and edge is visited once.
- **Space Complexity:** `O(V)` for `visited[]` plus recursion stack `O(H)` (H = recursion depth); worst-case `O(V)` when the graph is a long chain.