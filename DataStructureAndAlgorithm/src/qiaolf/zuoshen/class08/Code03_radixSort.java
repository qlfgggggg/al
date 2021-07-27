package qiaolf.zuoshen.class08;

/**
 * @Description: 基数排序:桶排序的一种    不基于比较的排序
 * Author:qiaolf
 * @Date:2021/3/27 9:36
 **/
public class Code03_radixSort {

    public static void radixSort(int arr[]){
        if (arr==null || arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    /**
     *arr[l....r]排序，最大值的十进制位数maxbits
     * @param arr
     * @param l
     * @param r
     * @param maxbits
     */
    private static void radixSort(int[] arr, int l, int r, int maxbits) {
            final int radix=10;
            int i=0,j=0;
            //有多少个数准备多少个辅助空间
            int help[]=new int[l-r+1];
        for (int k = 1; k <= maxbits; k++) {//有多少位就进出多少次
            //10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int count[]=new int[radix];//0-9    前缀和
            for (int m = l; m <= r; m++) {
                j=getDigit(arr[m],k);//比如125，当k=1时，j=5
                count[j]++;//每一位是j的数有多少个
            }
            //count[i]前缀和
            for (i = 1; i < radix; i++) {
                count[i]=count[i]+count[i-1];
            }
            //将arr数组的数从右往左遍历放入辅助数组
            for (i = r; i >=l ; i--) {
                j=getDigit(arr[i],k);
                help[count[j]-1]=arr[i];
                count[j]--;
            }
            //将辅助数组的数放回原数组
            for (i=l,j=0; i <= r; i++,j++) {
                arr[i]=help[j];
            }
        }

    }


    /**
     * 求数组最大值有多少位
     * @param arr
     * @return
     */
    public static int maxbits(int arr[]){
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int bits=1;
        while ((max=max/10)>0){
            bits++;
        }
        return bits;
    }

    /**
     *
     * @param x  数字
     * @param d  位数  从1开始到最大位数比如125最大位数为3
     * @return   某位的数字大小
     */
    public static int getDigit(int x,int d){
        return (x/((int)Math.pow(10,d-1)))%10;
    }
}
