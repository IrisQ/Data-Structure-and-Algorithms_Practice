/**
 * The common point of this kind of Questions:
 * Break/segment a String into several parts, and make sure each part satisfy specific requirements.
 * eg: valid parentheses/valid Ip address/palindrome.
 * [22], [17] are similar to "combination" problem, but using string/StringBuilder instead of List<Integer>.
 
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * eg: n = 3, get ["((()))", "(()())",  "(())()", "()(())", "()()()"]
  
   17. Letter Combinations of a Phone Number
 * Given a digit string, return all possible letter combinations that the number could represent.
 * Input:Digit string "23", Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 
 * 93. Restore IP Addresses (not very similar...)
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * Given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 
 * 131. Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s. eg: s = "aab"
 * [ ["aa","b"], ["a","a","b"]]
 */
public class Enumeration {
    /**
     * 22. Generate Parentheses
     * [Solution]: 1. condition for dfs: left == 0 && right == 0,
     *             2. given a string "(", could add "(" or ")", using StringBuilder 
     *                get length before append, and set back to len after recursion, then try another option.
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, new StringBuilder("("), n - 1, n - 1);
        return res;
    }
    
    void helper(List<String> res, StringBuilder sb, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(sb.toString() + ")");
            return;
        }
        int len = sb.length();  // save original length, then try add "(" first
        if (left != 0) {
            helper(res, sb.append("("), left - 1, right);
        }
        sb.setLength(len);      // set back to original length, then try add ")".
        // the condition here is IMPORTANT! Can't be "right != 0"
        // That may leads to "())(()" condition, to avoid this, need to make sure left <= right
        if (left <= right) {
            helper(res, sb.append(")"), left, right - 1);
        }
    }
    
    /**
     * 17. Letter Combinations of a Phone Number
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        dfs(res, digits, new StringBuilder(), 0);
        return res;
    }
    private void dfs(List<String> res, String digits, StringBuilder sb, int curIdx) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        String cur = getLetters(digits.charAt(curIdx));
        int len = sb.length();                          // save original length
        for (char c:cur.toCharArray()) {
            dfs(res, digits, sb.append(c), curIdx + 1);
            sb.setLength(len);                          // set back to original length
        }
    }
    private String getLetters(char num) {
        switch(num) {
            case '2': return "abc";
            case '3': return "def";
            case '4': return "ghi";
            case '5': return "jkl";
            case '6': return "mno";
            case '7': return "pqrs";
            case '8': return "tuv";
            case '9': return "wxyz";
        }
        return null;
    }
}
