package qiaolf.zuoshen.class10;

import java.util.Stack;

/**
 * @Description: 非递归方式遍历二叉树
 * Author:qiaolf
 * @Date:2021/5/4 16:43
 **/
public class code02_NoRecursion {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.left=node2;
        node1.right=node3;
        node2.right=node4;
        node3.left=node5;
        node5.right=node6;
        Node node0=null;
//        pre(node1);
        middle(node1);
//        last(node1);
    }

    //非递归遍历，进栈先头，后右，再左，出队时先头，后左，再右，每次出队就要将出队节点的左右孩子节点入队。
    public static void pre(Node head){
        if (head==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.value);
            if (pop.right!=null)
                stack.push(pop.right);
            if (pop.left!=null)
                stack.push(pop.left);
        }
    }

    //用两个队列，队列1，队列2，队列1正常入队顺序：先头，后左，再右，出队顺序，头，右，左，每次出队一个就入队队列2，最后队列2出队顺序：左，右，头，就是后序遍历
    public static void last(Node head){
        if (head==null){
            return;
        }
       Stack<Node> stack = new Stack<>();
       Stack<Node> stack1 = new Stack<>();
       stack.push(head);
       while (!stack.isEmpty()){
           Node pop = stack.pop();
           stack1.push(pop);
           if (pop.left!=null)
               stack.push(pop.left);
           if (pop.right!=null){
               stack.push(pop.right);
           }
       }
       while (!stack1.isEmpty()){
           System.out.println(stack1.pop().value);
       }
    }

    //将cur节点的所有左边界加入到栈中，遇到空就弹出节点看右边界，再对右边界做一样的事情
    public static void middle(Node cur){
        if (cur==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur!=null){
            //如果当前节点不为空，就一直往左边走，直到走到空
            if (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else{//如果左边界为空了
                Node pop = stack.pop();
                System.out.println(pop.value);
                cur=pop.right;
            }
        }
    }
}
