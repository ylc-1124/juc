package cn.sust.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//自定义一个类实现Callable接口
class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable");
        return 2048;
    }
}
public class CallableDemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建两个未来任务对象  FutureTask是 Runnable的一个实现类
        FutureTask<Integer> futureTask1=new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" come in callable");
            return 1024;
        });
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyCallable());

        //创建两个线程
        new Thread(futureTask1,"线程1").start();
        new Thread(futureTask2,"线程2").start();

        System.out.println("线程1返回的值=>" + futureTask1.get());
        System.out.println("线程2返回的值=>" + futureTask2.get());

    }
}
