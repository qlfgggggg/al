package qiaolf.zuoshen.class10;

/**
 * @Description: 遍历二叉树   用递归方式先中后序遍历二叉树
 * Author:qiaolf
 * @Date:2021/5/4 16:13
 **/
public class code01_traversalBianryTree {

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
//        middle(node1);
        last(node1);
    }

    public static void pre(Node head){
        if (head==null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void middle(Node head){
        if (head==null){
            return;
        }
        if (head.left!=null)
            middle(head.left);
        System.out.println(head.value);
        if (head.right!=null)
            middle(head.right);
    }

    public static void last(Node head){
        if (head==null){
            return;
        }
        if (head.left!=null)
            last(head.left);
        if (head.right!=null)
            last(head.right);
        System.out.println(head.value);
    }


}
