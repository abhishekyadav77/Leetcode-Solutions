class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int gift: gifts){
            pq.offer(gift);
        }

        for(int i =0; i<k; i++){
            int max = pq.poll();
            int result = (int) Math.sqrt(max);
            pq.offer(result);


        }
        long sum =0;
        while(!pq.isEmpty()){
            sum += pq.poll();
        }
        return sum;
    }
}