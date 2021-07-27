package qiaolf.leetcode;

/**
 * @Description: 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/6/1 13:51
 **/
public class solution98 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    /**
     * 需要用到深度优先遍历
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return process(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean process(TreeNode node, long lower, long upper) {
        if (node==null){
            return true;
        }
        if (node.val>= upper || node.val<=lower){
            return false;
        }
        return process(node.left,lower,node.val) && process(node.right, node.val, upper);
    }
}
