package qiaolf.zuoshen.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 双链表实现栈和队列
 * Author:qiaolf
 * @Date:2021/3/12 0:34
 **/
public class code03_DoubleToStackAndQueue {
    public static class DoubleNode<T>{
        public T val;
        public DoubleNode next;
        public DoubleNode last;
        public DoubleNode(T val) {
            this.val = val;
        }
    }
    public static class DoubleEndsQueue<T>{
        public DoubleNode<T> head;
        public DoubleNode<T> tail;

        /**
         * 插入节点在最前面，把插入的节点作为head
         * @param value
         */
        public void addFromHead(T value){
            DoubleNode<T> cur=new DoubleNode<>(value);
            if (head==null){//这里头结点和尾结点是一个节点
                head=cur;
                tail=cur;
            }else{//头结点一直往前走
                cur.next=head;
                head.last=cur;
                head=cur;
            }
        }

        /**
         *将节点插在尾部，作为尾结点
         * @param value
         */
        public void addFromButtom(T value){
            DoubleNode<T> cur=new DoubleNode<>(value);
            if (head==null){
                head=cur;
                tail=cur;
            }else{
                cur.last=tail;
                tail.next=cur;
                tail=cur;
            }
        }
        public T popFromHead(){
            if (head==null){
                return null;
            }
            //弹出一个头节点，头结点改变,需要返回头结点的值
            DoubleNode<T> cur=head;
            if (head==tail){
                head=null;
                tail=null;
            }else{
                head=head.next;
                //将上一个被弹出的头结点断开
                cur.next=null;
                cur.last=null;
            }
            return cur.val;
        }
        public T popFromButtom(){
            if (head==null){
                return null;
            }
            DoubleNode<T> cur=tail;
            if (head==tail){
                head=null;
                tail=null;
            }else{
                tail=tail.last;
                cur.next=null;
                cur.last=null;
            }
            return cur.val;
        }
        public boolean isEmpty(){
            return head==null;
        }
    }

    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue;
        public MyStack(){
            queue=new DoubleEndsQueue<T>();
        }
        public void push(T value){
            queue.addFromButtom(value);
        }
        public T pop(){
            return queue.popFromButtom();
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T>{
        private DoubleEndsQueue<T> queue;
        public MyQueue(){
            queue=new DoubleEndsQueue<>();
        }
        public void push(T value){
            queue.addFromHead(value);
        }
        public T poll(){
            return queue.popFromButtom();
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static boolean isEqual(Integer a,Integer b){
        if (a==null && b!=null){
            return false;
        }
        if (a!=null && b==null){
            return false;
        }
        if (a==null && b==null){
            return true;
        }
        return a.equals(b);
    }

    public static void main(String[] args) {
        int oneTestNum=100;
        int value=10000;
        int testTimes=10000;
        for (int i = 0; i < testTimes; i++) {
            MyQueue<Integer> myQueue = new MyQueue<>();
            MyStack<Integer> myStack = new MyStack<>();
            //java对栈没有做实现,我这里用链表代替
            Queue<Integer> queue = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < oneTestNum; j++) {
                int nums = (int) (Math.random()*value);
                if (stack.isEmpty()){
                    stack.push(nums);
                    myStack.push(nums);
                }else{
                    if (Math.random()<0.5){
                        stack.push(nums);
                        myStack.push(nums);
                    }else{
                        if (!isEqual(stack.pop(),myStack.pop())){
                            System.out.println("oppos!");
                        }
                    }
                }
                int num = (int) (Math.random()*value);
                if (stack.isEmpty()){
                    queue.offer(num);
                    myQueue.push(num);
                }else{
                    if (Math.random()<0.5){
                        queue.offer(num);
                        myQueue.push(num);
                    }else{
                        if (!isEqual(queue.poll(),myQueue.poll())){
                            System.out.println("oppos!");
                        }
                    }
                }
            }
        }
        System.out.println("finish");
    }
}
