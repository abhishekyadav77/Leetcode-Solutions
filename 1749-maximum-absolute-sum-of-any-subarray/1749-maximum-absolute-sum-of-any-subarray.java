class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxcurr = nums[0];
        int maxsum = nums[0];
        int mincurr = nums[0];
        int minsum = nums[0];

        for (int i = 1; i<nums.length; i++) {
            maxcurr = Math.max(nums[i] , maxcurr+ nums[i]);
            maxsum = Math.max(maxsum, maxcurr);
            mincurr = Math.min(nums[i], mincurr +nums[i]);
            minsum = Math.min(minsum, mincurr);
        }
        return Math.max(maxsum, -minsum);
    }
}