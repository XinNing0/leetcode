/*
 * @lc app=leetcode id=704 lang=java
 *
 * [704] Binary Search
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1; // Target not found
        }
        //Calculate the middle index quickly to avoid overflow
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid; // Target found
        } 
        if (nums[mid] < target) {
            return binarySearch(nums, target, mid + 1, right); // Search in the right half
        } 
        return binarySearch(nums, target, left, mid - 1); // Search in the left half
        }
    }


// @lc code=end

