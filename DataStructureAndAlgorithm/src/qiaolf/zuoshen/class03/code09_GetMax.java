package qiaolf.zuoshen.class03;

/**
 * @Description: 递归求数组最大值
 * Author:qiaolf
 * @Date:2021/3/23 17:54
 **/
public class code09_GetMax {
    public static int getMax(int[] arr,int l,int r){
        if (l==r){
            return arr[l];
        }
        //l+((r-l)>>1) !=  l + (r-l) >> 1    wtf
        int max1=getMax(arr,l,l+((r-l)>>1));
        int max2=getMax(arr,l+1+((r-l)>>1),r);
//        return max1<max2 ? max2 : max1;
        return Math.max(max1,max2);
    }

    public static void main(String[] args) {
        int[] arr = {2,5,7,1,6,8,2,8,8,9};
        System.out.println(getMax(arr,0,arr.length-1));
    }
}
