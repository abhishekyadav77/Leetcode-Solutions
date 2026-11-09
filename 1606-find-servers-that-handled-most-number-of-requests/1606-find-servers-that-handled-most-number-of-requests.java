class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;

        // Available servers in sorted order
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Busy servers: {endTime, serverId}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        int[] count = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < n; i++) {
            int time = arrival[i];

            // Release servers that have finished processing
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            // If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Find the first available server >= i % k
            int start = i % k;
            Integer server = available.ceiling(start);

            // Wrap around if no such server exists
            if (server == null) {
                server = available.first();
            }

            // Assign the request
            available.remove(server);

            count[server]++;
            maxRequests = Math.max(maxRequests, count[server]);

            busy.offer(new int[]{time + load[i], server});
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}