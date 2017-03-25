/**
 * LintCode-Longest Increasing Continuous Subsequence
 * Give an integer [array]，find the longest increasing continuous subsequence in this array.
 * eg: For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
 *     For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
 
 * 329. Longest Increasing Path in a Matrix
 * Given an integer [matrix], find the length of the longest increasing path.
 */
public class Memorize {
    /**
     * LintCode-Longest Increasing Continuous Subsequence--EASY
     * Time: O(n), Space:O(1)
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) return 0;
        int maxLen = 1;
        int curLeftLen = 1, curRightLen = 1;
        for (int i = 1;i < A.length;i++) {
            if (A[i] < A[i - 1]) {
                curLeftLen++;
                maxLen = Math.max(maxLen, curLeftLen);
            } else {
                curLeftLen = 1;
            }
            
            if (A[i] > A[i - 1]) {
                curRightLen++;
                maxLen = Math.max(maxLen, curRightLen);
            } else {
                curRightLen = 1;
            }
        }
        return maxLen;
    }
    
    /**
     * 329. Longest Increasing Path in a Matrix
     * Array->Matrix, Use dfs ? Seach for every matrix[i][j] -> TIME: O(mn*k)
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int maxLen = 1;
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j, 1));
            }
        }
        return maxLen;
    }
    // Using dfs without memorization: WORKS BUT SLOW, checked duplicate paths.
    private int dfs(int[][] matrix, int x, int y, int curLen) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) return curLen;
        int m = matrix.length, n = matrix[0].length;
        int up = (x > 0 && matrix[x - 1][y] > matrix[x][y]) ? dfs(matrix, x-1, y, curLen + 1) : curLen;
        int down = (x < m - 1 && matrix[x + 1][y] > matrix[x][y]) ? dfs(matrix, x+1, y, curLen + 1) : curLen;
        int left = (y > 0 && matrix[x][y - 1] > matrix[x][y]) ? dfs(matrix, x, y - 1, curLen + 1) : curLen;
        int right = (y < n - 1 && matrix[x][y + 1] > matrix[x][y]) ? dfs(matrix, x, y + 1, curLen + 1) : curLen;
        return Math.max(Math.max(up, down), Math.max(left, right));
    }
    // Now use dp and memorization. dp[i][j] saves the curLen checked for each matrix[i][j].
    // And if track to same (i, j) later, can simply use it.
    private int dfs(int[][] matrix, int x, int y, int[][] dp) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) return 0;
        if (dp[x][y] != 0) return dp[x][y];
        
        int m = matrix.length, n = matrix[0].length;
        int up = (x > 0 && matrix[x - 1][y] > matrix[x][y]) ? dfs(matrix, x-1, y, dp) : 0;
        int down = (x < m - 1 && matrix[x + 1][y] > matrix[x][y]) ? dfs(matrix, x+1, y, dp) : 0;
        int left = (y > 0 && matrix[x][y - 1] > matrix[x][y]) ? dfs(matrix, x, y - 1, dp) : 0;
        int right = (y < n - 1 && matrix[x][y + 1] > matrix[x][y]) ? dfs(matrix, x, y + 1, dp) : 0;
        int curLen = Math.max(Math.max(up, down), Math.max(left, right)) + 1;   // add the current one
        dp[x][y] = curLen;
        return curLen;
    }
}
