class Solution {
    public String maxSumOfSquares(int num, int sum) {
         if (sum > 9 * num) {
            return "";
        }
        if (sum == 0) {
            return num == 0 ? "" : "";
        }
        StringBuilder result = new StringBuilder();
        while (sum > 0) {
            if (sum >= 9) {
                result.append(9);
                sum -= 9;
            } else {
                result.append(sum);
                sum = 0;
            }
        }
        while (result.length() < num) {
            result.append(0);
        }

        return result.toString();
    }
}