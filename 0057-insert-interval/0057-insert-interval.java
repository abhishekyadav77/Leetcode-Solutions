class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int[][] arr = new int[intervals.length + 1][2];

        for (int i = 0; i < intervals.length; i++) {
            arr[i] = intervals[i];
        }

        arr[intervals.length] = newInterval;

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        for (int[] interval : arr) {
            if (result.isEmpty() ||
                result.get(result.size() - 1)[1] < interval[0]) {

                result.add(interval);
            }
            else {
                result.get(result.size() - 1)[1] =
                    Math.max(
                        result.get(result.size() - 1)[1],
                        interval[1]
                    );
            }
        }

     return result.toArray(new int[result.size()][]);
    }
}