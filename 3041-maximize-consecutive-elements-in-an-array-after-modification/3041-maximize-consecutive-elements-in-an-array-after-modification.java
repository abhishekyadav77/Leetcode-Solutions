class Solution {
    public int maxSelectedElements(int[] nums) {
         // Sort the array to process elements in ascending order
        Arrays.sort(nums);
        
        // dp.get(x) = max length of consecutive sequence ending at value x
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int num : nums) {
            // Option 1: Increment num to (num + 1), extending a sequence ending at num
            int lenPlusOne = dp.getOrDefault(num, 0) + 1;
            dp.put(num + 1, lenPlusOne);
            
            // Option 2: Keep num as is, extending a sequence ending at (num - 1)
            int lenSame = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num, lenSame);
            
            // Update the global maximum length
            maxLen = Math.max(maxLen, Math.max(lenPlusOne, lenSame));
        }
        
        return maxLen;
    }
}