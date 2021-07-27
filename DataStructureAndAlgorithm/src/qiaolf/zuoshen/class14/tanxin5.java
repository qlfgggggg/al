package qiaolf.zuoshen.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 * Author:qiaolf
 * @Date:2021/4/5 11:53
 **/
public class tanxin5 {
        //思路：将项目按照成本放进小根堆里(按照成本最小排序),弹出项目按照利润进入大根堆(按照利润最大排序),
        //项目
        public class Program{
            public int cost;//成本
            public int profits;//利润

            public Program(int cost, int profits) {
                this.cost = cost;
                this.profits = profits;
            }
        }

        //成本比较器
        public  class CostComparator implements Comparator<Program>{

            @Override
            public int compare(Program o1, Program o2) {
                return o1.cost-o2.cost;//o1<o2
            }
        }

        //利润比较器
        public class ProfitsComparator implements Comparator<Program>{

            @Override
            public int compare(Program o1, Program o2) {
                return o2.profits-o1.profits;
            }
        }

    /**
     *
     * @param Capital  成本
     * @param Profits  利润
     * @param k   串行最多做的项目：一个项目做完(做完立即拿到钱)才能做第二个项目
     * @param W   你的本金
     * @return
     */
    public  int findMaximizedCapital(int k,int W,int[] Profits,int[] Capital){
            //将成本放入小根堆
            PriorityQueue<Program> cp = new PriorityQueue<>(new CostComparator());
            Program[] programs = new Program[Profits.length];
            //将项目按照成本放入小根堆？为什么？因为你做一个项目肯定要看你的本金能不能大于成本吧
            for (int i = 0; i < Capital.length; i++) {
                //一定要项目成本和利润对应上
                programs[i]=new Program(Capital[i], Profits[i]);
                cp.add(programs[i]);
            }
            PriorityQueue<Program> pp = new PriorityQueue<>(new ProfitsComparator());
            while (k>0){
                while (!cp.isEmpty() && cp.peek().cost<=W){
                    pp.add(cp.poll());
                }
                if (pp.isEmpty()){
                    return W;
                }
                W += pp.poll().profits;
                k--;
            }
            return W;
    }

    public static void main(String[] args) {
        tanxin5 tanxin5 = new tanxin5();
        int maximizedCapital = tanxin5.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1});
        System.out.println(maximizedCapital);
    }

}
