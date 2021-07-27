package cn.sust.read_write;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁降级
 */
public class ReadWriteLockDemo02 {
    public static void main(String[] args) {
        //可重入读写锁读写对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        //获取读写锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();

        //锁降级   写锁（独占锁）可以降级为读锁（共享锁）
        //1.获取写锁
        writeLock.lock();
        System.out.println("写入数据...");
        //2.获取读锁（锁降级）
        readLock.lock();
        System.out.println("读取数据...");
        //3.释放写锁
        writeLock.unlock();
        //4.释放读锁
        readLock.unlock();
        System.out.println("===========");
        //读锁不能升级成写锁  读的时候不能写
        readLock.lock();
        System.out.println("reading...");
        readLock.unlock(); //只有释放了读锁才能写
        writeLock.lock();
        System.out.println("writing...");
    }
}
