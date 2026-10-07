class Solution {
    public int maxNumberOfBalloons(String text) {

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> have = new HashMap<>();

        String target = "balloon";
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            have.put(c, have.getOrDefault(c, 0) + 1);
        }

        int answer = Integer.MAX_VALUE;
    
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            char c = entry.getKey();
            int required = entry.getValue();
            int available = have.getOrDefault(c, 0);
            int possible = available / required;
            answer = Math.min(answer, possible);
        }
        return answer;
    }
}