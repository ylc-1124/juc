package cn.sust.threadSafe_HashMap;

import java.util.*;

/**
 * 演示HashMap线程不安全问题
 */
public class Demo01 {
    public static void main(String[] args) {   //ConcurrentModificationException
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //往集合中添加内容
                map.put(key,UUID.randomUUID().toString().substring(0, 8));
                //读取集合中的内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
