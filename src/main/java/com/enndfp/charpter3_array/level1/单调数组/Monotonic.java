package com.enndfp.charpter3_array.level1.单调数组;

/**
 * 判断一个数组是否是单调数组
 *
 * @author Enndfp
 */
public class Monotonic {
    /**
     * 一次遍历
     *
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean inc = true, dec = true;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                inc = false;
            }
            if (nums[i] < nums[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }
}
