package qiaolf.shangguigu.singleLinkedList;

public class HeroNodeListTest {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1,"qiao1","liang1");
        HeroNode h2 = new HeroNode(2,"qiao2","liang2");
        HeroNode h3 = new HeroNode(3,"qiao3","liang3");
        HeroNode h4 = new HeroNode(4,"qiao4","liang4");
        HeroNode h5 = new HeroNode(5,"qiao5","liang5");
        HeroNodeList list=new HeroNodeList();
//        list.addLast(h1);
//        list.addLast(h2);
//        list.addLast(h3);
//        list.addLast(h4);
//        list.addLast(h5);
//        list.update(new HeroNode(6,"3","3"));
//        list.del(new HeroNode(2,"1","1"));
        list.addByOrder(h5);
        list.addByOrder(h1);
        list.addByOrder(h4);
        list.addByOrder(h2);
        list.addByOrder(h3);
        list.list();
    }
}
