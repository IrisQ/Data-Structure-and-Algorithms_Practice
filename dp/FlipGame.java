/**
 * 293. Flip Game
 * Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 
 * 294. Flip Game II
 * Write a function to determine if the starting player can guarantee a win.
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
    
    /**
     * Similar to Game Theory questions... Minmax
     */
    public boolean canWin(String s) {
        if (s.indexOf("++") == -1) return false;
        
        return false;
    }
    
   
}
