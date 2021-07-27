package qiaolf.zuoshen.class16;

import java.util.*;

/**
 * @Description:   有向无环图,不关心权重
 * Author:qiaolf
 * @Date:2021/4/7 10:11
 **/
public class Code03_topologySort2 {

    public List<Node> topologySort(Graph graph){
        //key：节点，value：入度，主要存储每个节点的入度
        HashMap<Node,Integer> inMap = new HashMap<>();
        //存储入度为0的节点
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node value : graph.nodes.values()) {
            inMap.put(value,value.in);
            if (value.in==0){
                zeroInQueue.add(value);
            }
        }
        List<Node> list = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node poll = zeroInQueue.poll();
            list.add(poll);
            //将出队列的入度为0的节点的邻居节点的影响消除，每个邻居节点的入度都减去1
            for (Node next : poll.nexts) {
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }
        return list;
    }
}
