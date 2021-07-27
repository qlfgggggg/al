package qiaolf.zuoshen.class16_2;

import java.util.ArrayList;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/5/24 9:20
 **/
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in=0;
        this.out=0;
        nexts=new ArrayList<>();
        edges=new ArrayList<>();
    }
}
