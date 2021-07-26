package cn.sust.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Lock接口的实现类 可重入锁ReentrantLock
 */
//1.创建资源类 定义属性和操作
class LTicket {
    //票数
    private int number = 30;

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(); //构造方法可设置是否为公平锁

    //卖票方法
    public void sale() {
        lock.lock();
        //判断是否有票
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()
                        + ":售出了一张票,剩余票数:" + (--number));
            }
        } finally {
            lock.unlock();
        }

    }
}

public class LSaleTicket {
    public static void main(String[] args) {
        //创建LTicket对象
        LTicket ticket = new LTicket();
        //创建三个线程，使用 lamdas表达式
        new Thread(()->{
            while (true) {
                ticket.sale();
            }
        },"1号售票员").start();
        new Thread(()->{
            while (true) {
                ticket.sale();
            }
        },"2号售票员").start();
        new Thread(()->{
            while (true) {
                ticket.sale();
            }
        },"3号售票员").start();

    }
}
