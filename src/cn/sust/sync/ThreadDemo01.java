package cn.sust.sync;
/**
 * synchronized关键字 进行线程通信
 */
//1.创建资源类，定义属性和操作方法
class Share {
    private int number = 0;

    //+1
    public synchronized void incr() throws InterruptedException {
        //判断  使用while替换原来的if解决虚假唤醒问题
        while (number != 0) { //不等于0 线程等待
                this.wait(); //wait特点：哪里等待哪里唤醒

        }
        //等于0时
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        this.notifyAll();  //唤醒阻塞的线程
    }

    //-1
    public synchronized void decr() throws InterruptedException {
        while (number != 1) {
                this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        this.notifyAll();

    }
}
public class ThreadDemo01 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr(); //+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr(); //-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr(); //+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程3").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr(); //-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程4").start();
    }

}
