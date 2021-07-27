package qiaolf.zuoshen.class04;

/**
 * @Description: 归并排序递归方法实现
 * Author:qiaolf
 * @Date:2021/3/23 21:09
 **/
public class Code01_mergeSort {


    public static void sort(int arr[]){
        if (arr==null || arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l==r){
            return;
        }
        process(arr,l,l+((r-l)>>1));
        process(arr,l+((r-l)>>1)+1,r);
        merge(arr,l,l+((r-l)>>1),r);
    }

    private static void merge(int[] arr, int l, int M, int r) {
        int tem[] = new int[r-l+1];
        int i=0;
        int q=l,p=M+1;
        while (q<=M && p<=r){
            if (arr[q]<=arr[p]){
                tem[i++]=arr[q++];
            }else{
                tem[i++]=arr[p++];
            }
//            arr[q]<=arr[p] ? tem[i++]=arr[q++] : tem[i++]=arr[p++];
        }
        while (q<=M){
            tem[i++]=arr[q++];
        }
        while (p<=r){
            tem[i++]=arr[p++];
        }
        for (i = 0; i < tem.length; i++) {
            arr[l+i]=tem[i];
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,5,6,8,1,2,9,9,1,5,6,8,2};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
