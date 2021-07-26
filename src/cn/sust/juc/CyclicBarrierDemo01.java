package cn.sust.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示 juc辅助类-CyclicBarrier循环屏障
 * 案例：收集 7颗龙珠召唤神龙
 * 思考：1.一颗龙珠对应着一个线程，只有集齐了 7颗龙珠才可以触发召唤神龙的事件-->（也对应了一个线程）
 */
public class CyclicBarrierDemo01 {
    //创建固定值，需要收集的龙珠数量
    private static final int NUMBER = 7;

    public static void main(String[] args) {

        //创建循环屏障，当 cyclicBarrier.await次数满了NUMBER就会执行循环屏障中的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐了7龙珠开始召唤神龙...");
        });

        //集齐七龙珠过程
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "星龙珠收集到了");
                try {
                    cyclicBarrier.await();  //阻塞等待的对象是循环屏障
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
