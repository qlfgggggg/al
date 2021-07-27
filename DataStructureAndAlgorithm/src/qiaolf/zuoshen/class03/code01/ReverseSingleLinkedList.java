package qiaolf.zuoshen.class03.code01;

//单链表的反转
public class ReverseSingleLinkedList {
    public Node head=new Node();

    public Node reverseSingleLinkedList(Node head){
         Node next=null;
         Node pre=null;
         while (head!=null){
             next=head.next;
             head.next=pre;
             pre=head;
             head=next;
         }
         return head;
    }
}
