class Solution {
    public int scoreOfParentheses(String s) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(0); // Initialize with a base score for the current level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Entering a new nested level
            } else {
                int innerScore = stack.pop();
                int currentScore = stack.pop();
                // If innerScore is 0, it means we hit "()", which scores 1.
                // Otherwise, it means we hit "(A)", which scores 2 * innerScore.
                int addedScore = innerScore == 0 ? 1 : 2 * innerScore;
                stack.push(currentScore + addedScore);
            }
        }

        return stack.pop();
    }
}