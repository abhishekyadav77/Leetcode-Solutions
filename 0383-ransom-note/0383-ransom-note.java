class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

         HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> have = new HashMap<>();

         for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            have.put(c, have.getOrDefault(c, 0) + 1);
        }
         for (Map.Entry<Character, Integer> entry : need.entrySet()) {

            char c = entry.getKey();
            int freqNeed = entry.getValue();
            int freqHave = have.getOrDefault(c, 0);
            if (freqHave < freqNeed) {
                return false;
            }
        }
        return true;
    }
}