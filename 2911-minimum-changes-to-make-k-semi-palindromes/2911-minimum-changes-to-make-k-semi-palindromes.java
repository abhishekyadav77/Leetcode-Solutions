class Solution {
    public int minimumChanges(String s, int k) {
         int n = s.length();
        // Precompute divisors for every possible substring length (1 to n)
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 1; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // cost[i][j] = min changes to make substring s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        int INF = 1_000_000;
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // length must be at least 2
                int len = j - i + 1;
                for (int d : divisors[len]) {
                    cost[i][j] = Math.min(cost[i][j], getCost(s, i, j, d));
                }
            }
        }

        // dp[i][p] = min changes to partition prefix s[0..i-1] into p semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) { // each partition needs length >= 2
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[j][p - 1] != INF && cost[j][i - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    // Helper to calculate changes needed for s[start..end] with step d
    private int getCost(String s, int start, int end, int d) {
        int changes = 0;
        for (int rem = 0; rem < d; rem++) {
            int left = start + rem;
            int right = end - d + 1 + rem;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}