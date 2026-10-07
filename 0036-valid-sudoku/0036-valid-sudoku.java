import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];
                if (currentVal == '.') {
                    continue;
                }

                // Construct unique string representations for row, column, and sub-box
                String rowKey = currentVal + " in row " + i;
                String colKey = currentVal + " in col " + j;
                String boxKey = currentVal + " in box " + (i / 3) + "-" + (j / 3);

                // If any of these strings already exist in the set, a duplicate is found
                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false;
                }
            }
        }

        return true;
    }
}