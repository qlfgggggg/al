package qiaolf.zuoshen.class19_2;

/**
 * @Description: 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * Author:qiaolf
 * @Date:2021/7/12 9:15
 **/
public class code2_bag {

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue1(bag, weights, values));
        System.out.println(dp(bag, weights, values));
    }

    /**
     *
     * @param bag
     * @param weights
     * @param values
     * @return
     */
    public static int maxValue1(int bag,int weights[],int values[]){
        if (weights.length!=values.length || values.length<1 || weights.length<1 || bag<0){
            return 0;
        }
        return process1(bag,weights,values,0);
    }

    private static int process1(int bag, int[] weights, int[] values, int index) {
        if (bag<0){
            return -1;
        }
        if (index==values.length){
            return 0;
        }
        int p1 = process1(bag, weights, values, index+1);
        int p2 = 0;
        int next = process1(bag-weights[index],weights,values,index+1);
        if (next!=-1){
            p2 = values[index] + next;
        }

        return Math.max(p1,p2);
    }

    public static int dp(int bag,int weights[],int values[]){

        if (weights.length!=values.length || values.length<1 || weights.length<1 || bag<0){
            return 0;
        }

        int N = weights.length;
        int dp[][] = new int[N+1][bag+1];
        for (int index = N-1; index >=0; index--) {
            //每当选择一个宝物索引都可能对应背包容量从0~bag
            for (int rest = 0; rest <= bag ; rest++) {
                int p1 = dp[index+1][rest];
                int p2 = 0;
                int next = rest-weights[index]<0 ? -1 : dp[index+1][rest-weights[index]];
                if (next!=-1){
                    p2 = values[index]+next;
                }
                //怎么找出从index位置开始并且背包还剩rest的最大价值？关键在于index位置要还是不要
                dp[index][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

}
