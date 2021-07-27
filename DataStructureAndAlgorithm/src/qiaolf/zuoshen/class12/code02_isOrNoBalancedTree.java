package qiaolf.zuoshen.class12;

/**
 * @Description: 一棵树书否是平衡二叉树:用递归套路
 * 判断条件：以某个节点为根节点的二叉树的左右子树都是平衡二叉树，并且左右子树的高度差不能超过1
 * Author:qiaolf
 * @Date:2021/5/5 21:29
 **/
public class code02_isOrNoBalancedTree {

    public static class Info{
        public boolean balanced;
        public int height;

        public Info(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    //判断二叉树是否是平衡二叉树
    public static boolean isBalanced2(Node head){
        return process(head).balanced;
    }
    public static Info process(Node x){
        if (x==null)
            return new Info(true,0);
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        boolean balanced=true;
        if (leftInfo.balanced==false)
            balanced=false;
        if (rightInfo.balanced==false)
            balanced=false;
        int max = Math.max(leftInfo.height, rightInfo.height);
        int min = Math.min(leftInfo.height, rightInfo.height);
        if ((max-min)>1)
            balanced=false;
        return new Info(balanced,max+1);
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
