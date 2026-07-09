class Solution {

    private int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftArrow = 0;
        int rightArrow = 0;

        if (node.left != null && node.left.val == node.val) {
            leftArrow = left + 1;
        }

        if (node.right != null && node.right.val == node.val) {
            rightArrow = right + 1;
        }

        ans = Math.max(ans, leftArrow + rightArrow);

        return Math.max(leftArrow, rightArrow);
    }
}