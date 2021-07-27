package qiaolf.leetcode;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/3 22:45
 **/
public class solution2 {
    public int countNicePairs(int[] nums) {
        long l = 0;
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + rev(nums[j]) == nums[j] + rev(nums[i]))
                    l++;
            }
        }
        return (int) ((int)l%(Math.pow(10,9)+7));
    }
    //将数字反转
    public static int rev(int num){
        char[] chars = String.valueOf(num).toCharArray();
        int length = chars.length;
        int nu = 0;
        for (int i = length-1; i >= 0; i--) {
            nu+= (int)(chars[i]-'0') * Math.pow(10, i)!=0 ? (int)(chars[i]-'0') * Math.pow(10, i) : 0;
        }
        return nu;
    }

    public static void main(String[] args) {
        System.out.println(rev(12345));
    }
}
