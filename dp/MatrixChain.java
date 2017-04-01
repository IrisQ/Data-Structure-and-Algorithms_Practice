/**
 * 132. Palindrome Partitioning II
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 
 -LintCode Stone Game
 * 
 
 * 312. Burst Balloons
 
 * 87. Scramble String
 * 
 */
public class MatrixChain {
    /**
     * dp[i]: 0-i之间最小的cut数, dp[i] = min(i, 1 + dp[i - 1])
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len];
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0;i < len;i++) {
            dp[i] = i;              // default dp[i] = i
            for (int j = 0;j <= i;j++) {
                // if [j, i] is palindrome, update dp[i]
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || isPalindrome[j + 1][i - 1])) {  
                    isPalindrome[j][i] = true;
                    if (j == 0) {
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[len - 1];
    }
    
}
