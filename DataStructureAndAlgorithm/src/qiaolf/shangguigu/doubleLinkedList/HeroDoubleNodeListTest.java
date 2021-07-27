package qiaolf.shangguigu.doubleLinkedList;

public class HeroDoubleNodeListTest {
    public static void main(String[] args) {
        HeroDoubleNodeList list = new HeroDoubleNodeList();
        HeroDoubleNode h1 = new HeroDoubleNode(1,"qiao1","liang1");
        HeroDoubleNode h2 = new HeroDoubleNode(2,"qiao2","liang2");
        HeroDoubleNode h3 = new HeroDoubleNode(3,"qiao3","liang3");
        HeroDoubleNode h4 = new HeroDoubleNode(4,"qiao4","liang4");
//        list.addLast(h1);
//        list.addLast(h2);
//        list.addLast(h3);
//        list.addLast(h4);
//        list.update(new HeroDoubleNode(2,"qiao","liang"));
//        list.del(new HeroDoubleNode(4,"qiao1","liang1"));
        list.addByOrder(h2);
        list.addByOrder(h4);
        list.addByOrder(h3);
        list.addByOrder(h1);
        list.list();
    }
}
