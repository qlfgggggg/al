package qiaolf.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * Author:qiaolf
 * @Date:2021/5/21 11:11
 **/
public class Offer03 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }
}
