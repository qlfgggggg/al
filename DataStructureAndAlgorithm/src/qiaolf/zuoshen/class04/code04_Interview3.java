package qiaolf.zuoshen.class04;

import java.util.concurrent.ForkJoinPool;

/**
 * @Description: 常见面试题3:
 * 一个数组中对于每个数num,求后面有多少个*2仍旧小于num,求总个数
 * Author:qiaolf
 * @Date:2021/3/24 10:35
 **/
//这道题可以用归并排序的方法解
public class code04_Interview3 {

    public static void main(String[] args) {
        int arr[] = {1,3,4,6,7,2,5,0,1,7,9};
        int arr2[]= {1,3,4,6,7,2,5,0,1,7,9};
        System.out.println(total(arr));
        System.out.println(total2(arr2));
    }
    
    public static int total2(int arr[]){
        int sum = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]>2*arr[j]){
                    sum++;
                }
            }
        }
        return sum;
    }

    public static int total(int arr[]){
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
                process(arr,l,l+((r-l)>>1))+
                process(arr,l+((r-l)>>1)+1,r)+
                merge(arr,l,l+((r-l)>>1),r);
        return ans;

    }

    private static int merge(int[] arr, int l, int M, int r) {
        int tem[] = new int[r-l+1];
        int i=0,p=l,q=M+1;
        int sum = 0,windowR = M+1;
        //在每次merge之前，左右组都已经排好序了，在合并之前进行计算sum
        for (int j = l; j <= M; j++) {
            while (windowR<=r && arr[j]>(2*arr[windowR])){
                windowR++;
            }
            sum += windowR-M-1;
        }
        while (p<=M && q<=r){
            tem[i++] = arr[p] <= arr[q] ? arr[p++] : arr[q++];
        }
        while (p<=M){
            tem[i++] = arr[p++];
        }
        while (q<=r){
            tem[i++]=arr[q++];
        }
        for (i = 0; i < tem.length; i++) {
            arr[l+i]=tem[i];
        }

        return sum;
    }
}
