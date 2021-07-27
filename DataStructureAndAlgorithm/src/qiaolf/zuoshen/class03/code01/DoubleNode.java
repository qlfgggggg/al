package qiaolf.zuoshen.class03.code01;

public class DoubleNode {
    int val;
    DoubleNode next;
    DoubleNode pre;

    public DoubleNode(int val) {
        this.val = val;
    }

    public DoubleNode() {
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "val=" + val +
                '}';
    }
}
