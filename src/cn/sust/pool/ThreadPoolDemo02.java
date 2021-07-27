package cn.sust.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 */
public class ThreadPoolDemo02 {
    public static void main(String[] args) {
        ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(
                2, //常驻核心线程数量
                5, //最大开启线程数量
                5L, //非核心线程最大空闲时间，超过就会关闭
                TimeUnit.SECONDS, //单位
                new ArrayBlockingQueue<>(3),  //阻塞队列的类型和容量
                Executors.defaultThreadFactory(), //默认线程工厂
                new ThreadPoolExecutor.AbortPolicy()); //默认拒绝策略

        for (int i = 1; i <= 7; i++) {
            myThreadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行了");
            });
        }
    }
}
