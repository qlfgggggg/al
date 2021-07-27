package qiaolf.zuoshen.class35;

/**
 * @Description: 红黑树
 * Author:qiaolf
 * @Date:2021/5/8 10:51
 **/
public class code03_RBTree {


    public static class RBTreeMap<K extends Comparable<K>,V>{
        private RBNode<K,V> root;
        public final boolean RED = false;
        public final boolean BLACK = true;

        /**
         * 左旋
         * @param cur
         * @return
         */
        private RBNode<K,V> leftRotate(RBNode<K,V> cur){
            RBNode<K, V> rbNode = cur.r;
            cur.r=rbNode.l;
            if (rbNode.l!=null)
                rbNode.l.parent=cur;
//            if (cur.parent!=null){
//                if (cur==cur.parent.l){
//                    cur.parent.l=rbNode;
//                    cur.parent=rbNode;
//                }else{
//                    cur.parent.r=rbNode;
//                    cur.parent=rbNode;
//                }
//            }else{
//               cur.parent=rbNode;
//            }
            if (cur.parent!=null){
                rbNode.parent=cur.parent;
                if (cur==cur.parent.l){
                    cur.parent.l=rbNode;
                }else{
                    cur.parent.r=rbNode;
                }
            }else{//如果当前节点就是根节点时：需要把旋转上去的节点作为根节点
                this.root=rbNode;
                this.root.parent=null;
            }
            cur.parent=rbNode;
            rbNode.l=cur;
            return rbNode;
        }

        /**
         * 右旋
         * @param cur
         * @return
         */
        private RBNode<K,V> rightRotate(RBNode<K,V> cur){
            RBNode<K, V> rbNode = cur.l;
            cur.l=rbNode.r;
            if (rbNode.r!=null){
                rbNode.r.parent=cur;
            }
            if (cur.parent!=null){
                //rbNode的父节点设置为cur节点的父节点
                rbNode.parent=cur.parent;
                if (cur==cur.parent.l){
                    cur.parent.l=rbNode;
                }else{
                    cur.parent.r=rbNode;
                }
            }else {
                this.root=rbNode;
                this.root.parent=null;
            }
            cur.parent=rbNode;
            rbNode.r=cur;
            return rbNode;
        }

        /**
         * 父节点
         * @param rbNode
         * @return
         */
        public RBNode<K,V> parentOf(RBNode<K,V> rbNode){
            if (rbNode!=null)
                return rbNode.parent;
            return null;
        }


        public boolean isRED(RBNode rbNode){
            if (rbNode!=null)
                return rbNode.color==RED;
            return false;
        }

        public boolean isBLACK(RBNode rbNode){
            if (rbNode!=null)
                return rbNode.color==BLACK;
            return false;
        }

        public void setRED(RBNode rbNode){
            if (rbNode!=null)
                rbNode.color=RED;
        }

        public void setBLACK(RBNode rbNode){
            if (rbNode!=null)
                rbNode.color=BLACK;
        }


        /**
         * 暴露的插入节点
         * @param key
         * @param value
         * @return
         */
        public void insertNode(K key,V value){
            RBNode<K, V> rbNode = new RBNode<>(key, value, RED);
            insertNode(rbNode);
        }

        /**
         * 内部节点插入   找到要插入节点的父节点来插入    如果要插入节点存在就替换，如果不存在就一直找到底
         * @param rbNode   要插入的节点
         * @return
         */
        private void insertNode(RBNode<K,V> rbNode){
            RBNode<K,V> cur = root;
            RBNode<K,V> parent = root;
            if (root!=null){
                while (cur!=null){
                    parent=cur;
                    if (rbNode.k.compareTo(cur.k)<0){  //要插入节点的key大于
                        cur=cur.l;
                    }else if (rbNode.k.compareTo(cur.k)>0){
                        cur=cur.r;
                    }else{  //如果在树中已经存在key了可以直接替换
                        cur.v=rbNode.v;
                        return;
                    }
                }
                //找到父节点parent   判断是插在父节点的右侧还是左侧
                if (rbNode.k.compareTo(parent.k)<0){   //小于父节点  插入左侧
                    rbNode.parent=parent;
                    parent.l=rbNode;
                }else {//在右侧插入
                    rbNode.parent=parent;
                    parent.r=rbNode;
                }
            }else{  //如果根节点不存在就  将插入节点作为根节点
                this.root = new RBNode<>(rbNode.k, rbNode.v, RED);
                this.root.parent=null;
                this.root.color=BLACK;
            }
            //插入节点后修复平衡
            insertFixUp(rbNode);
        }

