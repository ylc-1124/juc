package cn.sust.sync;

//1.创建资源类 定义属性和操作
class Ticket {
    //票数
    private int number = 30;

    //卖票方法
    public synchronized void sale() {
            //判断是否有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName()
                    + ":售出了一张票,剩余票数:" + (--number));
        }
    }
}

public class SaleTicket {
    //2.创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        //创建Ticket对象
        Ticket ticket = new Ticket();
        //创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        }, "1号售票员").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        }, "2号售票员").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        }, "3号售票员").start();
    }
}
