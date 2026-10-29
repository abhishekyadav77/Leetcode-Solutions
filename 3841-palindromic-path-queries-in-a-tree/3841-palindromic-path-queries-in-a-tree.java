class Solution {
    private int n, timer;
    private List<Integer>[] graph;
    private int[] parent, depth, size, heavy;
    private int[] head, pos, values, nodeAtPos;
    private int[] segTree;

    public List<Boolean> palindromePath(int n, int[][] edges, String s,
                                        String[] queries) {
        this.n = n;
        this.timer = 0;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        values = new int[n];
        nodeAtPos = new int[n];

        Arrays.fill(heavy, -1);

        for (int i = 0; i < n; i++) {
            values[i] = 1 << (s.charAt(i) - 'a');
        }

        dfs(0, -1);
        decompose(0, 0);

        segTree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                values[u] = 1 << (c - 'a');
                update(1, 0, n - 1, pos[u], values[u]);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    private int dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;

        int maxSize = 0;

        for (int v : graph[u]) {
            if (v == p) {
                continue;
            }

            depth[v] = depth[u] + 1;

            int childSize = dfs(v, u);
            size[u] += childSize;

            if (childSize > maxSize) {
                maxSize = childSize;
                heavy[u] = v;
            }
        }

        return size[u];
    }

    private void decompose(int u, int chainHead) {
        head[u] = chainHead;
        pos[u] = timer;
        nodeAtPos[timer++] = u;

        if (heavy[u] != -1) {
            decompose(heavy[u], chainHead);
        }

        for (int v : graph[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    private void build(int node, int left, int right) {
        if (left == right) {
            segTree[node] = values[nodeAtPos[left]];
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
    }

    private void update(int node, int left, int right,
                        int index, int value) {
        if (left == right) {
            segTree[node] = value;
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
    }

    private int query(int node, int left, int right,
                      int ql, int qr) {
        if (ql <= left && right <= qr) {
            return segTree[node];
        }

        if (right < ql || left > qr) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        return query(node * 2, left, mid, ql, qr)
                ^ query(node * 2 + 1, mid + 1, right, ql, qr);
    }

    private int queryPath(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= query(1, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        mask ^= query(1, 0, n - 1, pos[u], pos[v]);

        return mask;
    }
}