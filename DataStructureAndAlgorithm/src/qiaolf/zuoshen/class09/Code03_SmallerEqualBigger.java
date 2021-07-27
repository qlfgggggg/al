package qiaolf.zuoshen.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:  将单向链表按某值划分成左边小、中间相等、右边大的形式
 *1）把链表放入数组里，在数组上做partition（笔试用）
 * 2）分成小、中、大三部分，再把各个部分之间串起来（面试用）
 * Author:qiaolf
 * @Date:2021/3/27 20:37
 **/
public class Code03_SmallerEqualBigger {
    public static class ListNode{
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 1）把链表放入数组里，在数组上做partition（笔试用）
     * @param head
     * @return
     */
    public static ListNode test(ListNode head){
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node!=null){
            list.add(node.val);
            node=node.next;
        }

        Integer array[]=new Integer[list.size()];
        int j=0;
        for (Integer integer : list) {
            array[j]=integer;
            j++;
        }
        partition(array,0,array.length-1);
        node=head;
        for (int i = 0; i < array.length; i++) {
            node.val=array[i];
            node=node.next;
        }
        return head;
    }



    public static void main(String[] args) {
        Integer arr[]={6,7,9,4,6,7,6,9,3,6};
        partition(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"->");
        }
        System.out.println(" ");
        ListNode node=new ListNode(6);
        node.next=new ListNode(7);
        node.next.next=new ListNode(9);
        node.next.next.next=new ListNode(4);
        node.next.next.next.next=new ListNode(6);
        node.next.next.next.next.next=new ListNode(7);
        node.next.next.next.next.next.next=new ListNode(6);
        node.next.next.next.next.next.next.next=new ListNode(9);
        node.next.next.next.next.next.next.next.next=new ListNode(3);
        node.next.next.next.next.next.next.next.next.next=new ListNode(6);
        ListNode test = test(node);
        ListNode test2 = test2(node, 6);
        while (test2!=null){
            System.out.print(test2.val+"->");
            test2=test2.next;
        }
    }

    private static Integer[] partition(Integer[] array, int l, int r) {
        if (l>r){
            return new Integer[]{-1,-1};
        }
        if (l==r){
            return new Integer[]{l,r};
        }
        int less= l-1;
        int more=r;
        int index=l;
        while (index<more){
            if (array[index]<array[r]){
                swap(array,index,less+1);
                less++;
                index++;
            }else if (array[index]==array[r]){
                index++;
            }else{
                swap(array,index,more-1);
                more--;
            }
        }
        swap(array,r,more);
        return new Integer[]{less+1,more};
    }
    public static void swap(Integer[] arr,int i,int j){
        Integer tem=arr[i];
        arr[i]=arr[j];
        arr[j]=tem;
    }


    public static ListNode test2(ListNode head,int pvoit){
        if (head==null){
            return head;
        }
        ListNode sH=null;
        ListNode sT=null;
        ListNode eH=null;
        ListNode eT=null;
        ListNode mH=null;
        ListNode mT=null;
        ListNode next;//保存下一个节点的
        while (head!=null){
            next=head.next;
            if (head.val<pvoit){
                if (sH==null){
                    sH=head;
                    sT=head;
                }else{
                    sT.next=head;
                    sT=head;
                }
            }else if (head.val==pvoit){
                if (eH==null){
                    eH=head;
                    eT=head;
                }else {
                    eT.next=head;
                    eT=head;
                }
            }else {
                if (mH==null){
                    mH=head;
                    mT=head;
                }else {
                    mT.next=head;
                    mT=head;
                }
            }
            head=next;
        }
        if (sH!=null){
            sT.next=eH;
            eT= eH==null ? sT:eT;
        }
        if (eT!=null){
            eT.next=mH;
        }
        return sH!=null ? sH : (eH!=null ? eH : mH);
    }


}
