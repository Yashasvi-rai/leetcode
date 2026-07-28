class Solution {
    public int divide(int dividend, int divisor) {
        // Edge Case: Overflow check
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert both dividend and divisor to negative to avoid overflow
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        int quotient = 0;

        // Perform bitwise division
        while (dividend <= divisor) {
            int tempDivisor = divisor;
            int count = 1;

            // Double the divisor as long as it doesn't overflow and is <= dividend
            // Note: Since values are negative, tempDivisor << 1 must be >= Integer.MIN_VALUE
            while (tempDivisor >= (Integer.MIN_VALUE >> 1) && dividend <= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                count <<= 1;
            }

            dividend -= tempDivisor;
            quotient += count;
        }

        return negative ? -quotient : quotient;
    }
}