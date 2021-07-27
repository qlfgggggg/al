package qiaolf.leetcode.data.sort;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/7/13 17:30
 **/
public class solution {

    /**
     * 归并排序：
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     *
     * 示例:
     *
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     * 说明:
     *
     * A.length == n + m
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/sort-algorithms/osomav/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {

        int i = 0,j=0,k=0;
        int arr[]=new int[m+n];
        while (i<m && j<n){
            arr[k++]=A[i]<=B[j] ? A[i++] : B[j++];
        }
        while (i<m){
            arr[k++]=A[i++];
        }

        while (j<n){
            arr[k++]=B[j++];
        }
        for (int l = 0; l < arr.length; l++) {
            A[l]=arr[l];
        }
    }


    /**
     * 剑指 Offer 51. 数组中的逆序对
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [7,5,6,4]
     * 输出: 5
     *  
     *
     * 限制：
     *
     * 0 <= 数组长度 <= 50000
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/sort-algorithms/etdd3m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums.length<2 || nums==null)
            return 0;
        return process(nums,0,nums.length-1);
    }

    private int process(int[] nums, int l, int r) {
        if (l==r)
            return 0;
        int ans = 0;
        ans = process(nums,l,l+((r-l)>>1))+
        process(nums,l+1+((r-l)>>1),r)+
        merg(nums,l,l+((r-l)>>1),r);
        return ans;
    }

    private int merg(int[] nums, int l, int mid, int r) {
        int res[]=new int[r-l+1];
        int i=0;
        int p=l;
        int q=mid+1;
        int count = 0;
        while (p<=mid && q<=r){
            count+= nums[p]>nums[q] ? (mid-p+1) : 0;
            res[i++] = nums[p]<=nums[q] ? nums[p++] : nums[q++];
        }

        while (p<=mid){
            res[i++] = nums[p++];
        }

        while (q<=r){
            res[i++]=nums[q++];
        }

        for (int j = 0; j < res.length; j++) {
            nums[j+l]=res[j];
        }
        return count;
    }
}
