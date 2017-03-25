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

}
