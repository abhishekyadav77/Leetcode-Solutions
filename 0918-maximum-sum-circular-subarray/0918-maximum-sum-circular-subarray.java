class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int total = 0;

        int maxCurr = nums[0];
        int maxSum = nums[0];

        int minCurr = nums[0];
        int minSum = nums[0];

        total += nums[0];

        for (int i = 1; i < nums.length; i++) {

            total += nums[i];

     //Maximum subarray of array
            maxCurr =Math.max(nums[i], maxCurr + nums[i]);
            maxSum = Math.max(maxSum, maxCurr);

        //Minimum subarray of an array
            minCurr = Math.min(nums[i], minCurr + nums[i]);
            minSum = Math.min(minSum, minCurr);
        }

        // All negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Circular subarray
        int circularSum = total - minSum;

        return Math.max(maxSum, circularSum);
    }
}