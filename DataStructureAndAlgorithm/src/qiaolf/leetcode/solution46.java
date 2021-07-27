package qiaolf.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/7/13 11:33
 **/
public class solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        List<Integer> integerList = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            rest.add(nums[i]);
        }
        return process(list,integerList,rest);
    }

    private List<List<Integer>> process(List<List<Integer>> list, List<Integer> integerList, List<Integer> rest) {
        if (rest.isEmpty()){
            list.add(integerList);
        }else{
            for (int i = 0; i <rest.size() ; i++) {
                Integer remove = rest.remove(i);
                List<Integer> numList = new ArrayList<>();
                numList.addAll(integerList);
                numList.add(remove);
                process(list,numList,rest);
                rest.add(i,remove);
            }
        }
        return list;
    }
}
