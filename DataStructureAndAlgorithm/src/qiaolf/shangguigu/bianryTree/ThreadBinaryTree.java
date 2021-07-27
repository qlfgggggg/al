package qiaolf.shangguigu.bianryTree;

/**
 * @Description: 线索化二叉树:将浪费的指针利用起来
 * 1.n个节点的二叉树含有n+1个空指针域，n+1=2n-(n-1),解释n个节点，每个节点可以分出两个指针共2n个节点
 * 每个节点被一个指针指着（除了根节点），共有(n-1)个节点，所以有2n-(n-1)=n+1个节点没用到
 * 2.可以利用这些空指针域存放指向该节点在某种遍历次序下(中序，后序，前序)的前驱和后继节点的指针(这种附加的指针成为“线索”)
 * 3.这种加上了线索的链表称为线索链表，相应的二叉树称为线索二叉树
 * 4.线索二叉树分为：前,中,后序二叉树
 * Author:qiaolf
 * @Date:2021/3/23 9:49
 **/

//我们这里写的是中序遍历二叉树：左指针指向的可以是左节点
public class ThreadBinaryTree {




    //线索化二叉树
    class ThreadTree{
        HeroNode root;
        //为了线索化二叉树需要创建指向当前节点的前驱节点的指针
        HeroNode pre = null;

        public ThreadTree(HeroNode root) {
            this.root = root;
        }
        //遍历线索化二叉树的方法

    }
    //线索化二叉树节点
    class HeroNode{
        int no;
        HeroNode left;//默认为空
        HeroNode right;

        //还需要有能够分辨出来left究竟是指向左节点还是前驱节点：为0指向左节点，为1指向前驱节点
        int leftType;
        //分辨right指向，为0指向右节点，为1指向那个后继节点
        int rightType;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getLeftType() {
            return leftType;
        }

        public void setLeftType(int leftType) {
            this.leftType = leftType;
        }

        public int getRightType() {
            return rightType;
        }

        public void setRightType(int rightType) {
            this.rightType = rightType;
        }

        public HeroNode getLeft() {
            return left;
        }

        public void setLeft(HeroNode left) {
            this.left = left;
        }

        public HeroNode getRight() {
            return right;
        }

        public void setRight(HeroNode right) {
            this.right = right;
        }

    }
}
