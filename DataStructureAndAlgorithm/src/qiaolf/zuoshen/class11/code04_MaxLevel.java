package qiaolf.zuoshen.class11;

import qiaolf.zuoshen.class12.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Description: 求二叉树最宽的层有多少个节点
 * Author:qiaolf
 * @Date:2021/5/5 0:16
 **/
public class code04_MaxLevel {

    //解法1：
    public static int maxLevel(Node head){
        if (head==null)
            return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int max = 0;
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNode = 0;
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            if (poll.left!=null){
                queue.add(poll.left);
                //下一层结尾在变化
                nextEnd=poll.left;
            }
            if (poll.right!=null){
                queue.add(poll.right);
                //下一层结尾在变化
                nextEnd=poll.right;
            }
            curLevelNode++;
            if (poll==curEnd){//当前层已经结束
                //求出当前层最大宽度
                max=Math.max(max,curLevelNode);
                //因为要到下一层了，重置当前层节点数
                curLevelNode=0;
                //因为要到下一层了，当前层结尾变为下一层结尾
                curEnd=nextEnd;
            }
        }
        return max;
    }

    //解法2：
    public static int maxLevelUseMap(Node head){
        if (head==null)
            return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int max = 0;
        int curLevel = 1;  //当前你正在统计第几层的宽度
        int curLevelNode = 0;  //当前层的节点数
        //当前节点在哪一层
        Map<Node,Integer> map = new HashMap<>();
        map.put(head,1);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            Integer curNodeLevel = map.get(poll);  //当前节点在哪一层
            if (poll.left!=null){
                map.put(poll.left,curNodeLevel+1);
                queue.add(poll.left);
            }
            if (poll.right!=null){
                map.put(poll.right,curNodeLevel+1);
                queue.add(poll.right);
            }
            //当前你正在统计的层和弹出的节点是在一层   节点数++
            if (curLevel==curNodeLevel){
                curLevelNode++;
            }else{//否则
                max=Math.max(max,curLevelNode);
                curLevel++;
                curLevelNode=1;  //为什么是1？因为下一层一开始就queue.poll()弹出来一个了，要算上那一个
            }

        }
        //有可能是在最后一层：curLevel==curNodeLevel  queue.isEmpty()  队列已经为空了，却没有max
        max=Math.max(max,curLevelNode);
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxLevelUseMap(head) != maxLevel(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
