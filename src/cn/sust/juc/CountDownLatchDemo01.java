package cn.sust.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 演示 juc辅助类-CountDownLatch 倒计数器
 * 案例：当 6的同学都离开教室，班长才可以关门
 * 思考：1.每个同学都是一个个体，想啥时候走就啥时候走，可以联想到是一个同学对应了一个线程
 *      2.班长对应着主线程，须在关门前确保6个同学都走了才可以关门
 */
public class CountDownLatchDemo01 {
    public static void main(String[] args) throws InterruptedException {
        //首先创建CountDownLatch对象并设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //6个同学陆续离开教室
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开了教师");
                //倒计时器-1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //阻塞等待倒计时器变成 0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长关门走人了");
    }
}
