class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = getDigitSum(nums[i]);
            pairs[i][1] = nums[i];
        }

        Arrays.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        Map<int[], Integer> targetMap = new HashMap<>(); 
        Map<Integer, Integer> valueToTargetIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToTargetIndex.put(pairs[i][1], i);
        }
  
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {

            if (visited[i] || valueToTargetIndex.get(nums[i]) == i) {
                continue;
            }
            
            int cycleSize = 0;
            int current = i;
            while (!visited[current]) {
                visited[current] = true;
                current = valueToTargetIndex.get(nums[current]);
                cycleSize++;
            }
        
            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }
        
        return totalSwaps;
    }
    
    // Helper function to extract and sum digits
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}