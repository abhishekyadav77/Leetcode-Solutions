class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack =new Stack<>();

        for (int i = 0; i < s.length(); i++) {
             char c =s.charAt(i);
            if (!stack.isEmpty() && stack.peek()[0] == c) {
             stack.peek()[1]++;

                if(stack.peek()[1] == k) {
                    stack.pop();
                }
            }
             else {
                stack.push(new int[]{c, 1});
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int[] pair :stack){
            char c =(char) pair[0];
            int count = pair[1];

      for (int i = 0; i < count; i++) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}