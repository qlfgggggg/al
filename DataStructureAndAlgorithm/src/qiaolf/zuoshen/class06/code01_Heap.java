package qiaolf.zuoshen.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 顺序存储二叉树(二叉树节点和数组必须是对应的,而且二叉树是完全二叉树,满二叉树一定是完全
 * 二叉树),
 * 树节点间的关系：一棵树的节点在数组中索引位置为index,他的左孩子节点为2*index+1,右孩子节点为
 * 2*index+2,父节点位置index-1/2
 * Author:qiaolf
 * @Date:2021/3/25 9:51
 **/
//优先级队列就是堆实现的，优先级队列和堆是一回事


    /*
    *heapInsert和heapify的调整的时间复杂度都是O(logN)
    *
    *当堆里面索引为index位置的数发生了变化，执行heapInsert（上升）和heapify(下沉)方法(这两个方法只有一个会执行,要么上升，要么下降，也有可能都不执行，不动）
    *
    * */
public class code01_Heap {
    //实现小根堆:很简单，我们不是实现大根堆了吗？直接实现一个比较器,将数值比较反过来
    public static class MyComparetor implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            //本来比较器的比较时return o1 - o2   -1:o1<o2
            //现在我们反着来
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        //优先级队列,如果构造器里不传任何东西，默认是小根堆，小的放上面
//        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //我们写了比较器，大的放上面     大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparetor());

        heap.add(5);
        heap.add(3);
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }

    //大根堆：顺序存储二叉树的每个子树的根节点都比子节点要大，子树根节点最大
    public class MyMaxHeap{
        private int heap[];//堆是数组实现
        private int limit;//数组的大小
        private int heapSize;//堆的大小,堆不一定要把数组沾满
        public MyMaxHeap(int limit){
            heap = new int[limit];
            this.limit=limit;
            heapSize=0;
        }
        //判断堆是否为空
        public boolean isEmpty(){
            return heapSize==0;
        }
        //判断堆是否满了
        public boolean isFull(){
            return heapSize==limit;
        }
        //往堆里存放数据,存放的数据放到数组的最后一位
        public void put(int value){
            if (isFull()){
                throw new RuntimeException("堆满了,不能放数据了");
            }
            heap[heapSize]=value;//往数组里放数据,但是大根堆还需要将数据做出处理
            //往堆里放数据后堆的大小要加1：heapSize++
            //处理加入后数据
            heapInsert(heap,heapSize++);//这里是先使用heapSize,然后才对heapSize加1
        }

        //数组两个数交换
        public void swap(int arr[],int i,int j){
            int tem=arr[i];
            arr[i]=arr[j];
            arr[j]=tem;
        }

        //数据上升
        //调节不参与数据增减
        //将加入的值和他的父节点比较，如果比父节点大，就一直循环和上面的父节点比较
        private void heapInsert(int[] heap, int index) {
            while (heap[index]>heap[(index-1)/2]){
                swap(heap,index,(index-1)/2);
                //交换之后将交换后的索引位置给index
                index=(index-1)/2;
            }
        }
        //从堆里取出最大值，并返回,最大值就是根节点，取出最大值后将最后一个元素放到最大值位置，再进行处理，在堆里找出最大值放在根节点
        public int pop(){
            if (isEmpty()){
                throw new RuntimeException("堆为空,不能取数据了");
            }
            int ans = heap[0];//根节点就放在0位置
            //处理
            //将最后索引位置的值和最大值位置的值交换
            swap(heap,heapSize-1,0);
            //交换后堆的大小减去1
            heapSize--;
            //处理
            heapify(heap,0,heapSize);
            //返回
            return ans;
        }

        //调节不参与数据增减
        //将该位置的值下沉
        private void heapify(int[] heap, int index,int heapSize) {
            int left = 2*index+1;//找出index的做孩子节点
            while (left<heapSize){//left最多只能到达索引heapSize-1位置处，就是最后一个节点
                //找出index位置的左右孩子节点哪个更大
                int largest = heap[left+1] > heap[left] && (left+1) < heapSize ? (left+1) : left;
                largest = heap[index] > heap[largest] ? index : largest;
                if (index==largest){
                    break;//如果index位置的值更大，就跳出循环，不往下进行
                }
                swap(heap,index,largest);
                index = largest;//交换之后将largest作为index
                left = 2*index+1;
            }
        }
    }
}
