class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        // Sort logs by request time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store query times along with their original indices
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(sortedQueries,
                (a, b) -> Integer.compare(a[0], b[0]));

        // Frequency of requests for each server in the window
        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] query : sortedQueries) {

            int time = query[0];
            int index = query[1];

            int startTime = time - x;

            // Add logs that fall before or at the query time
            while (right < logs.length && logs[right][1] <= time) {

                int serverId = logs[right][0];

                map.put(serverId, map.getOrDefault(serverId, 0) + 1);

                if (map.get(serverId) == 1) {
                    activeServers++;
                }

                right++;
            }

            // Remove logs that fall before the window
            while (left < right && logs[left][1] < startTime) {

                int serverId = logs[left][0];
                int count = map.get(serverId);

                if (count == 1) {
                    map.remove(serverId);
                    activeServers--;
                } else {
                    map.put(serverId, count - 1);
                }

                left++;
            }

            // Servers without requests in the window
            ans[index] = n - activeServers;
        }

        return ans;
    }
}