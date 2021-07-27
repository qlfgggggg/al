package qiaolf.LRU;

public class DoubleNodeList {
    public Node head;
    public Node tail;
    public int size=0;
    //双链表插入节点
    public void addFirst(Node x){
        if (head==null){
            head=x;
            tail=x;
            size++;
        }else {
            x.next=head;
            head.last=x;
            head=x;
            size++;
        }
    }
    public Node remove(Node x){
        while (head!=null){
            if (head.key!=x.key){
                break;
            }
            head=head.next;
        }
        Node cur=head;
        Node pre=head;
        while (cur!=null){
            if (cur.key==x.key){
                pre.next=cur.next;
                cur.next.last=pre;
                size--;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }

        return head;
    }
    public Node removeLast(){
        if (head==null){
            return null;
        }else{
            Node cur=tail;
            if (head==tail){
                head=null;
                tail=null;
                size--;
            }else{
                tail= tail.last;
                cur.next=null;
                cur.last=null;
                size--;
            }
            return cur;
        }
    }
    public int size(){
       return size;
    }
}
