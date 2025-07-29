# Time and Space Complexity Cheat Sheet

---

## 🧠 Big-O Complexity Basics

| Big-O      | Description      | Example                     |
| ---------- | ---------------- | --------------------------- |
| O(1)       | Constant time    | Accessing array by index    |
| O(log N)   | Logarithmic time | Binary Search               |
| O(N)       | Linear time      | Traversing an array         |
| O(N log N) | Log-linear time  | Merge Sort, Heap Sort       |
| O(N²)      | Quadratic time   | Bubble Sort, Insertion Sort |
| O(2ⁿ)      | Exponential time | Recursive Fibonacci         |
| O(N!)      | Factorial time   | Generating permutations     |

---

## 📦 Common Data Structures

### 📁 Array

| Operation       | Time             | Space |
| --------------- | ---------------- | ----- |
| Access          | O(1)             | O(N)  |
| Search          | O(N)             | O(N)  |
| Insert (end)    | O(1) (amortized) | O(N)  |
| Insert (middle) | O(N)             | O(N)  |
| Delete          | O(N)             | O(N)  |

---

### 🔗 Linked List

| Operation               | Time   | Space |
| ----------------------- | ------ | ----- |
| Access/Search           | O(N)   | O(N)  |
| Insert/Delete at head   | O(1)   | O(N)  |
| Insert/Delete at tail   | O(1)\* | O(N)  |
| Insert/Delete in middle | O(N)   | O(N)  |

> \* O(1) only if a tail pointer is maintained (e.g., in a singly/doubly linked list)

---

### 📚 Stack / Queue

| Operation     | Time | Space |
| ------------- | ---- | ----- |
| Push/Enqueue  | O(1) | O(N)  |
| Pop/Dequeue   | O(1) | O(N)  |
| Access/Search | O(N) | O(N)  |

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

| Operation | Time | Space  |
| --------- | ---- | ------ |
| Insert    | O(L) | O(N×L) |
| Search    | O(L) | O(N×L) |
| Delete    | O(L) | O(N×L) |

> L = Length of word, N = Number of words

---

### 🌐 Graph Representations

| Representation   | Add Edge Time | Space    |
| ---------------- | ------------- | -------- |
| Adjacency Matrix | O(1)          | O(V²)    |
| Adjacency List   | O(1)          | O(V + E) |

---

## ⚙️ Sorting Algorithms

| Algorithm      | Best       | Avg        | Worst      | Space      | Stable |
| -------------- | ---------- | ---------- | ---------- | ---------- | ------ |
| Bubble Sort    | O(N)       | O(N²)      | O(N²)      | O(1)       | ✅     |
| Selection Sort | O(N²)      | O(N²)      | O(N²)      | O(1)       | ❌     |
| Insertion Sort | O(N)       | O(N²)      | O(N²)      | O(1)       | ✅     |
| Merge Sort     | O(N log N) | O(N log N) | O(N log N) | O(N)       | ✅     |
| Quick Sort     | O(N log N) | O(N log N) | O(N²)      | O(log N)\* | ❌     |
| Heap Sort      | O(N log N) | O(N log N) | O(N log N) | O(1)\*\*   | ❌     |
| Counting Sort  | O(N + K)   | O(N + K)   | O(N + K)   | O(K)       | ✅     |
| Radix Sort     | O(N×K)     | O(N×K)     | O(N×K)     | O(N + K)   | ✅     |

> \* Quick Sort: Avg space is O(log N), worst is O(N) without tail recursion
> \*\* Heap Sort: In-place, but uses an array-based binary heap

---

## 🔍 Searching Algorithms

| Algorithm     | Time     | Space |
| ------------- | -------- | ----- |
| Linear Search | O(N)     | O(1)  |
| Binary Search | O(log N) | O(1)  |

> Binary Search requires sorted input

---

## 🧩 Algorithm Paradigms

### Divide and Conquer

| Algorithm  | Time       | Space    |
| ---------- | ---------- | -------- |
| Merge Sort | O(N log N) | O(N)     |
| Quick Sort | O(N log N) | O(log N) |

---

### Dynamic Programming

| Type                   | Time       | Space      |
| ---------------------- | ---------- | ---------- |
| Memoization (Top-Down) | O(N)–O(N²) | O(N)–O(N²) |
| Tabulation (Bottom-Up) | O(N)–O(N²) | O(N)–O(N²) |

---

### Greedy

> Used in: Activity Selection, Huffman Coding, Kruskal’s, Prim’s

| Time       | Space |
| ---------- | ----- |
| O(N log N) | O(N)  |

---

### Backtracking

> Used in: N-Queens, Sudoku, Permutations

| Time  | Space |
| ----- | ----- |
| O(Bᴰ) | O(D)  |

> B = branching factor, D = depth of solution tree

---

## 🌉 Graph Algorithms

| Algorithm       | Time Complexity    | Space Complexity |
| --------------- | ------------------ | ---------------- |
| BFS (Adj List)  | O(V + E)           | O(V)             |
| DFS (Adj List)  | O(V + E)           | O(V)             |
| Dijkstra (PQ)   | O((V + E) log V)\* | O(V)             |
| Bellman-Ford    | O(V × E)           | O(V)             |
| Floyd-Warshall  | O(V³)              | O(V²)            |
| Kruskal’s       | O(E log E)         | O(V)             |
| Prim’s (PQ)     | O((V + E) log V)\* | O(V)             |
| Topo Sort (DFS) | O(V + E)           | O(V)             |

> \* Using a **binary heap**. With Fibonacci Heap, Dijkstra and Prim can be improved to O(E + V log V)
