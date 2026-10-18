class Solution {
    static final int MOD = 1000000007;
    public int numOfWays(int[] nums) {
        int n = nums.length;
        long[][] comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1]
                        + comb[i - 1][j]) % MOD;
            }
        }
        long ways = countWays(nums, comb);
        return (int) ((ways - 1 + MOD) % MOD);
    }
    private long countWays(int[] nums, long[][] comb) {
        int n = nums.length;
        if (n <= 2) {
            return 1;
        }
        int root = nums[0];
        java.util.List<Integer> left = new java.util.ArrayList<>();
        java.util.List<Integer> right = new java.util.ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left.add(nums[i]);
            } else {
                right.add(nums[i]);
            }
        }
        int leftSize = left.size();
        int rightSize = right.size();
        int[] leftArr = left.stream().mapToInt(Integer::intValue).toArray();
        int[] rightArr = right.stream().mapToInt(Integer::intValue).toArray();
        long leftWays = countWays(leftArr, comb);
        long rightWays = countWays(rightArr, comb);
        long total = comb[leftSize + rightSize][leftSize];
        total = (total * leftWays) % MOD;
        total = (total * rightWays) % MOD;
        return total;
    }
}