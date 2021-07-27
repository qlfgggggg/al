package qiaolf.leetcode;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/3 21:59
 **/
public class solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1,t2=l2,t4=new ListNode(0);
        int m = 0;
        ListNode t3=t4;
        while (t1!=null && t2 != null){
            t3.val=(t1.val+t2.val)%10+m;
            m= (t1.val+t2.val)>=10 ? 1 : 0;
            t1=t1.next;
            t2=t2.next;
            t3=t3.next;
        }
        while (t1!=null){
            t3.val=(t1.val+m)%10;
            m=(t1.val+m)>=10 ? 1:0;
            t1=t1.next;
            t3=t3.next;
        }
        while (t2!=null){
            t3.val=(t2.val+m)%10;
            m=(t1.val+m)>=10 ? 1:0;
            t2=t2.next;
            t3=t3.next;
        }
        if (m>0)
            t3.val=m;
        return t3;
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
