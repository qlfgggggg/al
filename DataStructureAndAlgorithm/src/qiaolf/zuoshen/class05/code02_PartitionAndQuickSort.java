package qiaolf.zuoshen.class05;

import java.net.PortUnreachableException;
import java.util.Stack;

/**
 * @Description: 快速排序
 * Author:qiaolf
 * @Date:2021/3/24 20:51
 **/
public class code02_PartitionAndQuickSort {
    public static void swap(int arr[],int i,int j){
        int tem = arr[i];
        arr[i]=arr[j];
        arr[j]=tem;
    }

    //分好区之后划分点的位置就不用再参与排序了
    //分区，左边某部分都是小于等于arr[R]的，右边某部分都是大于的
    //快速排序,在arr[L====R]上，以arr[R]作为划分值
    //<= X   >X
    public static int partition(int arr[],int L,int R){
        if (L>R){
            return -1;
        }
        if (L==R){
            return L;
        }
        int less = L-1;//小于等于arr[R]值的最右边界
        int index = L;//当前位置的值
        while (index<R){//只要当前位置没有到达最后位置，就一直向后移动
            //如果当前位置的值小于arr[R],将当前位置的值和less下一个位置的值交换，且less边界向右移动一位
            //index向右移动一位
            if (arr[index]<=arr[R]){
//                swap(arr,++less,index++);//不能这样写，不管if条件发不发生index都要后移
                swap(arr,++less,index);
            }
            index++;
        }
        swap(arr,R,++less);//最后将R位置的值和less下一个位置的值交换
        //最后返回R位置----less
        return less;
    }

    //分好区之后划分点的位置就不用再参与排序了
//    分区，左边某部分都是小于等于arr[R]的，中间一部分都是等于的，右边某部分都是大于的
    //arr[L.....R]荷兰国旗问题划分，以arr[R]做划分值
    // < X    ==X    >X
    public static int[] netherLandFlag(int arr[],int L,int R){
        if (L>R){
            return new int[]{-1,-1};
        }
        if (L==R){
            return new int[]{L,R};
        }
        int less = L-1;// <区有边界
        int more = R;//>区左边界
        int index = L;//当前位置
        while (index<more){//这里要用more，不可以再用R了，因为more在后退
            if (arr[index]<arr[R]){
                swap(arr,++less,index++);
            }else if (arr[index]==arr[R]){
                index++;
            }else{//>  arr[index] > arr[R] 将more位置前一个数和index位置交换，并且index不进行++,这是因为交换more位置过来的数还没有和arr[R]进行比较
//                swap(arr,index++,--more);
                swap(arr,index,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }

    //快速排序1.0
    public static void quickSort1(int arr[]){
        if (arr==null || arr.length<2){
            return;
        }
        process1(arr,0,arr.length-1);
    }

    private static void process1(int[] arr, int l, int r) {
        if (l>=r){
            return;
        }
        int partition = partition(arr, l, r);
        process1(arr,l,partition-1);
        process1(arr,partition+1,r);
    }
    
    //快速排序2.0
    public static void quickSort2(int arr[]){
        if (arr==null || arr.length<2){
            return;
        }
        process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l>=r){
            return;
        }
        int[] ints = netherLandFlag(arr, l, r);
        process2(arr,l,ints[0]-1);
        process2(arr,ints[1]+1,r);
    }

    //快速排序3.0
    //快速排序1.0缺点：每次1 除了划分点不参与排序，和划分点相等的数还要参加排序
    //快速排序2.0缺点：在最右侧找arr[R]作为划分点，可能会出现以下情况：1.0也有这种情况
    //[1,2,3,4,5,6,7]以7作为划分点，每次排序前面的数都要和划分点比较，7（第一个划分点），6（第二个），最坏时间复杂度O(n^2)
    //所以我们现在用中间的某个数和7交换：
    public static void quickSort3(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        process3(arr,0,arr.length-1);
    }

    private static void process3(int[] arr, int l, int r) {
        if (l>=r){
            return;
        }
        swap(arr,l+(int)(Math.random()*(r-l+1)),r);//将中间的某个数和最右边的数交换一下，防止极端情况出现
        int[] ints = netherLandFlag(arr, l, r);
        process3(arr,l,ints[0]-1);
        process3(arr,ints[1]+1,r);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

//    public static void main(String[] args) {
//        int arr[] = {1,2,3,3,2,1,4,6,8,9,4,5,6,7,9,0,1,4};
////        quickSort1(arr);
////        quickSort1(arr);
//        quickSort3(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);;
//        }
//    }
}
