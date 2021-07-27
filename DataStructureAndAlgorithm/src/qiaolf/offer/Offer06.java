package qiaolf.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * Author:qiaolf
 * @Date:2021/6/1 11:32
 **/
public class Offer06 {
    public int[] reversePrint(ListNode head) {
        if (head==null){
            return new int[0];
        }
        ListNode cur=head;
        List<Integer> list=new ArrayList<>();
        while (cur!=null){
            list.add(cur.val);
            cur=cur.next;
        }
        int[] res=new int[list.size()];
        int len=list.size()-1;
        for (int i = 0; i < res.length; i++) {
            res[i]=list.get(len-i);
        }
        return res;
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
