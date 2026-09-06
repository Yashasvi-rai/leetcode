class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of distinct subsequences of s matching t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty target string t can always be matched in 1 way
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            char sChar = s.charAt(i - 1);
            // Iterate backwards to avoid using updated values from the current row
            for (int j = n; j >= 1; j--) {
                char tChar = t.charAt(j - 1);
                if (sChar == tChar) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}