package cn.sust.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int flag = 1;  //1 AA  2 BB  3 CC
    //创建可重入锁
    private Lock lock = new ReentrantLock();
    //创建三个condition 对应三个线程的状态
    private Condition c1= lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 打印信息5次
     * @param loop 轮数
     */
    public void print5(int loop) throws InterruptedException {
        lock.lock(); //上锁
        try {
            while (flag != 1) { //判断
                c1.await(); //等待
            }
            for (int i = 1; i <=5; i++) { //干活
                System.out.println(Thread.currentThread().getName()+i+":轮数=>"+loop);
            }
            flag = 2; //修改标记
            c2.signal(); //唤醒c2
        }finally {
            lock.unlock(); //解锁
        }
    }
    /**
     * 打印信息10次
     * @param loop 轮数
     */
    public void print10(int loop) throws InterruptedException {
        lock.lock(); //上锁
        try {
            while (flag != 2) { //判断
                c2.await(); //等待
            }
            for (int i = 1; i <=10; i++) { //干活
                System.out.println(Thread.currentThread().getName()+i+":轮数=>"+loop);
            }
            flag = 3; //修改标记
            c3.signal(); //唤醒c3
        }finally {
            lock.unlock(); //解锁
        }
    }
    /**
     * 打印信息15次
     * @param loop 轮数
     */
    public void print15(int loop) throws InterruptedException {
        lock.lock(); //上锁
        try {
            while (flag != 3) { //判断
                c3.await(); //等待
            }
            for (int i = 1; i <=15; i++) { //干活
                System.out.println(Thread.currentThread().getName()+i+":轮数=>"+loop);
            }
            flag = 1; //修改标记
            c1.signal(); //唤醒c1
        }finally {
            lock.unlock(); //解锁
        }
    }


}
public class ThreadDemo03 {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}
