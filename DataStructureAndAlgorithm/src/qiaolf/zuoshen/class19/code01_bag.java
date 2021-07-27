package qiaolf.zuoshen.class19;

/**
 * @Description: 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * Author:qiaolf
 * @Date:2021/4/2 20:50
 **/
public class code01_bag {
    public static int bag(int weigths[],int values[],int bag){
        if (weigths==null || values==null || weigths.length!=values.length || weigths.length==0){
            return 0;
        }
        return process(weigths,values,0,bag);
    }

    //前面装多少东西不管，我们只管从索引index位置开始最多可以装价值多少东西
    private static int process(int[] weigths, int[] values, int index, int bag) {
        //背包可以装重量为0，价值为其他的物品
        if (bag<0){
          return -1;//知道这里为什么是-1吗？为了下面的判断
        }
        //当索引位置来到weigths.length位置时就没有东西可以装了
        if (index==weigths.length){
            return 0;
        }
        //我们可以选择index位置装入背包还可以选择不装入背包
        //不装入背包
        int process = process(weigths, values, index + 1, bag);
        //装入背包
        //这种装入的方式不对，因为有可能在装入index时bag - weigths[index]<0
        //int value=values[index]+process(weigths, values, index + 1, bag - weigths[index]);
        int next=process(weigths, values, index + 1, bag - weigths[index]);
        int value=0;
        //不等于-1，说明bag - weigths[index]不小于0,我们在递归开头有说明
        if (next!=-1){
            value=values[index]+next;
        }
        return Math.max(value,process);
    }

    public static void main(String[] args) {
        int weights[]={0,2,4,3,4,1,0};
        int values[]={29,17,5,21,33,1,15};
        int bag=10;
        System.out.println(bag(weights,values,10));
        System.out.println(dp(weights,values,10));
    }

    //严格表结构
    //动态规划解法：
    public static int dp(int weigths[],int values[],int bag){
        //当index==weigths.length时下标越界,下标越界的那一行都为0,下标越界所以对应的二维数
        int dp[][]=new int[weigths.length+1][bag+1];

        //将dp表填好
//        最后一行都为0，java数组默认不设置都为0，不用我们再弄了
        for (int index=weigths.length-1;index>=0;index--) {
            for (int rest=0; rest <=bag ; rest++) {
                int p1 = dp[index+1][rest];
                int p2=0;
                int next= rest-weigths[index] < 0 ? -1 : dp[index+1][rest-weigths[index]];
                if (next!=-1){
                    p2=next+values[index];
                }
                dp[index][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }
}
