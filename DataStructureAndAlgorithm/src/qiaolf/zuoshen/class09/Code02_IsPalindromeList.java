package qiaolf.zuoshen.class09;

import java.time.format.TextStyle;
import java.util.Stack;

/**
 * @Description:    给定一个单链表的头节点head，请判断该链表是否为回文结构
 * Author:qiaolf
 * @Date:2021/3/27 17:27
 **/
public class Code02_IsPalindromeList {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value=value;
        }
    }
    /**
     * 用栈结构来做   =====在笔试时可以用容器，但是面试时不可以用容器,面试官会觉得你太low
     * @param head
     * @return
     */
    public static  boolean isOrNot(Node head){
        if (head==null){
            return false;
        }
        Node node = head;
        Stack<Node> stack = new Stack<>();
        while (node!=null){
            stack.push(node);
            node=node.next;
        }
        node=head;
        while (node!=null){
            if (node.value!=stack.pop().value){
                return false;
            }
            node=node.next;
        }
        return true;
    }

    /**
     * 用快慢指针来做:当是奇数时找出中间节点，当是偶数时找出上中节点
     * 后半部分做链表的反转:然后遍历前半部分链表，后半部分链表看看是否相等
     * @param head
     * @return
     */
    public static boolean isOrNot2(Node head){
        if (head==null){
            return false;
        }
        if (head.next==null){
            return true;
        }
        if (head.next.next==null){
            if (head.value==head.next.value)
                return true;
            else
                return false;
        }
        //这两种都对
//        Node slow = head.next;
//        Node fast = head.next.next;
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //这个时候slow就是中间节点,我们利用中间节点做链表反转
        Node pre=null;
        Node next=null;
        while (slow!=null){
            next=slow.next;
            slow.next=pre;
            pre=slow;
            slow=next;
        }
        //这个时候pre就变为反转链表的头结点,链表遍历完成之后别忘了把链表复原呀
        Node node = head;
        Node tail = pre;
        boolean flag=true;
        while (pre!=null && node!=null){
            if (pre.value!=node.value){
                flag= false;
                break;
            }
            node=node.next;
            pre=pre.next;
        }
        //在返回前将链表复原
        pre=null;
        next=null;
        while (tail.next!=null){
            next=tail.next;
            tail.next=pre;
            pre=tail;
            tail=next;
        }
        return flag;
    }

    public static void main(String[] args) {
        Node test=null;
        test=new Node(1);
        test.next=new Node(2);
        test.next.next=new Node(3);
        test.next.next.next=new Node(4);
        test.next.next.next.next=new Node(3);
        test.next.next.next.next.next=new Node(2);
        test.next.next.next.next.next.next=new Node(1);
        boolean orNot = isOrNot(test);
        System.out.println(orNot);
        boolean orNot2 = isOrNot2(test);
        System.out.println(orNot2);
    }


}
