package qiaolf.zuoshen.class35;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/5/7 9:10
 **/
public class code01_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>,V>{
        public K k;
        public V v;
        public int height;
        public AVLNode<K,V> l;
        public AVLNode<K,V> r;

        public AVLNode(K k, V v, int height) {
            this.k = k;
            this.v = v;
            this.height = 1;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>,V>{
        private AVLNode<K,V> root;
        private int size;

        public AVLTreeMap() {
            this.root = null;
            this.size = 0;
        }

        public AVLNode<K,V> rightRotate(AVLNode<K,V> cur){
            AVLNode<K,V> leftNode = cur.l;
            cur.l=leftNode.r;
            leftNode.r=cur;
            cur.height=Math.max((cur.l!=null ? cur.l.height:0),(cur.r!=null ? cur.r.height:0))+1;
            leftNode.height=Math.max((leftNode.l!=null ? leftNode.l.height:0),(leftNode.r!=null ? leftNode.r.height:0))+1;
            return leftNode;
        }

        public AVLNode<K,V> leftRotate(AVLNode<K,V> cur){
            AVLNode<K,V> rightNode = cur.r;
            cur.r=rightNode.l;
            rightNode.l=cur;
            cur.height=Math.max((cur.l!=null ? cur.l.height:0),(cur.r!=null ? cur.r.height:0))+1;
            rightNode.height=Math.max((rightNode.l!=null ? rightNode.l.height:0),(rightNode.r!=null ? rightNode.r.height:0))+1;
            return rightNode;
        }

        //调平衡
        public AVLNode<K,V> maintain(AVLNode<K,V> cur){
            if (cur==null)
                return null;
            //求出左子树高度，右子树高度条平衡
            int leftHeight = cur.l!=null ? cur.l.height : 0;
            int rightHeight = cur.r!=null ? cur.r.height : 0;
            //左右高度差超过1
            if (Math.abs(leftHeight-rightHeight)>1){
                if (leftHeight>rightHeight){
                    int leftleftHeight = cur.l.l!=null ? cur.l.l.height:0;
                    int leftRightHeight = cur.l.r!=null ? cur.l.r.height:0;
                    if (leftleftHeight>=leftRightHeight){
                        cur=rightRotate(cur);
                    }else{
                       cur.l=leftRotate(cur.l);
                       cur=rightRotate(cur);
                    }
                }else{
                    int rightrightHeight = cur.r.r!=null ? cur.r.r.height : 0;
                    int rightLeftHeight = cur.r.l != null ? cur.r.l.height : 0;
                    if (rightrightHeight>=rightLeftHeight){
                        cur=leftRotate(cur);
                    }else{
                        cur.r=rightRotate(cur.r);
                        cur=leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        public AVLNode<K,V> add(AVLNode<K,V> cur,K key,V value){
            if (cur==null)
                return new AVLNode(key,value,1);
            else {
                //要插入节点要插入cur节点左子树
                if (key.compareTo(cur.k)<0){
                    cur.l = add(cur.l, key, value);
                }else {//插入右子树
                    cur.r = add(cur.r,key,value);
                }
                cur.height=Math.max((cur.l!=null ? cur.l.height:0),(cur.r!=null ? cur.r.height:0))+1;
                return maintain(cur);
            }
        }


        public AVLNode<K,V> delete(AVLNode<K,V> cur,K key){
            if (cur==null){
                return null;
            }else{
                //左子树
                if (key.compareTo(cur.k)<0){
                    cur.l=delete(cur.l,key);
                }else if (key.compareTo(cur.k)>0){
                    cur.r=delete(cur.r,key);
                }else {
                    //删除当前节点
                    //如果左右子树都为空
                    if (cur.l==null && cur.r==null){
                        cur=null;
                    }else if (cur.l==null && cur.r!=null){
                        cur=cur.r;
                    }else if (cur.r==null && cur.l!=null){
                        cur=cur.l;
                    }else{//左右子树都不为空，可以让右子树的最左边界来代替cur,或者让左子树的最右边界来代替cur=====我们这里选择右子树最左边界
                        AVLNode<K, V> desc= cur.r;
                        while (desc.l!=null){//当desc.l==null时就是最左边界
                            desc=desc.l;
                        }

                        cur.r=delete(cur.r, desc.k);
                        desc.l=cur.l;
                        desc.r=cur.r;
                        cur=desc;
                    }
                }
                if (cur!=null){
                    cur.height=Math.max((cur.l!=null ? cur.l.height:0),(cur.r!=null ? cur.r.height:0))+1;
                }
                return maintain(cur);
            }
        }

        //找最接近的
        public AVLNode<K,V> findLastIndex(K k){
            //从根节点开始找
            AVLNode<K,V> ans = root;
            AVLNode<K,V> cur = root;
            while (cur!=null){
                ans=cur;
                if (k.compareTo(cur.k)==0){
                    break;
                }else if (k.compareTo(cur.k)<0){
                    cur=cur.l;
                }else {
                    cur=cur.r;
                }
            }
            return ans;
        }

        public AVLNode<K,V> findLastNoSmallIndex(K k){//找最接近k的，   只要K<=cur.r的   你找出的是大于于等于k的所以是nosmall
            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur!=null){
                if (k.compareTo(cur.k)==0){
                    ans=cur;
                    break;
                }else if (k.compareTo(cur.k)<0){
                    ans=cur;
                    cur=cur.l;
                }else {
                    cur=cur.r;
                }
            }
            return cur;
        }

        //不大于   你找出来的key  <=  你传进来的k
        public AVLNode<K,V> findLastNoBigIndex(K k){ //找最接近的   只要k>=cur.k的   你找出的是小于等于k的所以是noBig
            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur!=null){
                if (k.compareTo(cur.k)==0){
                    ans=cur;
                    break;
                }else if (k.compareTo(cur.k)<0){
                    cur=cur.l;
                }else {
                    ans=cur;
                    cur=cur.r;
                }
            }
            return ans;
        }

        public int getSize(){
            return size;
        }

        public boolean containKey(K key){
            if (key==null)
                return false;
            AVLNode<K, V> lastIndex = findLastIndex(key);
            return lastIndex!=null && key.compareTo(lastIndex.k)==0 ? true : false;
        }



        public void put(K key,V value){
            if (key==null){
                return;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex!=null && key.compareTo(lastIndex.k)==0)
                lastIndex.v=value;
            else {
                size++;
                root=add(root,key,value);
            }
        }

        public void remove(K k){
            if (k==null){
                return;
            }
            if (containKey(k)){
                size--;
                root=delete(root,k);
            }
        }

        public V get(K key){
            if (key==null){
                return null;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex!=null && key.compareTo(lastIndex.k)==0){
                return lastIndex.v;
            }
            return null;
        }

        public K firstKey(){
            if (root==null){
                return null;
            }
            AVLNode<K,V> cur = root;
            while (cur.l!=null){
                cur=cur.l;
            }
            return cur.k;
        }

        public K lastKey(){
            if (root==null){
                return null;
            }
            AVLNode<K,V> cur = root;
            while (cur.r!=null){
                cur=cur.r;
            }
            return cur.k;
        }

        //地板
        public K floorKey(K key){
            if (key==null){
                return null;
            }
            AVLNode<K, V> lastNoBigIndex = findLastNoBigIndex(key);
            return lastNoBigIndex != null ? lastNoBigIndex.k : null;
        }
        public K ceilingKey(K key){
            if (key==null){
                return null;
            }
            AVLNode<K, V> lastNoSmallIndex = findLastNoSmallIndex(key);
            return lastNoSmallIndex != null ? lastNoSmallIndex.k : null;
        }
    }
}
