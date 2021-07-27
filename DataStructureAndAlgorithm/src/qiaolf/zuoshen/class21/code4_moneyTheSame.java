package qiaolf.zuoshen.class21;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * Author:qiaolf
 * @Date:2021/7/27 10:47
 **/
public class code4_moneyTheSame {
    public static int total(int arr[],int aim){
        if (arr==null || arr.length==0 || aim<0){
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        int size = map.size();
        int[] money = new int[size];
        int[] zhang = new int[size];
        int i=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            money[i]= entry.getKey();
            zhang[i++]= entry.getValue();
        }
        return process(money,zhang,0,aim);
    }

    private static int process(int[] money, int[] zhang, int index, int rest) {
        if (index==money.length){
            return rest==0 ? 1:0;
        }
        int ways = 0;
        for (int i = 0; i <= zhang[index] && rest-money[index]*i>=0; i++) {
            ways+=process(money,zhang,index+1,rest-money[index]*i);
        }
        return ways;
    }
    public static int dpp(int arr[],int aim){
        if (arr==null || arr.length==0 || aim<0){
            return 0;
        }

        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        int size = map.size();
        int[] money = new int[size];
        int[] zhang = new int[size];
        int i=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            money[i]= entry.getKey();
            zhang[i++]= entry.getValue();
        }
        int dp[][] = new int[size+1][aim+1];
        dp[size][0] = 1;
        for (int j = size-1; j>=0 ; j--) {
            for (int k = 0; k <= aim ; k++) {
                int ways = 0;
                for (int l = 0; l <= zhang[j] && money[j]*l<=k; l++) {
                    ways+=dp[j+1][k-money[j]*l];
                }
                dp[j][k]=ways;
            }
        }

        return dp[0][aim];
    }


    public static int dpp2(int arr[],int aim){
        if (arr==null || arr.length==0 || aim<0){
            return 0;
        }

        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        int size = map.size();
        int[] money = new int[size];
        int[] zhang = new int[size];
        int i=0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            money[i]= entry.getKey();
            zhang[i++]= entry.getValue();
        }
        int dp[][] = new int[size+1][aim+1];
        dp[size][0] = 1;
        for (int j = size-1; j>=0 ; j--) {
            for (int k = 0; k <= aim ; k++) {
                int ways = 0;
                for (int l = 0; l <= zhang[j] && money[j]*l<=k; l++) {
                    ways+=dp[j+1][k-money[j]*l];
                }
                dp[j][k]=ways;
            }
        }
        return dp[0][aim];
    }
}
