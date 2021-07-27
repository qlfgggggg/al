package qiaolf.DoubleLinkedList;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/3/12 10:52
 **/
public class Node {
    int val;
    Node next;
    Node last;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
