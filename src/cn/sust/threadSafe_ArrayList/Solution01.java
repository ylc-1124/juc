package cn.sust.threadSafe_ArrayList;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * 解决集合线程安全 （一）
 *  使用Vector集合
 *  看源码可知 Vector的方法前面有synchronized关键字 是线程安全的
 */
public class Solution01 {
    public static void main(String[] args) {
        List<String> list = new Vector<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                //往集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));
                //读取集合中的内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
