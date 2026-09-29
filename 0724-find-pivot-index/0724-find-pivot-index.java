class Solution {
    public int pivotIndex(int[] nums) {
        int rightsum = 0;
        for(int num : nums) {
            rightsum = rightsum + num;
        }

        int leftsum = 0;

        for (int i = 0; i<nums.length; i++) {

            rightsum = rightsum - nums[i];

            if(leftsum == rightsum) {
                return i;
            }

            leftsum = leftsum +nums[i];
        }
        return -1;
    }
}