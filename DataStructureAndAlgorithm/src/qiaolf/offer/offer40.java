package qiaolf.offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * Author:qiaolf
 * @Date:2021/5/21 14:45
 **/
public class offer40 {
    //堆
    /**
     * 顺序存储二叉树(用数组来实现):当前节点的左孩子：2n+1,右孩子2n+2,父节点n/2
     */
    //小根堆
   public class MyCompatator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }


    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();//默认是小根堆
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int[] res=new int[k];
        for (int i = 0; i < k; i++) {
            res[i]=queue.poll();
        }
        return res;
    }




    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(20);
        myHeap.put(1);
        myHeap.put(3);
        myHeap.put(4);
        myHeap.put(2);
        myHeap.put(7);
        myHeap.put(5);
        myHeap.put(6);
        myHeap.put(8);
        for (int i = 0; i < myHeap.heapSize; i++) {
            System.out.println(myHeap.heap[i]);
        }
        System.out.println("===================");
        for (int i = 0; i < 3; i++) {
            System.out.println(myHeap.poll());
        }
        System.out.println("================");
        for (int i = 0; i < myHeap.heapSize; i++) {
            System.out.println(myHeap.heap[i]);
        }

    }


    //大根堆，顺序存储二叉树的每个根节点都比孩子节点要大
    public static class MyHeap{
        int heap[];//堆是有数组实现
        int limit;//数组的大小
        int heapSize;//堆的大小

        public MyHeap(int limit){
            this.heap=new int[limit];
            this.limit=limit;
            this.heapSize=0;
        }

        //判断堆是否满了
        public boolean isFull(){
            return heapSize==limit;
        }
        //判断堆是否为空
        public boolean isEmpty(){
            return heapSize==0;
        }
        //往堆里存放数据
        public void put(int node){
            if (isFull()){
                throw new RuntimeException("堆满了，不能再存放数据了");
            }
            //将数据放在最后
            heap[heapSize++]=node;
            heapRise(heap,heapSize-1);
        }

        //调整顺序，上升，要和父节点进行比较
        private void heapRise(int[] heap, int i) {
            //父节点
//            int next=heap[i/2];
            int next=i/2;
            while (heap[i]>heap[next]){
                swap(heap,i,next);
                i=next;
                next=i/2;
            }
        }

        private void swap(int[] arr,int i,int j){
            int tem = arr[i];
            arr[i]=arr[j];
            arr[j]=tem;
        }
        //弹出堆顶部元素
        public int poll(){
            if (isEmpty()){
                throw new RuntimeException("堆是空的,请添加元素");
            }
            //堆顶部元素，最大值
            int value = heap[0];
            //将堆最后一个元素和堆顶部
            heap[0]=heap[--heapSize];
            heapDown(heap,0);
            return value;
        }

        private void heapDown(int[] heap, int i) {
            int left=2*i+1;
            while (left<heapSize){
                int large= (left+1)<heapSize && heap[left]>=heap[left+1] ? left : left+1;
                large = heap[large]>heap[i] ? large : i;
                if (large==i){
                    break;
                }
                swap(heap,large,i);
                i=large;
                left=2*i+1;
            }
        }
    }



}
