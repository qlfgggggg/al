package qiaolf.zuoshen.class08;

import java.util.Arrays;

/**
 * @Description: 基数排序
 * Author:qiaolf
 * @Date:2021/3/27 11:27
 **/
public class Code03_radixSort_2 {

    /**
     * 求一个数在第d位的数值，比如125在第一位为5，第二位为2，第3位为1
     * @param x   一个数
     * @param d    第几位
     * @return   返回在该位的数值
     */
    public static int getdigst(int x,int d){
        return (x/((int)Math.pow(10,d-1)))%10;
    }

    /**
     * 求数组最大值的位数
     * @param arr
     * @return
     */
    public static int getMaxBit(int arr[]){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int res=1;
        while ((max=max/10)>0){
            res++;
        }
        return res;
    }

    public static void radixSort(int arr[]){
        if (arr==null || arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,getMaxBit(arr));
    }

    /**
     *
     * @param arr
     * @param l
     * @param r
     * @param bit   最大值有多少位
     */
    private static void radixSort(int[] arr, int l, int r, int bit) {
        final int radix=10;
        int i=0,j=0;
        int help[]=new int[r-l+1];
        for (int d = 1; d <= bit; d++) {
            int count[]=new int[radix];
            for (i=l;i<=r;i++){
                j=getdigst(arr[i],d);
                count[j]++;//代表小于等于j的有几位,则help的下标索引应该是从右往左最大的开始-1
            }
            //求count的前缀和
            for ( i = 1; i <radix ; i++) {
                count[i]=count[i]+count[i-1];
            }
            //
            for (i = r; i >=l; i--) {
                j=getdigst(arr[i],d);
                //这里的意思是：比如j=5,如果小于等于j的有8位，即是count[j]=8,则help下标索引是从0开始的，因为是从右往左数，所以下标应该是count[j]-1
                help[count[j]-1]=arr[i];
                //之后count[j]--
                count[j]--;
            }
            for (i=l,j=0; i <=r ; i++,j++) {
                arr[i]=help[j];
            }
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}
