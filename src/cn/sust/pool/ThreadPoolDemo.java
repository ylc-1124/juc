package cn.sust.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示三种创建线程池的方式
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //第一种：一池n线程池
      //  ExecutorService threadPool1 = Executors.newFixedThreadPool(5); //5个窗口

        //第二种：一池一线程池
     //   ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); //1个窗口

        //第三种：一池可扩容线程池
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            //10个顾客对应着10个线程请求
            for (int i = 0; i < 10; i++) {
                //执行
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "正在办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭线程池
            threadPool3.shutdown();
        }

    }
}
