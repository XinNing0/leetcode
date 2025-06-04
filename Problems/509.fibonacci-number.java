/*
 * @lc app=leetcode id=509 lang=java
 *
 * [509] Fibonacci Number
 */

// @lc code=start
class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        return fib(n - 1) + fib(n - 2);
        
    }
}
// @lc code=end

//recursion: only consider the base case and the resursive case
//1. get what index and return what value
//2. how to devide the problem into smaller subproblems
//3. what is the case to return
//time complexity: O(2^n)
//space complexity: O(n) for the call stack
//this is a naive solution, it can be optimized using memoization or dynamic programming

