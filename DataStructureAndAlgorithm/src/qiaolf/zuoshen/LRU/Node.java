package qiaolf.zuoshen.LRU;

/**
 * @Description:
 * Author:qiaolf
 * @Date:2021/5/19 20:21
 **/
public class Node{
    int key;
    int value;
    Node next;
    Node pre;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
