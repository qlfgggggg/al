package qiaolf.zuoshen.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 将n叉树编码为二叉树，二叉树可以解码为n叉树:  将多叉树的同级孩子节点都放到左子树的右边界上
 *
 * Author:qiaolf
 * @Date:2021/5/4 23:37
 **/
public class code03_EncodeNaryTreetoBinaryTree {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

//    Definition for a binary tree node.
     public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {//将n变为二叉树
            if (root==null){
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left=en(root.children);
            return head;
        }
        public TreeNode en(List<Node> children){
           TreeNode head = null;
           TreeNode cur = null;
            for (Node child : children) {
                TreeNode node = new TreeNode(child.val);
                if (head==null){
                    head = node;
                }else{
                   cur.right = node;
                }
                cur = node;
                cur.left=en(child.children);
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root==null){
                return null;
            }
            Node head = new Node(root.val);
            head.children=de(root.left);
            return head;
        }
        public List<Node> de(TreeNode treeNode){
            List<Node> children = new ArrayList<>();
            while (treeNode!=null){
                Node node = new Node(treeNode.val);
                node.children=de(treeNode.left);
                children.add(node);
                treeNode=treeNode.right;
            }
            return children;
        }
    }
}
