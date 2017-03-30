/**
 * 293. Flip Game
 * Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 
 * 294. Flip Game II
 * Write a function to determine if the starting player can guarantee a win.
 
 * 464. Can I Win
 * Given an integer maxChoosableInteger and another integer desiredTotal, 
 * The player who first causes the running total >= desiredTotal wins, and players cannot re-use integers.
 * determine if the first player to move can force a win, assuming both players play optimally.
 */
 
public class FlipGame {
    /**
     * [SOLUTION]: just use for-loop.... what's the connection with dp???  AC
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() == 0) return res;
        int len = s.length();
        for (int i = 1;i < len;i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
            }
        }
        return res;
    }
    
    /** STILL HAVE BUG, Haven't fixed yet!!!
     * [Solution]: memorized search, use map to avoid duplicates, 
     *             and the game itself can be seen as first win/second lose,
     *             top-down
     */
    public boolean canWin(String s) {
        Map<String, Boolean> map = new HashMap<>(); // <String, can guarantee win or not>
        return helper(s, map);
    }
    private boolean helper(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        boolean canWin = false;
        // check all options, if any of them could lead to [guaranteed win], if not, return false
        char[] chars = s.toCharArray();
        for (int i = 1;i < s.length();i++) {
            if (chars[i] == '+' && chars[i - 1] == '+') {
                chars[i] = chars[i - 1] = '-';
                boolean cur = canWin(new String(chars));
                if (!cur) {
                    canWin = true;
                    break;
                }
                chars[i] = chars[i - 1] = '+';
            }
        }
        map.put(new String(chars), canWin);
        return canWin;
    }
    
    /**
     * [SOLUTION]: 
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        Map<Integer, Boolean> map = new HashMap<>();
        boolean[] used = new boolean[maxChoosableInteger + 1];
        return memorizedSearch(maxChoosableInteger, desiredTotal, map, used, 0);
    }
    private boolean memorizedSearch(int maxChoosableInteger, int desiredTotal,
             Map<Integer, Boolean> map, boolean[] used, int curTotal) {
        if (desiredTotal <= curTotal) return false;
        if (map.containsKey(curTotal)) return map.get(curTotal);
        for (int i = 1;i <= maxChoosableInteger;i++) {
            if (used[i]) continue;  // check every unchosen number
            used[i] = true;
            if (!memorizedSearch(maxChoosableInteger, desiredTotal, map, used, curTotal + i)) {
                map.put(i, true);
                used[i] = false;
                return true;
            }
            used[i] = false;
        }
        
    }
   
}
