package qiaolf.zuoshen.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/4/5 20:14
 **/
public class code02_island1 {
    public int numIslands(char[][] grid) {
        int island=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    island++;
                    //感染的方式：只要该位置的值为'1‘,置为0，如果该位置的上下左右有为’1‘的全部置为0
                    inject(grid,i,j);
                }
            }
        }
        return island;
    }
    private void inject(char[][] grid, int i, int j) {
        if (i<0 || i==grid.length || j<0 || j==grid[0].length || grid[i][j]!='1'){
            return;
        }
        grid[i][j]=0;
        inject(grid,i-1,j);
        inject(grid,i,j-1);
        inject(grid,i+1,j);
        inject(grid,i,j+1);
    }
    //用并查集的方式
//    public class UnionFind{
//        private int[][] parent;
//        private int[][] size;
//        private int[][] help;
//        private int sets;
//
//        public UnionFind(int M,int N) {
//            parent = new int[N][M];
//            size = new int[N][M];
//            help = new int[N][M];
//            sets =
//        }
//    }


    public int numIslands1(char[][] grid){
        Diot diots[][] = new Diot[grid.length][grid[0].length];
        List<Diot> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    //为了表示每个1与众不同
                    diots[i][j]=new Diot();
                    list.add(diots[i][j]);
                }
            }
        }
        UnoinFind1<Diot> unoinFind1 = new UnoinFind1<>(list);
        //我们在看是是否需要合并时需要向左，上方看
        //第0列
        for (int i = 1; i < grid.length; i++) {
            if (grid[i-1][0]=='1' && grid[i][0]=='1'){
                unoinFind1.unoin(diots[i][0],diots[i-1][0]);
            }
        }
        //第0行
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j-1]=='1' && grid[0][j]=='1'){
                unoinFind1.unoin(diots[0][j],diots[0][j-1]);
            }
        }
        //普通列，普通行
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j <grid[0].length ; j++) {
                if (grid[i-1][j]=='1' && grid[i][j]=='1'){
                    unoinFind1.unoin(diots[i][j],diots[i-1][j]);
                }
                if (grid[i][j-1]=='1' && grid[i][j]=='1'){
                    unoinFind1.unoin(diots[i][j],diots[i][j-1]);
                }
            }
        }
        return unoinFind1.sets();
    }
    public class Diot{

    }
    public class Node<Diot>{
        Diot diot;

        public Node(Diot diot) {
            this.diot = diot;
        }
    }
    //外部传给你的是值，不知道你的值被Node包裹
    public class UnoinFind1<Diot>{
        private HashMap<Diot,Node<Diot>> nodes;
        private HashMap<Node<Diot>,Node<Diot>> parents;
        private HashMap<Node<Diot>,Integer> size;//只有代表节点才有size,在合并unoin时将非代表节点的size移除,一个代表节点代表一个集合，

        //外部传给你的是值，不知道你的值被Node包裹
        public UnoinFind1(List<Diot> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            size = new HashMap<>();
            for (Diot diot : list) {
                Node<Diot> diotNode = new Node<>(diot);
                nodes.put(diot,diotNode);
                parents.put(diotNode,diotNode);
                size.put(diotNode,1);
            }
        }

        //内部，外部感知不到
        private Node<Diot> findFather(Node<Diot> cur){
            Stack<Node<Diot>> stack = new Stack<>();
            while (cur!=parents.get(cur)){
                stack.push(cur);
                cur=parents.get(cur);
            }
            while (!stack.isEmpty()){
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        //外部可以感知到：
        public boolean isSampleUnoin(Diot a,Diot b){
            return findFather(nodes.get(a))==findFather(nodes.get(b));
        }

        public void unoin(Diot a, Diot b){
            if (!isSampleUnoin(a,b)){
                Node<Diot> afather = findFather(nodes.get(a));
                Node<Diot> bfather = findFather(nodes.get(b));
                Node<Diot> big = size.get(afather) >= size.get(bfather) ? afather : bfather;
                Node<Diot> small = big == afather ? bfather : afather;
                parents.put(small,big);
                size.put(big,size.get(afather)+size.get(bfather));
                size.remove(small);
            }
        }
        public int sets(){
            return size.size();
        }
    }


    //
    public int numIslands2(char[][] grid){
        UnionFind2 unionFind2 = new UnionFind2(grid);
        //我们在看是是否需要合并时需要向左，上方看
        //第0列
        for (int i = 1; i < grid.length; i++) {
            if (grid[i-1][0]=='1' && grid[i][0]=='1'){
                unionFind2.unoin(unionFind2.index(i-1,0),unionFind2.index(i,0) );
            }
        }
        //第0行
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j-1]=='1' && grid[0][j]=='1'){
                unionFind2.unoin(unionFind2.index(0,j-1),unionFind2.index(0,j) );
            }
        }
        //普通列，普通行
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j <grid[0].length ; j++) {
                if (grid[i-1][j]=='1' && grid[i][j]=='1'){
                    unionFind2.unoin(unionFind2.index(i-1,j), unionFind2.index(i,j) );
                }
                if (grid[i][j-1]=='1' && grid[i][j]=='1'){
                    unionFind2.unoin(unionFind2.index(i,j-1), unionFind2.index(i,j) );
                }
            }
        }
        return unionFind2.set();
    }
    //将二维数组转为一维数组
    public class UnionFind2{
        private int[] parents;
        private int[] size;
        private int[] help;
        private int col;
        private int set;

        public UnionFind2(char[][] grid) {
            col = grid[0].length;
            int row = grid.length;
            int len = col*row;
            parents = new int[len];
            size = new int[len];
            help = new int[len];
            set = 0;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (grid[r][c]=='1'){
                        int i = index(r, c);
                        parents[i]=i;
                        set++;
                        size[i]=1;
                    }
                }
            }
        }

        private int index(int r,int c){
            return r*col+c;
        }

        public int find(int i){
            int hi=0;
            while (i!=parents[i]){
                help[hi++]=i;
                i=parents[i];
            }
            for (hi--; hi >=0 ; hi--) {
                parents[help[hi]]=i;
            }
            return i;
        }

        public boolean isSampleUnoin(int i,int j){
            return find(i)==find(j);
        }
        public void unoin(int i,int j){
            if (!isSampleUnoin(i,j)){
                if (size[parents[i]]>=size[parents[j]]){
                    parents[parents[j]]=parents[i];
                    set--;
                }else{
                    parents[parents[i]]=parents[j];
                    set--;
                }
            }
        }
        public int set(){
            return set;
        }
    }

    public static void main(String[] args) {

    }
}
