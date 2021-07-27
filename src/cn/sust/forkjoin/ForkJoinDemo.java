package cn.sust.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

//1.写一个任务类 继承 RecursiveTask抽象类
class MyTask extends RecursiveTask<Integer> {
    //拆分差值不超过10，一个线程只计算10以内的加法
    private static final Integer VALUE = 10;
    private int begin;   //拆分开始值
    private int end;  //拆分结束值
    private int result; //结束值

    //创建有参构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分合并过程
    @Override
    protected Integer compute() {
        //判断两个数相加的值是否大于10
        if (end - begin < VALUE) {
            //相加
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            //进一步拆分
            //获取中间值
            int mid = (begin + end) / 2;
            //拆分左边
            MyTask myTask01 = new MyTask(begin, mid);
            //拆分右边
            MyTask myTask02 = new MyTask(mid + 1, end);
            //调用方法拆分
            myTask01.fork();
            myTask02.fork();
            //合并结果
            result = myTask01.join() + myTask02.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0, 100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并之后的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);
        //关闭池对象
        forkJoinPool.shutdown();
    }
}
