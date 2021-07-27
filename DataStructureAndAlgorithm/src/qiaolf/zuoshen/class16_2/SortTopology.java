package qiaolf.zuoshen.class16_2;

import java.util.*;

/**
 * @Description: 拓扑排序
 * Author:qiaolf
 * @Date:2021/5/24 14:22
 **/
public class SortTopology {
    public static List<Node> sortedTopology(Graph graph){
        //node某个节点   integer节点的剩余入度，当剩余入度为0就说明这个点可以进入拓扑序里
        Map<Node,Integer> inMap = new HashMap<>();
        //剩余入度为0的节点 当剩余入度为0就说明这个点可以进入拓扑序里
        Queue<Node> zeroInQueue = new LinkedList<>();
        //遍历图的所有节点，找到入度为0的节点
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in==0){
                zeroInQueue.add(node);
            }
        }
        List<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node poll = zeroInQueue.poll();
            res.add(poll);
            for (Node next : poll.nexts) {
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
