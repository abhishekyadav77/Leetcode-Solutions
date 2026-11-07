import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Available servers: weight, index
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // Busy servers: finish time, weight, index
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });

        // Initially, every server is available.
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        for (int j = 0; j < m; j++) {

            // Move all servers that have finished
            // back into the available heap.
            while (!busy.isEmpty() && busy.peek()[0] <= j) {
                int[] server = busy.poll();

                available.offer(new int[]{
                    server[1],
                    server[2]
                });
            }

            // If every server is busy, wait for the
            // server that finishes earliest.
            if (available.isEmpty()) {
                int[] server = busy.poll();

                ans[j] = server[2];

                busy.offer(new int[]{
                    server[0] + tasks[j],
                    server[1],
                    server[2]
                });
            } else {
                // Select the best available server.
                int[] server = available.poll();

                int weight = server[0];
                int index = server[1];

                ans[j] = index;

                // Record when this server becomes free.
                busy.offer(new int[]{
                    j + tasks[j],
                    weight,
                    index
                });
            }
        }

        return ans;
    }
}