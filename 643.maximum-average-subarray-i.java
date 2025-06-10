/*
 * @lc app=leetcode id=643 lang=java
 *
 * [643] Maximum Average Subarray I
 */

// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int windowSum = 0;
        // Calculate the sum of the first 'k' elements
        for (int i= 0; i < k; i++) {
            windowSum += nums[i];
        }
        int maxSum = windowSum;
        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            // Add the next element and remove the first element of the previous window
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return (double) maxSum / k; // Return the maximum average
    }  
}
// @lc code=end

