class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1;
        int matchIdx = 0;
        
        while (sIdx < sLen) {
            // Case 1: Direct match or '?'
            if (pIdx < pLen && (p.charAt(pIdx) == s.charAt(sIdx) || p.charAt(pIdx) == '?')) {
                sIdx++;
                pIdx++;
            }
            // Case 2: '*' encountered, record its position and the current string index
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                matchIdx = sIdx;
                pIdx++;
            }
            // Case 3: Mismatch, but we previously saw a '*'
            // Backtrack: increment the string pointer relative to the last '*'
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                matchIdx++;
                sIdx = matchIdx;
            }
            // Case 4: Mismatch and no '*' to fall back on
            else {
                return false;
            }
        }
        
        // Check remaining characters in the pattern are all '*'
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        
        return pIdx == pLen;
    }
}