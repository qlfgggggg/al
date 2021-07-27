package qiaolf.zuoshen.class19_2;


/**
 * @Description: 给定两个长度都为N的数组weights和values,
 * weights[i]和values[i]分别代表 i号物品的重量和价值.
 * 给定一个正数bag，表示一个载重bag的袋子,
 * 你装的物品不能超过这个重量.
 * 返回你能装下最多的价值是多少?
 * Author:qiaolf
 * @Date:2021/7/8 16:32
 **/
public class code1_bag {


    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    public static int maxValue1(int[] weights,int[] values,int bags){
        if (weights==null || weights.length==0 || values==null || values.length != weights.length || bags<0){
            return -1;
        }
        return process1(bags,weights,values,0);
    }

    /**
     *前面的索引位置的东西装还时没装已经过去了，我们不能决定，我们只能从index位置开始决定
     * @param bags  背包剩余的容量
     * @param weights   宝物的重量数组
     * @param values   宝物的价值数组
     * @param index      宝物在数组里的索引
     * @return
     */
    private static int process1(int bags, int[] weights, int[] values,int index) {
        //当背包剩余的容量小于0
        if (bags<0){
            return -1;
        }

        //宝物的索引已经超出宝物数组的索引范围
        if (index==values.length){
            return 0;
        }

        //背包没有装value[index]的宝物
        int p1 = process1(bags,weights,values,index+1);
        int p2 = 0;
        //背包装了value[index]的宝物
        int p3 = process1(bags-weights[index], weights, values, index+1);
        //装了weights[index]之后,并且背包容量还有，大于等于0,p2的价值
        if (p3!=-1){
            p2 = p3+values[index];
        }
        return Math.max(p1,p2);
    }

    public static int dp(int weights[],int values[],int bags){
        if (weights==null || weights.length==0 || values==null || values.length != weights.length || bags<0){
            return -1;
        }
        int N = weights.length;
        //这张表的含义是
        int dp[][]=new int[N+1][bags+1];
        for (int index=N-1;index>=0;index--){
            //当宝物的索引在index时,背包的剩余容量在0~bags
            for (int rest=0;rest<=bags;rest++){
                int p1 = dp[index+1][rest];//背包
                int p2 = 0;
                int next = rest-weights[index] < 0 ? -1 : dp[index+1][rest-weights[index]];
                if (next!=-1){
                    p2 = values[index] + next;
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        //这个动态规划缓存的含义是什么？   在每个位置能装的最大价值，什么时候能装最多，当在0索引位置，且背包容量为bag时能装最多
        return dp[0][bags];
    }



}
