class Solution {
    static final int DRAW = 0;
    static final int MOUSE = 1;
    static final int CAT = 2;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][0] = graph[m].length;
                degree[m][c][1] = graph[c].length;

                for (int x : graph[c])
                    if (x == 0)
                        degree[m][c][1]--;
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int c = 1; c < n; c++) {
            for (int t = 0; t < 2; t++) {
                color[0][c][t] = MOUSE;
                queue.offer(new int[]{0, c, t, MOUSE});

                color[c][c][t] = CAT;
                queue.offer(new int[]{c, c, t, CAT});
            }
        }

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int m = cur[0];
            int c = cur[1];
            int turn = cur[2];
            int result = cur[3];

            for (int[] parent : parents(graph, m, c, turn)) {

                int pm = parent[0];
                int pc = parent[1];
                int pt = parent[2];

                if (color[pm][pc][pt] != DRAW)
                    continue;

                if ((pt == 0 && result == MOUSE) ||
                    (pt == 1 && result == CAT)) {

                    color[pm][pc][pt] = result;
                    queue.offer(new int[]{pm, pc, pt, result});

                } else {

                    degree[pm][pc][pt]--;

                    if (degree[pm][pc][pt] == 0) {

                        int lose =
                                (pt == 0) ? CAT : MOUSE;

                        color[pm][pc][pt] = lose;
                        queue.offer(new int[]{pm, pc, pt, lose});
                    }
                }
            }
        }

        return color[1][2][0];
    }

    private List<int[]> parents(int[][] graph, int m, int c, int turn) {

        List<int[]> res = new ArrayList<>();

        if (turn == 0) {

            for (int pc : graph[c]) {

                if (pc == 0)
                    continue;

                res.add(new int[]{m, pc, 1});
            }

        } else {

            for (int pm : graph[m]) {
                res.add(new int[]{pm, c, 0});
            }
        }

        return res;
    }
}