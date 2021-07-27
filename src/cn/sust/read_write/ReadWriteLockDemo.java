package cn.sust.read_write;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 总结：1.写锁是独占锁 由下面毫秒值可以看出 每次只有一个线程在执行写操作
 *      2.读锁是共享锁，由下面毫秒值可以看出 可以多个线程一起进行读操作
 */
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
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key
                    +"  当前毫秒值=>"+System.currentTimeMillis());
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            //放入数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key
                    +"  当前毫秒值=>"+System.currentTimeMillis());
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
            System.out.println(Thread.currentThread().getName() + "正在读操作" + key
                    +"  当前毫秒值=>"+System.currentTimeMillis());
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);
            //读
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了" + key
                    +"  当前毫秒值=>"+System.currentTimeMillis());
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
