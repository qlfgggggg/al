package qiaolf.shangguigu.singleLinkedList;

import sun.util.locale.provider.FallbackLocaleProviderAdapter;

public class HeroNodeList {
    public HeroNode head=new HeroNode(0,"","");
    //遍历单链表
    public void list(){
        HeroNode temp = head.next;
        while (temp!=null){
            System.out.print(temp+"=>");
            temp=temp.next;
        }
    }
    //添加节点
    public void addLast(HeroNode heroNode){
        HeroNode temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=heroNode;
    }
    //更新节点
    public void update(HeroNode heroNode){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag=false;
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.id==heroNode.id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next.name=heroNode.name;
            temp.next.nickName=heroNode.nickName;
        }else{
            System.out.printf("没有找到要更新的节点%d",heroNode.id);
        }
    }
    //删除节点
    public void del(HeroNode heroNode){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag=false;
        while (temp.next!=null){
            if (temp.next.id==heroNode.id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("没有找到待删除的节点%d",heroNode.id);
        }
    }

    //按顺序添加节点
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        while (temp.next!=null){
            if (temp.next.id>heroNode.id){
                break;
            }
            temp=temp.next;
        }
        heroNode.next=temp.next;
        temp.next=heroNode;
    }
}
