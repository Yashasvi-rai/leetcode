class Solution {

    int MOD = 1000000007;

    public int kConcatenationMaxSum(int[] arr, int k) {

        long sum = 0;
        for (int x : arr)
            sum += x;

        if (k == 1)
            return (int) (kadane(arr, 1) % MOD);

        long ans = kadane(arr, 2);

        if (sum > 0)
            ans += (long) (k - 2) * sum;

        return (int) (ans % MOD);
    }

    private long kadane(int[] arr, int times) {

        long max = 0;
        long curr = 0;

        int n = arr.length;

        for (int t = 0; t < times; t++) {
            for (int i = 0; i < n; i++) {
                curr = Math.max(0, curr + arr[i]);
                max = Math.max(max, curr);
            }
        }

        return max;
    }
}