package qiaolf.zuoshen.class16_2;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description: 最小生成树
 * Author:qiaolf
 * @Date:2021/5/31 15:39
 **/
public class Prime {
    /**
     * 最小生成树P算法：
     */

    public List<Edge> p(Graph graph){
        //小根堆  里面放的是边
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        //存放已经放入的点
        HashSet<Node> nodeHashSet = new HashSet<>();

        //存放结果的边集合
        List<Edge> list = new ArrayList<>();

        for (Node node : graph.nodes.values()) {

            if (!nodeHashSet.contains(node)){
                nodeHashSet.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()){
                    Edge poll = queue.poll();
                    Node toNode = poll.toNode;
                    if (!nodeHashSet.contains(toNode)){
                        nodeHashSet.add(toNode);
                        list.add(poll);
                        for (Edge edge : toNode.edges) {
                            queue.add(edge);
                        }
                    }
                }
            }
            //break; 如果存在森林就不加break,不存在森林就加
        }
        return list;
    }
}
