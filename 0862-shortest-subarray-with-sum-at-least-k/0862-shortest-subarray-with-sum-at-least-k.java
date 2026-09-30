import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        
  // Build the prefix sum array to handle negative numbers
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        Deque<Integer> indices = new ArrayDeque<>();
        int minLength = n + 1;
        
        for (int i = 0; i <= n; i++) {
 // Shrink the window from the left if the current subarray sum is >= k
            while (!indices.isEmpty() && prefixSum[i] - prefixSum[indices.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - indices.pollFirst());
            }
            
  // Maintain a strictly increasing order of prefix sums in the deque
            while (!indices.isEmpty() && prefixSum[i] <= prefixSum[indices.peekLast()]) {
                indices.pollLast();
            }
            
            indices.offerLast(i);
        }
        
 // Return -1 if no valid subarray was found, otherwise return the minimum length
        return minLength == n + 1 ? -1 : minLength;
    }
}
