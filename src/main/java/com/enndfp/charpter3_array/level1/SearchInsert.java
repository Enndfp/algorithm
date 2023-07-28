package com.enndfp.charpter3_array.level1;

/**
 * 在数组中寻找插入位置
 *
 * @author Enndfp
 */
public class SearchInsert {
    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
