package qiaolf.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/7/13 12:42
 **/
public class solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        return process(list,nums,0);
    }

    private List<List<Integer>> process(List<List<Integer>> list, int[] nums, int index) {
        if (index==nums.length){
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                arr.add(nums[i]);
            }
            list.add(arr);
        }else{
            boolean visited[]=new boolean[21];
            for (int i = index; i < nums.length; i++) {
                if (!visited[nums[i]]){
                    visited[nums[i]]=true;
                    swap(nums,i,index);
                    process(list,nums,index+1);
                    swap(nums,i,index);
                }
            }
        }
        return list;
    }

    private void swap(int[] arr,int i,int j){
        int tem = arr[i];
        arr[i]=arr[j];
        arr[j]=tem;
    }
}