        /*插入节点的可能造成平衡破坏，需要调整平衡
        1.如果红黑树为空可以直接将插入节点做为红黑树的根节点，注意要变色
        2.如果插入节点的key在红黑树中存在，就直接替换节点中的值内容
        3.如果插入节点的父节点是黑色的，没有破坏红黑树的平衡性不用调整平衡
        4.如果插入节点的父节点是红色的    （可以知道一定有爷节点且爷爷节点为黑色）
            4.1 如果插入节点的叔叔节点不为空且为红色   变换颜色： 黑(爷爷)红(父亲，叔叔)红(插入节点)    =====>    红(爷爷)黑(父亲,叔叔)红(插入节点)
                并将当前指针定位到爷爷节点
            4.2 如果插入节点的叔叔节点为空或者为黑色
                4.2.1 如果插入节点位于父节点的左侧，且父节点位于爷爷节点的左侧  LL红 问题
                平衡解决方案：变色，旋转    父亲节点变为黑色，爷爷节点变为红色  对爷爷节点进行右旋
                4.2.2 如果插入节点位于父节点的右侧，且父节点位于爷爷节点的左侧  LR红 问题
                平衡解决方案：旋转 ，变色，旋转    父节点左旋，将问题转化为LL红，插入节点来到原来父节点位置，将原插入节点变为黑色，原爷爷节点变为红色，对爷爷节点进行右旋
                (老师的答案是将旋转后的插入节点作为当前节点)
            4.3 如果插入节点的叔叔节点为空或者为黑色
                4.3.1 如果插入节点位于父节点的右侧，且父节点位于爷爷节点的右侧  RR红 问题
                平衡解决方案：变色，旋转    父亲节点变为黑色，爷爷节点变为红色  对爷爷节点进行左旋
                4.3.2 如果插入节点位于父节点的右侧，且父节点位于爷爷节点的左侧  RL红 问题
                平衡解决方案：旋转 ，变色，旋转    父节点右旋，将问题转化为RR红，插入节点来到原来父节点位置，将原插入节点变为黑色，原爷爷节点变为红色，对爷爷节点进行左旋
                (老师的答案是将旋转后的插入节点作为当前节点)
        *
        *
        *
        *
        * */

        /**
         * 对节点平衡     这个节点是我们插入的节点
         * @param rbNode
         */
        private void insertFixUp(RBNode<K,V> rbNode){
            //1情况我们不关心,在插入时我们已经做过，2情况我们也不关心，不涉及平衡，3情况我们也不关心，不涉及平衡
            //我们只关心4情况
            //父节点
            RBNode<K, V> parent = parentOf(rbNode);
            //爷爷节点
            RBNode<K, V> pparent = parentOf(parent);
            if (parent!=null && isRED(parent)){   //父亲节点为红色  一定有爷爷节点

                //如果父亲节点在是爷爷节点的左孩子   要找叔叔节点必须要先找到叔叔是爷爷节点的左孩子还是右孩子
                if (parent==pparent.l){
                    //叔叔节点  是爷爷节点的右孩子
                    RBNode<K, V> uncle = parent.r;
                    //如果叔叔节点为红色
                    if (uncle!=null && isRED(uncle)){
                        uncle.color=BLACK;
                        parent.color=BLACK;
                        pparent.color=RED;
                        //递归调整颜色
                        insertFixUp(pparent);
                        //一定要返回
                        return;//退出
                    }else{//叔叔节点为空或者为黑色
                        //如果当前节点在父节点的左侧
                        if (rbNode==parent.l){  //LL红问题
                            //解决方式  变色  旋转
                            parent.color=BLACK;
                            pparent.color=RED;
                            //对爷爷节点进行右旋
                            rightRotate(pparent);
                            return;//退出
                        }else{  //LR红文体
                            //解决方式
                            leftRotate(parent);

                            /*//左旋之后  一切关系都变了   当前节点占据了原来父节点的位置
                            parent=pparent.l;
                            //左旋之后  问题又转化为LL红问题
                            //解决方式  变色  旋转
                            parent.color=BLACK;
                            pparent.color=RED;
                            //对爷爷节点进行右旋
                            rightRotate(pparent);*/
                            //老师教的方法  由于左旋后的情况变为LL红的问题   且原先的父节点变为原先插入节点的左孩子   我们只需要继续递归调用就好
                            insertFixUp(parent);
                            return;//退出   每一个都要有退出  因为递归函数别人到用到你来怎么来结束呢？
                        }
                    }
                }else {  //如果父亲节点在是爷爷节点的右孩子
                    //叔叔节点是爷爷节点的左孩子
                    RBNode<K, V> uncle = pparent.l;
                    if (uncle!=null && isRED(uncle)){
                        uncle.color=BLACK;
                        parent.color=BLACK;
                        pparent.color=RED;
                        //递归调整颜色
                        insertFixUp(pparent);
                        //一定要返回
                        return;//退出
                    }else {//叔叔节点为空或者为黑
                        //如果当前节点在父节点的左侧
                        if (rbNode==parent.l){  //RL红问题

                            //解决方式
                            rightRotate(parent);
                           /* //右旋之后一切都变了   当前节点占据原来父节点的位置  且问题转变为 RR红问题
                            parent=pparent.r;
                            //解决方式  变色  旋转
                            parent.color=BLACK;
                            pparent.color=RED;
                            leftRotate(pparent);*/
                            //老师教的方式
                            insertFixUp(parent);
                            return;
                        }else{  //RR红问题
                            //解决方式  变色  旋转
                            parent.color=BLACK;
                            pparent.color=RED;
                            //对爷爷节点进行左旋
                            leftRotate(pparent);
                            return;
                        }
                    }
                }
            }
        }
    }

    //红黑树节点
    public static class RBNode<K extends Comparable<K>,V>{
        public K k;
        public V v;
        public boolean color;
        public RBNode<K,V> parent;
        public RBNode<K,V> l;
        public RBNode<K,V> r;

        public RBNode(K k, V v, boolean color) {
            this.k = k;
            this.v = v;
            this.color = color;
        }
    }
}
