package qiaolf.zuoshen.class03;

/**
 * @Description:
 * Author:qiaolf
 * @Date:2021/3/19 17:03
 **/
public class Code05_ArrayToStack {

    public static class MyStack{
        private int size;
        private int limit;
        private int end;
        private int arr[];
        public MyStack(int limit){
            this.end=0;
            this.limit=limit;
            this.size=0;
            arr=new int[limit];
        }

        //压栈
        public void pull(int val){
            if (size==limit){
                throw new RuntimeException("栈满了，不要添加数据了");
            }
            size++;
            arr[end]=val;
            end++;
        }

        //出栈
        public int poll(){
            if (size==0){
                throw new RuntimeException("栈是空的，不要再出栈了");
            }
            size--;
            end--;
            return arr[end];
        }

        public boolean isEmpty(){
            return size==0;
        }

        public boolean isFull(){
            return size==limit;
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(10);
        for (int i = 0; i < 10; i++) {
            myStack.pull(i);
        }
        for (int i = 0; i < 7; i++) {
            System.out.println(myStack.poll());;
        }

        for (int i = 0; i < 10; i++) {
            if (myStack.isFull())
                break;
            myStack.pull(i);
        }

        for (int i = 0; i < 12; i++) {
            if (myStack.isEmpty())
                break;
            System.out.println(myStack.poll());
        }
    }
}
