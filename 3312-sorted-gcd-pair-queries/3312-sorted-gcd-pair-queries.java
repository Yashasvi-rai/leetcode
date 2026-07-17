import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] divisible = new long[max + 1];

        // Count numbers divisible by each d
        for (int d = 1; d <= max; d++) {
            for (int multiple = d; multiple <= max; multiple += d) {
                divisible[d] += freq[multiple];
            }
        }

        long[] exact = new long[max + 1];

        // Inclusion-Exclusion
        for (int d = max; d >= 1; d--) {
            long cnt = divisible[d];
            exact[d] = cnt * (cnt - 1) / 2;

            for (int multiple = d * 2; multiple <= max; multiple += d) {
                exact[d] -= exact[multiple];
            }
        }

        // Prefix counts
        long[] prefix = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            prefix[d] = prefix[d - 1] + exact[d];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // 1-based position

            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) / 2;
                if (prefix[mid] >= target)
                    r = mid;
                else
                    l = mid + 1;
            }
            ans[i] = l;
        }

        return ans;
    }
}