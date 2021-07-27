package qiaolf.data.heap;

import java.awt.image.SampleModel;

/**
 * @Description: 堆的实现:这一次来实现小根堆
 * Author:qiaolf
 * @Date:2021/5/21 17:05
 **/
public class MyHeap {
    int heap[];
    int heapSize;
    int limit;

    public MyHeap(int limit) {
        this.heap = new int[limit];
        this.heapSize = 0;
        this.limit = limit;
    }
    private boolean isFull(){
        return heapSize==limit;
    }
    private boolean isEmpty(){
        return heapSize==0;
    }
    private void swap(int[] arr,int i,int j){
        int tem = arr[i];
        arr[i]=arr[j];
        arr[j]=tem;
    }

    //往堆中添加
    public void put(int node){
        if (isFull()){
            return;
        }
        heap[heapSize++]=node;
        heapUp(heap,heapSize-1);
    }
    //小根堆
    private void heapUp(int[] heap, int index) {
        int parent=index>>1;
        while (heap[parent]>heap[index]){
            swap(heap,index,parent);
            index=parent;
            parent=index>>1;
        }
    }
    //弹出堆顶部元素
    public int poll(){
        if(isEmpty()){
            return -1;
        }
        int res=heap[0];
        heap[0]=heap[--heapSize];
        heapDown(heap,0);
        return res;
    }

    private void heapDown(int[] heap, int index) {
        int left = index<<1 + 1;
        while (left<heapSize){
            int small= (left+1) < heapSize && heap[left] <= heap[left+1] ? left : left+1;
            small = heap[small] < heap[index] ? small : index;
            if (small==index){
                break;
            }
            swap(heap,index,small);
            index= small;
            left = index<<1 + 1;
        }
    }

    //堆排序  : 从小到大,小根堆
//    public int[] sort(int[] arr){
//
//    }
}
