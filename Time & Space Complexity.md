# Time and Space Complexity Cheat Sheet

---

## 🧠 Big-O Complexity Basics

| Big-O      | Description      | Example                     |
| ---------- | ---------------- | --------------------------- |
| O(1)       | Constant time    | Array index access          |
| O(log N)   | Logarithmic time | Binary Search               |
| O(N)       | Linear time      | Traversing an array         |
| O(N log N) | Log-linear time  | Merge Sort, Heap Sort       |
| O(N²)      | Quadratic time   | Bubble Sort, Insertion Sort |
| O(2ⁿ)      | Exponential time | Recursive Fibonacci         |
| O(N!)      | Factorial time   | Permutation generation      |

> **Note on Fibonacci**: Recursive Fibonacci is actually Θ(φⁿ) (φ≈1.618), but O(2ⁿ) is commonly used as an upper bound.

---

## 📦 Common Data Structures

### 📁 Array (Dynamic)

| Operation       | Time             | Space |
| --------------- | ---------------- | ----- |
| Access          | O(1)             | O(N)  |
| Search          | O(N)             | O(N)  |
| Insert (end)    | O(1) (amortized) | O(N)  |
| Insert (middle) | O(N)             | O(N)  |
| Delete          | O(N)             | O(N)  |

> **Note**: Static arrays don't allow insertion/deletion. Amortized time accounts for dynamic array resizing.

---

### 🔗 Linked List

| Operation               | Time     | Space | Notes |
| ----------------------- | -------- | ----- | ----- |
| Access/Search           | O(N)     | O(N)  |       |
| Insert/Delete at head   | O(1)     | O(N)  |       |
| Insert at tail          | O(1)\*   | O(N)  | (1)   |
| Delete at tail          | O(1)\*\* | O(N)  | (2)   |
| Insert/Delete in middle | O(N)     | O(N)  | (3)   |

> (1) Insertion at tail: O(1) with tail pointer (both singly/doubly)  
> (2) Deletion at tail: O(1) for doubly linked list; O(N) for singly linked list  
> (3) After finding target position

---

### 📚 Stack / Queue

| Operation     | Time | Space | Notes |
| ------------- | ---- | ----- | ----- |
| Push/Enqueue  | O(1) | O(N)  | (1)   |
| Pop/Dequeue   | O(1) | O(N)  | (1)   |
| Access/Search | O(N) | O(N)  |       |

> (1) Assumes efficient implementation (linked list or circular buffer)

---

### 🗃️ Hash Table

| Operation | Avg Time | Worst Time | Space |
| --------- | -------- | ---------- | ----- |
| Insert    | O(1)     | O(N)       | O(N)  |
| Delete    | O(1)     | O(N)       | O(N)  |
| Search    | O(1)     | O(N)       | O(N)  |

> Worst-case occurs with excessive collisions (e.g., poor hash function)

---

### 🌲 Binary Search Tree (BST)

| Operation | Avg Time | Worst Time (unbalanced) | Space |
| --------- | -------- | ----------------------- | ----- |
| Insert    | O(log N) | O(N)                    | O(N)  |
| Delete    | O(log N) | O(N)                    | O(N)  |
| Search    | O(log N) | O(N)                    | O(N)  |

---

### 🌳 AVL / Red-Black Tree (Self-Balancing BST)

| Operation | Time     | Space |
| --------- | -------- | ----- |
| Insert    | O(log N) | O(N)  |
| Delete    | O(log N) | O(N)  |
| Search    | O(log N) | O(N)  |

---

### 🔺 Heap (Min or Max Heap)

| Operation    | Time     | Space |
| ------------ | -------- | ----- |
| Insert       | O(log N) | O(N)  |
| Delete       | O(log N) | O(N)  |
| Find min/max | O(1)     | O(N)  |
| Search       | O(N)     | O(N)  |

> `Find min/max` is O(1) for either **min-heap** or **max-heap**, not both

---

### 🔡 Trie (Prefix Tree)

| Operation | Time | Space       | Notes |
| --------- | ---- | ----------- | ----- |
| Insert    | O(L) | O(L) per op | (1)   |
| Search    | O(L) | O(1)        |       |
| Delete    | O(L) | O(1)        |       |

