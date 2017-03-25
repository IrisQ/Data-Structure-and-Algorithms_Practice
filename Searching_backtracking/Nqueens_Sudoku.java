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
     * 51. N-Queens
     * [SOLUTION]: 1. queen is valid only if: no queen in same row/column/diagonal
     *             2. use queens[n], queens[2] = 1: a queen (2, 1) -> for queens in each i, queens[i] should be unique
     *                use dfs and loop through each to find match.
     * Time: O(n!)
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        if (n <= 0) return res;
        dfs(res, queens, n, 0);
        return res;
    }
    // cur: the current row working on.
    private void dfs(List<List<String>> res, int[] queens, int n, int cur) {
        if (cur == n) {       // have already processed last row, now add into res.
            List<String> list = new ArrayList<>();
            for (int queen:queens) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0;j < queen;j++) {
                    sb.append('.');
                }
                sb.append('Q');
                for (int j = queen + 1;j < n;j++) {
                    sb.append('.');
                }
                list.add(sb.toString());
            }
            res.add(list);
        } else {
            for (int i = 0;i < n;i++) {     // try each number to find a match
                queens[cur] = i;
                if (isValid(queens, cur)) {
                    dfs(res, queens, n, cur + 1);
                }
            }
        }
    }
    // if 2 queens in same 45' angle: x1 - x2 = y1 - y2.
    // if 2 queens in same 135' angle: x1 + y1 = x2 + y2.
    boolean isValid(int[] queens, int pos) {
        for (int i = 0;i < pos;i++) {
            // row is initially different, same column || 45' aggle || 135' angle
            if (queens[i] == queens[pos] || queens[i] - queens[pos] == i - pos || i + queens[i] == pos + queens[pos]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 52. N-Queens II -- Only need # of distinct solutions.
     * [SOLUTION]: just need to update count.
     */
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        int[] queens = new int[n];
        return dfs(queens, n, 0, 0);
    }
    
    private int dfs(int[] queens, int n, int cur, int count) {
        if (cur == n) {
            return count + 1;
        } else {
            for (int i = 0;i < n;i++) {
                queens[cur] = i;
                if (isValid(queens, cur)) {
                    count = dfs(queens, n, cur + 1, count);
                }
            }
        }
        return count;
    }
    
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
