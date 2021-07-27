package qiaolf.zuoshen.class09;

/**
 * @Description: 原链表:l1 -> l2 -> l3 -> l4 -> r1 -> r2 -> r3 -> r4
 * 改动后的链表l1 -> r4 -> l2 -> r3 -> l3 -> r2 -> l4 -> r1
 * Author:qiaolf
 * @Date:2021/3/27 19:52
 **/
public class LRTest {
    public static class ListNode{
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode test1(ListNode head){
        if (head==null || head.next==null || head.next.next==null){
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //slow是中间节点，反转链表
        ListNode pre = null,next=null;
        while (slow!=null){
            next=slow.next;
            slow.next=pre;
            pre=slow;
            slow=next;
        }
        //pre相当于反转之后的中间节点后部分的头结点
        ListNode node = head;
        next=null;
        while (node!=null && pre.next!=null){
            next=pre.next;
            pre.next=node.next;
            node.next=pre;
            pre=next;
            node=node.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode te=null;
        te=new ListNode(0);
        te.next=new ListNode(2);
        te.next.next=new ListNode(4);
        te.next.next.next=new ListNode(1);
        te.next.next.next.next=new ListNode(3);
        te.next.next.next.next.next=new ListNode(5);
//        te.next.next.next.next.next.next=new ListNode(7);
        te=test1(te);
        while (te!=null){
            System.out.println(te.val);
            te=te.next;
        }
    }
}
