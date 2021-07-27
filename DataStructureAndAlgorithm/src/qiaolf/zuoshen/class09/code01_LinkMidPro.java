package qiaolf.zuoshen.class09;

/**
 * @Description: 链表中点问题
 * Author:qiaolf
 * @Date:2021/3/27 15:57
 **/
public class code01_LinkMidPro {


    //链表节点
    public class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value=value;
        }
    }

    /**
     * 给定链表头结点，奇数返回唯一中间节点，偶数返回上中节点,快慢指针法
     * @param head
     * @return
     */
    public Node backMid1(Node head){
        if (head==null || head.next==null || head.next.next==null){
            return head;
        }
        //链表有3个节点或以上
        Node slow = head.next;//慢指针
        Node fast = head.next.next;//快指针
        while (fast.next != null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * @param head
     * @return
     */
    public Node backMid2(Node head){
        if (head==null || head.next==null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * @param head
     * @return
     */
    public Node backMid3(Node head){
        if (head==null || head.next==null || head.next.next==null){
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }


    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     * @param head
     * @return
     */
    public Node backMid4(Node head){
        if (head==null || head.next==null){
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
