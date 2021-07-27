package qiaolf.shangguigu.bianryTree;

import javax.swing.*;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/3/21 23:32
 **/
public class BianryTreeTest {



    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"qiao1");
        HeroNode node2 = new HeroNode(2,"qiao2");
        HeroNode node3 = new HeroNode(3,"qiao3");
        HeroNode node4 = new HeroNode(4,"qiao4");
        HeroNode node5 = new HeroNode(5,"qiao5");
        Bianry bianry = new Bianry(root);
        root.left=node2;
        root.right=node3;
        node3.right=node4;
        node3.left=node5;
//        System.out.println("前");
//        bianry.preOrder();
//        System.out.println("中");
//        bianry.middleOrder();
//        System.out.println("后");
//        bianry.afterOrder();
//
//        System.out.println("=======");
//        System.out.println("前");
//        bianry.pre(5);
//        System.out.println();
//        System.out.println("中");
//        System.out.println();
//        bianry.middle(5);
//        System.out.println();
//        System.out.println("后");
//        System.out.println();
//        bianry.after(5);
          System.out.println("前");
          bianry.preOrder();
          bianry.delNode(15);
          bianry.preOrder();



    }



    static class Bianry{

        private HeroNode root;

        public Bianry(HeroNode root) {
            this.root = root;
        }

        //前序遍历
        public void preOrder(){
            if (root!=null){
                this.root.preOrder();
            }else{
                System.out.println("该二叉树为空");
            }
        }

        //中序遍历
        public void middleOrder(){
            if (root!=null){
                this.root.middleOrder();
            }else{
                System.out.println("该二叉树为空");
            }
        }


        //后序遍历
        public void afterOrder(){
            if (root!=null){
                this.root.afterOrder();
            }else{
                System.out.println("该二叉树为空");
            }
        }

        //前序遍历查找
        public void pre(int no){
            if (root!=null){
                HeroNode heroNode = this.root.preFind(no);
                if (heroNode!=null){
                    System.out.printf("查找到%d,%s",no,heroNode.getName());
                }else{
                    System.out.printf("没有查找到%d",no);
                }
            }else{
                System.out.println("该树为空");
            }
        }

        //中序查找
        public void middle(int no){
            if (root!=null){
                HeroNode heroNode = this.root.middleFind(no);
                if (heroNode!=null){
                    System.out.printf("查找到%d,%s",no,heroNode.getName());
                }else{
                    System.out.printf("没有查找到%d",no);
                }
            }else{
                System.out.println("该树为空");
            }
        }

        //后序查找
        public void after(int no){
            if (root!=null){
                HeroNode heroNode = this.root.afterFind(no);
                if (heroNode!=null){
                    System.out.printf("查找到%d,%s",no,heroNode.getName());
                }else{
                    System.out.printf("没有查找到%d",no);
                }
            }else{
                System.out.println("该树为空");
            }
        }

        //删除二叉树的节点
        public void delNode(int no){
            if (root==null || root.node==no){
                root=null;//如果该二叉树为空或者要删除的节点就是根节点
            }else{
                this.root.delNode(no);
            }
        }

    }

    static class HeroNode{
        int node;
        String name;
        HeroNode left;
        HeroNode right;

        public int getNode() {
            return node;
        }

        public void setNode(int node) {
            this.node = node;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HeroNode(int node, String name) {
            this.node = node;
            this.name = name;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "node=" + node +
                    ", name='" + name + '\'' +
                    '}';
        }

        //前序遍历
        public void preOrder(){
            System.out.println(this);
            if (this.left != null){
                this.left.preOrder();
            }
            if (this.right!=null){
                this.right.preOrder();
            }
        }

        //中序遍历
        public void middleOrder(){
            if (this.left!=null){
                this.left.middleOrder();
            }
            System.out.println(this);
            if (this.right!=null){
                this.right.middleOrder();
            }
        }
        //后序遍历
        public void afterOrder(){
            if (this.left!=null){
                this.left.afterOrder();
            }
            if (this.right!=null){
                this.right.afterOrder();
            }
            System.out.println(this);
        }

        //前序遍历查找
        public HeroNode preFind(int no){
            System.out.println("前序遍历查找");
            if (this.node==no){
                return this;
            }
            HeroNode heroNode = null;
            if (this.left!=null){
                heroNode=this.left.preFind(no);
            }
            if (heroNode!=null)
                return heroNode;
            if (this.right!=null)
                heroNode=this.right.preFind(no);
            return heroNode;
        }

        //后序遍历查找
        public HeroNode afterFind(int no){
            HeroNode heroNode = null;
            if (this.left!=null){
                heroNode = this.left.afterFind(no);
            }
            if (heroNode!=null)
                return heroNode;
            if (this.right!=null){
                heroNode = this.right.afterFind(no);
            }
            if (heroNode!=null)
                return heroNode;
            System.out.println("后序遍历查找");
            if (this.node==no)
                return this;
            return heroNode;
        }


        //中序遍历查找
        public HeroNode middleFind(int no){
            HeroNode heroNode = null;
            if (this.left!=null){
                heroNode = this.left.middleFind(no);
            }
            if (heroNode!=null)
                return heroNode;
            System.out.println("中序遍历查找");
            if (this.node==no){
                return this;
            }
            if (this.right!=null){
                heroNode = this.right.middleFind(no);
            }
            if (heroNode!=null)
                return heroNode;
            return heroNode;
        }

        //删除节点：
        //如果代删除节点是叶子节点，直接删除，如果是非叶子节点，则删除子树
        //我们再删除二叉树节点时应该找到当前节点的左子节点或者右子节点
        public void delNode(int no){
            //我们要删除二叉树的节点，需要找到他的前一个节点
            if (this.left!=null && this.left.node==no){
                this.left=null;
                return;
            }
            if (this.right!=null && this.right.node==no){
                this.right=null;
                return;
            }
            if (this.left!=null){
                this.left.delNode(no);
            }
            if (this.right!=null){
                this.right.delNode(no);
            }
        }

        //只删除二叉树的一个节点，当为非叶子节点时，在删除该节点的时候，如果只有左子节点不为空，就将左子节点作为该节点，如果左右节点都不为空，就将左子节点作为该节点
        public void delNodeNoChild(int no){
            if (this.left!=null && this.left.node==no){
            }
        }
    }
}
