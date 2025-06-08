/*
 * @lc app=leetcode id=448 lang=java
 *
 * [448] Find All Numbers Disappeared in an Array
 */

import java.util.List;
import java.util.ArrayList;

// @lc code=start
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // Use the index as a hash map to mark the presence of numbers
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1; // Convert to zero-based index
            if (nums[index] > 0) {
                nums[index] = -nums[index]; // Mark as found by negating the value
            }
        }
        
        // Collect the indices of the numbers that were not found
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) { // If the value is still positive, it means the number was missing
                result.add(i + 1); // Convert back to one-based number
            }
        }
        
        return result;
        
    }
}
// @lc code=end

