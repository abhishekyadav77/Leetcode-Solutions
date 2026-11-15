class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int evenCount = 1; // Tracks even prefix sums (initialized to 1 for empty prefix sum 0)
        int oddCount = 0;  // Tracks odd prefix sums
        int prefixSum = 0;
        int result = 0;

        for (int num : arr) {
            prefixSum += num;

            if ((prefixSum & 1) == 1) {
                // Odd prefix sum minus any previous even prefix sum yields an odd subarray sum
                result = (result + evenCount) % MOD;
                oddCount++;
            } else {
                // Even prefix sum minus any previous odd prefix sum yields an odd subarray sum
                result = (result + oddCount) % MOD;
                evenCount++;
            }
        }

        return result;
    }
}