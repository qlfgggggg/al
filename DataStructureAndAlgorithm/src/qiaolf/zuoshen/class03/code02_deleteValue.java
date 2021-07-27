package qiaolf.zuoshen.class03;

import qiaolf.zuoshen.class03.code01.Node;

import java.util.LinkedList;

/**
 * @Description: 在链表中删除给定值，这个给定的值可能是头结点的值，也可能是全部节点的值，总之如果是头结点的值需要删除头结点返回一个新的头结点
 * 所以要有返回值,要写对数器验证正确与否
 * Author:qiaolf
 * @Date:2021/3/12 0:04
 **/
public class code02_deleteValue {

    public static Node deleteValue(Node head,int num){
        //先找到第一个不会删除的节点作为头结点,因为有可能把头结点也给删除
        while (head!=null){
            if (head.val!=num){
                break;
            }
            head=head.next;//要把头结点返回
        }
        Node pre=head;//这个节点是用来保存不要删除的节点的
        Node cur=head;//向下遍历
        while (cur!=null){//把要删除的节点删除
            if (cur.val==num){//待删除节点
                pre.next=cur.next;
            }else{
                pre=cur;
            }
            cur=cur.next;
        }
        return head;//最后返回头结点
    }
}
