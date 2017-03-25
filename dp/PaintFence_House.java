/**
 * 276. Paint Fence
 * A fence with n posts, each post can be painted with one of the k colors.
 * HOW many ways are there to make sure <= 2 adjacent fence posts have the same color?
 
 * 256. Paint House
 * A row of n houses, each house can be painted red, blue or green, cost of painting each house with a certain color is different.
 * Find minimum cost to paint all houses so that no two adjacent houses have the same color.
 
 * 265. Paint House II
 * Each house can be painted with one of the k colors.
 */

public class PaintFence_House {
    /**
     * 276. Paint Fence
     * [SOlution]: no more than 2 adjacent fence posts have same color, so dp[i] is connected with dp[i - 1], dp[i - 2]
     *             so the color of n-th fence, should be different from (n - 1)th, or different from (n - 2)th
     *             dp[n] = (k - 1)*dp[n - 1] + (k - 1)*dp[n - 2]
     */
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) return 0;
        if (n == 1) return k;
        int[] dp = new int[n];  // dp[i]: # of ways to paint i houses
        dp[0] = k;
        dp[1] = k*k;
        for (int i = 2;i < n;i++) {
            dp[i] = (dp[i - 2] + dp[n - 1]) * (k - 1);
        }
        return dp[n - 1];
    }
    
    /**
     * 256. Paint House
     * [solution]: need to keep track, for each row, the min cost of using R/G/B in this row.
     * Time: O(n)
     */
    public int minCost(int[][] costs) {
        int minRed = 0, minBlue = 0, minGreen = 0;
        int curRed = 0, curBlue = 0, curGreen = 0;          
        for (int[] cost:costs) {
            curRed = cost[0] + Math.min(minGreen, minBlue); // the min cost if use red on this house
            curBlue = cost[1] + Math.min(minGreen, minRed); 
            curGreen = cost[2] + Math.min(minBlue, minRed);
            minRed = curRed;
            minBlue = curBlue;
            minGreen = curGreen;
        }
        return Math.min(minRed, Math.min(minGreen, minBlue));
    }
    
    /**
     * 265. Paint House II
     * [SOLUTION-1]: can use as [Paint House-1], and Time would be O(n*k*k).
     * [SOLUTION-2]: ONLY need to record the index for min and second-min cost till previous house.
     *             if current row the smallest cost has same color with min, then go with min2.
     * Time: O(nk)
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int m = costs.length;
        int n = costs[0].length;
        int min1 = -1, min2 = -1;
        for (int i = 0;i < m;i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;
            for (int j = 0;j < n;j++) {
                if (j != last1) {    // current color j is not the same with the color of last min1
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }
                
                // find the indices of 1st and 2nd smallest cost
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;                    
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[m - 1][min1];
    }
    
    
}
