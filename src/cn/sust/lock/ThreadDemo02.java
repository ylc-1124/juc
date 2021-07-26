package cn.sust.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share {
    private int number = 0;
    //设置可重入锁
    private  Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //+1
    public void incr() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            //通知
            condition.signalAll();

        }finally {
            lock.unlock(); //解锁
        }

    }

    //-1
    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo02 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();//+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();//-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();//+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();//-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
