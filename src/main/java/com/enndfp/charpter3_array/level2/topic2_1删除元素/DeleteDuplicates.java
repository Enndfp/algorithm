package com.enndfp.charpter3_array.level2.topic2_1删除元素;

/**
 * 删除重复元素，重复只保留一个
 *
 * @author Enndfp
 */
public class DeleteDuplicates {

    /**
     * 快慢双指针
     *
     * @param nums 待处理数组
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        //slow表示可以放入新元素的位置，索引为0的元素不用管
        int slow = 1;
        //循环起到了快指针的作用
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
