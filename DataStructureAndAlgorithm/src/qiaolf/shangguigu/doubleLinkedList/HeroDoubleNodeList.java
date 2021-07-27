package qiaolf.shangguigu.doubleLinkedList;

//不带尾结点的双链表
public class HeroDoubleNodeList {
    public HeroDoubleNode head=new HeroDoubleNode(0,"","");
    //遍历双向链表
    public void list(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroDoubleNode temp=head.next;
        while (temp!=null){
            System.out.print(temp+"=>");
            temp=temp.next;
        }
    }
    //添加一个节点到链表的最后
    public void addLast(HeroDoubleNode heroDoubleNode){
        HeroDoubleNode temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=heroDoubleNode;
        heroDoubleNode.last=temp;
    }
    //修改双向链表的节点
    public void update(HeroDoubleNode heroDoubleNode){
        if (head.next==null){
            System.out.println("链表是空的");
            return;
        }
        HeroDoubleNode temp = head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;//已经遍历完链表
            }
            if (temp.id==heroDoubleNode.id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=heroDoubleNode.name;
            temp.nickName=heroDoubleNode.nickName;
        }else{
            System.out.printf("没有找到要修改的节点%d",heroDoubleNode.id);
        }
    }

    //删除节点
    public void del(HeroDoubleNode heroDoubleNode){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroDoubleNode temp = head.next;
        boolean flag=false;
        while (true){
            if (temp==null){
                break;
            }
            if (temp.id==heroDoubleNode.id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.last.next=temp.next;
            //如果temp的后一个节点为空就不要执行这段代码
            if (temp.next!=null){
                temp.next.last=temp.last;
            }
        }else{
            System.out.printf("没有找到要删除的节点%d",heroDoubleNode.id);
        }
    }
    //按照编号顺序插入节点,需要找到带插入节点的前一个节点，那就需要找打前一个节点的后一个节点的id值大于带插入节点的id
    public void addByOrder(HeroDoubleNode heroDoubleNode){
        HeroDoubleNode temp = head;
        while (temp.next != null) {
            if (temp.next.id>heroDoubleNode.id){
                break;
            }
            temp=temp.next;
        }
        if (temp.next!=null){
            heroDoubleNode.next=temp.next;
            temp.next.last=heroDoubleNode;
        }
        temp.next=heroDoubleNode;
        heroDoubleNode.last=temp;
    }
}
