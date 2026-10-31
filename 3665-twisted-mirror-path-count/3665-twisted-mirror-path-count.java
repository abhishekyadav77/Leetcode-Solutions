
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] grid;
    private int[][] dp;
    private int m, n;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, 0);
    }

    private int solve(int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 0;
        }

        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        long ways = 0;

        // Try moving right
        ways += move(row, col, 0);

        // Try moving down
        ways += move(row, col, 1);

        return dp[row][col] = (int) (ways % MOD);
    }

    private int move(int row, int col, int direction) {
        if (direction == 0) {
            col++;
        } else {
            row++;
        }

        while (row < m && col < n) {

            // Destination reached
            if (row == m - 1 && col == n - 1) {
                return 1;
            }

            // Stop when we reach a non-mirror cell
            if (grid[row][col] == 0) {
                return solve(row, col);
            }

            // Mirror: moving right turns down
            if (direction == 0) {
                row++;
                direction = 1;
            }
            // Mirror: moving down turns right
            else {
                col++;
                direction = 0;
            }
        }

        // Robot moved outside the grid
        return 0;
    }
}

