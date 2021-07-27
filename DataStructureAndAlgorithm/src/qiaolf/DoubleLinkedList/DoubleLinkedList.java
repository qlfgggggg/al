package qiaolf.DoubleLinkedList;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/3/12 10:53
 **/
public class DoubleLinkedList {
    //双链表要有头结点和尾结点
    private static Node head;
    private static Node tail;
    public static void addFromHead(int value){
        Node cur=new Node(value);
        if (head==null){
            head=cur;
            tail=cur;
        }else {
            cur.next=head;
            head.last=cur;
            head=cur;
        }
    }

    public static void addFromButtom(int value){
        Node cur=new Node(value);
        if (head==null){
            head=cur;
            tail=cur;
        }else{
            cur.last=tail;
            tail.next=cur;
            tail=cur;
        }
    }

    public static Integer popFromHead(){
        if (head==null){
            return null;
        }else{
            //链表就一个数据
            Node cur=head;
            if (head==tail){
                head=null;
                tail=null;
            }else{
                head=head.next;
                cur.next=null;
                cur.last=null;
            }
            return cur.val;
        }
    }

    public static Integer popFormButtom(){
        if (head==null){
            return null;
        }else{
            Node cur=tail;
            if (head==tail){
                head=null;
                tail=null;
            }else{
                tail=tail.last;
                cur.next=null;
                cur.last=null;
            }
            return cur.val;
        }
    }
    public static Node reverse(Node head){
        Node next=null;
        Node pre=null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }
}
