class Solution {
    public int maximumSum(int[] arr) {

        int noDelete = arr[0];
        int oneDelete = 0;
        int ans = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int oldNoDelete = noDelete;

            // Don't delete current element
            noDelete = Math.max(arr[i], noDelete + arr[i]);

            // Delete current OR deletion already happened
            oneDelete = Math.max(oldNoDelete, oneDelete + arr[i]);

            ans = Math.max(ans, Math.max(noDelete, oneDelete));
        }

        return ans;
    }
}