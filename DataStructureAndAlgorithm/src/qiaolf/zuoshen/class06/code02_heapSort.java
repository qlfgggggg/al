package qiaolf.zuoshen.class06;

import java.util.Comparator;

/**
 * @Description: 堆排序
 * Author:qiaolf
 * @Date:2021/3/25 14:10
 **/
public class code02_heapSort {

    //数组两个数交换
    public void swap(int arr[],int i,int j){
        int tem=arr[i];
        arr[i]=arr[j];
        arr[j]=tem;
    }
    //将index索引位置的数上行到该去的位置和父节点进行比较,直到往上前进不动
    public void heapInsert(int heap[],int index){
        while (heap[index]>heap[(index-1)/2]){
            swap(heap,index,(index-1)/2);
            //交换之后将交换后的索引位置给index
            index=(index-1)/2;
        }
    }

    //将index索引位置的数下探，去到该去的位置上和左右孩子做比较,直到往下前进不动
    public void heapify(int heap[],int index,int heapSize){//heapSize堆的大小
        int left = 2*index+1;
        while (left<heapSize){
            int largest = (left+1)<heapSize && heap[left+1] > heap[left] ? left+1 : left;
            largest = heap[largest] > heap[index] ? largest : index;
            if (index==largest){
                break;
            }
            swap(heap,index,largest);
            index=largest;
            left=2*index+1;
        }
    }
    public void heapSort(int heap[]){
        if (heap==null || heap.length<2){
            return;
        }
        //先将堆变成大根堆,大根堆的根节点是最大的，将最大值放到数组的最后位置
        //O(N*logN)
//        for (int i = 0; i < heap.length; i++) {//O(N)
//           heapInsert(heap,i); //O(logN)
//        }


        //O(N)时间复杂度,所以我们再一次归并排序用这个:解释在体系班，堆和堆排序那节时间在将近结尾
        for (int i = heap.length-1; i >=0 ; i--) {
            heapify(heap,i,heap.length);
        }
        int heapSize = heap.length;
        swap(heap,0,--heapSize);
        while (heapSize>0){
//            //每次将根节点和heapSize-1索引位置互换,换过后，前面的数都比该位置的数小
//            swap(heap,0,heapSize);
//            //heapSize位置换到0位置的数要进行heapify下探
//            heapify(heap,0,heapSize);
//            //下探之后又变成大根堆
            heapify(heap,0,heapSize);
            swap(heap,0,--heapSize);
        }
    }

    //比较器
    public class MyComparetor implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }

    public static void main(String[] args) {
        code02_heapSort s = new code02_heapSort();
        int arr[] = {3,8,1,5,2,9,8,5,2,1};
//        int arr[] = {2,4,6,8,1,6};
        s.heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
