package qiaolf.zuoshen.class11;

import qiaolf.zuoshen.class12.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 二叉树的宽度优先遍历：按层遍历
 * Author:qiaolf
 * @Date:2021/5/4 17:40
 **/
public class code01_BreadthFirstSearch {

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
        level(node1);
    }

    //要用到队列
    public static void level(Node head){
        if (head==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            if (poll.left!=null)
                queue.add(poll.left);
            if (poll.right!=null)
                queue.add(poll.right);
        }
    }
}
