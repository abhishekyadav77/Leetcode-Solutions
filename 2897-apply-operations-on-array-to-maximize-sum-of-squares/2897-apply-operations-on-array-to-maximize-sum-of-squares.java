class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        int[] bitCounts = new int[31];

        for (int num :nums) {
            for (int i =0; i < 31; i++) {
                if (((num>> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumSquares= 0;
     
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int j = 0; j < 31; j++) {
                if (bitCounts[j] > 0) {
                    currentNum |= (1L << j);
                    bitCounts[j]--;
                }
            }
            totalSumSquares = (totalSumSquares + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSumSquares;
    }
}