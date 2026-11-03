class Solution {
    public int countMagicalPairs(String s) {
        int n = s.length();
        int ans = 0;

        for (int j = 0; j < n - 1; j++) {
            int[] count = new int[27];
            HashSet<Character> left = new HashSet<>();

            for (int i = j; i >= 0; i--) {
                left.add(s.charAt(i));
                count[left.size()]++;
            }

            HashSet<Character> right = new HashSet<>();

            for (int k = j + 1; k < n; k++) {
                right.add(s.charAt(k));
                ans += count[right.size()];
            }
        }

        return ans;
    }
}