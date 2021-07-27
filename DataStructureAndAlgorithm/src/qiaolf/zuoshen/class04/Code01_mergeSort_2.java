package qiaolf.zuoshen.class04;

/**
 * @Description: 用迭代的方式写归并函数
 * Author:qiaolf
 * @Date:2021/3/23 22:25
 **/
public class Code01_mergeSort_2 {

    public static void main(String[] args) {
        int arr[] ={5,3,2,7,8,9,1,1,3,3,4,9,0,3};
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void mergeSort(int arr[]){
        if (arr==null || arr.length<2){
            return;
        }
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize<N){
            int L = 0;//左组的最左边界,
            //这是一轮比较下来，一轮比较下来mergeSize要  X2
            while (L<N){//有好几对左右组，左组的最左边界一直在增加：
                int M = L+mergeSize-1;//左组的最右边界
                if (M>=N){//如果左组的最右边界大于N
                    break;
                }
                int R = Math.min(M+mergeSize,N-1);//求右组的最右边界，有可能最右边界越界，所以要求最小值
                merge(arr,L,M,R);
                L=R+1;//左右组为一个比较单元，下一个比较单元的左组的最左边界
            }
            //mergeSize要  X2，但是在x2之前要先判断，一下是否会溢出，
            //因为N如果是在整形int的边缘，2*mergeSize>N的话，会溢出整形
            if (mergeSize>N/2){
                break;
            }
            //步长*2
            mergeSize = mergeSize << 1;
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int tem[] = new int[r-l+1];
        int q = l,p=m+1,i=0;
        while (q<=m && p<=r){
            tem[i++]  =   arr[q] <= arr[p] ? arr[q++] : arr[p++];
        }
        while (q<=m){
            tem[i++] = arr[q++];
        }
        while (p<=r){
            tem[i++] = arr[p++];
        }

        for (i = 0;  i< tem.length; i++) {
            arr[l+i]=tem[i];
        }
    }
}
