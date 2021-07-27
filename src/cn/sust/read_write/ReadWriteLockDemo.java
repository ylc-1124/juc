package cn.sust.read_write;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//资源类
class MyCache {
    //创建Map集合
    private volatile Map<String, Object> map = new HashMap<>();
    //创建读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    //放数据
    public void put(String key, Object value) {
        //添加写锁
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key);
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            //放入数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            rwLock.writeLock().unlock();
        }


    }

    //取数据
    public Object get(String key) {
        //添加读锁
        rwLock.readLock().lock();
        Object result = null;

        try {
            System.out.println(Thread.currentThread().getName() + "正在读操作" + key);
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            //读
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放读锁
            rwLock.readLock().unlock();
        }

        return result;
    }
}

/**
 * 读写锁作用演示
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建5个写线程
        for (int i = 1; i <= 5; i++) {
            int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        //创建5个读线程
        for (int i = 1; i <= 5; i++) {
            int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}
