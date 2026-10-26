class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] rows =new int[m][2];
        for(int i=0; i<m; i++){
            int low=0;
            int high=n;

            while(low<high){
                int guess = (low+high)/2;
                if(mat[i][guess] ==1){
                    low = guess+1;

                }
                else{
                    high=guess;
                }
            }
            rows[i][0]= low;
            rows[i][1]=i;
        }
        Arrays.sort(rows, (a,b) ->{
            if(a[0] != b[0]){
                return Integer.compare(a[0],b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        int[] result =new int[k];
        for(int i=0; i<k; i++){
            result[i]= rows[i][1];
        }
        return result;
    }
}