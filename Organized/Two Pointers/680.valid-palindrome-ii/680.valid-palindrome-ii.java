/*
 * @lc app=leetcode id=680 lang=java
 *
 * [680] Valid Palindrome II
 */

// @lc code=start
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }
        Pair pair = findDifference(s, 0, s.length() - 1);
        return isPalindrome(s, pair.left + 1, pair.right) 
        || isPalindrome(s, pair.left, pair.right - 1);
    }

    // Simple Pair class to hold two integer values
    private static class Pair {
        int left;
        int right;
        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private Pair findDifference(String s, int left, int right){
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return new Pair(left, right);
    }

    private boolean isPalindrome(String s, int left, int right) {
        Pair pair = findDifference(s, left, right);
        return pair.left >= pair.right;
    }
}

// @lc code=end

// Time Complexity: O(n)
// Space Complexity: O(1)
// This solution uses a two-pointer technique to check if the string can be a palindrome by removing at most one character.

