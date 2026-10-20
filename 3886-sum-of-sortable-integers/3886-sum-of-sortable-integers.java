class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (isValid(nums, n, k)) {
                    sum += k;
                }
            }
        }

        return sum;
    }
    private boolean isValid(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int dropCount = 0;
            for (int j = 0; j < k; j++) {
                int currIdx = i + j;
                int nextIdx = i + (j + 1) % k;

                if (nums[currIdx] > nums[nextIdx]) {
                    dropCount++;
              }

                currentMin = Math.min(currentMin, nums[currIdx]);
                currentMax = Math.max(currentMax, nums[currIdx]);
            }
            if (dropCount > 1) {
                return false;
            }
            if (currentMin < prevMax) {
                return false;
            }
            prevMax = currentMax;
        }

        return true;
    }
}