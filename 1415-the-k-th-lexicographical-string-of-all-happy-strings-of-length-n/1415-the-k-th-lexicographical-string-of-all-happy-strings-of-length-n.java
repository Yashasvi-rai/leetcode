class Solution {

    private int count = 0;
    private String ans = "";

    public String getHappyString(int n, int k) {
        backtrack(new StringBuilder(), n, k);
        return ans;
    }

    private void backtrack(StringBuilder sb, int n, int k) {

        if (sb.length() == n) {
            count++;

            if (count == k) {
                ans = sb.toString();
            }
            return;
        }

        char[] chars = {'a', 'b', 'c'};

        for (char ch : chars) {

            if (sb.length() > 0 &&
                sb.charAt(sb.length() - 1) == ch) {
                continue;
            }

            sb.append(ch);
            backtrack(sb, n, k);

            if (!ans.isEmpty()) {
                return;
            }

            sb.deleteCharAt(sb.length() - 1);
        }
    }
}