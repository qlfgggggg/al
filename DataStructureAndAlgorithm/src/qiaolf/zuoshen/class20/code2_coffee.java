package qiaolf.zuoshen.class20;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Description: 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 * Author:qiaolf
 * @Date:2021/7/22 23:09
 **/
public class code2_coffee {
    /**
     * 怎么解答这道题？
     * 关键是：首先把每个人喝完咖啡的时间都放在一个数组中，这个数组是从小到大来排列的，为什么要用到这个数组呢?
     * 洗咖啡杯就是从这个数组里来挑选，从小到大来挑选数组中的咖啡被来进行清洗，洗咖啡杯既可以用咖啡机，也可以使用
     * 让咖啡被自动挥发的方法，自动挥发所有的咖啡杯是并行的，用咖啡机清洗只能是串行的，因为只有一台咖啡清洗机
     * 怎么将咖啡被喝完的时间从小到大排放在数组中呢？要用到小根堆？小根堆里存放的是咖啡
     */

    /**
     * 咖啡机可以被使用时的类  ： 咖啡机可以被使用的开始时间，咖啡机冲一杯咖啡的时间
     */
    public class CofeeTime implements Comparable<CofeeTime> {
        public int  beginTime;//某个咖啡机可以被使用的们开始时间
        public int time;//某个咖啡机冲一杯咖啡的时间
        //小根堆比较大小谁的结束时间早，谁放到小根堆的堆顶
//        @Override
//        public int compare(CofeeTime o1, CofeeTime o2) {
//            return (o2.beginTime+o2.time) - (o1.beginTime+o1.time);
//        }

        @Override
        public int compareTo(CofeeTime o) {
            return (this.beginTime+this.time)-(o.beginTime+o.time);
        }
    }

    /**
     * 给定咖啡机冲泡时间数组，和一共有多少个人，生成一个数组，这个数组就是每个人喝完咖啡的时间数组
     * @param arr
     * @param N
     * @return
     */
    public int[] endTimeTable(int arr[],int N){
        int[] res = new int[N];
        PriorityQueue<CofeeTime> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            CofeeTime cofeeTime = new CofeeTime();
            cofeeTime.time=arr[i];
            cofeeTime.beginTime=0;
            queue.add(cofeeTime);
        }
        for (int i = 0; i < res.length; i++) {
            CofeeTime poll = queue.poll();
            res[i]=poll.beginTime+poll.time;
            poll.beginTime=res[i];
            queue.add(poll);
        }
        return res;
    }

    /**
     *
     * @param endTimeTable
     * @param index 清洗第index个杯子
     * @param free 咖啡清洗机可以自由使用的时间  这个时间可能在等待有脏的咖啡杯，也可能脏的咖啡杯在等待自由时间
     * @param a 清洗需要用的时间
     * @param b  自然挥发需要用的时间
     * @return
     */
    public int minEndTime(int[] endTimeTable,int index,int free,int a,int b){
        if (index==endTimeTable.length){
            return 0;
        }
        //第index个咖啡被可能被清洗
        int self=Math.max(endTimeTable[index],free) + a;//比较咖啡杯被用完时间 和 咖啡清洗机自由时间那个大，哪个大就选则哪个时间来进行咖啡机清洗
        int p1 = minEndTime(endTimeTable,index+1,self,a,b);
        int t1 = Math.max(self,p1);

        //第index选择自然挥发
        int self1 = endTimeTable[index] + b;
        int p2 = minEndTime(endTimeTable,index+1,free,a,b);
        int t2 = Math.max(self1,p2);

        return Math.min(t1,t2);
    }


    public int minEndTime2(int[] endTimeTable,int a,int b){

        //由于递归函数有两个可变参数，所以我们设计dp表
        int N = endTimeTable.length;//0-N
        //设计最大的咖啡清洗机的自由可以被用时间======咖啡杯全部用咖啡清洗机来进行清洗，并且是串行清洗的
        int maxFree = 0;
        for (int i = 0; i < N; i++) {
            maxFree += Math.max(endTimeTable[i],maxFree)+a;//Math.max(endTimeTable[i],maxFree)：选择咖啡清洗机是自由时间  和 咖啡杯被用完时间较大的那个作为清洗咖啡杯开始时间
        }
        int[][] dp = new int[N+1][maxFree+1];
        for (int index = N-1; index>=0; index--) {
            for (int j = 0; j <=maxFree ; j++) {
                int p1 = Math.max(endTimeTable[index],j)+a;
                int p2 = p1>=maxFree ? 0 : dp[index+1][p1];
                int p3 = Math.max(p1,p2);

                int t1 = endTimeTable[index] + b;
                int t2 = dp[index+1][j];
                int t3 = Math.max(t1,t2);

                dp[index][j]=Math.min(p3,t3);
            }
        }

        return dp[0][0];
    }


    public static void main(String[] args) {
        int N = 10;
        int[] arr1 = new int[]{3,8,27};
        code2_coffee coffee = new code2_coffee();
        int[] ints = coffee.endTimeTable(arr1, N);
        int i = coffee.minEndTime(ints, 0, 0, 2, 15);
        int i2 = coffee.minEndTime2(ints,2, 15);
        System.out.println(i);
        System.out.println(i2);
    }
}
