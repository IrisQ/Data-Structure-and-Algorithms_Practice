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
                        if (List is not full) List.add(nums[i])     ||  add candidates
     *                  else if (List contains nums[i]) count[i]++  ||  check if candidate is really majority
     *                  else if (In List, some integer's corresponding count[j] == 0) replace that integer to num[i], set count=1
     *                  else count[m]-- for all elements in count.
     * Time: O(n)
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
     
     /**
      * 229. Majority Element II
      */
     public List<Integer> majorityElement(int[] nums) {
         List<Integer> res = new ArrayList<>();
         if (nums == null || nums.length == 0) return res;
         int num1 = 0, num2 = 0;
         int count1 = 0, count2 = 0;
         int len = nums.length;
         for (int i = 0;i < len;i++) {
            if (num1 == nums[i]) {
                count1++;
            } else if (num2 == nums[i]) {
                count2++;
            } else if (count1 == 0) {
                num1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                num2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
         }
         
         count1 = count2 = 0;       // count again and check if num1, num2 are really > len/3
         for (int num:nums) {
            if (num == num1) count1++;
            else if (num == num2) count2++;
         }
         if (count1 > len/3) res.add(num1);
         if (count2 > len/3) res.add(num2);
         return res;
     }
    
     /**
      * 
      */
     public int majorityNumber(ArrayList<Integer> nums, int k) {
         if (nums == null || nums.size() == 0) return -1;
         List<Integer> numList = new ArrayList<>();
         List<Integer> countList = new ArrayList<>();
         int size = nums.size();
         for (int i = 0;i < size;i++) {
             int cur = nums.get(i);
             if (numList.size() < k - 1 && !numList.contains(cur)) {    // 1. list not full yet
                 numList.add(cur);
                 countList.add(1);
             } else {
                 int idx = numList.indexOf(cur);
                 if (idx != -1) {                           // 2. contains nums[i]
                     countList.set(idx, countList.get(idx) + 1);
                 } else {                                   // if doesn't contain...
                     int zeroIdx = countList.indexOf(0);
                     if (zeroIdx != -1) {                   // 3. if exists integer whose count == 0
                         numList.remove(zeroIdx);
                         countList.remove(zeroIdx);
                         numList.add(cur);
                         countList.add(1);
                     } else {                               // 4. all the integer's count > 0, then count-- for all.
                         for (int j = 0;j < countList.size();j++) {
                             countList.set(j, countList.get(j) - 1);
                         }
                     }
                 }
             }
         }
         for (int i = 0;i < size;i++) {
             int count = 0;
             for (int num:nums) {
                 if (num == numList.get(i)) count++;
             }
             if (count > size / k) return numList.get(i);
         }
         return -1;
     }
}
