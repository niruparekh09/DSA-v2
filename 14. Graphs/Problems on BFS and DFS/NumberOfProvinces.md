# Comparison of both the approaches

1. **Time & space complexity** for each code.
2. **Key differences in approach** (what each one actually does).
3. **Step-by-step dry run** of both codes on the same example matrix so you can see they find the same components.

---

## Problem input (we'll use this example)

```text
isConnected =
[ [1,0,0,1],
  [0,1,1,0],
  [0,1,1,0],
  [1,0,0,1] ]
```

Interpretation: edges are `0↔3` and `1↔2` (plus diagonal `1`s = self-loops). The graph has **2 provinces/components**:
`{0,3}` and `{1,2}`.

---

## 1) Complexity

### Code 1 — matrix DFS (no conversion)

```text
for each unvisited node i:
  dfs(i) {
    mark i visited
    for j = 0..V-1: if adjMat[i][j]==1 and j not visited -> dfs(j)
  }
```

- **Time complexity:** `O(V^2)` in the worst case.
    - Reason: each DFS visit scans the entire row `adjMat[node][0..V-1]` which is `O(V)` work per visited node. If
      almost every node gets visited, total ≈ `V * V = V^2`.

- **Space complexity:** `O(1)` extra besides input + `O(V)` for `visited[]` + `O(V)` recursion stack in worst case.
  Input matrix itself is `O(V^2)`.

### Code 2 — convert to adjacency list, then DFS

```text
// conversion:
for i = 0..V-1:
  for j = 0..V-1:
    if isConnected[i][j]==1 && i!=j: neighbours.add(j)
// then run DFS on adjList
```

- **Time complexity:** `O(V^2 + V + E)` → simplifies to **`O(V^2)` worst-case** because conversion scans the full matrix
  `O(V^2)`.
    - After conversion, DFS traversal is `O(V + E)`. If `E` is much smaller than `V^2`, the traversal part is cheap —
      but conversion still cost `V^2`.

- **Space complexity:** `O(V + E)` for the adjacency list + `O(V)` for `visited[]` + recursion stack `O(V)`. (Plus the
  input matrix if it still exists.)

**Bottom line:** with the matrix _already provided_ both approaches are `O(V^2)` worst-case. But if you _start from an
edge list_ or can avoid conversion, adjacency list algorithms can be much faster (`O(V + E)`) on sparse graphs.

---

## 2) Difference in approach (intuitive)

- **Code 1 (matrix DFS):**
    - **Neighbor discovery:** For a node `u` you find neighbors by scanning the entire row `adjMat[u][0..V-1]`.
    - **When good:** small `V`, dense graphs, or when the matrix is the natural input, and you don’t want conversion
      overhead.
    - **Consequence:** scanning full row for each node, even if node has few neighbors.

- **Code 2 (adj-list DFS):**
    - **Two phases:** (A) convert the matrix to adjacency lists (scan matrix once), (B) do list-based DFS where you
      iterate only actual neighbors.
    - **When good:** useful if you will run many graph traversals or algorithms that iterate neighbors frequently, or if
      you start from an edge list.
    - **Consequence:** conversion costs `O(V^2)`. After conversion, neighbor iteration is efficient: you only touch
      `deg(u)` entries for node `u`.

---

## 3) Dry run (step-by-step) — both codes on the sample matrix

### Shared start

`V = 4`
Initial `visited = [false, false, false, false]`
`count/cnt = 0`

---

### Dry run — **Code 1** (matrix DFS)

**Outer loop i = 0**

- `visited[0]` is false → increment `cnt = 1` → call `dfs(0)`.
- `dfs(0)`:
    - `visited[0] = true` → visited = `[T, F, F, F]`
    - Scan row 0: j=0 (`1` but visited[0]==true → skip), j=1 (`0` skip), j=2 (`0` skip), j=3 (`1` and !visited[3]) →
      call `dfs(3)`.
        - `dfs(3)`: mark visited[3]=true → visited = `[T, F, F, T]`
            - Scan row 3: j=0 (`1` but visited[0] true), j=1 (0), j=2 (0), j=3 (1 but visited[3] true) → return.

        - return to `dfs(0)`, finished scanning → return.

- After finishing dfs(0): visited = `[T, F, F, T]`.

**Outer loop i = 1**

- `visited[1]` false → `cnt = 2` → call `dfs(1)`.
- `dfs(1)`:
    - `visited[1] = true` → `[T, T, F, T]`
    - Scan row 1: j=0 (0), j=1 (1 but visited[1] true), j=2 (1 and !visited[2]) → call `dfs(2)`.
        - `dfs(2)`: visited[2]=true → `[T, T, T, T]`
            - Scan row 2: j=0 (0), j=1 (1 but visited[1] true), j=2 (1 but visited[2] true), j=3 (0) → return.

        - return to dfs(1) → finished → return.

**Outer loop i = 2,3** are visited → skip.

**Final result:** `cnt = 2` (components `{0,3}` and `{1,2}`)

---

### Dry run — **Code 2** (convert → adjList → DFS)

#### Phase A — Convert matrix → adjList (skip self-loops i==j)

Scan all cells and build neighbors:

- Row 0: entries: j=0 (1 but i==j skip), j=1(0), j=2(0), j=3(1) → `adjList[0] = [3]`
- Row 1: j=0(0), j=1(1 skip), j=2(1) → `adjList[1] = [2]`
- Row 2: j=0(0), j=1(1), j=2(1 skip) → `adjList[2] = [1]`
- Row 3: j=0(1), j=1(0), j=2(0), j=3(1 skip) → `adjList[3] = [0]`

Adjacency lists:

```
0 -> [3]
1 -> [2]
2 -> [1]
3 -> [0]
```

#### Phase B — DFS using lists

`visited = [F,F,F,F]`, `cnt = 0`

**Outer i = 0**

- visited[0] false → `cnt = 1` → dfs(0)
    - dfs(0): visited[0]=true → `[T,F,F,F]`, add 0
    - iterate neighbors of 0: neighbor 3 -> not visited -> dfs(3)
        - dfs(3): visited[3]=true → `[T,F,F,T]`, add 3
        - neighbors of 3: neighbor 0 -> already visited -> return

    - finish dfs(0)

**Outer i = 1**

- visited[1] false → `cnt = 2` → dfs(1)
    - dfs(1): visited[1]=true → `[T,T,F,T]`, add 1
    - neighbor 2 -> not visited -> dfs(2)
        - dfs(2): visited[2]=true → `[T,T,T,T]`, add 2
        - neighbor 1 -> already visited -> return

    - finish dfs(1)

**i = 2,3** visited → skip
**Final result:** `cnt = 2`

---

## 4) Practical recommendations / takeaways

- If **input is adjacency matrix** and `V` is small, using Code 1 (matrix DFS) is straightforward, simple and fine.
- If **graph is large and sparse (E ≪ V^2)** and you will do many neighbor iterations (or multiple traversals), convert
  to adjacency list **only if** you can afford the `O(V^2)` conversion cost — or better: if you can get the input as an
  edge list, build adj list directly in `O(V + E)`.
- For **one-off** component count with adjacency matrix input, Code 1 and Code 2 are both `O(V^2)` overall; Code 1
  avoids building an extra structure and is simpler.