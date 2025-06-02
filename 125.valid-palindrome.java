/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // Move left pointer to the next alphanumeric character
            while (left < right && !isValidChar(s.charAt(left))) {
                left++;
            }
            // Move right pointer to the previous alphanumeric character
            while (left < right && !isValidChar(s.charAt(right))) {
                right--;
            }
            // Compare characters in a case-insensitive manner
            if (left < right && !isEqualIgnoreCase(s.charAt(left), s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isValidChar(char c) {
        return Character.isLetterOrDigit(c);
    }
    private boolean isEqualIgnoreCase(char c1, char c2) {
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

}
// @lc code=end

