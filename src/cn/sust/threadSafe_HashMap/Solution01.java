package cn.sust.threadSafe_HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap线程不安全解决方法：  ConcurrentHashMap<>
 */
public class Solution01 {
    public static void main(String[] args) {   //ConcurrentModificationException
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //往集合中添加内容
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                //读取集合中的内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
