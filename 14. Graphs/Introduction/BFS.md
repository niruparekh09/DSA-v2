# BFS — Handling Disconnected Components & Example

## 1) How handling of DISCONNECTED components works (for `bfsOfGraph()`)

```text
    ------------------------------------------------------------
    How handling of DISCONNECTED components works
    ------------------------------------------------------------

    Example Graph (V = 4):

        Component 1:        Component 2:
         0 ---- 1            2 ---- 3

    Adjacency representation:
        0: [1]
        1: [0]
        2: [3]
        3: [2]

    Process (looping i = 0 -> 3):

    i = 0:
        visited[0] == false -> run bfs(0)
        BFS(0) visits: 0, 1

    i = 1:
        visited[1] == true -> skip

    i = 2:
        visited[2] == false -> run bfs(2)
        BFS(2) visits: 2, 3

    i = 3:
        visited[3] == true -> skip

    Result:
        Both disconnected components {0,1} and {2,3} are fully traversed.
        BFS output = [0, 1, 2, 3]  // Order may vary based on adjacency list

```

---

## 2) Visual representation and queue-step example (from the `main()` example in the repo)

```text

    Visual representation of the example graph in main:

        1
        |
        0 -- 3
        |
        2
        |
        4

    Adjacency (from code):
    0: [2, 3, 1]
    1: [0]
    2: [0, 4]
    3: [0]
    4: [2]

    BFS starting at node 0 (neighbors visited in listed order):
    Queue steps:
     - start: [0]
     - visit 0 -> enqueue [2,3,1]
     - visit 2 -> enqueue 4   => queue [3,1,4]
     - visit 3 -> (no new)    => queue [1,4]
     - visit 1 -> (no new)    => queue [4]
     - visit 4 -> (no new)    => queue []

    Resulting BFS order: [0, 2, 3, 1, 4]

    Complexity:
     - Time: O(V + E)   (each node and edge examined once)
     - Space: O(V)      (visited array + queue up to O(V))
```