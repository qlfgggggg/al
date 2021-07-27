package qiaolf.zuoshen.class11;

import qiaolf.zuoshen.class12.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 二叉树的序列化与反序列化
 * Author:qiaolf
 * @Date:2021/5/4 17:56
 **/
public class code02_serial {

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
//        [1, 2, null, 4, null, null, 3, 5, null, 6, null, null, null]
//        [1, 2, null, 4, null, null, 3, 5, null, 6, null, null, null]
//        Queue<String> queue1 = preS(node1);
//        System.out.println(queue1);
//        Node node = buildPreSerial(queue1);
//        pre(node);
        Queue<String> queue = levelSerial(node1);
        System.out.println(queue);
        Node node = buildLevel(queue);
        pre(node);
    }

    //按层序列化:用到辅助队列，为了遍历
    public static Queue<String> levelSerial(Node head){
        Queue<String> queue = new LinkedList<>();
        if (head==null){
            queue.add(null);
        }else{
            queue.add(String.valueOf(head.value));
            Queue<Node> queue1 = new LinkedList<>();
            queue1.add(head);
            while (!queue1.isEmpty()){
                Node poll = queue1.poll();
                if (poll.left==null){
                    queue.add(null);
                }else {
                    queue.add(String.valueOf(poll.left.value));
                    queue1.add(poll.left);
                }
                if (poll.right==null){
                    queue.add(null);
                }else {
                    queue.add(String.valueOf(poll.right.value));
                    queue1.add(poll.right);
                }
            }
        }
        return queue;
    }

    //层级反序列化
    public static Node buildLevel(Queue<String> queue){
        if (queue.isEmpty() || queue.size()==0){
            return null;
        }
        Queue<Node> queue1 = new LinkedList<>();
        Node head = generetor(queue.poll());
        if (head!=null){
            queue1.add(head);
        }
        Node node = null;//作为头结点
        while (!queue1.isEmpty()){
            node = queue1.poll();//相当于node=head;  之后对所有node的操作都和head有关
            node.left=generetor(queue.poll());
            node.right=generetor(queue.poll());
            if (node.left!=null){
                queue1.add(node.left);
            }
            if (node.right!=null){
                queue1.add(node.right);
            }
        }
        return head;
    }
    public static Node generetor(String s){
        if (s==null)
            return null;
        return new Node(Integer.valueOf(s));
    }

    //前序序列化,你给我一个头结点，我将二叉树序列化放到队列中存储起来
    public static Queue<String> preS(Node head){
        Queue<String> queue = new LinkedList<>();
        preSerial(head,queue);
        return queue;
    }
    public static void preSerial(Node head,Queue<String> queue){
        if (head==null){
            queue.add(null);
        }else{
            queue.add(String.valueOf(head.value));
            preSerial(head.left,queue);
            preSerial(head.right,queue);
        }
    }

    //前序反序列化
    public static Node buildPreSerial(Queue<String> queue){
        if (queue.isEmpty() || queue.size()==0){
            return null;
        }
        return preb(queue);
    }

    private static Node preb(Queue<String> queue) {
        String poll = queue.poll();
        if (poll==null)
            return null;
        Node head = new Node(Integer.valueOf(poll));
        head.left=preb(queue);
        head.right=preb(queue);
        return head;
    }



    public static void pre(Node head){
        if (head==null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }
}
