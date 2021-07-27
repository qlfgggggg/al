package qiaolf.shangguigu.bianryTree;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * @Description: 顺序存储二叉树:顺序存储二叉树只适用于完全二叉树(满二叉树)    ------使用的地方：8大排序算法的堆排序
 * Author:qiaolf
 * @Date:2021/3/22 21:18
 **/

/*顺序存储二叉树特点：
*1.顺序存储二叉树只适用于完全二叉树(满二叉树)
* 2.第n个元素的左子节点为2n+1
* 第n个元素的右子节点为2n+2
* 第n个元素的父节点为(n-1)/2
* n表示广度优先遍历的顺序号，从0开始
* */
public class ArrayBinaryTree {



    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayTree arrayTree = new ArrayTree(arr);
//        arrayTree.preOrder();
//          arrayTree.midOrder();
        arrayTree.aftOrder();
    }

     static class ArrayTree{
        //这个二叉树是用数组存储的
        int[] array;
        public ArrayTree(int[] array) {
            this.array = array;
        }

        public void preOrder(){
            preOrder(0);//根节点遍历
        }

        public void midOrder(){
            midOrder(0);
        }
         public void aftOrder(){
             aftOrder(0);
         }
        //前序遍历
        public void preOrder(int index){//第n个元素开始遍历,从0开始计数
            if (array.length==0 || array==null){
                System.out.println("该二叉树为空");
            }
            //输出当前节点
            System.out.println(array[index]);
            if (2*index+1<array.length){//左子节点
                preOrder(2*index+1);
            }
            if (2*index+2<array.length){
                preOrder(2*index+2);
            }
        }

        //中序遍历
         public void midOrder(int index){
             if (array.length==0 || array==null){
                 System.out.println("该二叉树为空");
             }
             if (2*index+1<array.length){//左子节点
                 midOrder(2*index+1);
             }
             System.out.println(array[index]);
             if (2*index+2<array.length){
                 midOrder(2*index+2);
             }
         }

         //后序
         public void aftOrder(int index){
             if (array.length==0 || array==null){
                 System.out.println("该二叉树为空");
             }
             if (2*index+1<array.length){//左子节点
                 aftOrder(2*index+1);
             }

             if (2*index+2<array.length){
                 aftOrder(2*index+2);
             }
             System.out.println(array[index]);
         }
    }

}


