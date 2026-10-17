class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Step 1: Create a collection of pairs (digit_sum, value)
        // We pack them into a 2D array or an array of objects for efficient custom sorting
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = getDigitSum(nums[i]);
            pairs[i][1] = nums[i];
        }
        
        // Step 2: Sort based on criteria: digit sum ascending, then by numerical value ascending
        Arrays.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        // Step 3: Map each value to its final target position index
        Map<int[], Integer> targetMap = new HashMap<>(); // Using HashMap because values are distinct
        Map<Integer, Integer> valueToTargetIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToTargetIndex.put(pairs[i][1], i);
        }
        
        // Step 4: Use a visited array to identify and track permutation cycles
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            // If the element is already visited or already at its correct spot, skip it
            if (visited[i] || valueToTargetIndex.get(nums[i]) == i) {
                continue;
            }
            
            // Calculate cycle length
            int cycleSize = 0;
            int current = i;
            while (!visited[current]) {
                visited[current] = true;
                current = valueToTargetIndex.get(nums[current]);
                cycleSize++;
            }
            
            // A cycle of size k requires k - 1 swaps
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