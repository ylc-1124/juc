package cn.sust.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//异步调用和同步调用
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //同步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":completableFuture");
        });
        completableFuture.get();

        //消息队列mq
        //异步调用
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":completableFuture1");
            return 1024;
        });
        completableFuture1.whenComplete((t, u) -> {
            System.out.println("t:" + t);  //返回值
            System.out.println("u:" + u);  //u是异常信息

        }).get();
    }

}
