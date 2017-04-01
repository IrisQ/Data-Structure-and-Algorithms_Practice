/**
 * DP: Optimal Substructure + Overlap Subproblem
 *
   1. Overlap Subproblem, thinking in top-down, to get maxProfit(n), 
      it is related with 2 subproblems, maxProfit(n - 1), maxProfit(n - 2)
   2. Optimal Substructure, if maxProfit(n - 1), maxProfit(n - 2) are optimal solutions for subproblems, 
      then we can only use the solution of those 2 + judgement of current element -> optimal solution of maxProfit(n)
 
 * LintCode - Coins in a Line
 * There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. 
 * The player who take the last coin wins. Given n, can you decide the first player will win/lose?
 
 * LintCode - Coins in a Line II
 * There are n coins with different value in a line. Two players take turns to take 1 or 2 coins from left until no more coins left. 
 * The player who take the coins with the most value wins. The first player will win/lose?
 
 * 375. Guess Number Higher or Lower II
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time guess x and x is wrong, I'll tell you whether the number I picked is higher or lower, and you need to pay x.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 */

/**
 * MinMax Algorithm.
 */
public class GameTheory {
    /**
     * [SOLUTION]: dp[n] depends on dp[n - 1] and dp[n - 2]. 
     * dp[n] can't be true only if dp[n - 1] == dp[n - 2] == true.
     * Use dp[] to avoid overlap.
     */
    public boolean firstWillWin(int n) {
        if (n <= 0) return false;
        boolean[] dp = new boolean[n + 1];
        dp[1] = dp[2] = true;
        for (int i = 3;i <= n;i++) {
            dp[n] = !dp[n - 1] || !dp[n - 2];
        }
        return dp[n];
    }
    
    /**
     * dp[i]: the max number that can get in [i, end]. eg: dp[n] = 0, dp[n - 1] = values[n - 1]...
     * when at i, 2 solutions:
     *      1. take values[i], 
     *          -> other person: 
     *                  -> values[i+1]             -> then we can only choose from i+2 to end, max can get is dp[i+2]
     *                  -> values[i+1]+values[i+2] -> can only choose from i+3 to end, max can get is dp[i+3]
     *      2. take values[i]+values[i+1],
     *          -> other person:
     *                  -> values[i+2]             -> then can only choose from i+3 to end, max can get is dp[i+3]
     *                  -> values[i+3]+values[i+4] -> then can only choose from i+5 to end, max can get is dp[i+5]
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) return false;
        int n = values.length;
        if(n <= 2) return true;        
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = values[n - 1];
        dp[n - 2] = values[n - 1] + values[n - 2];
        dp[n - 3] = values[n - 3] + values[n - 2];
        for (int i = n - 4;i >= 0;i--) {        // has to start from n - 4, since (i + 4) must <= n
            // min1/min2 是自己有可能拿到的两种可能性，第一种是自己拿了values[i], 在对方的两种选择中，会给我们留下
            // 能拿到的更小的一种选择
            int min1 = values[i] + Math.min(dp[i + 2], dp[i + 3]);  
            int min2 = (values[i] + values[i + 1]) + Math.min(dp[i + 3], dp[i + 4]);
            dp[i] = Math.max(min1, min2);
        }
        int sum = 0;
        for (int v:values) sum += v;
        return dp[0] * 2 > sum;
    }

    /**375. Guess Number Higher or Lower II
     * dp[i][j]: when searching in [i, j], the min cost needed to guarantee 
     * finding the number.
     */
    public int getMoneyAmount(int n) {
        return getMinCost(0, n-1, new int[n][n]);
    }
    private int getMinCost(int start, int end, int[][] dp) {
        if (start >= end) return 0;
        if (dp[start][end] != 0) return dp[start][end];
        
        // since we can choose from [start, end], then of course we will choose
        // the number that indicates less cost/min cost
        int minCost = Integer.MAX_VALUE;
        for (int i = start;i <= end;i++) {  // iL the number we can guess
            // use max, since we need to find a guarantee cost/a max cost
            int cost = (i + 1) + Math.max(getMinCost(start, i-1, dp), 
                                getMinCost(i+1, end, dp));
            minCost = Math.min(cost, minCost);
        }
        dp[start][end] = minCost;
        return dp[start][end];
    }
}
