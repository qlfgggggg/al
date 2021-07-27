package qiaolf.leetcode;

import java.util.TreeSet;

/**
 * @Description:
 * Author:qiaolf
 * @Date:2021/3/24 14:19
 **/
public class Solution327 {

    public static int countRangeSum(int[] nums,int lower,int upper) {
       if (nums==null || nums.length==0){
           return 0;
       }
       //这里要用long类型，因为前缀和可能结果为long
       long sum[] = new long[nums.length];
       sum[0]=nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i]=sum[i-1]+nums[i];
        }
        //我们将问题转换为以j为数组结尾索引，l=sum[j]-upper,u=sum[j]-lower,sum[i-1]属于[l,u]范围的问题，这里可以用到归并排序的知识，
       return process(sum,0,sum.length-1,lower,upper);
    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l==r){
            if (sum[l]<=upper && sum[l]>=lower){
                return 1;
            }else{
                return 0;
            }
        }
        int ans=0;
        ans=
        process(sum,l,l+((r-l)>>1),lower,upper)+
        process(sum,l+((r-l)>>1)+1,r,lower,upper)+
        merge(sum,l,l+((r-l)>>1),r,lower,upper);
        return ans;
    }

    private static int merge(long[] sum, int l, int M, int r, int lower, int upper) {
        //进来先不merge，左右组都是排好对的了,我们直接
        //我们将问题转换为以j为数组结尾索引，l=sum[j]-upper,u=sum[j]-lower,sum[i-1]属于[l,u]范围的问题，这里可以用到归并排序的知识，
        int ans=0;
        int windowR=l;
        int windowL=l;
        //这层相当于右组
        for (int i = M+1; i <= r; i++) {
            long min = sum[i]-upper;
            long max = sum[i]-lower;
            //这层相当于左组的满足条件的最左边界:为什么sum[windowL]<min?因为只要是sum[windowL]<min，就一直往右移，直到sum[windowL]>=min为止
            while (windowL<=M && sum[windowL]<min){
                windowL++;
            }
            //这组相当于左组满足条件的最右边界
            while (windowR<=M && sum[windowR]<=max){
                windowR++;
            }
            ans += windowR-windowL;
        }

        int i=0,p=l,q=M+1;
        long tem[] = new long[r-l+1];
        while (p<=M && q<=r){
            tem[i++] = sum[p] <= sum[q] ? sum[p++] : sum[q++];
        }
        while (p<=M){
            tem[i++] = sum[p++];
        }
        while (q<=r){
            tem[i++]=sum[q++];
        }
        for (i = 0; i < tem.length; i++) {
            sum[l+i]=tem[i];
        }
        return ans;
    }
}
