/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Use a HashMap to store the value and its index
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // If no solution is found
        return new int[0];
    }
}
// @lc code=end
//time complexity: O(n)
//space complexity: O(1)
// This solution uses a brute force approach to find the two indices of the numbers that add up to the target.

