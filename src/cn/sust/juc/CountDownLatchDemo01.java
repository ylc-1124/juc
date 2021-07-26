package cn.sust.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 演示CountDownLatch辅助类
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
