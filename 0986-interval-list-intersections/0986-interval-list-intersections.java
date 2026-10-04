class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i< firstList.length && j< secondList.length) {
            int start1 = firstList[i][0];
            int end1= firstList[i][1];
            int start2= secondList[j][0];
            int end2 =secondList[j][1];

            int start= Math.max(start1, start2);
            int end =Math.min(end1, end2);

            if(start<= end) {
                ans.add(new int[]{start, end});
            }
            if(end1<= end2){
                i++;
            }
            else{
                j++;
            }

         }
         return ans.toArray(new int[ans.size()][]);
    }
}