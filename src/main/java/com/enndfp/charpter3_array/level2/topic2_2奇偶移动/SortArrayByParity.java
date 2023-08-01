package com.enndfp.charpter3_array.level2.topic2_2奇偶移动;

/**
 * 奇偶移动
 *
 * @author Enndfp
 */
public class SortArrayByParity {

    /**
     * 对撞型双指针
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 > nums[right] % 2) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
            if (nums[left] % 2 == 0) left++;
            if (nums[right] % 2 == 1) right--;
        }
        return nums;
    }

}
