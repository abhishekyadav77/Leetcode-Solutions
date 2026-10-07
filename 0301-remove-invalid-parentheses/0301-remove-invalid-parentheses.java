class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        Queue<String> q = new LinkedList<>();

        q.add(s);
        visited.add(s);

        boolean found = false;

        while (!q.isEmpty()) {

            String current = q.poll();

            if (isValid(current)) {

                ans.add(current);
                found = true;
            }

            if (found) {
                continue;
            }
            for (int i = 0; i < current.length(); i++) {

                if (current.charAt(i) != '(' &&
                    current.charAt(i) != ')') {
                    continue;
                }

                String next =
                    current.substring(0, i) +
                    current.substring(i + 1);

                if (!visited.contains(next)) {

                    visited.add(next);
                    q.add(next);
                }
            }
        }

        return ans;
    }


    private boolean isValid(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            }

            else if (c == ')') {

                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}