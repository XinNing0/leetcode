/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result; // This line is technically unreachable if the input is valid.
        
    }
}
// @lc code=end
//time complexity: O(n^2)
//space complexity: O(1)
// This solution uses a brute force approach to find the two indices of the numbers that add up to the target.

