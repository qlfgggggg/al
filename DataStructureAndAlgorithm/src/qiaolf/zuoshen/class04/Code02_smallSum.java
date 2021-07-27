package qiaolf.zuoshen.class04;

/**
 * @Description: 小和问题：数组中，一个数前面的所有比他小的数累加，所有数前面比他小的数累加和就是小和
 * Author:qiaolf
 * @Date:2021/3/23 23:58
 **/
//我们用归并，一个单元分左右组，求左组中每个数在右组中有多少个数比他大，有几个就将这个数乘以几作为小和一部分
//同一组在和其他组进行比较时不产生小和，
public class Code02_smallSum {

    public static int megerSortSmallSum(int arr[]){
        if (arr==null || arr.length<2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l==r){
            return 0;
        }
        int ans = 0;
        ans =
                process(arr,l,l+((r-l)>>1))
                +process(arr,l+((r-l)>>1)+1,r)
                +merge(arr,l,l+((r-l)>>1),r);
        return ans;
    }

    private static int merge(int[] arr, int l, int M, int r) {
        int tem[] = new int[r-l+1];
        int i=0,q=l,p=M+1;
        int sum = 0;
        while (q<=M && p <= r){
            //这里不用等于号，因为小和问题，当左组和右组某个数相等时，先copy右组，直到在右组找到比左组大的数才copy左组
            //左组小于右组,产生小和问题:
            sum += arr[q]<arr[p] ? (r-p+1)*arr[q] : 0;
            tem[i++]= arr[q]<arr[p] ? arr[q++] : arr[p++];
        }
        while (q<=M){
            tem[i++] = arr[q++];
        }
        while (p<=r){
            tem[i++]=arr[p++];
        }
        for (i = 0; i < tem.length; i++) {
            arr[l+i]=tem[i];
        }
        return sum;
    }
    
    public static int arraySmallSum(int arr[]){
        int sum =0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i]){
                    sum +=arr[j];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int arr[] = {1,23,4,1,5,8,9,3,4,6,7,8};
        System.out.println(arraySmallSum(arr));
        System.out.println(megerSortSmallSum(arr));
    }
}
