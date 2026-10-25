class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int low = matrix[0][0];
        int high = matrix[n-1][m-1];
        
        while(low<high){
            int guess= low+(high-low)/2;
            int count = fun(matrix, guess);

            if(count<k) {
                low = guess+1;
            }
            else{
                high= guess;
            }
        }
        return low;
    }
        private int fun(int[][] matrix , int target){
            int n = matrix.length;
            int row = n-1;
            int col =0;
            int count = 0;
            while(row>=0 && col < matrix[0].length){
                if(matrix[row][col] <= target){
                count = count+row+1;
                col++;
                }
                else{
                    row--;
            }
            
        }
        return count;
    }
}
