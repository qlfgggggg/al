package qiaolf.zuoshen.class35;

/**
 * @Description: 精细版avl树
 * Author:qiaolf
 * @Date:2021/5/7 16:00
 **/
public class code02_AVLTreeMap {

    //排序二叉树的key都必须是可比较的   avl数节点
    public static class AVLNode<K extends Comparable<K>,V>{
        public K k;
        public V v;
        public AVLNode<K,V> l;
        public AVLNode<K,V> r;
        public int h;

        public AVLNode(K key,V value,int h){
            this.k = k;
            this.v = value;
            this.h = 1;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>,V>{
        private AVLNode<K,V> root;
        private int size;

        public AVLTreeMap() {
            this.root = null;
            this.size = 0;
        }

        //违反平衡   破坏平衡有四种情况   左左  右右  左右 右左
        //右旋   以你传进来的节点右旋   要返回新节点     ======左左 情况
        private AVLNode<K,V> rightRotate(AVLNode<K,V> cur){
            AVLNode<K, V> leftNode = cur.l;
            cur.l=leftNode.r;
            leftNode.r=cur;
            cur.h = Math.max((cur.l!=null ? cur.l.h : 0),(cur.r!=null ? cur.r.h : 0)) +1;
            //右旋之后调整高度   leftNode升为代替cur的节点   他们的高度都变了   需要调整高度
            leftNode.h = Math.max((leftNode.l!=null ? leftNode.l.h : 0),(leftNode.r!=null ? leftNode.r.h : 0)) +1;
            //返回调整后的节点
            return leftNode;
        }

        //=====================右右情况
        //左旋   以你传进来的节点来进行左旋
        private AVLNode<K,V> leftRotate(AVLNode<K,V> cur){
            AVLNode<K,V> rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            cur.h = Math.max((cur.l!=null ? cur.l.h : 0),(cur.r!=null ? cur.r.h : 0)) +1;
            rightNode.h = Math.max((rightNode.l!=null ? rightNode.l.h : 0),(rightNode.r!=null ? rightNode.r.h : 0)) +1;
            //返回调整后的节点
            return rightNode;
        }

        //调整平衡情况
        private AVLNode<K,V> maintain(AVLNode<K,V> cur){
            if (cur==null){
                return null;
            }
            int leftHeight = cur.l.h;
            int rightHeight = cur.r.h;
            if (Math.abs(leftHeight-rightHeight)>1){//左右子树高度差大于1
                if (leftHeight>rightHeight){//左子树高于右子树
                    int leftLeftHeight = cur.l.l.h;
                    int leftRightHeight = cur.l.r.h;
                    if (leftLeftHeight>=leftRightHeight){   //          ======================左左情况  只需要进行右旋就可以调整平衡
                        cur=rightRotate(cur);
                    }else{//=====================左右情况   先对cur.l进行左旋   再对cur进行右旋
                         cur.l = leftRotate(cur.l);
                         cur = rightRotate(cur);
                    }
                }else{   //右子树高于左子树
                    int rightRightHeight = cur.r.r.h;
                    int rightLeftHeight = cur.r.l.h;
                    if (rightRightHeight>=rightLeftHeight){   //右右情况     直接进行左旋
                        cur = leftRotate(cur);
                    }else{    //右左情况   先对cur.r进行右旋   再对cur进行左旋
                        cur.r=rightRotate(cur.r);
                        cur=leftRotate(cur);
                    }
                }
            }
            return cur;//调整平衡时可能根节点变了  所以要返回新的根节点
        }

        //添加节点
        private AVLNode<K,V> add(AVLNode<K,V> cur,K key,V value){
            if (cur==null)
                return new AVLNode(key,value,1);
            else{
                if (key.compareTo(cur.k)<0){
                    cur.l=add(cur.l,key,value);
                }else {
                    cur.r=add(cur.r,key,value);
                }
                    cur.h= Math.max((cur.l!=null ? cur.l.h:0),(cur.r!=null ? cur.r.h:0))+1;
                return maintain(cur);
            }
        }

        //删除节点
        private AVLNode<K,V> delete(AVLNode<K,V> cur,K key){
            if (cur==null){
                return null;
            }else{
                if (key.compareTo(cur.k)<0){
                    cur.l=delete(cur.l,key);
                }else if (key.compareTo(cur.k)>0){
                    cur.r=delete(cur.r,key);
                }else {  //删除该节点
                    if (cur.l==null && cur.r==null){//如果该节点没有左右孩子，直接删除
                        cur=null;
                    }else if (cur.l==null && cur.r!=null){  //只有右孩子
                        cur=cur.r;
                    }else if (cur.l!=null && cur.r==null){//只有左孩子
                        cur=cur.l;
                    }else {  //左右孩子都有    可以将左子树的最右边界和要删除节点替换   或者将右子树最左边界和待删除节点替换
                        //我这里找出右孩子(右子树)最左边界
                        AVLNode<K, V> right = cur.r;
                        while (right.l!=null){//直到找到右子树的最左边界   直到找到一个节点的左孩子为空就到最左边界
                            right=right.l;
                        }
                        cur.r=delete(cur.r,right.k);//删除最左边界的那个节点
                        //cur的左右子树都被right节点接管
                        right.l=cur.l;
                        right.r=cur.r;
                        cur=right;//最后将cur指向right
                    }
                }
                if (cur!=null){
                    cur.h=Math.max((cur.l!=null ? cur.l.h:0),(cur.r!=null ? cur.r.h : 0))+1;
                }
                return maintain(cur);
            }
        }

        //找到最接近k的节点
        private AVLNode<K,V> findLastIndex(K key){
            AVLNode<K,V> pre = root;
            AVLNode<K,V> cur = root;
            while (cur!=null){
                pre = cur;
                if (key.compareTo(cur.k)==0)
                    break;
                else if (key.compareTo(cur.k)<0)
                    cur=cur.l;
                else
                    cur = cur.r;
            }
            return pre;
        }

        //找到最接近k的节点   并且这个节点不大于  k   要找的节点都是小于等于key的
        private AVLNode<K,V> findLastNoBigIndex(K key){
            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur!=null){
                if (key.compareTo(cur.k)==0){
                    ans=cur;
                    break;
                }else if (key.compareTo(cur.k)<0){
                    cur=cur.l;
                }else{
                    ans=cur;
                    cur=cur.r;
                }
            }
            return ans;
        }

        private AVLNode<K,V> findLastNoSmallIndex(K key){
            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur!=null){
                if (key.compareTo(cur.k)==0){
                    ans=cur;
                    break;
                }else if (key.compareTo(cur.k)<0){
                    ans=cur;
                    cur=cur.l;
                }else{
                    //如果分到这一侧   也就是右子树   说明传进来的key一直大于该子树上的key
                    cur=cur.r;
                }
            }
            return ans;
        }

