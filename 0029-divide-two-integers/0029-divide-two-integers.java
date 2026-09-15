class Solution {
    public int divide(int dividend, int divisor) {
        // Edge case: overflow when dividing MIN_VALUE by -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine the sign of the result
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Convert both to negative to prevent overflow issues
        long dvd = dividend < 0 ? (long) dividend : -dividend;
        long dvs = divisor < 0 ? (long) divisor : -divisor;
        
        int quotient = 0;
        
        while (dvd <= dvs) {
            long tempDvs = dvs;
            long multiple = 1;
            
            // Shift left (multiply by 2) while possible without overflowing long
            while (dvd <= (tempDvs << 1) && (tempDvs << 1) < 0) {
                tempDvs <<= 1;
                multiple <<= 1;
            }
            
            dvd -= tempDvs;
            quotient += multiple;
        }
        
        return negative ? -quotient : quotient;
    }
}