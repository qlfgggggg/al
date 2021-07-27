package qiaolf.zuoshen.class21;

/**
 * @Description: arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * Author:qiaolf
 * @Date:2021/7/24 15:51
 **/
public class code3_moneyNoLimit {
    public static int total(int arr[],int aim){
        //aim==0也可以。所有钱都选0张
        if (arr==null || arr.length==0 || aim<0){
            return 0;
        }
        return process1(arr,0,aim);
    }

    private static int process1(int[] arr, int index, int rest) {
//        if (rest<0){
//            return 0;
//        }
//        if (rest==0){
//            return 1;
//        }
        if (index==arr.length){
            return rest==0 ? 1 : 0;
        }
        int ways = 0;
        //有枚举
        for (int i = 0; arr[index]*i<=rest; i++) {
            ways+=process1(arr,index+1,rest-arr[index]*i);
        }
        return ways;
    }

    public static int dp(int[] arr,int aim){
        if (arr.length==0 || arr==null || aim<0){
            return 0;
        }
        int N = arr.length;
        int dp[][] = new int[N+1][aim+1];
        dp[N][0]=1;
        //有枚举
        for (int i = N-1; i >=0; i--) {
            for (int j = 0; j <= aim; j++) {
                int ways = 0;
                for (int k = 0; k*arr[i]<=j; k++) {
                    ways+=dp[i+1][j-arr[i]*k];
                }
                dp[i][j]=ways;
            }
        }
        return dp[0][aim];
    }

    public static int dpp(int arr[],int aim){
        if (arr.length==0 || arr==null || aim<0){
            return 0;
        }
        int N = arr.length;
        int dp[][] = new int[N+1][aim+1];
        dp[N][0]=1;
        for (int i = N-1; i >=0; i--) {
            for (int j = 0; j <= aim ; j++) {
                dp[i][j] = (j-arr[i]>=0) ? (dp[i][j-arr[i]]+dp[i+1][j]) : dp[i+1][j];
            }
        }
        return dp[0][aim];
    }
}
