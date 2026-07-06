class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n + 1];
        int[] ans = new int[2];

        for (int num : nums) {
            freq[num]++;
        }

        for (int i = 1; i <= n; i++) {
            if (freq[i] == 2) {
                ans[0] = i; // Duplicate number
            }
            if (freq[i] == 0) {
                ans[1] = i; // Missing number
            }
        }

        return ans;
    }
}