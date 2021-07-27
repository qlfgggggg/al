package qiaolf.zuoshen.class03;

import javax.swing.*;
import java.util.Stack;

/**
 * @Description: 用两个栈去实现队列:stackPush(放数据的栈),stackPop(取数据的栈)
 * 向外提供的方法：add,poll,peek,
 * 对内提供的方法:pushToPop(判断pop是否非空)
 * 每次添加数据是将数据添加到stackPush,添加的同时pushToPop(判断pop是否非空),
 * 每次poll  pushToPop
 * Author:qiaolf
 * @Date:2021/3/23 13:50
 **/
public class code07_twoStackImplQueue {
    public static class MyQueue{
        //两个栈，一个栈是存放数据，一个栈是出数据的地方
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public MyQueue() {
            stackPop = new Stack<>();
            stackPush = new Stack<>();
        }

        //push栈往pop栈倒数据,这个不能让外部调用
        private void pushToPop(){
            //只有pop栈为空才倒数据,一次将数据倒完，栈底元素变为栈顶元素
            if (stackPop.isEmpty()){
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }
        //add
        public void add(int value){
            stackPush.push(value);
            pushToPop();
        }

        //poll
        public int poll(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return stackPop.pop();
        }

        //peek
        public int peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()){
                throw new RuntimeException("queue is empty");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
            MyQueue myQueue = new MyQueue();
            myQueue.add(1);
            myQueue.add(2);
            myQueue.add(3);
            myQueue.add(4);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());
        myQueue.add(5);
        myQueue.add(6);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.peek());

    }
}
