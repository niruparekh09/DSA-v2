import java.util.Arrays;

public class SurroundedRegions {

    // Delta Row & Col for neighbor traversal
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        SurroundedRegions s = new SurroundedRegions();
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        s.solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    /*
     * Approach: Boundary DFS
     * Pattern: Matrix Traversal / Flood Fill
     * Time Complexity: O(N * M) - Each cell is visited at most a constant number of times.
     * Space Complexity: O(N * M) - For visited array and recursion stack.
     */
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][] vis = new int[n][m];

        // Key Strategy: "Reverse Thinking". Instead of finding surrounded regions,
        // find 'O's connected to the boundary (which CANNOT be surrounded).

        // Step 1: Traverse boundaries (1st/Last Row & Col).
        // Run DFS from any 'O' on the boundary to mark it and its connected 'O's as "Safe".

        // 1st and Last Row
        for (int j = 0; j < m; j++) {
            // 1st row
            if (vis[0][j] == 0 && board[0][j] == 'O') {
                dfs(0, j, vis, board);
            }
            // Last row
            if (vis[n - 1][j] == 0 && board[n - 1][j] == 'O') {
                dfs(n - 1, j, vis, board);
            }
        }

        // 1st and Last Col
        for (int i = 0; i < n; i++) {
            // 1st col
            if (vis[i][0] == 0 && board[i][0] == 'O') {
                dfs(i, 0, vis, board);
            }
            // Last Col
            if (vis[i][m - 1] == 0 && board[i][m - 1] == 'O') {
                dfs(i, m - 1, vis, board);
            }
        }

        // Step 2: Traverse the whole grid.
        // If a cell is 'O' but NOT visited, it means it wasn't connected to the boundary.
        // Therefore, it is surrounded by 'X's and should be captured.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    // Standard DFS to mark connected 'O' components
    private void dfs(int i, int j, int[][] vis, char[][] board) {
        vis[i][j] = 1;
        int n = board.length;
        int m = board[0].length;

        for (int k = 0; k < 4; k++) {
            // Note: Subtraction here works as addition of the inverse direction, traversing neighbors.
            int r = i - dRow[k];
            int c = j - dCol[k];

            if (isValid(n, m, r, c) && vis[r][c] == 0 && board[r][c] == 'O') {
                dfs(r, c, vis, board);
            }
        }
    }

    private boolean isValid(int n, int m, int x, int y) {
        if (x < 0 || x >= n) return false;
        if (y < 0 || y >= m) return false;
        return true;
    }
}