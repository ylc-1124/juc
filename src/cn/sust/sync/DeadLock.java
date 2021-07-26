package cn.sust.sync;

/**
 * 演示死锁  可以用jps -l 指令查看进程号  再用jstack xxxx 查看是否是死锁
 */
public class DeadLock {
    //创建两个对象
    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "持有a锁，试图获取b锁");
                try {
                    Thread.sleep(100); //这 100ms是为了让B线程创建出来
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取到了b锁");
                }
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "持有b锁，试图获取a锁");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取到了a锁");
                }
            }
        }, "B").start();
    }
}
