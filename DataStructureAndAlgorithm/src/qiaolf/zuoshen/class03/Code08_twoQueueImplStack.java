package qiaolf.zuoshen.class03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 两个队列去实现栈
 * 对外提供:出栈pop，压栈push,peek
 * Author:qiaolf
 * @Date:2021/3/23 13:51
 **/
//每次取数据将一个队列的数据除了最后一个都添加到另一个队列，取数据取最后还剩的那个数据
public class Code08_twoQueueImplStack {
    static class MyStack{
        private Queue<Integer> queue;//存数据队列
        private Queue<Integer> help;//帮助队列，

        public MyStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }
        //添加数据
        public void offer(int e){
            queue.offer(e);
        }
        //poll
        public int poll(){
            //取queue队列里的数据，找到还剩一个数据
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            //交换queue和help,不然每次还需要明确queue和help
            Queue<Integer> temp;
            temp = queue;
            queue = help;
            help = temp;
            return ans;
        }
        //peek,看一看栈首元素
        public int peek(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            int ans = queue.peek();
            //交换queue和help,不然每次还需要明确queue和help
            Queue<Integer> temp;
            temp = queue;
            queue = help;
            help = temp;
            return ans;
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.offer(1);
        myStack.offer(2);
        myStack.offer(3);
        myStack.offer(4);
        myStack.offer(5);
        System.out.println(myStack.peek());
        System.out.println(myStack.poll());
        System.out.println(myStack.peek());
    }
}
