/**
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * eg: 4 queens: 
 * [ [".Q..",  // Solution 1
 *    "...Q",
 *    "Q...",
 *    "..Q."],

 *   ["..Q.",  // Solution 2
 *    "Q...",
 *    "...Q",
 *    ".Q.."]]]
 *
 * 52. N-Queens II
 * Return the total number of distinct solutions, instead of board configurations.
 
 * 36. Valid Sudoku
 * Determine if a Sudoku is valid.
 
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.', assume only one unique solution.
 */

public class Nqueens_Sudoku {
    /**
     * [Solution]: Use 3 matrix to record what numbers have appear before in each ror/column/block.
     * Time: O(91)
     */
    public boolean isValidSudoku(char[][] board) {        
        boolean[][] row = new boolean[9][9];   // if row[1][2] = true, means 2-th row contains 3
        boolean[][] col = new boolean[9][9];
        boolean[][] blk = new boolean[9][9];
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                if (board[i][j] == '.') continue;
                int cur = board[i][j] - '1';
                if (row[i][cur] || col[j][cur] || blk[i/3 + (j/3)*3][cur]) return false;
                row[i][cur] = true;
                col[j][cur] = true;
                blk[i/3 + (j/3)*3][cur] = true;
            }
        }
        return true;
    }
    
    /**
     * 37. Sudoku Solver
     * [Solution]:
     */
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];   
        boolean[][] col = new boolean[9][9];
        boolean[][] blk = new boolean[9][9];
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                if (board[i][j] == '.') continue;
                int cur = board[i][j] - '1';
                row[i][cur] = col[j][cur] = blk[i/3 + (j/3)*3][cur] = true;
            }
        }
        dfs(board, row, col, blk, 0);
    }
    // need to return true/false to check if the current routine works, and turn back timely when not.
    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] blk, int idx) {        
        if (idx == 81) return true;
        int x = idx / 9;
        int y = idx % 9;
        int blockIdx = x/3 + (y/3)*3;
        if (board[x][y] != '.') return dfs(board, row, col, blk, idx + 1);
        for (char c = '1';c <= '9';c++) {   // one of them should be right answer, let's try every one.           
            int i = c - '1';
            if (row[x][i] || col[y][i] || blk[blockIdx][i]) continue;   // jump over the number already exists in col/column/block
            board[x][y] = c;
            row[x][i] = col[y][i] = blk[blockIdx][i] = true;
            
            if (dfs(board, row, col, blk, idx + 1)) return true;        
            
            board[x][y] = '.';
            row[x][i] = col[y][i] = blk[blockIdx][i] = false;            
        }
        return false;        
    }
}