        public int getSize(){
            return size;
        }

        public boolean containKey(K key){
            if (key==null){
                return false;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            return lastIndex!=null && key.compareTo(lastIndex.k)==0 ? true : false;
        }

        public void put(K key,V value){
            if (key==null)
                return;
            //在插入节点时要先判断该节点key值在二叉树中是否存在  ？ 怎么判断呢？就找到最接近key的节点判断key是否相等
            AVLNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex!=null && key.compareTo(lastIndex.k)==0){
                lastIndex.v=value;
            }else {
                size++;
                add(root,key,value);
            }
        }
        public void delete(K key){
            if (key==null)
                return;
            if (containKey(key)){
                size--;
                delete(root,key);
            }
        }

        public V get(K key){
            if (key==null)
                return null;
            AVLNode<K, V> lastIndex = findLastIndex(key);
            return lastIndex!=null && key.compareTo(lastIndex.k) ==0 ? lastIndex.v : null;
        }

        public K firrstKey(){
            if (root==null)
                return null;
            AVLNode<K,V> cur = root;
            while (cur.l!=null){
                cur=cur.l;
            }
            return cur.k;
        }

        public K lastKey(){
            if (root==null)
                return null;
            AVLNode<K,V> cur = root;
            while (cur.r!=null){
                cur=cur.r;
            }
            return cur.k;
        }

        //找到大于等于key的最小值   用findLastNoSmallIndex
        public K floorKey(K key){
            if (key==null)
                return null;
            AVLNode<K, V> lastNoSmallIndex = findLastNoSmallIndex(key);
            return lastNoSmallIndex!=null ? lastNoSmallIndex.k : null;
        }

        //找到小于等于k的最大值
        public K ceilingKey(K key){
            if (key==null)
                return null;
            AVLNode<K, V> lastNoBigIndex = findLastNoBigIndex(key);
            return lastNoBigIndex != null ? lastNoBigIndex.k : null;
        }
    }
}
