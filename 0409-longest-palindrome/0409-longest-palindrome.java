class Solution {
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int answer = 0;
        boolean hasOdd = false;
        for (int freq : map.values()) {
            if (freq % 2 == 0) {
                answer += freq;
            } 
            else {
                answer += freq - 1;
                hasOdd = true;
            }
        }
        if (hasOdd) {
            answer++;
        }
        return answer;
    }
}