package com.enndfp.charpter3_array.level2.topic2_1删除元素;

/**
 * 原地移除所有数值等于 val 的元素
 *
 * @author Enndfp
 */
public class DeleteVal {

    /**
     * 快慢双指针
     *
     * @param nums 待处理数组
     * @param val  待删元素
     * @return 不重复元素个数
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
