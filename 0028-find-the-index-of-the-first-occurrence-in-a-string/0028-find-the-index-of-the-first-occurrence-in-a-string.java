class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        
        // Loop through haystack up to the point where needle can still fit
        for (int i = 0; i <= n - m; i++) {
            // Check if the substring matches needle
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        
        return -1; // Needle is not part of haystack
    }
}