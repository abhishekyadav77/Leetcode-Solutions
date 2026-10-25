class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low =1;
        int high = Math.min(m * n, k);
        while(low<high){
            int mid=low+(high-low)/2;
            int count=0;
            for(int i=1; i<=m; i++){
                count += Math.min(n,mid/i);
            }
            if(count<k){
                low= mid+1;
            }
            else{
                high=mid;
            }
        }
        return low;
    }
}