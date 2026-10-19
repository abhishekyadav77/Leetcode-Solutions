class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTargetCount = 0;
        int equalToTargetCount = 0;
        for (int num : nums) {
            if (num < target) {
                lessThanTargetCount++;
            } else if (num == target) {
                equalToTargetCount++;
            }
        }       
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < equalToTargetCount; i++) {
            result.add(lessThanTargetCount + i);
        }
        
        return result;
    }
}