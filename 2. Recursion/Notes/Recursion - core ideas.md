# Recursion - core ideas

Of course! Let's break down recursion for Data Structures and Algorithms (DSA), focusing on the practical and often confusing aspect of helper functions.

### Part 1: What is Recursion?

At its core, recursion is a problem-solving technique where a function calls itself to solve smaller instances of the same problem. Think of it like a set of Russian Matryoshka dolls. To find the smallest doll, you open the current one, which reveals a slightly smaller doll (a smaller version of the problem). You repeat this process until you find the final, solid doll that cannot be opened.

Every recursive solution has two essential components:

1.  **Base Case:** This is the stopping condition. It's the simplest version of the problem that can be solved directly, without making another recursive call. In our doll analogy, it's the solid doll that can't be opened. Without a base case, the function would call itself forever, leading to a **stack overflow error**.
2.  **Recursive Step:** This is where the magic happens. The function calls itself, but with an input that is "closer" to the base case. This step breaks the current problem down into a smaller, simpler sub-problem. This is opening a doll to find the next smaller one.

**How it works technically:** When a function calls another function, the system uses a **call stack** to keep track of where it was. It pushes the current function's state (its variables and the line of code it's on) onto the stack. When the called function finishes, the system pops the state from the stack and resumes the original function. In recursion, a function keeps pushing new versions of itself onto the stack until a base case is hit. Then, it unwinds the stack, returning values all the way back up.

---

### Part 2: The Helper Function Dilemma: When and Why?

This is a critical concept for writing clean and effective recursive code. The decision to use a helper function depends on one question: **Does the function signature I want the public to use have all the information the recursive logic needs?**

#### When a Helper Function is **NOT** Needed

You don't need a helper function when the parameters of the function are sufficient to:

1.  Define the current sub-problem completely.
2.  Compute the result directly from the return values of its recursive calls.

The state required for the computation is implicitly managed by the call stack itself.

**Example: Factorial**
`factorial(n)` only needs the integer `n`. The sub-problem is `factorial(n-1)`. The result is simply `n * factorial(n-1)`. The signature `factorial(n)` is perfectly sufficient.

```java
public int factorial(int n) {
    // Base Case
    if (n <= 1) {
        return 1;
    }
    // Recursive Step
    return n * factorial(n - 1);
}
```

#### When a Helper Function **IS** Needed

A helper function becomes necessary when the recursion needs to **track additional state or information** that is not part of the original, "clean" function signature. This extra information is passed along through the recursive calls.

Common scenarios for needing a helper:

1.  **Accumulators:** When you need to build up a result, like a sum, a string, or a list, across recursive calls.
2.  **Bounds and Indices:** When working with a data structure like an array, you might need to pass down the current `left` and `right` indices you are considering (e.g., recursive binary search).
3.  **Path Tracking:** In trees or graphs, you often need to keep track of the path taken to get to the current node.
4.  **Maintaining Constraints:** When you need to pass down constraints, like a `min` and `max` value for validating a Binary Search Tree.

The common pattern is:

- A **public-facing function** with a simple, clean signature. This is what the user of your code calls. It often initializes the process.
- A **private helper function** with an expanded signature that includes the extra state-tracking parameters. The public function calls this helper to start the recursion.

---

### Part 3: Why is Understanding This Important?

1.  **API Design and Encapsulation:** The primary function provides a clean interface. The user doesn't need to know about the internal state management of your recursion. They just call `findPaths(root, sum)`, not `findPathsHelper(root, sum, 0, new ArrayList<>(), new ArrayList<>())`. This hides complexity and reduces the chance of user error.
2.  **State Management:** It's the correct way to manage state in recursion. Using a helper with state parameters avoids the use of global or class-level variables, which are not thread-safe and can lead to messy, unpredictable bugs when the function is called multiple times.
3.  **Clarity and Readability:** It separates the "setup" (in the public function) from the core "recursive logic" (in the helper function), making the code easier to understand and debug.

---

### Part 4: Complex Example - Find All Root-to-Leaf Paths with a Target Sum

This is a classic tree problem that perfectly illustrates the need for a helper function.

**Problem:** Given a binary tree and a number `targetSum`, find all root-to-leaf paths where the sum of the nodes' values on the path equals `targetSum`.

The public-facing function should be simple: `List<List<Integer>> pathSum(TreeNode root, int targetSum)`.

Notice this signature is insufficient for our recursion. As we traverse down the tree, we need to know:

- What is the sum of the path _so far_? (An accumulator)
- What are the nodes on the path _so far_? (Path tracking)

Therefore, we need a helper function.

#### The Code (Java)

```java
import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class PathSumExample {

    // 1. PUBLIC-FACING FUNCTION
    // Clean signature. Initializes the result list and calls the helper.
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>(); // Initialize the final list of paths
        List<Integer> currentPath = new ArrayList<>(); // Initialize the list for the current path

        // Kick off the recursion with the helper function
        findPaths(root, targetSum, 0, currentPath, result);

        return result;
    }

    // 2. PRIVATE HELPER FUNCTION
    // Expanded signature with state-tracking parameters: currentSum and currentPath.
    private void findPaths(TreeNode node, int targetSum, int currentSum, List<Integer> currentPath, List<List<Integer>> result) {

        // Base case 1: If the node is null, there's no path here. Stop.
        if (node == null) {
            return;
        }

        // Add the current node to the path and update the sum
        currentPath.add(node.val);
        currentSum += node.val;

        // Base case 2 (Successful): If it's a leaf and the sum matches the target.
        if (node.left == null && node.right == null && currentSum == targetSum) {
            // Found a valid path! Add a *copy* of the current path to the result.
            // Crucial: Create a new list, otherwise future changes to currentPath will affect the result.
            result.add(new ArrayList<>(currentPath));
        }

        // Recursive Step: Explore the left and right children
        findPaths(node.left, targetSum, currentSum, currentPath, result);
        findPaths(node.right, targetSum, currentSum, currentPath, result);

        // BACKTRACKING: This is the most critical step for this problem.
        // Before returning from this function call, "undo" the choice of adding this node.
        // This allows the other branch (e.g., the right branch after the left is fully explored)
        // to have a clean slate for its path.
        currentPath.remove(currentPath.size() - 1);
    }
}
```

