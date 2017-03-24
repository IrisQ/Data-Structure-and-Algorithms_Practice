/**
 * 1. Lintcode-Permutations
 * Given a list of [distinct] numbers, return all possible permutations. eg:[1, 2, 3]
 
 * 2. Lintcode-Permutations II
 * Given a list of numbers(maybe not distinct), return all possible permutations. eg: [1, 2, 2]
 
 * 78. Subsets
 * Given a set of [distinct] integers, nums, return all possible subsets.eg:[1, 2, 3]

 * 90. Subsets II
 * not distinct integers, [may contain duplicates], return all possible subsets.eg:[1, 2, 2]
 
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 
 * 39. Combination Sum
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 
 * 40. Combination Sum II
 * Each number in C may only be used once in the combination.
 
 * 216. Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers 
 * from 1 to 9 can be used and each combination should be a unique set of numbers.

 * 377. Combination Sum IV
 * Given an integer array with all positive numbers and no duplicates, find the number of 
 * possible combinations that add up to a positive integer target
 */
 

 public class Solution {
    /**
     * Permutation-1: all the numbers are distinct. Check if list contains before add into list
     * Time: O()
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), nums);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        } 
        for (int i = 0;i < nums.length;i++) {
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            dfs(res, list, nums);
            list.remove(list.size() - 1);
        }
    } 
     
    /**
     * Permutation-2: numbers are not unique, but need to make sure lists in res are unique. 
     *                each nums[i] can only be used once.
     * [Solution-1] 1.Use Set<List<Integer>> + return new ArrayList<>(res); 
     *              instead of using List<List<Integer>> to make sure all the sub-lists in res are unique.
     *              2.boolean[] used, used[i] keep track if nums[i] has been used before, skip used ones.
     * [Solution-2] 1*. Modify dfs, in for loop, skip the ones which nums[i] == nums[i - 1]. 
     */
    // this one is using solution-1
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        boolean[] used = new boolean[nums.length];
        // Solution-2: need to sort before. Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, used);
        return new ArrayList<>(res);
    } 
    
    private void dfs(Set<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        } 
        for (int i = 0;i < nums.length;i++) {
            if (used[i]) continue;
            list.add(nums[i]);
            used[i] = true;
            dfs(res, list, nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
            // Solution-2: add this line:
            // while (i < nums.length && nums[i + 1] == nums[i]) i++;
        }
    }
    
    /**
     * 78. Subsets
     * [Solution]: 1.Well... make sure every subset can be added, 
     *             so dfs don't need to check before "res.add(new ArrayList<>(list));"
     *             2. Use curIdx to make sure no duplicates.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    // loop start from cur
    private dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int cur) {
        res.add(new ArrayList<>(list));        
        for (int i = cur;i < nums.length;i++) {
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
     
    /**
     * 90. Subsets II
     * [Solution]: same as Permutation-2. Use Set / sort + while loop in for
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int curIdx) {
        res.add(new ArrayList<>(list));
        for (int i = curIdx;i < nums.length;i++) {
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
    }
    
    /**
     * 77. Combinations
     * [Solution]: Since it should contains all conbinations, if we have [1, 4], don't need [4, 1]
     *             then can simply use cur as index to prevent going back.
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, k, 1);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> list, int n, int k, int cur) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = cur;i <= n;i++) {  // numbers start from i.
            list.add(i);
            dfs(res, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }
     
    /**
     * 39. Combination Sum
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int cur, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        } else if (sum > target) {
            return;
        }
        for (int i = cur;i < candidates.length;i++) {
            list.add(candidates[i]);
            dfs(res, list, candidates, target, i + 1, sum + candidates[i]);
            list.remove(list.size() - 1);
        }
    } 
    /**
     * 40. Combination Sum II/III
     * Only difference is candidates[] may contain duplicate numbers. 
     * [Solution]: Same as Permutation-2, sort + while / 
     */ 
     
     /**
      * 377. Combination Sum IV
      * Only need to find the number of possible combinations
      * [Solution]: similar to [climbing stairs], using dp
      * Time: O(nlogn) + O(target * n).
      */
     public int combinationSum4(int[] nums, int target) {
         int count = 0;
         Arrays.sort(nums);
         int[] dp = new int[target + 1];   // dp[i]: # of possible combinations to get sum i 
         dp[0] = 1;
         for (int i = 1;i <= target;i++) {
             for (int num:nums) {
                if (num > i) break;
                else if (num == i) dp[i]++;
                else dp[i] += dp[num - i];
             }
         }
         return dp[target];
     }
 }
