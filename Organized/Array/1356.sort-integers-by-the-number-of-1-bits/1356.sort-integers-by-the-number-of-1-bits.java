/*
 * @lc app=leetcode id=1356 lang=java
 *
 * [1356] Sort Integers by The Number of 1 Bits
 */

// @lc code=start
class Solution {
    public int[] sortByBits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
            
        }
        return Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> {
                    int countA = Integer.bitCount(a);
                    int countB = Integer.bitCount(b);
                    if (countA == countB) {
                        return Integer.compare(a, b);
                    }
                    return Integer.compare(countA, countB);
                })
                .mapToInt(Integer::intValue)
                .toArray(); 
    }
}
// @lc code=end

