class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n= matrix.length;
        int m = matrix[0].length;
        int low = 0;
        int high = m*n-1;
        while(low<= high){
            int guess= (low+high)/2;
            int row = guess/m;
            int col = guess%m;
            if(matrix[row][col]== target)
            return true;
            if(matrix[row][col] <target)
            low = guess+1;
            else
            high=guess-1;
        }
        return false;
    }
}