class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    freeCount[i]++;
                }
            }
        }

        // dp[b] = max copies from first-time purchases using at most budget b
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];
            int gain = 1 + freeCount[i];

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + gain);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            int extraCopies = (budget - b) / minPrice;
            ans = Math.max(ans, dp[b] + extraCopies);
        }

        return ans;
    }
}