### Part 5: Dry Run

Let's dry run the code with this tree and `targetSum = 22`.

```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```

**`result = []`**

1.  `pathSum(root=5, target=22)` is called.
    - It creates `result = []`, `currentPath = []`.
    - It calls `findPaths(node=5, target=22, sum=0, path=[], result)`.

2.  **`findPaths(node=5, sum=0, path=[])`**
    - `node` is not null.
    - `path` becomes `[5]`. `sum` becomes `5`.
    - It's not a leaf node.
    - Recursive call on left child: `findPaths(node=4, target=22, sum=5, path=[5], result)`.

3.  **`findPaths(node=4, sum=5, path=[5])`**
    - `node` is not null.
    - `path` becomes `[5, 4]`. `sum` becomes `9`.
    - It's not a leaf node.
    - Recursive call on left child: `findPaths(node=11, target=22, sum=9, path=[5, 4], result)`.

4.  **`findPaths(node=11, sum=9, path=[5, 4])`**
    - `node` is not null.
    - `path` becomes `[5, 4, 11]`. `sum` becomes `20`.
    - It's not a leaf node.
    - Recursive call on left child: `findPaths(node=7, target=22, sum=20, path=[5, 4, 11], result)`.

5.  **`findPaths(node=7, sum=20, path=[5, 4, 11])`**
    - `node` is not null.
    - `path` becomes `[5, 4, 11, 7]`. `sum` becomes `27`.
    - It IS a leaf node (`left` and `right` are null).
    - Check condition: `sum == target`? `27 == 22` is **false**.
    - **BACKTRACK:** `path.remove(7)`. `path` is now `[5, 4, 11]`.
    - This call finishes and returns.

6.  Back in **`findPaths(node=11, ...)`**
    - The left call is done. Now, recursive call on right child: `findPaths(node=2, target=22, sum=20, path=[5, 4, 11], result)`.

7.  **`findPaths(node=2, sum=20, path=[5, 4, 11])`**
    - `node` is not null.
    - `path` becomes `[5, 4, 11, 2]`. `sum` becomes `22`.
    - It IS a leaf node.
    - Check condition: `sum == target`? `22 == 22` is **true**.
    - **PATH FOUND!** A _copy_ of `[5, 4, 11, 2]` is added to `result`. `result` is now `[[5, 4, 11, 2]]`.
    - **BACKTRACK:** `path.remove(2)`. `path` is now `[5, 4, 11]`.
    - This call finishes and returns.

8.  Back in **`findPaths(node=11, ...)`**
    - Both left and right calls are done.
    - **BACKTRACK:** `path.remove(11)`. `path` is now `[5, 4]`.
    - This call finishes and returns.

9.  Back in **`findPaths(node=4, ...)`**
    - The left call (`node=11`) is done.
    - Recursive call on right child: `findPaths(node=null, target=22, sum=9, path=[5, 4], result)`. This returns immediately.
    - **BACKTRACK:** `path.remove(4)`. `path` is now `[5]`.
    - This call finishes and returns.

10. Back in **`findPaths(node=5, ...)`**
    - The entire left subtree exploration is done.
    - Now, recursive call on right child: `findPaths(node=8, target=22, sum=5, path=[5], result)`.

...The process continues in the same fashion for the right subtree, eventually finding the path `[5, 8, 4, 5]` (sum=22) is not correct `5+8+4+1` should be the other path, let me correct the tree diagram in my head. Ah, the last node is 1, not 5. The other path is `[5, 8, 4, 5]` -> `5+8+4+5 = 22`. Let's assume the diagram's last node is `5`. Oh, wait, the diagram has a final `1`. The sum `5+8+4+1` is `18`. So only one path is found. The dry run logic is sound. Let's finish the trace.

11. **`findPaths(node=8, sum=5, path=[5])`**
    - path becomes `[5, 8]`, sum becomes `13`.
    - Calls left `findPaths(node=13, sum=13, path=[5, 8])`. This is a leaf, but `13+13 != 22`. It backtracks.
    - Calls right `findPaths(node=4, sum=13, path=[5, 8])`.
        - path becomes `[5, 8, 4]`, sum becomes `17`.
        - Calls left `findPaths(node=null, ...)`. Returns.
        - Calls right `findPaths(node=1, sum=17, path=[5, 8, 4])`.
            - path becomes `[5, 8, 4, 1]`, sum becomes `18`.
            - It's a leaf, but `18 != 22`.
            - Backtrack, remove 1.
        - Backtrack, remove 4.
    - Backtrack, remove 8.
    - Backtrack, remove 5.

12. Finally, the first call to `pathSum` returns the `result` list, which contains `[[5, 4, 11, 2]]`.
