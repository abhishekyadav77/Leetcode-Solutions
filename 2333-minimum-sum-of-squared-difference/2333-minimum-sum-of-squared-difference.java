class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        

        int[] diffCounts = new int[100001];
        long totalDiffSum = 0;
        int maxDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                diffCounts[diff]++;
                totalDiffSum += diff;
                maxDiff = Math.max(maxDiff, diff);
            }
        }
      
        if (totalDiffSum <= k) {
            return 0;
        }
        
     
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (diffCounts[d] == 0) {
                continue;
            }
            
            long count = diffCounts[d];
            
            long take = Math.min(k, count);
            
            diffCounts[d] -= take;
            diffCounts[d - 1] += take;
            k -= take;

            if (diffCounts[d] > 0) {
                d++;
            }
        }
        long minSquaredSum = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (diffCounts[d] > 0) {
                minSquaredSum += (long) d * d * diffCounts[d];
            }
        }  
        return minSquaredSum;
    }
}