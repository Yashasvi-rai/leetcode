import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        int sLen = s.length();

        if (sLen < totalLen) {
            return result;
        }

        // Frequency map of words to find
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Iterate through possible starting offsets
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> seenMap = new HashMap<>();
            int count = 0;

            while (right + wordLen <= sLen) {
                String subWord = s.substring(right, right + wordLen);
                right += wordLen;

                // Check if the word is part of the words array
                if (wordMap.containsKey(subWord)) {
                    seenMap.put(subWord, seenMap.getOrDefault(subWord, 0) + 1);
                    count++;

                    // If the word count exceeds the expected amount, shrink the window from the left
                    while (seenMap.get(subWord) > wordMap.get(subWord)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seenMap.put(leftWord, seenMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // If we matched all words, record the starting index
                    if (count == numWords) {
                        result.add(left);
                    }
                } else {
                    // Reset the window if a non-matching word is encountered
                    seenMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}