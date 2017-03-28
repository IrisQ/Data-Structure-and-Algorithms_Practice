/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 
 * 200. Number of Islands
 * 130. Surrounded Regions
 * All can use DFS to solve. 
 */
public class DFS_2D {
    /**
     * 79. Word Search
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        int m = board.length, n = board[0].length;
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, int x, int y, String word, int idx) {
        if (idx == word.length()) return true;
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length ||
           board[x][y] != word.charAt(idx)) return false;        
        int[] vert = {0, 0, -1, 1};
        int[] hori = {-1, 1, 0, 0};
        board[x][y] = '#';                  // ATTENTION! prevent from step back
        for (int i = 0;i < vert.length;i++) {
            if (dfs(board, x + hori[i], y + vert[i], word, idx + 1)) return true;
        }
        board[x][y] = word.charAt(idx);
        return false;
    }
    
    /**
     * 200. Number of Islands 一遍过
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);    // set all '1' connected with this one to '0'
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || 
           grid[x][y] != '1') return;
        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }
    
}
