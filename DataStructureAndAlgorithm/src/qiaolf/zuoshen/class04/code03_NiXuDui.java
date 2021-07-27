package qiaolf.zuoshen.class04;

/**
 * @Description: 在一个数组中，任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对，
 * 返回数组中的所有逆序对
 * Author:qiaolf
 * @Date:2021/3/24 9:46
 **/
//这道题用到归并
public class code03_NiXuDui {

    public static void main(String[] args) {
        int nums[] ={1,3,6,8,9,2,1,0,5,7};
        int nums1[] ={9,2,1,0,5,7};
        System.out.println(reversePairs(nums1));
    }
    public static int reversePairs(int nums[]){
        if (nums==null || nums.length<2){
            return 0;
        }
        return process(nums,0,nums.length-1);
    }

    private static int process(int[] nums, int l, int r) {
        if (l==r){
            return 0;
        }
        int ans = 0;
        ans=process(nums,l,l+((r-l)>>1)) +
        process(nums,l+1+((r-l)>>1),r) +
        merge(nums,l,l+((r-l)>>1),r);
        return ans;
    }

    //必须要用到归并排序，因为你这样设计的初衷是左右组都是排好序的
    private static int merge(int[] nums, int l, int M, int r) {
        int tem[] = new int[r-l+1];
        int i=0;//这个是存tem下标
        int p=l;//左组下标开始位置
        int q=M+1;//右组下标开始位置
        int sum = 0;//逆序对
        //左右组都没有越界
        while (p<=M && q<=r){
            //要排序
            sum += nums[p] > nums[q] ? (M-p+1) : 0 ;
            //这里要将数组按照升序拍好上面那一步才能那样用
            tem[i++] = nums[p] <= nums[q] ? nums[p++] : nums[q++];
        }
        while (p<=M){
            tem[i++] = nums[p++];
        }
        while (q<=r){
            tem[i++]=nums[q++];
        }
        for (i = 0; i < tem.length; i++) {
            nums[l+i]=tem[i];
        }

        return sum;
    }
}
