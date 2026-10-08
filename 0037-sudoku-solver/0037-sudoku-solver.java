class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            
                            if (solve(board)) {
                                return true;
                            }
                            
                            board[i][j] = '.'; // Backtrack
                        }
                    }
                    return false; // Trigger backtracking if no digits 1-9 fit
                }
            }
        }
        return true; // Puzzle solved
    }
    
    private boolean isValid(char[][] board, int row, int col, char c) {
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        
        for (int i = 0; i < 9; i++) {
            // Check row
            if (board[row][i] == c) return false;
            // Check column
            if (board[i][col] == c) return false;
            // Check 3x3 sub-box
            if (board[startRow + i / 3][startCol + i % 3] == c) return false;
        }
        return true;
    }
}