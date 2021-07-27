package qiaolf.zuoshen.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 广度优先遍历
 * Author:qiaolf
 * @Date:2021/4/7 9:36
 **/
public class BFS {
    public static void bfs(Node start){
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
