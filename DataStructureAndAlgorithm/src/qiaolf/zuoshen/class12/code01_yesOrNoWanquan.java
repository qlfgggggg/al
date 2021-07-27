package qiaolf.zuoshen.class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 判断二叉树是否是完全二叉树:空二叉树也是完全二叉树
 * Author:qiaolf
 * @Date:2021/5/5 14:46
 **/
public class code01_yesOrNoWanquan {

    public static boolean isOrNo(Node head){
        if (head==null)
            return true;
        //是否遇到过左右孩子不双全的情况
        boolean flag = false;
        Node l=null;//左孩子
        Node r=null;//右孩子

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            l=poll.left;
            r=poll.right;
            //遇到了左右孩子不双全且该节点不是叶子节点   或者 有有孩子无左孩子
           if (  (flag && (r!=null || l != null))  ||  (r!=null && l==null) )
               return false;
           if (l!=null)
               queue.add(l);
           if (r!=null)
               queue.add(r);
           //遇到左右孩子不双全的情况
           if (l==null || r==null){
               flag = true;
           }
        }
        return false;
    }
}
