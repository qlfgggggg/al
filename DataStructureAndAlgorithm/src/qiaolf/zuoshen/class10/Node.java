package qiaolf.zuoshen.class10;

/**
 * @Description: 二叉树节点
 * Author:qiaolf
 * @Date:2021/5/4 16:14
 **/
public class Node<T> {
    public T value;
    public Node left;
    public Node right;

    public Node(T value) {
        this.value = value;
    }
}
