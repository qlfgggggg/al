package qiaolf.shangguigu.singleLinkedList;

//单链表节点
public class HeroNode {
    public int id;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode() {
    }

    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
