package qiaolf.zuoshen.class14;

import java.util.PriorityQueue;

/**
 * @Description: 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 *
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 *
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 *输入一个数组，返回分割的最小代价。
 * Author:qiaolf
 * @Date:2021/4/5 10:30
 **/
public class tanxin3 {
    //用哈夫曼树来解决,实现哈夫曼树要用到优先级队列:   哈夫曼树的思想就是每一步代价最少，在步数一定的情况下
    public static int lessMoney(int arr[]){
        if (arr==null || arr.length==0){
            return 0;
        }
        //小根堆，优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int total=0;
        while (queue.size()>1){
            int i=queue.poll()+queue.poll();
            total+=i;
            queue.add(i);
        }
//        Integer peek = queue.peek();
        return total;
    }

    public static void main(String[] args) {
        int arr[]={10,20,30};
        System.out.println(lessMoney(arr));
    }
}
