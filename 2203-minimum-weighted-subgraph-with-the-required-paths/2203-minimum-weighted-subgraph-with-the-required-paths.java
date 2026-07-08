class Solution {

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        List<int[]>[] graph = new ArrayList[n];
        List<int[]>[] reverse = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            reverse[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        long[] dist1 = dijkstra(graph, src1);
        long[] dist2 = dijkstra(graph, src2);
        long[] dist3 = dijkstra(reverse, dest);

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (dist1[i] == Long.MAX_VALUE ||
                dist2[i] == Long.MAX_VALUE ||
                dist3[i] == Long.MAX_VALUE)
                continue;

            ans = Math.min(ans,
                    dist1[i] + dist2[i] + dist3[i]);
        }

        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    private long[] dijkstra(List<int[]>[] graph, int src) {

        int n = graph.length;

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[src] = 0;
        pq.offer(new long[]{src, 0});

        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            int node = (int) curr[0];
            long cost = curr[1];

            if (cost > dist[node])
                continue;

            for (int[] next : graph[node]) {

                int neighbor = next[0];
                int weight = next[1];

                if (dist[neighbor] > cost + weight) {
                    dist[neighbor] = cost + weight;
                    pq.offer(new long[]{neighbor, dist[neighbor]});
                }
            }
        }

        return dist;
    }
}