/**
 * 62. Unique Paths
 * From top-left to bottom-right, can only move down/right, a m*n grid. How many possible unique paths are there?
 
 * 63. Unique Paths II
 * if some obstacles are added to the grids, how many possible unique pathes are there?
 * obstable-1, empty space-0
 
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
 * which minimizes the sum of all numbers along its path.
 
 * 221. Maximal Square
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 
 * 85. Maximal Rectangle
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 */

public class MatrixPath {
    /**
     * 62. Unique Paths
     * [SOLUTION-1]: 1. fill first row/column with 1, 
     *               2. for others: dp[i][j] = dp[i - 1][j] + dp[i][j - 1
     * Space: O(mn)
     * [SOLUTION-2]: Save space...Actually every row's condition is only related with previous row.
     *               So can make it O(n).
     * Space: O(2n) -> O(n), Time is always O(mn)
     *
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        int[][] paths = int[m][n];
        Arrays.fill(paths[0], 1);
        for (int i = 0;i < m;i++) {
            paths[i][0] = 1;
        }
        
        for (int i = 1;i < m;i++) {
            for (int j = 1;j < n;j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }
    // [SOLUTION-2]: here space: O(2n)
    public int uniquePaths(int m, int n) {
        int[] prevRow = new int[n];
        int[] curRow = new int[n];
        Arrays.fill(prevRow, 1);
        for (int i = 1;i < m;i++) {     // every loop indiates one line
            curRow[0] = 1;
            for (int j = 1;j < n;j++) {
                curRow[j] = prevRow[j - 1] + curRow[j - 1]; // cur = up + left
            }
            prevRow = Arrays.copyOfRange(curRow, 0, n);;
        }
        return curRow[n - 1];
    }
    // [SOLUTION-3]: But actually we can do it with O(n), prevRow can be omitted, and only use curRow to update itself.
    public int uniquePaths(int m, int n) {
        int[] row = new int[n];
        Arrays.fill(row, 1);
        for (int i = 1;i < m;i++) {
            row[0] = 1;
            for (int j = 1;j < n;j++) {
                row[j] = row[j] + row[j - 1];
            }
        }
        return row[n - 1];
    }
    
    /**
     * 63. Unique Paths II
     * The difference is, now there are some obstables. Hence:
     * [SOLUTION]: 1.if obstacleGrid[i][j] = 1, paths[i][j] = 0
     *             else the same, paths[i][j] = paths[i - 1][j] + paths[i][j - 1]; // left + up >> O(mn)
     *             Less Space: paths[i] = paths[i - 1] + paths[i];                 // left + up
     *             2. Can't simply use Arrays.fill for 0-th row and column, need to check obstacles.
     * Time: O(mn), Space:O(n)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] row = new int[n];
        for (int i = 0;i < n;i++) {
            if (obstacleGrid[0][i] == 0) row[i] = 1;
            else break;
        }
        // already has obstacle in 0-th column, the ones down can only be 0.
        boolean hasObstacle = false;    
        for (int i = 1;i < m;i++) {
            if (obstacleGrid[i][0] == 1 || hasObstacle) {
                row[0] = 0;
                hasObstacle = true;
            }// else: remain the same. row[0] may be 0 before, then remain 0. 
            
            for (int j = 1;j < n;j++) {
                if (obstacleGrid[i][j] == 1) row[j] = 0;
                else row[j] = row[j] + row[j - 1];
            }
        }
        return row[n - 1];
    }
    
    
}
