import java.util.*;

class Solution {

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        int n = heights.length;
        int m = queries.length;

        int[] ans = new int[m];

        List<int[]> offline = new ArrayList<>();

        for (int i = 0; i < m; i++) {

            int a = queries[i][0];
            int b = queries[i][1];

            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                offline.add(new int[]{
                        b,
                        Math.max(heights[a], heights[b]),
                        i
                });
            }
        }

        offline.sort((x, y) -> y[0] - x[0]);

        List<Integer> stack = new ArrayList<>();

        int j = n - 1;

        for (int[] q : offline) {

            int b = q[0];
            int need = q[1];
            int idx = q[2];

            while (j > b) {

                while (!stack.isEmpty() &&
                        heights[stack.get(stack.size() - 1)] <= heights[j]) {
                    stack.remove(stack.size() - 1);
                }

                stack.add(j);
                j--;
            }

            int l = 0;
            int r = stack.size() - 1;
            int res = -1;

            while (l <= r) {

                int mid = (l + r) / 2;

                if (heights[stack.get(mid)] > need) {
                    res = stack.get(mid);
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            ans[idx] = res;
        }

        return ans;
    }
}