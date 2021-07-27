package qiaolf.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 示例 4：
 *
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 示例 5：
 *
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * Author:qiaolf
 * @Date:2021/7/21 19:48
 **/
public class solution239 {
    /**
     * 滑动窗口的长度为k,数组长度为nums.length,所有滑动窗口最大值长度num.length-k+1
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //双端队列  从大到小
        LinkedList<Integer> qmax = new LinkedList<>();
        //装滑动窗口最大值的容器
        int[] res = new int[nums.length-k+1];
        int index = 0;
        int r = 0;
        while (r< nums.length){
            while (!qmax.isEmpty() && nums[qmax.peekLast()]<=nums[r]){
                qmax.pollLast();
            }
            qmax.offer(r);
            while (!qmax.isEmpty() && qmax.peekFirst()==r-k){
                qmax.pollFirst();
            }
            if (r>=k-1){
                res[index++]=nums[qmax.peekFirst()];
            }
            r++;
        }
        return res;
    }
}
