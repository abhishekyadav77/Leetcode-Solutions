class Solution {
    public int findMaxLength(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int diff = 0;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                diff--;
            } else {
                diff++;
            }
            if (map.containsKey(diff)) {
                int len = i - map.get(diff);

                res = Math.max(res, len);

            } else {
                map.put(diff, i);
            }
        }
        return res;
    }
}