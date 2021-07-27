package qiaolf.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/7/13 14:25
 **/
public class solution46_2 {

    public List<List<Integer>> permute(int[] nums) {
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
            for (int i = index; i < nums.length; i++) {
                swap(nums,i,index);
                process(list,nums,index+1);
                swap(nums,i,index);
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
