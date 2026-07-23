class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        // Base cases for n <= 2
        if (n <= 2) {
            return n;
        }

        // For n >= 3, the answer is 2^(bit_length(n))
        // 32 - Integer.numberOfLeadingZeros(n) gives the bit length of n
        int bitLength = 32 - Integer.numberOfLeadingZeros(n);

        return 1 << bitLength;
    }
}