
class Solution {

    static class Window {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.merge(val, 1, Integer::sum);
            size++;
            sum += val;
        }

        void remove(int val) {
            map.merge(val, -1, Integer::sum);
            if (map.get(val) == 0) {
                map.remove(val);
            }
            size--;
            sum -= val;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }
    }

    Window lower = new Window();
    Window upper = new Window();

    void rebalance() {
        while (lower.size < upper.size) {
            int val = upper.first();
            upper.remove(val);
            lower.add(val);
        }

        while (lower.size > upper.size + 1) {
            int val = lower.last();
            lower.remove(val);
            upper.add(val);
        }
    }

    void insert(int val) {
        if (lower.size == 0 || val <= lower.last()) {
            lower.add(val);
        } else {
            upper.add(val);
        }
        rebalance();
    }

    void erase(int val) {
        if (lower.map.containsKey(val)) {
            lower.remove(val);
        } else {
            upper.remove(val);
        }
        rebalance();
    }

    long getCost() {
        int median = lower.last();

        long leftCost =
                (long) median * lower.size - lower.sum;

        long rightCost =
                upper.sum - (long) median * upper.size;

        return leftCost + rightCost;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        lower = new Window();
        upper = new Window();

        for (int i = 0; i < n; i++) {
            insert(nums[i]);

            if (i >= x) {
                erase(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 0; i <= n; i++) {
                if (i > 0) {
                    dp[j][i] = Math.min(
                            dp[j][i],
                            dp[j][i - 1]
                    );
                }

                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                            dp[j][i],
                            dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }
}