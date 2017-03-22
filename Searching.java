/**
 * 491. Increasing Subsequences https://leetcode.com/problems/increasing-subsequences/#/description
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * KEY POINT: number can repeat in each list, but the result list shouldn't have duplicate lists. 
 * eq: it can has [6, 7, 7], but result should only contain one [6, 7]
 * USE set!
 * INSTEAD OF USING " List<List<Integer>> res = new ArrayList<>(); "
 * USE Set<List<Integer>> res = new HashSet<>();  // TO MAKE SURE each list is unique
 * AND THEN return new ArrayList<>(res);
 */
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        //List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> res = new HashSet<>();   ////神奇！！！
        dfs(res, new ArrayList<>(), nums, 0);
        return new ArrayList<>(res);
    }
    
    /**
     * nums[start] not included in list.
     */
    void dfs(Set<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        if (list.size() > 1) res.add(new ArrayList<>(list));
        int len = nums.length;
        for (int i = start;i < len;i++) {
            if (list.size() == 0 || list.get(list.size() - 1) <= nums[i]) {
                list.add(nums[i]);
                dfs(res, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
} 
 
