package qiaolf.zuoshen.class16_2;

import java.util.*;

/**
 * @Description: 拓扑排序
 * Author:qiaolf
 * @Date:2021/5/24 14:22
 **/
public class SortTopology_1 {
    //拓扑排序的要点就是入度为0的节点才可以进入  list
    public static List<Node> sortedTopology(Graph graph){
        //存储入度为0的点
        Queue<Node> zeroInQueue =new LinkedList<>();
        //存储key节点  value剩余的入度
        HashMap<Node,Integer> inMap = new HashMap<>();
        //遍历整张图，
        for (Node node : graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in==0)
                zeroInQueue.add(node);
        }

        //存储返回的节点，拓扑排序结果
        List<Node> res = new ArrayList<>();
        //找入度为0的点的后继节点
        while (!zeroInQueue.isEmpty()){
            Node poll = zeroInQueue.poll();
            res.add(poll);
            for (Node next : poll.nexts) {
                //poll节点是入度为0的节点，弹出之后，消除该节点的影响后，他的所有后继节点的入度都要减去1
                inMap.put(next,inMap.get(next)-1);
                //如果next节点的入度也为0，就放入zeroInQueue
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
