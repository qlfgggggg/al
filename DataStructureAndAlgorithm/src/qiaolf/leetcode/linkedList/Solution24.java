package qiaolf.leetcode.linkedList;
//单链表反转
public class Solution24 {
    public ListNode reverseList(ListNode head) {
        ListNode pre=null;
        ListNode next=null;
        while (head!=null){
            next=head.next;//保存下一个结点
            head.next=pre;//head结点指向前一个节点
            pre=head;//前一个节点后移
            head=next;//head后移，最终next=null,head=null,在这之前pre节点到达最后一个节点
        }
        return pre;
    }
}
