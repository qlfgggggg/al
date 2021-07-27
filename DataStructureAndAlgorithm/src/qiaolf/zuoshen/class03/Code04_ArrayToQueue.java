package qiaolf.zuoshen.class03;

/**
 * @Description: 数组实现栈和队列
 * Author:qiaolf
 * @Date:2021/3/18 18:10
 **/
public class Code04_ArrayToQueue {

    public static class MyQueue{
        private int[] arr;
        private int pushi;//end
        private int polli;//begin
        private int size;//
        private int limit;//最大容量

        public MyQueue(int limit){
            this.arr=new int[limit];
            this.pushi=0;
            this.polli=0;
            this.size=0;
            this.limit=limit;
        }
        public void push(int value) {
            if (size==limit){
                throw new RuntimeException("队列满了,不能再添加数据了");
            }
            size++;
            arr[pushi]=value;
            //本来队列没有长度的限制的话，这里应该是pushi++,但是这里队列长度有限，我们用环形数组实现队列
            pushi=next(pushi);
        }

        public int poll(){
            if (size==0){
                throw new RuntimeException("队列为空,不能出队列数据");
            }
            size--;
            int red = arr[polli];
            polli=next(polli);
            return red;
        }

        private int next(int i){
            //每次push,或者poll时,下标就向后移动以为，但是下标最大值只能是limit-1
            return i<limit-1 ? i+1 : 0;
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(10);
        for (int i = 0; i < 10; i++) {
            myQueue.push(i);
        }
        for (int i = 0; i < 8; i++) {
            myQueue.poll();
        }
        for (int i = 0; i < 5; i++) {
            myQueue.push(i);
        }

        for (int i = 0; i < 6; i++) {
            System.out.println(myQueue.poll());
        }
    }
}
