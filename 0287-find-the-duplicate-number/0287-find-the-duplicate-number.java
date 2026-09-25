class Solution {
    public int findDuplicate(int[] nums) {

        // this can be solved by hashset also and by using the brute force solution also

       // Phase1  Find meeting point in a array by using do while loops 
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

    // step 2 find the entrance of cycle
        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}