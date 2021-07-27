package qiaolf.zuoshen.class09;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/3/27 22:02
 **/
public class Test {
    public static void swap(int[] arr,int i,int j){
        int tem=arr[i];
        arr[i]=arr[j];
        arr[j]=tem;
    }

    public static int[] partition(int[] array, int l, int r) {
        if (l>r){
            return new int[]{-1,-1};
        }
        if (l==r){
            return new int[]{l,r};
        }
        int less= l-1;
        int more=r;
        int index=l;
        while (index<more){
            if (array[index]<array[r]){
                swap(array,index,less+1);
                less++;
                index++;
            }else if (array[index]==array[r]){
                index++;
            }else{
                swap(array,index,more-1);
                more--;
            }
        }
        swap(array,r,more);
        return new int[]{less+1,more};
    }

    public static void main(String[] args) {
        int arr[]={8,3,5,6,7,1,4,5,6};
        int[] partition = partition(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
        for (int i = 0; i < partition.length; i++) {
            System.out.println(partition[i]);
        }
    }
}
