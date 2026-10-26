import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> freq=new HashMap<>();
         // Step1 Count frequency
        for(String word:words) {
            freq.put(word,freq.getOrDefault(word, 0) + 1);
        }
           // Step2 Create a min-heap
        PriorityQueue<String> pq=new PriorityQueue<>((a, b) -> {
            if(!freq.get(a).equals(freq.get(b))) {
                return freq.get(a)-freq.get(b);
            }
            return b.compareTo(a);
        });
    // Step3 Maintain only k words
        for(String word:freq.keySet()) {
            pq.offer(word);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Step4: Extract words
        List<String> result=new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        // Step5 Reverse the result 
        Collections.reverse(result);
        return result;
    }
}