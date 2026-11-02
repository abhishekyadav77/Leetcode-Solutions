class Solution {
    public String reorganizeString(String s) {
        int n =s.length();
        int[] freq= new int[26];
        int maxFreq=0;
        for(char ch:s.toCharArray()){
            freq[ch-'a']++;
            maxFreq=Math.max(maxFreq, freq[ch-'a']);
        }
        if(maxFreq >(n+1)/2){
            return "";
        }
        PriorityQueue<int[]> pq= new PriorityQueue<>(
            (a,b)->b[1] -a[1]
        );
        for(int i=0; i<26; i++){
            if(freq[i]>0){
                pq.offer(new int[]{i,freq[i]});
            }
        }
        StringBuilder result = new StringBuilder();
        int[] prev =null;
        while(!pq.isEmpty()){
            int[] curr = pq.poll(); 
            result.append((char) (curr[0] + 'a')); 
            curr[1]--; 
            if (prev != null && prev[1] > 0) {
                 pq.offer(prev);
                  }
                   prev=curr; 
                   }
                    if (prev !=null && prev[1] > 0) { 
                return ""; 
                }
                         
        return result.toString();
    }
}