package cn.sust.threadSafe_ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 演示线程不安全的情况
 * ArrayList<>就是线程不安全的
 */
public class Demo01 {
    public static void main(String[] args) {  //ConcurrentModificationException
        List<String> list = new ArrayList<>();
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
