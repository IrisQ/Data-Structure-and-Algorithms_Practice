public class Majority {
    /**
     * 169. Majority Element
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     
     * 229. Majority Element II
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
     * The algorithm should run in linear time and in O(1) space.
     
     * LintCode. Majority Number III
     * Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.
     *
     * [SOLUTION-1]: 1. Use Map, keep track of the # of each integer's appearance;
     *               2. Check each map if they match condition.
     * [SOLUTION-2]: [Moore’s Voting Algorithm]
     *               Given nums[], size is n, find all elements > [n / k] times.
     *               There are maximum (k-1) integers fit this condition. So keep track of an array/List whose length is (k-1)
     * AND CHECK:    for each nums[i] in nums:
                        if (List is not full) List.add(nums[i])
     *                  else if (List contains nums[i]) count[i]++
     *                  else if (In List, some integer's corresponding count[j] == 0) replace that integer to num[i], set count=1
     *                  else count[m]-- for all elements in count.
     */
     
     /**
      * 169. Majority Element, skip 1-st step
      */
     public int majorityElement(int[] nums) {
        int num = nums[0];
        int count = 1;
        for (int i = 1;i < nums.length;i++) {
            if (num == nums[i]) {
                count++;
            } else if (count == 0) {
                num = nums[i];
                count = 1;
            } else {
                count--;
            }
        }
        return num;
     }
        return num;
     }
     
     /**
      * 229. Majority Element II
      */
     public List<Integer> majorityElement(int[] nums) {
         List<Integer> res = new ArrayList<>();
         if (nums == null || nums.length == 0) return res;
         
         return res;
     }
}
