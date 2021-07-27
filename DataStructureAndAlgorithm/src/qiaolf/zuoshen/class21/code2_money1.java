package qiaolf.zuoshen.class21;

/**
 * @Description: arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3      ----从左往右的模型
 * Author:qiaolf
 * @Date:2021/7/24 15:13
 **/
public class code2_money1 {

    public static int total(int[] arr,int aim){
        return process(arr,0,aim);
    }

    private static int process(int[] arr, int index, int rest) {

        if (rest<0){
            return 0;
        }
        //从左往右的模型
        if (index==arr.length){
            return rest ==0 ? 1:0;
        }
        int ways = 0;
        ways+=process(arr,index+1,rest-arr[index])+process(arr,index+1,rest);
        return ways;
    }

    public static int total2(int arr[],int aim){
        int N=arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1;
        for (int i = N-1; i >=0 ; i--) {
            for (int j = 0; j <= aim; j++) {
                int ways = 0;
                ways+=dp[i+1][j]+(j-arr[i]>=0 ? dp[i+1][j-arr[i]] : 0);
                dp[i][j]=ways;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,2,5,10,20,50,100};
        System.out.println(total(arr,25));
        System.out.println(total2(arr,25));
    }
}
