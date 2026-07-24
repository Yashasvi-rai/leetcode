class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];

        // Step 1: Forward pass to track lengths after each operation
        long currentLen = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                currentLen = Math.max(0, currentLen - 1);
            } else if (c == '#') {
                currentLen = currentLen * 2;
            } else if (c == '%') {
                // Length remains unchanged during reverse
            } else {
                currentLen++;
            }
            len[i] = currentLen;
        }

        // Out of bounds check
        if (k < 0 || k >= currentLen) {
            return '.';
        }

        // Step 2: Backward pass to track position k back to its source character
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long prevLen = (i > 0) ? len[i - 1] : 0;

            if (c == '*') {
                // Pop operation: index k unaffected
                continue;
            } else if (c == '#') {
                // Duplication: if k is in second half, map to first half
                if (k >= prevLen) {
                    k -= prevLen;
                }
            } else if (c == '%') {
                // Reversing: mirror index k across midpoint
                if (len[i] > 0) {
                    k = len[i] - 1 - k;
                }
            } else {
                // Character append: if k is the newly appended character, return it
                if (k == len[i] - 1) {
                    return c;
                }
            }
        }

        return '.';
    }
}