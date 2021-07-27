package qiaolf.zuoshen.LRU2;

import qiaolf.zuoshen.LRU.Node;

import java.util.HashMap;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/5/19 22:41
 **/
public class LRUCache {
    class Node{
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


    HashMap<Integer,Node> cache = new HashMap<>();
    int capacity;//LRUCache容量，当容量满了，再添加数据时就需要删除
    int count;//当前缓存容量，判断是否满了
    Node head,tail;



    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.head = new Node(0,0);
        this.tail = new Node(0,0);

        head.pre=null;
        head.next=tail;
        tail.next=null;
        tail.pre=head;
    }

    //在链表头部添加节点
    private void addToHead(Node node){
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
        node.pre=head;
    }
    //删除链表尾部的节点
    private int deleteTail(){
        //找到为节点，删除尾部节点的前一个节点
//        tail.pre=tail.pre.pre;
//        tail.pre.next.pre=null;
//        tail.pre.next.next=null;
//        tail.pre.next=tail;
        Node res=tail.pre;
        int key = removeNode(res);
        return key;
    }

    //删除给点给的节点
    private int removeNode(Node node){
        node.pre.next=node.next;
        node.next.pre=node.pre;
        //待删除的节点指向空，以便垃圾回收器回收
        int value = node.key;
        node.next=null;
        node.pre=null;
        return value;
    }

    //get过后把节点节点移到链表头部
    private void removeToHead(Node node){
        addToHead(node);
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node==null){
            return -1;
        }else{
            removeNode(node);
            addToHead(node);
        }
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node==null){//不存在就添加
            //放元素之前,如果已经满了，就删除尾部的节点
            if (count>=capacity){
                int k= deleteTail();
                //删除节点后，把对应的hashmap中的数据也给删除
                cache.remove(k);
                --count;
            }
            //向头部添加节点
            Node node1 = new Node(key,value);
            addToHead(node1);
            ++count;
            cache.put(key,node1);
        }else{//如果已经存在了该k代表的元素,就直接进行替换
            node.value=value;
            cache.put(key,node);
            removeNode(node);
            addToHead(node);
        }
    }

    public void list(){
        Node tem = head;
        while (tem!=null){
            System.out.print(tem+"=>");
            tem=tem.next;
        }
        System.out.println("=======");
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.list();
        lruCache.put(1,1);
        lruCache.list();
        lruCache.put(2,3);
        lruCache.list();
        lruCache.put(4,1);
        lruCache.list();
        int i = lruCache.get(1);
        System.out.println(i);
        lruCache.list();
    }
}
