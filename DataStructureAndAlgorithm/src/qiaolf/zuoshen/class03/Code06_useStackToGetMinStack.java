package qiaolf.zuoshen.class03;

import java.util.Stack;

/**
 * @Description: 可以使用现成的数据结构实现
 * Author:qiaolf
 * @Date:2021/3/19 17:37
 **/
public class Code06_useStackToGetMinStack {

    public class MyStack1{
        //用两个栈实现最小栈：具备栈的基本功能，put,poll,外加getMIn()得到最小值
        private Stack<Integer> dataStack;//存放数据的栈
        private Stack<Integer> minStack;//存放最小值的栈


        public int getMin(){
            //最小值的栈不只一个元素，越往栈顶放的元素越小
            if (minStack.isEmpty()){
                throw new RuntimeException("最小栈中没有元素了");
            }
            //这只是查看栈中最小元素，因为栈中最小元素都放在栈顶，所以peek一下
            return minStack.peek();
        }
        //push
        public void push(int value){
            //如果最小栈的元素为空，就将数据放入
            if (minStack.isEmpty()){
                minStack.push(value);
            }
            //如果待放入元素小于等于最小栈栈顶元素，放入最小栈
            if (value<=getMin()){
                minStack.push(value);
            }
            dataStack.push(value);
        }
        //pop
        public int pop(){
            if (minStack.isEmpty()){
                throw new RuntimeException("栈空了");
            }
//            if (dataStack.peek()==getMin()){
//                minStack.pop();
//                return dataStack.pop();
//            }else{
//                return dataStack.pop();
//            }
            int re = dataStack.pop();
            if (re==getMin()){
                minStack.pop();
            }
            return re;
        }
    }


}
