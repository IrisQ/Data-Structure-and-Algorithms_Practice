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
 }
