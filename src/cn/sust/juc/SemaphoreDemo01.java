package cn.sust.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 演示 juc辅助类-Semaphore 信号量
 * 案例：6辆轿车，3个车位，停一段时间离开
 * 问题抽象：一个车对应一个线程，一个车位对应一个许可
 */
public class SemaphoreDemo01 {
    public static void main(String[] args) {
        //创建信号量对象，设置许可数量
        Semaphore semaphore = new Semaphore(3);

        //模拟来了6辆车
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //抢占车位
                try {
                    semaphore.acquire();//抢占资源
                    System.out.println("=>"+Thread.currentThread().getName() + "号车抢到了车位");
                    //设置停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(8));
                    System.out.println(Thread.currentThread().getName() + "号车离开了=>");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(); //释放资源
                }
            },String.valueOf(i)).start();
        }
    }
}
