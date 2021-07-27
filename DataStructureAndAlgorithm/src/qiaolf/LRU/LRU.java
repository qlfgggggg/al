package qiaolf.LRU;

import java.util.HashMap;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/3/12 13:11
 **/
public class LRU {
    HashMap<Integer,Node> hashMap;
    DoubleNodeList cache;
    private int maxSize;

    public LRU(int maxSize) {
        hashMap = new HashMap<>();
        cache = new DoubleNodeList();
        this.maxSize = maxSize;
    }

    int get(int key){
        if (!hashMap.containsKey(key)){
            return -1;
        }else{
            return hashMap.get(key).val;
        }
    }
}
