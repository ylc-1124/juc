package cn.sust.threadSafe_ArrayList;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 解决集合线程安全 （三）
 * 写时复制技术 优点：并发读 独立写
 * 原理：1.对集合进行写操作时先复制这个集合
 *      2.在复制的集合中进行写操作，写完再替换原来的集合，不影响其他线程读
 */
public class Solution03 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
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
