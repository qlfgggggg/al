package qiaolf.zuoshen.class11;

import qiaolf.zuoshen.class10.Node;

/**
 * @Description: 二叉树的后继节点
 * 二叉树结构如下定义：
 * Class Node {
 * 	V value;
 * 	Node left;
 * 	Node right;
 * 	Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点{中序遍历的结果，该节点的后面一个节点}=>
 * 1.该节点有右树：后继节点是右树的最左孩子，
 * 2.没有右树，找到该节点的父节点，一直往上找，直到父节点是更高父节点的左孩子，更高父节点就是后继节点（也就是该节点是左树上的最右节点)
 * Author:qiaolf
 * @Date:2021/5/5 1:06
 **/
public class code05_HouNode{

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        pre(head);
        System.out.println("=========");
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        pre(head);
        System.out.println("=========");
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;
        pre(head);
        System.out.println("=========");
        Node test = head.left.left;//1
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;//2
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;//3
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;//4
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;//5
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;//6
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;//7
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;//8
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;//9
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }

    public static void pre(Node head){
        if (head==null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }


   public static class Node{
       public int value;
       Node left;
       Node right;
       Node parent;
       public Node(int value){
           this.value = value;
       }
   }


    /**
     * 中序遍历的后继节点
     * @param node   任意节点
     * @return
     */
   public static Node getSuccessorNode(Node node){
       if (node==null){
           return node;
       }
       //如果有右子树，就找右子树的最左节点
       if (node.right!=null){
           node=node.right;
           while (node.left!=null){
               node=node.left;
           }
           return node;
       }else { //如果没有右子树，就找父节点，一直找到父节点有右父节点
           Node parent = node.parent;
           //如果父节点不为空，循环可以一直继续，如果该节点是父节点的右子节点，继续，直到该节点是父节点的左子节点(当然parent!=null)
           while (parent!=null && parent.right==node){
               node=parent;
               parent=node.parent;
           }
           //如果找到当前节点是父节点的左子节点，或者没找到(parent==null)
           return parent;
       }
   }
}
