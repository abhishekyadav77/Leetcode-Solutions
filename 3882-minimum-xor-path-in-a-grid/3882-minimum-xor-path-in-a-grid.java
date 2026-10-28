class Solution {
    public int minCost(int[][] grid) {
         int m = grid.length;
        int n = grid[0].length;
        
        // dp[j][v] stores whether the XOR value 'v' is reachable at column j
        boolean[][] dp = new boolean[n][1024];
        
        // Base case: Starting at (0, 0)
        dp[0][grid[0][0]] = true;
        
        // Initialize the first row (can only come from the left)
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            for (int v = 0; v < 1024; v++) {
                if (dp[j - 1][v]) {
                    dp[j][v ^ val] = true;
                }
            }
        }
        
        // Process the remaining rows
        for (int i = 1; i < m; i++) {
            boolean[][] nextDp = new boolean[n][1024];
            
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                
                // 1. Transition from the cell above (i-1, j)
                for (int v = 0; v < 1024; v++) {
                    if (dp[j][v]) {
                        nextDp[j][v ^ val] = true;
                    }
                }
                
                // 2. Transition from the cell to the left (i, j-1)
                if (j > 0) {
                    for (int v = 0; v < 1024; v++) {
                        if (nextDp[j - 1][v]) {
                            nextDp[j][v ^ val] = true;
                        }
                    }
                }
            }
            dp = nextDp; // Roll over to the next row
        }
        
        // Find the minimum reachable XOR value at the bottom-right corner (m-1, n-1)
        for (int v = 0; v < 1024; v++) {
            if (dp[n - 1][v]) {
                return v;
            }
        }

        return -1;
    }
}