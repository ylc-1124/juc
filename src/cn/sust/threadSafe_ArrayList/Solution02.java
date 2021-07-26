package cn.sust.threadSafe_ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 解决集合线程安全 （二）
 * 使用Collections集合工具类中的synchronizedList()方法 使集合变成线程安全的
 */
public class Solution02 {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
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
