package cn.sust.threadSafe_HashSet;

import java.util.*;

/**
 * 演示HashSet线程不安全问题
 */
public class Demo01 {
    public static void main(String[] args) { //ConcurrentModificationException
        Set<String> list = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                //往集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));
                //读取集合中的内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
