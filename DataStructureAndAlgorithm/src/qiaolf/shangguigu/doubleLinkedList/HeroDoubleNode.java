package qiaolf.shangguigu.doubleLinkedList;

public class HeroDoubleNode {
    public int id;
    public String name;
    public String nickName;
    public HeroDoubleNode next,last;



    public HeroDoubleNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroDoubleNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