> L = Length of word  
> (1) Total space complexity: O(N×L) for N words

---

### 🌐 Graph Representations

| Representation   | Add Edge Time | Space    |
| ---------------- | ------------- | -------- |
| Adjacency Matrix | O(1)          | O(V²)    |
| Adjacency List   | O(1)          | O(V + E) |

---

## ⚙️ Sorting Algorithms

| Algorithm      | Best       | Avg        | Worst      | Space        | Stable |
| -------------- | ---------- | ---------- | ---------- | ------------ | ------ |
| Bubble Sort    | O(N)       | O(N²)      | O(N²)      | O(1)         | ✅     |
| Selection Sort | O(N²)      | O(N²)      | O(N²)      | O(1)         | ❌ (1) |
| Insertion Sort | O(N)       | O(N²)      | O(N²)      | O(1)         | ✅     |
| Merge Sort     | O(N log N) | O(N log N) | O(N log N) | O(N)         | ✅     |
| Quick Sort     | O(N log N) | O(N log N) | O(N²)      | O(log N) (2) | ❌     |
| Heap Sort      | O(N log N) | O(N log N) | O(N log N) | O(1)         | ❌     |
| Counting Sort  | O(N + K)   | O(N + K)   | O(N + K)   | O(K)         | ✅     |
| Radix Sort     | O(N×K)     | O(N×K)     | O(N×K)     | O(N + K)     | ✅     |

> (1) Selection Sort can be stable with stable swaps, but typically isn't  
> (2) Quick Sort: Avg space O(log N), worst O(N) without tail recursion optimization

---

## 🔍 Searching Algorithms

| Algorithm     | Time     | Space | Notes |
| ------------- | -------- | ----- | ----- |
| Linear Search | O(N)     | O(1)  |       |
| Binary Search | O(log N) | O(1)  | (1)   |

> (1) Requires sorted input. Recursive version uses O(log N) space.

---

## 🧩 Algorithm Paradigms

### Divide and Conquer

| Algorithm  | Time       | Space    |
| ---------- | ---------- | -------- |
| Merge Sort | O(N log N) | O(N)     |
| Quick Sort | O(N log N) | O(log N) |

---

### Dynamic Programming

| Type                   | Time             | Space            | Notes |
| ---------------------- | ---------------- | ---------------- | ----- |
| Memoization (Top-Down) | Problem-specific | Problem-specific | (1)   |
| Tabulation (Bottom-Up) | Problem-specific | Problem-specific | (1)   |

> (1) Common complexities: O(N) to O(N²) for 1D/2D problems

---

### Greedy

> Used in: Activity Selection, Huffman Coding, Kruskal’s, Prim’s

| Time       | Space | Notes |
| ---------- | ----- | ----- |
| O(N log N) | O(N)  | (1)   |

> (1) Complexity varies by algorithm (e.g., Huffman is O(N log N))

---

### Backtracking

> Used in: N-Queens, Sudoku, Permutations

| Time  | Space | Notes |
| ----- | ----- | ----- |
| O(Bᴰ) | O(D)  | (1)   |

> B = branching factor, D = depth of solution tree  
> (1) Space complexity assumes tail recursion optimization

---

## 🌉 Graph Algorithms

| Algorithm       | Time Complexity  | Space Complexity | Notes |
| --------------- | ---------------- | ---------------- | ----- |
| BFS (Adj List)  | O(V + E)         | O(V)             |       |
| DFS (Adj List)  | O(V + E)         | O(V)             |       |
| Dijkstra (PQ)   | O((V + E) log V) | O(V)             | (1)   |
| Bellman-Ford    | O(V×E)           | O(V)             |       |
| Floyd-Warshall  | O(V³)            | O(V²)            |       |
| Kruskal’s       | O(E log E)       | O(V)             | (2)   |
| Prim’s (PQ)     | O((V + E) log V) | O(V)             | (1)   |
| Topo Sort (DFS) | O(V + E)         | O(V)             |       |

> (1) With Fibonacci Heap: O(E + V log V)  
> (2) Equivalent to O(E log V) since log E = O(log V)
