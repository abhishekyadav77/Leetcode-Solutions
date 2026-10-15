class Solution {
    public int[] searchRange(int[] nums, int target) {
        int low =0;
        int high = nums.length-1;

        int res1 = -1;
        int res2 = -1;
        while(low<=high){
            int guess= (low+high)/2;

            if(nums[guess]==target){
                res1 = guess;
                high= guess-1;
            }
            else if(nums[guess] < target) {
                low = guess+1;
            }
            else{
                high = guess-1;
            }
        }       
        low = 0;
         high = nums.length-1;          
        while(low<=high){
            int guess = (low+high)/2;
            if(nums[guess] == target){
                res2= guess;
                low = guess+1;
            }
            else if(nums[guess] <target){
                low = guess+1;
            }
            else{
                high = guess-1;

            }
        }
        return new int[]{res1, res2};

    }
}