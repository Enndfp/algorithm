package com.enndfp.charpter3_array.level1.数组合并;

/**
 * 合并两个有序数组
 *
 * @author Enndfp
 */
public class Merge2Array {
    /**
     * 逆向双指针
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        for (int index = k - 1, nums1Index = m - 1, nums2Index = n - 1; index >= 0; index--) {
            if (nums1Index < 0) {
                //nums1已经取完，完全取nums2的值即可
                nums1[index] = nums2[nums2Index--];
            } else if (nums2Index < 0) {
                //nums2已经取完，完全取nums1的值即可
                break;
            } else if (nums1[nums1Index] > nums2[nums2Index]) {
                //nums1的元素值大于nums2，取nums1值
                nums1[index] = nums1[nums1Index--];
            } else {
                //nums2的元素值大于nums1，取nums2值
                nums1[index] = nums2[nums2Index--];
            }
        }
    }
}
