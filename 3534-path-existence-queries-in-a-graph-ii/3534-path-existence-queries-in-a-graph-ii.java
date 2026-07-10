class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] pos = new int[n];
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int[] next = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && values[r + 1] - values[i] <= maxDiff)
                r++;
            next[i] = r;
        }

        int[] prev = new int[n];
        int l = 0;
        for (int i = 0; i < n; i++) {
            while (values[i] - values[l] > maxDiff)
                l++;
            prev[i] = l;
        }

        int LOG = 18;

        int[][] upRight = new int[LOG][n];
        int[][] upLeft = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            upRight[0][i] = next[i];
            upLeft[0][i] = prev[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                upRight[k][i] = upRight[k - 1][upRight[k - 1][i]];
                upLeft[k][i] = upLeft[k - 1][upLeft[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int pu = pos[queries[i][0]];
            int pv = pos[queries[i][1]];

            if (pu == pv) {
                ans[i] = 0;
            } else if (pu < pv) {
                ans[i] = jumpRight(pu, pv, upRight, next);
            } else {
                ans[i] = jumpLeft(pu, pv, upLeft, prev);
            }
        }

        return ans;
    }

    private int jumpRight(int s, int t, int[][] up, int[] next) {

        if (next[s] >= t) return 1;

        int cur = s;
        int res = 0;

        for (int k = up.length - 1; k >= 0; k--) {
            int nxt = up[k][cur];
            if (nxt < t && nxt != cur) {
                cur = nxt;
                res += 1 << k;
            }
        }

        if (next[cur] >= t) return res + 1;
        return -1;
    }

    private int jumpLeft(int s, int t, int[][] up, int[] prev) {

        if (prev[s] <= t) return 1;

        int cur = s;
        int res = 0;

        for (int k = up.length - 1; k >= 0; k--) {
            int nxt = up[k][cur];
            if (nxt > t && nxt != cur) {
                cur = nxt;
                res += 1 << k;
            }
        }

        if (prev[cur] <= t) return res + 1;
        return -1;
    }
}