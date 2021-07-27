package qiaolf.zuoshen.LRU;

import sun.security.action.PutAllAction;

import java.util.HashMap;

/**
 * @Description: LRU最近最少使用，淘汰策略
 * 要实现get,delete,put时间复杂度O(1)，需要用到hashmap,双链表
 * hashmap的get,remove,put时间复杂度O(1)，但是还需要保证最近最少使用，无法实现，用双链表就可以轻松实现最近使用的放在双链表
 * 头部，最近没有使用的放在双链表尾部，删除尾部的
 * Author:qiaolf
 * @Date:2021/5/19 20:20
 **/
public class LRUCache{
    private HashMap<Integer,Node> cache = new HashMap<>();
    private int capacity;//LRUCache容量，当容量满了，再添加数据时就需要删除
    private int count;//当前缓存容量，判断是否满了
    private Node head,tail;

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

   public void put(int k,int value){
       Node node = cache.get(k);
       if (node==null){//不存在就添加
           //放元素之前,如果已经满了，就删除尾部的节点
           if (count>=capacity){
               int key = deleteTail();
               //删除节点后，把对应的hashmap中的数据也给删除
               cache.remove(key);
               --count;
           }
           //向头部添加节点
           Node node1 = new Node(k,value);
           addToHead(node1);
           ++count;
           cache.put(k,node1);
       }else{//如果已经存在了该k代表的元素,就直接进行替换
          node.value=value;
          cache.put(k,node);
           removeNode(node);
           addToHead(node);
       }
   }

   public int get(int k){
       Node node = cache.get(k);
       if (node==null){
           return -1;
       }else{
           removeNode(node);
           addToHead(node);
       }
       return node.value;
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
//        node1.next=node2;
//        node2.pre=node1;
//        node2.next=tail;
//        tail.pre=node2;
//
//        tail.pre=tail.pre.pre;
//        tail.pre.next.pre=null;
//        tail.pre.next.next=null;
//        tail.pre.next=tail;
//        System.out.println(node1);
//        System.out.println(node1.next);
        LRUCache lruCache = new LRUCache(3);
//        Node node1=new Node(1,1);
//        Node node2=new Node(2,2);
//        Node tail=new Node(3,3);
        lruCache.put(1,1);
        lruCache.get(1);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        System.out.println("cache:"+lruCache.cache.size());
        lruCache.list();
        lruCache.put(2,2);
        lruCache.put(3,3);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        System.out.println("cache:"+lruCache.cache.size());
        lruCache.put(4,4);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        System.out.println("cache:"+lruCache.cache.size());
        lruCache.put(5,5);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        System.out.println("cache:"+lruCache.cache.size());
        lruCache.put(6,6);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        lruCache.get(4);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        System.out.println("cache:"+lruCache.cache.size());
        lruCache.put(5,55);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        System.out.println("cache:"+lruCache.cache.size());
        lruCache.get(8);
        System.out.println("capacity:"+lruCache.capacity+";count:"+ lruCache.count);
        lruCache.list();
        System.out.println("cache:"+lruCache.cache.size());
    }
}
