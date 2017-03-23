public class Solution {
    /**
     * KEY POINT of SCANNING LINE: only START and END matters, only START and END needs to be updated
     */
    
    /**
     * 370. Range Addition: 
     * Use another definition:
     * int[] out = new int[length];
     * out[i] = 3, means for [i, end], all the positions +3
     * out[j] = -2, means for [j, end], all position -2
     * 所以遇到[1, 3, 2], 在out[1] +2, 意味着把1-end所有的都+2
     * 然后再在out[4] -2, 意味着4以及之后的不加
     * 所以处在i位置的会被out[0]-out[i-1]的数字影响
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        if (length <= 0) return null;
        int[] out = new int[length];
        if (updates == null || updates.length == 0) return out;
        for (int[] update:updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            out[start] += val;
            if (end + 1 < length) out[end + 1] -= val;
        }
        
        int carry = 0;
        for (int i = 0;i < length;i++) {
            out[i] += carry;
            carry = out[i];
        }
        return out;
    }
    
    /** 
     * 128. Longest Consecutive Sequence
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * For example, Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4
     * 
     * 每次看新的值的时候，比如[1, 3, 2, 4, 9, 8, 5]...
     * 拿到4的时候，和它有关系的只是3和5的长度，
     * map.get(3) 意味着有多少个数字和3连在一起(包括3)，假设是2,
     * 5means多少数字和5连在一起(includes)，假设是3，那么totalLen=6，
     * 是从2-3, 4, 5-7，这个时候put(4, 6)，2-3和5-7其实对应的值也都是6，
     * 但我们只需要修改2和7在map中对应的值，
     * 因为nums中，如果以后取得的某一个值为3-6，只会continue不会再判断，
     * 只有在遇到< 2 或者 >7 的数值的时候，才会用到map.get(2)和map.get(7)的值，
     * means 将来在map中能用到的只有2和7对应的值，所以只更新这两个就可以
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();    // <number, length>
        for (int i = 0;i < nums.length;i++) {
            int cur = nums[i];
            if (map.containsKey(cur)) continue;
            int leftLen = map.containsKey(cur - 1) ? map.get(cur - 1) : 0;
            int rightLen = map.containsKey(cur + 1) ? map.get(cur + 1) : 0;
            int totalLen = leftLen + 1 + rightLen;
            max = Math.max(max, totalLen);
            map.put(cur, totalLen);
            
            if (map.containsKey(cur - 1)) {
                map.put(cur - leftLen, totalLen);   // why (cur - leftLen)
            }
            if (map.containsKey(cur + 1)) {
                map.put(cur + rightLen, totalLen);
            }
        }
        return max;
    }
}
 
