import java.util.LinkedList;
import java.util.List;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/7/14 10:33
 **/
public class HelloGC {
    public static void main(String[] args) {
        System.out.println("HelloGC!");
        List<byte[]> list = new LinkedList<>();
        for(;;) {
            byte[] b = new byte[1024];
            list.add(b);
        }
    }
}
