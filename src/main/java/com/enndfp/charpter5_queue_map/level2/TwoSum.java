package com.enndfp.charpter5_queue_map.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author Enndfp
 */
public class TwoSum {

    /**
     * HashMap
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }
}
