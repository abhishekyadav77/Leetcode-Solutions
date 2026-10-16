class Solution {
    int mod = 1000000007;
    long[] sum;
    long[] square;
    long[] lazy;
    void update(int node, int start, int end, int l, int r) {
        if (l > end || r < start) {
            return;
        }
        if (l <= start && end <= r) {
            square[node] = (square[node] + 2 * sum[node] + (end - start + 1)) % mod;
            sum[node] = (sum[node] + (end - start + 1)) % mod;
            lazy[node]++;
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r);
        update(node * 2 + 1, mid + 1, end, l, r);
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % mod;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % mod;
    }
    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }
        int mid = (start + end) / 2;
        long val = lazy[node];
        apply(node * 2, start, mid, val);
        apply(node * 2 + 1, mid + 1, end, val);
        lazy[node] = 0;
    }
    void apply(int node, int start, int end, long val) {
        long len = end - start + 1;
        square[node] = (square[node] + 2 * val * sum[node] + val * val * len) % mod;
        sum[node] = (sum[node] + val * len) % mod;
        lazy[node] = (lazy[node] + val) % mod;
    }
    long query(int node, int start, int end, int l, int r) {
        if (l > end || r < start) {
            return 0;
        }
        if (l <= start && end <= r) {
            return square[node];
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        return (query(node * 2, start, mid, l, r)
                + query(node * 2 + 1, mid + 1, end, l, r)) % mod;
    }
    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];
        HashMap<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);
            update(1, 0, n - 1, prev + 1, i);
            ans = (ans + query(1, 0, n - 1, 0, i)) % mod;
            last.put(nums[i], i);
        }
        return (int) ans;
    }
}